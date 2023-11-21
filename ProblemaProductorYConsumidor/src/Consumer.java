import java.util.ArrayList;
import java.util.Random;

public class Consumer implements Runnable {
    private final ArrayList<Integer> products;

    public Consumer(ArrayList<Integer> products) {
        this.products = products;
    }

    public void run() {
        while (true) {
            try {
                this.consume();
                Thread.sleep(new Random().nextInt(250) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consume() throws InterruptedException {
        while (this.products.size() == 0) {
            this.wait();
        }

        this.products.remove(0);
        System.out.println("Consumidor consume");

        this.notify();
    }
}