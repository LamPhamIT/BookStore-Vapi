package com.shinn.bookstore.auth;


import com.shinn.bookstore.converter.UserConverter;
import com.shinn.bookstore.dto.UserDTO;
import com.shinn.bookstore.jwt.JwtService;
import com.shinn.bookstore.jwt.SecurityConfig;
import com.shinn.bookstore.model.UserEntity;
import com.shinn.bookstore.repository.UserRepository;
import com.shinn.bookstore.security.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserConverter userConverter, UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(UserDTO userDTO) {
        UserEntity user = userConverter.toUserEntity(userDTO);
        userRepository.save(user);
        String jwt = jwtService.generateToken(CustomUserDetail.mapUserToUserDetail(user));
        return AuthResponse
                .builder()
                .accessToken(jwt)
                .build();
    }

    public AuthResponse authenticate(UserDTO userDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
        );
        UserEntity user = userRepository.findByEmail(userDTO.getEmail());
        String jwt = jwtService.generateToken(CustomUserDetail.mapUserToUserDetail(user));
        return AuthResponse
                .builder()
                .accessToken(jwt)
                .build();
    }
}
