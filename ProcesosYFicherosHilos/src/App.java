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

    public static void execute(boolean useThreads) throws Exception {
        String[] fileNames = {"informatica.txt", "gerencia.txt", "contabilidad.txt", "comercio.txt", "rrhh.txt"};

        ArrayList<Process> processes = new ArrayList<>();

        for (int i = 0; i < fileNames.length; i++) {
            ProcessBuilder processBuilder = new ProcessBuilder("java", "src/FileProcessor.java", fileNames[i]);

            Process process = processBuilder.start();

            processes.add(process);

            if (useThreads) {
                Thread thread = new Thread(() -> {
                    try {
                        process.waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

                thread.start();
            } else {
                process.waitFor();
            }
        }

        int totalSum = 0;

        for (int i = 0; i < processes.size(); i++) {
            totalSum += readResult(processes.get(i));
        }

        System.out.println();
        System.out.println("\u001B[33mSuma total de cantidades: \u001B[32m" + totalSum + "\u001B[0m");
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        long time = System.currentTimeMillis();

        execute(false);

        System.out.println("\u001B[33mUsando UN SOLO hilo se tarda: \u001B[31m" + (System.currentTimeMillis() - time) + " ms\u001B[0m");
        System.out.println();

        time = System.currentTimeMillis();

        execute(true);

        System.out.println("\u001B[33mUtilizando VARIOS hilos se tarda: \u001B[31m" + (System.currentTimeMillis() - time) + " ms\u001B[0m");
        System.out.println();
    }
}
