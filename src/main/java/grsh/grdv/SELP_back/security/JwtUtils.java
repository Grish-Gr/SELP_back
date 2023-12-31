package grsh.grdv.SELP_back.security;

import grsh.grdv.SELP_back.entities.PaidSubscriptionCode;
import grsh.grdv.SELP_back.entities.Role;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    @Autowired
    private JwtProvider jwtProvider;

    public JwtAuthentication getJwtAuthentication(String accessToken){
        Claims claims = jwtProvider.getClaimsAccessToken(accessToken);
        JwtAuthentication jwtAuthentication = new JwtAuthentication();
        jwtAuthentication.setAuthenticated(true);
        jwtAuthentication.setUserID(Long.parseLong(claims.getSubject()));
        jwtAuthentication.setUsername(claims.get("username", String.class));
        jwtAuthentication.setPaidSubscription(PaidSubscriptionCode
            .valueOf(claims.get("paidSubscription", String.class))
        );
        jwtAuthentication.setRole(Role.valueOf(claims.get("role", String.class)));
        return jwtAuthentication;
    };

    public String generateNewRefreshToken(String refreshToken){
        Claims claims = jwtProvider.getClaimsRefreshToken(refreshToken);
        return jwtProvider.generateRefreshToken(
            Long.parseLong(claims.getSubject()),
            Role.valueOf(claims.get("role", String.class)),
            claims.get("username", String.class),
            PaidSubscriptionCode.valueOf(claims.get("paidSubscription", String.class))
        );
    }

    public String generateAccessTokenByRefreshToken(String refreshToken){
        Claims claims = jwtProvider.getClaimsRefreshToken(refreshToken);
        return jwtProvider.generateAccessToken(
            Long.parseLong(claims.getSubject()),
            Role.valueOf(claims.get("role", String.class)),
            claims.get("username", String.class),
            PaidSubscriptionCode.valueOf(claims.get("paidSubscription", String.class))
        );
    }

    public Long getExpireTimeAccessToken(String accessToken){
        Claims claims = jwtProvider.getClaimsAccessToken(accessToken);
        return claims.getExpiration().getTime();
    }

    public String getUserIDAuthService(String refreshToken){
        Claims claims = jwtProvider.getClaimsRefreshToken(refreshToken);
        return claims.get("role", String.class) + claims.getSubject();
    }

    public String getUserRole(String refreshToken){
        Claims claims = jwtProvider.getClaimsRefreshToken(refreshToken);
        return claims.get("role", String.class);
    }

    public String getPaidSubscription(String refreshToken){
        Claims claims = jwtProvider.getClaimsRefreshToken(refreshToken);
        return claims.get("paidSubscription", String.class);
    }
}
