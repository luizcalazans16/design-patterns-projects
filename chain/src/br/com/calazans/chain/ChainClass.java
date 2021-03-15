package br.com.calazans.chain;

import br.com.calazans.chain.handler.RequestAmountHandler;
import br.com.calazans.chain.handler.RequestHandler;
import br.com.calazans.chain.handler.UserDataHandler;
import br.com.calazans.chain.handler.UserRoleHandler;
import br.com.calazans.chain.model.Database;
import br.com.calazans.chain.model.UserData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChainClass {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Database database;

    private static void initializeDatabase(){
        database = new Database();

        database.registerUser("01398187070", new UserData("progress1234","Administrador de redes"));
        database.registerUser("25235410092", new UserData("qwer1234", "Gerente comercial"));


        RequestHandler requestHandler = new RequestAmountHandler(2);
        requestHandler.linkHandler(new UserDataHandler(database)).linkHandler(new UserRoleHandler(database));

        database.setRequestHandler(requestHandler);

    }

    public static void main(String[] args) throws IOException {
        initializeDatabase();
        boolean success;

        do {
            System.out.println("Insira o CPF: ");
            String cpf = reader.readLine();

            System.out.println("Insira a senha: ");
            String password = reader.readLine();

            success = database.validateAccess(cpf, new UserData(password));
        } while (!success);
    }

}
