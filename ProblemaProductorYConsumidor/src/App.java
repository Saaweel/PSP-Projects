import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        int maxStock = 10;

        ArrayList<Integer> products = new ArrayList<>();

        new Thread(new Producer(products, maxStock)).start();
        new Thread(new Producer(products, maxStock)).start();
        new Thread(new Consumer(products)).start();

        while (true) {
            Thread.sleep(1000);

            System.out.print("|");
            for (int i = 0; i < maxStock; i++)
                System.out.print("-");
            System.out.println("|");

            System.out.print("|");
            for (int i = 0; i < maxStock; i++)
                if (i < products.size())
                    System.out.print("·");
                else
                    System.out.print(" ");
            System.out.println("|");
            
            System.out.print("|");
            for (int i = 0; i < maxStock; i++)
                System.out.print("-");
            System.out.println("|");

            System.out.println();
        }
    }
}