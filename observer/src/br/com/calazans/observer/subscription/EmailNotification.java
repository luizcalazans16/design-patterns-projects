package br.com.calazans.observer.subscription;

import java.io.File;

public class EmailNotification implements CredentialsFileSubscribers {

    private String subscriberEmail;

    public EmailNotification(String subscriberEmail) {
        this.subscriberEmail = subscriberEmail;
    }

    @Override
    public void update(String operation, File file) {
        System.out.println("Enviando e-mail para: " + subscriberEmail + " referente à operação: " + operation.toUpperCase() +
                " no arquivo: " + file.getName());
    }
}
