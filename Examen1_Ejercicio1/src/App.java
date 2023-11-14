import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class App {
    public static void searchInDirectory(String path, String pattern) {
        File dir = new File(path);

        if (dir.exists() && dir.isDirectory()) {
            File [] files = dir.listFiles();

            for (File file: files) {
                if (file.isFile()) {
                    try {
                        ProcessBuilder pb = new ProcessBuilder("java", "Hijo", file.getAbsolutePath(), pattern);

                        Process process = pb.start();

                        InputStream is = process.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr);

                        String line;

                        while ((line = br.readLine()) != null) {
                            System.out.println("El archivo " + file.getPath() + " contiene " + pattern + " " + line + " veces");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    searchInDirectory(file.getAbsolutePath(), pattern);
                }
            }
        } else {
            System.out.println("La ruta especificada no es un directorio");
        }
    }

    public static void main(String[] args) throws Exception {
        searchInDirectory(args[0], args[1]);
    }
}
