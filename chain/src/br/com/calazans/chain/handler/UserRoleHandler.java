package br.com.calazans.chain.handler;

import br.com.calazans.chain.model.Database;
import br.com.calazans.chain.model.UserData;

public class UserRoleHandler extends RequestHandler {

    public static final String DEFAULT_ROLE_DESCRIPTION = "Administrador de redes";

    private Database database;

    public UserRoleHandler(Database database) {
        this.database = database;
    }

    @Override
    public Boolean verifyUserData(String cpf, UserData userData) {
        if (userData.getRole().equals(DEFAULT_ROLE_DESCRIPTION)) {
            System.out.println("Autenticação de Administrador...");
            return true;
        }
        System.out.println("Autenticação de usuário...");
        return validateNextHandler(cpf, userData);
    }
}
