package br.com.calazans.observer.manager;

import br.com.calazans.observer.publisher.CredentialsFileManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class FileManager {

    private static final String OPEN_OPERATION = "open";
    private static final String SAVE_OPERATION = "save";
    private static final String EDITION_OPERATION = "edit";

    public CredentialsFileManager credentialsFileManager;
    private File file;

    public FileManager() {
        this.credentialsFileManager = new CredentialsFileManager(OPEN_OPERATION, SAVE_OPERATION, EDITION_OPERATION);
    }


    public void openFile(String filePath) {
        this.file = new File(filePath);
        try {
            System.out.println("Conteúdo do arquivo: " + Files.readString(this.file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        credentialsFileManager.notify(OPEN_OPERATION, file);
    }

    public void editFile(String fileName, String credential) {
        if (this.file != null) {
            try (FileWriter fw = new FileWriter(fileName);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                System.out.println("Inserindo o parâmetro: " + credential);
                bw.write(credential);
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            credentialsFileManager.notify(EDITION_OPERATION, file);
        }
    }

    public void saveFile() throws Exception {
        if (this.file != null) {
            credentialsFileManager.notify(SAVE_OPERATION, file);
        } else {
            throw new Exception("Necessário abrir um arquivo antes de salvá-lo");
        }
    }
}
