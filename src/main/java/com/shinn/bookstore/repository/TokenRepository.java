package com.shinn.bookstore.repository;

import com.shinn.bookstore.model.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    @Query(value = "SELECT * FROM token WHERE User_ID = :userId", nativeQuery = true)
    List<TokenEntity> findAllTokenByUserId(@Param("userId") Long userId);
    Optional<TokenEntity> findByToken(String token);

    @Query(value= "SELECT * FROM token WHERE token = :token AND revoked=false", nativeQuery = true)
    Optional<TokenEntity> findTokenNotRevoked(@Param("token") String token);
}
