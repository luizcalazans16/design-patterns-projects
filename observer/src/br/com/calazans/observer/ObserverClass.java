package br.com.calazans.observer;

import br.com.calazans.observer.manager.FileManager;
import br.com.calazans.observer.subscription.EmailNotification;
import br.com.calazans.observer.subscription.FileOpeningNotification;

public class ObserverClass {

    private static final String OPEN_OPERATION = "open";
    private static final String SAVE_OPERATION = "save";
    private static final String EDITION_OPERATION = "edit";
    private static final String filePath = "C:\\Users\\luiz.calazans\\Desktop\\credentialsFile.txt";
    private static final String credential = "AKIA2UBWHFEMVBSDRYFK";

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        fileManager.credentialsFileManager.subscribe(OPEN_OPERATION, new FileOpeningNotification(filePath));
        fileManager.credentialsFileManager.subscribe(SAVE_OPERATION,
                new EmailNotification("luiz.calazans@viaflow.com.br"));
        fileManager.credentialsFileManager.subscribe(EDITION_OPERATION,
                new EmailNotification("luiz.calazans@viaflow.com.br"));

        try {
            fileManager.openFile(filePath);
            fileManager.editFile(filePath, credential);
            fileManager.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
