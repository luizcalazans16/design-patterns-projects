import entity.ScheduleFile;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScheduleFileMainClass {

    public static final String filePath = "C:\\Users\\luiz.calazans\\Desktop\\apontamento-horas\\" +
            "apontamento-de-horas-fourbank\\2020\\May\\apontamentoDeHoras_Maio_2020.csv";

    public static void main(String[] args) {

        String researchParameter = "Estudos";
        String researchParameter2 = "Onboarding";

        Thread t1 = new Thread(new Thread1(filePath, researchParameter));
        Thread t2 = new Thread(new Thread2(filePath, researchParameter2));

        t1.start();
        t2.start();

    }

    static class Thread1 implements Runnable {
        String filePath;
        String researchParameter;

        public Thread1(String filePath, String researchParameter) {
            this.filePath = filePath;
            this.researchParameter = researchParameter;
        }

        @Override
        public void run() {
            System.out.println("Iniciando primeira thread");
            ScheduleFile sf = ScheduleFile.getInstance(filePath);
            sf.analyzeFile(sf.getFile(), researchParameter);
            System.out.println(sf);
        }
    }

    static class Thread2 implements Runnable {

        String filePath;
        String researchParameter;

        public Thread2(String filePath, String researchParameter) {
            this.filePath = filePath;
            this.researchParameter = researchParameter;
        }

        @Override
        public void run() {
            System.out.println("Iniciando segunda thread");
            ScheduleFile sf = ScheduleFile.getInstance(filePath);
            sf.analyzeFile(sf.getFile(), researchParameter);
            System.out.println(sf);
        }
    }
}
