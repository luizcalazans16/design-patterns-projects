package br.com.calazans.observer.subscription;

import java.io.File;

public class FileOpeningNotification implements CredentialsFileSubscribers {
    private File log;


    public FileOpeningNotification(String fileName) {
        this.log = new File(fileName);
    }

    @Override
    public void update(String operation, File file) {
        System.out.println("Salvando o log: " + log + "\n" +
                "Foi realizada a operação: " + operation.toUpperCase() + " no arquivo: " + file.getName());
    }
}
