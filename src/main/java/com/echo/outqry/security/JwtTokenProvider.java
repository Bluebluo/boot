package com.echo.outqry.security;

import com.echo.outqry.entity.UserInfo;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication){
        UserInfo userInfo = (UserInfo)authentication.getPrincipal();
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + jwtExpirationInMs);
        return Jwts.builder().setSubject(userInfo.getuId()).setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwt(String autoToken){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(autoToken)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String autoToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(autoToken);
            return true;
        } catch (SignatureException ex) {
            LOGGER.error("invalid jwt signature");
        } catch (MalformedJwtException ex) {
            LOGGER.error("invalid jwt token");
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            LOGGER.error("JWT claims string is empty.");
        }
        return false;
    }
}
