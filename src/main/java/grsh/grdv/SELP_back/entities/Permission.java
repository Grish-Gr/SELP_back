package grsh.grdv.SELP_back.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Permission {
    USER_PERMISSION,
    WRITE_NEWS,
    ADMIN,
    BANNED_USER,
    DELETE_USER;

    public GrantedAuthority getAuthority() {
        return new SimpleGrantedAuthority(this.name());
    }
}
