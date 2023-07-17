package grsh.grdv.SELP_back.entities;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(
        Permission.USER_PERMISSION
    )),
    ADMIN(Set.of(
        Permission.ADMIN,
        Permission.USER_PERMISSION,
        Permission.WRITE_NEWS
    )),
    DEV(Set.of(
        Permission.ADMIN,
        Permission.USER_PERMISSION,
        Permission.BANNED_USER,
        Permission.WRITE_NEWS,
        Permission.DELETE_USER
    ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return permissions.stream().map(Permission::getAuthority).collect(Collectors.toSet());
    }
}
