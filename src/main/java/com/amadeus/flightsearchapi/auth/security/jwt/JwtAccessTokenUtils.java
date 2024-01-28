package com.amadeus.flightsearchapi.auth.security.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.amadeus.flightsearchapi.services.UserDetailsImpl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtAccessTokenUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAccessTokenUtils.class);
	private String jwtSecret = "FzJsLaEvThOSCnXIcUpmvzzbLrsGtfFzJsLaEvThOSCnXIcUpmvzzbLrsGtf";
	public static final long JWT_EXPIRATION_MS = 14_400_000L; // 4 hours
	public static final long REFRESH_TOKEN_EXPIRATION_MS = 2_592_000_000L; // 1 month

	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder()
				.subject((userPrincipal.getUsername()))
				.issuedAt(new Date())
				.expiration(new Date((new Date()).getTime() + JWT_EXPIRATION_MS))
				.signWith(key())
				.compact();
	}

	public String generateTokenFromUsername(String username) {
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date((new Date()).getTime() + JWT_EXPIRATION_MS))
				.signWith(key())
				.compact();
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(key()).build()
				.parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(key()).build().parse(authToken);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
