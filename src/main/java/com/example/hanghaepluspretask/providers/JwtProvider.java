package com.example.hanghaepluspretask.providers;

import com.example.hanghaepluspretask.util.DateUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;


@Component
public class JwtProvider {

	private static final String SIGNATURE_SECRET_KEY = "YourVerySecureKeyThatIsAtLeast32CharactersLong";
	private SecretKey secretKey = Keys.hmacShaKeyFor(SIGNATURE_SECRET_KEY.getBytes(StandardCharsets.UTF_8));

	// JWT 토큰 생성
	public String createToken(UUID userId, long expirationSeconds) {
		Instant now = DateUtil.now();

		return Jwts.builder()
				.claim("userId", String.valueOf(userId))
				.issuedAt(Date.from(now))
				.expiration(Date.from(DateUtil.after(expirationSeconds, ChronoUnit.SECONDS, now)))
				.signWith(secretKey)
				.compact();
	}

	// JWT 토큰에서 사용자 ID 추출
	public UUID getUserId(String token) {
		Jws<Claims> jws;

		try {
			jws = Jwts.parser()
					.verifyWith(secretKey)
					.build()
					.parseSignedClaims(token);
		} catch (ExpiredJwtException e) { // TODO : 커스텀 예외 처리 필요
			throw new IllegalArgumentException("Token has expired", e);
		} catch (JwtException e) { // TODO : 커스텀 예외 처리 필요
			throw new IllegalArgumentException("Invalid JWT token", e);
		}

		return jws.getPayload().get("userId", UUID.class);
	}
}
