package controle.finaceiro.api.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:updade"),
    ADMIN_CREATE("admin:read"),
    ADMIN_DELETE("admin:read"),

    USER_READ("user:read"),
    USER_UPDATE("user:updade"),
    USER_CREATE("user:read"),
    USER_DELETE("user:read");

    @Getter
    private final String permission;

}
