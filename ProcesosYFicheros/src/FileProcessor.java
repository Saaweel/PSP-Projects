import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {
    public static void main(String[] args) {
       int sum = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;

            while ((line = br.readLine()) != null) {
                sum += Integer.parseInt(line);
            }

            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}