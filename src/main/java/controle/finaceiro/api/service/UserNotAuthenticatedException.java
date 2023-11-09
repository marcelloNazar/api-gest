package controle.finaceiro.api.service;

public class UserNotAuthenticatedException extends RuntimeException {
    public UserNotAuthenticatedException() {
        super("Usuário não autenticado.");
    }
}
