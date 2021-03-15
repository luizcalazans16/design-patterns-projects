package br.com.calazans.chain.handler;

import br.com.calazans.chain.model.Database;
import br.com.calazans.chain.model.UserData;

public class UserDataHandler extends RequestHandler {

    private Database database;

    public UserDataHandler(Database database) {
        this.database = database;
    }

    public Boolean verifyUserData(String cpf, UserData userData) {
        if(!database.isCPFPresent(cpf)) {
            System.out.println("CPF não registrado no sistema.");
            return false;
        }
        if(!database.isPasswordValid(cpf, userData.getPassword())) {
            System.out.println("Senha incorreta.");
            return false;
        }
        return validateNextHandler(cpf, userData);
    }
}
