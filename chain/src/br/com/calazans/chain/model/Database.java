package br.com.calazans.chain.model;

import br.com.calazans.chain.handler.RequestHandler;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Database {

    private Map<String, UserData> usersData = new HashMap<>();
    private Map<String, String> userRole = new HashMap<>();
    private RequestHandler requestHandler;


    public void setRequestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public Boolean validateAccess(String cpf, UserData userData) {
        UserData data = usersData.get(cpf);
        if(requestHandler.verifyUserData(cpf, data)){
            System.out.println("Usuário autenticado com sucesso!");
            return true;
        }
        return false;
    }

    public void registerUser(String cpf, UserData userData){
        usersData.put(cpf, userData);
    }

    public void registerUserRole(String cpf, String role) {
        userRole.put(cpf, role);
    }

    public Boolean isCPFPresent(String cpf) {
        return usersData.containsKey(cpf);
    }

    public Boolean isPasswordValid(String cpf, String password){
        return usersData.get(cpf).getPassword().equals(password);
    }
}
