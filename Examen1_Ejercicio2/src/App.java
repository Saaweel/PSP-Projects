import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static Process createUserProcess(String path, String user) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("java", "UserFolderCreator", path, user);

        return pb.start();
    }

    public static void createUsers(String path, String users) {
        File pathFile = new File(path);

        if (pathFile.exists() && pathFile.isDirectory()) {
            File usersFile = new File(users);

            if (usersFile.exists() && usersFile.isFile()) {
                try (BufferedReader br = new BufferedReader(new FileReader(usersFile))) {
                    ArrayList<Process> processes = new ArrayList<>();

                    String line;

                    while ((line = br.readLine()) != null) {
                        processes.add(createUserProcess(path, line));
                    }

                    for (Process p: processes) {
                        p.waitFor();
                    }

                    createUserProcess(path, "admin");

                    System.out.println("Todos los usuarios fueron creados correctamente");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No se ha encontrado el archivo de usuarios");
            }
        } else {
            System.out.println("La ruta especificada no es un directorio");
        }
    }
    public static void main(String[] args) throws Exception {
        createUsers(args[0], args[1]);
    }
}