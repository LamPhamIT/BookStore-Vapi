package com.shinn.bookstore.auth;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinn.bookstore.converter.UserConverter;
import com.shinn.bookstore.dto.UserDTO;
import com.shinn.bookstore.jwt.JwtService;
import com.shinn.bookstore.model.TokenEntity;
import com.shinn.bookstore.model.UserEntity;
import com.shinn.bookstore.repository.TokenRepository;
import com.shinn.bookstore.repository.UserRepository;
import com.shinn.bookstore.dto.CustomUserDetail;
import com.shinn.bookstore.token.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AuthService {
    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserConverter userConverter, UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(UserDTO userDTO) {
        UserEntity user = userConverter.toUserEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        String accessToken = jwtService.generateAccessToken(CustomUserDetail.mapUserToUserDetail(user));
        String refreshToken = jwtService.generateRefreshToken(CustomUserDetail.mapUserToUserDetail(user));
        user = userRepository.findByUsername(user.getUsername());
        saveRefreshToken(user, refreshToken);
        return AuthResponse
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthResponse authenticate(UserDTO userDTO) throws BadCredentialsException {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
        );
        UserEntity user = userRepository.findByEmail(userDTO.getEmail());
        String accessToken = jwtService.generateAccessToken(CustomUserDetail.mapUserToUserDetail(user));
        String refreshToken = jwtService.generateRefreshToken(CustomUserDetail.mapUserToUserDetail(user));
        return AuthResponse
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void revokeToken(UserEntity userEntity) {
        List<TokenEntity> tokenEntities = tokenRepository.findAllTokenByUserId(userEntity.getId());
        if (!tokenEntities.isEmpty()) {
            tokenEntities.forEach(tokenEntity -> {
                tokenEntity.setRevoked(true);
                tokenEntity.setExpired(true);
            });
            tokenRepository.saveAll(tokenEntities);
        }
    }

    public void saveRefreshToken(UserEntity user, String jwtToken) {
        TokenEntity tokenEntity = TokenEntity.builder()
                .tokenType(TokenType.BEARER)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .user(user)
                .build();
        tokenRepository.save(tokenEntity);
    }

    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String refreshToken;
        String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is missing");
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            UserEntity user = userRepository.findByUsername(username);
            if (jwtService.isValidToken(refreshToken, CustomUserDetail.mapUserToUserDetail(user)) && tokenRepository.findTokenNotRevoked(refreshToken).isPresent()) {
                String accessToken = jwtService.generateAccessToken(CustomUserDetail.mapUserToUserDetail(user));
                String newRefreshToken = jwtService.generateRefreshToken(CustomUserDetail.mapUserToUserDetail(user));
                revokeToken(user);
                saveRefreshToken(user, newRefreshToken);

                AuthResponse authResponse = AuthResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(newRefreshToken)
                        .build();
                return ResponseEntity.ok(authResponse);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
