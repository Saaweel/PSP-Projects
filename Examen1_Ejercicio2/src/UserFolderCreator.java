import java.nio.file.Files;
import java.nio.file.Paths;

public class UserFolderCreator {
    public static void main(String[] args) throws Exception {
        Files.createDirectory(Paths.get(args[0] + "/" + args[1]));

        Files.createFile(Paths.get(args[0] + "/" + args[1] + "/bienvenida.txt"));
    }
}