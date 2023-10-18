import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {
    private static int readResult(Process process) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;

            int sum = 0;

            while ((line = br.readLine()) != null) {
                sum += Integer.parseInt(line);
            }

            return sum;
        } catch (Exception e) {
            e.printStackTrace();

            return 0;
        }
    }

    public static void main(String[] args) {
        String[] fileNames = {"informatica.txt", "gerencia.txt", "contabilidad.txt", "comercio.txt", "rrhh.txt"};

        ArrayList<Process> processes = new ArrayList<>();

        for (int i = 0; i < fileNames.length; i++) {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("java", "src/FileProcessor.java", fileNames[i]);

                processes.add(processBuilder.start());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (Process process : processes) {
            try {
                process.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int totalSum = 0;
        
        for (int i = 0; i < processes.size(); i++) {
            totalSum += readResult(processes.get(i));
        }

        System.out.println("Suma total de cantidades: " + totalSum);
    }
}
