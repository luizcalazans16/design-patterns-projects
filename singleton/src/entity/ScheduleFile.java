package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class ScheduleFile {

    private static ScheduleFile instance = null;
    private File file;
    private String path;

    public File getFile() {
        return file;
    }

    public static ScheduleFile getInstance(String filePath) {

        ScheduleFile result = instance;
        if(result != null) {
            return result;
        }
        synchronized (ScheduleFile.class) {
            if(instance == null) {
                instance = new ScheduleFile(filePath);
                System.out.println("Recurso criado: " + instance);
            }
            return  instance;
        }
    }

    private ScheduleFile(String path) {
        this.path = path;
        this.file = new File(path);
    }

    public synchronized void analyzeFile(File file, String researchParameter) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (linha.toLowerCase().contains(researchParameter.toLowerCase())) {
                    String[] result = linha.split(",");
                    System.out.println("No dia " + result[0] + ", a atividade realizada foi:  - " + result[7]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
