package br.com.calazans.observer.publisher;

import br.com.calazans.observer.subscription.CredentialsFileSubscribers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CredentialsFileManager {
    Map<String, List<CredentialsFileSubscribers>> subscribers = new HashMap<>();

    public CredentialsFileManager(String... operations) {
        for (String operation : operations) {
            this.subscribers.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String operation, CredentialsFileSubscribers credentialsFileSubscriber) {
        List<CredentialsFileSubscribers> users = subscribers.get(operation);
        users.add(credentialsFileSubscriber);
    }

    public void unsubscribe(String operation, CredentialsFileSubscribers credentialsFileSubscriber) {
        List<CredentialsFileSubscribers> users = subscribers.get(operation);
        users.remove(credentialsFileSubscriber);
    }

    public void notify(String operation, File file) {
        List<CredentialsFileSubscribers> subscribersList = subscribers.get(operation);
        for (CredentialsFileSubscribers credentialsFileSubscriber : subscribersList) {
            credentialsFileSubscriber.update(operation, file);
        }
    }
}
