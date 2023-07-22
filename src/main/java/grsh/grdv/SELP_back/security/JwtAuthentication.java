package grsh.grdv.SELP_back.security;

import grsh.grdv.SELP_back.entities.PaidSubscription;
import grsh.grdv.SELP_back.entities.PaidSubscriptionCode;
import grsh.grdv.SELP_back.entities.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

@Getter
@Setter
public class JwtAuthentication implements Authentication {

    private String username;
    private Long userID;
    private Role role;
    private PaidSubscriptionCode paidSubscription;
    private boolean isAuthenticated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return role;
    }

    @Override
    public Object getPrincipal() {
        return userID;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public boolean implies(Subject subject) {
        return Authentication.super.implies(subject);
    }
}
