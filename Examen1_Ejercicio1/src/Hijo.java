import java.io.BufferedReader;
import java.io.FileReader;

public class Hijo {
    public static void main(String[] args) throws Exception {        
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;

            int count = 0;

            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if (word.equals(args[1])) {
                        count++;
                    }
                }
            }

            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}