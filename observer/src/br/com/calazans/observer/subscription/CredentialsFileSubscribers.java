package br.com.calazans.observer.subscription;

import java.io.File;

public interface CredentialsFileSubscribers {

    void update(String operation, File file);
}
