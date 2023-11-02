import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        String[][] inputs = { { "1", "2" }, { "1", "2", "3" }, { "1" } };
        String[] outputs = { "3", "32", "Parametros invalidos requeridos: <int> <int>" };

        for (int i = 0; i < inputs.length; i++) {
            String[] command = new String[inputs[i].length + 2];

            command[0] = "java";
            command[1] = "src/Suma.java";
            
            for (int j = 0; j < inputs[i].length; j++) {
                command[j + 2] = inputs[i][j];
            }

            Process process = new ProcessBuilder(command).start();

            process.waitFor();

            try (InputStream is = process.getInputStream();
                 InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader br = new BufferedReader(isr)) {

                String line;

                while ((line = br.readLine()) != null) {
                    System.out.println("--------------------------------------------------------------------------------------------------------");
                    if (line.equals(outputs[i]))
                    System.out.print("| \u001B[32m[Aprobado]\u001B[0m");
                    else
                    System.out.print("| \u001B[31m[Suspenso]\u001B[0m");
                    System.out.println(" " + i + ": " + line + " / " + outputs[i]);
                    System.out.println("--------------------------------------------------------------------------------------------------------");
                }
            }
        }
    }
}