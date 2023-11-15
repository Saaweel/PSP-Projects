import java.util.ArrayList;
import java.util.Random;

public class Producer implements Runnable {
    private final ArrayList<Integer> products;
    private final int maxStock;

    public Producer(ArrayList<Integer> products, int maxStock) {
        this.products = products;
        this.maxStock = maxStock;
    }

    public void run() {
        while (true) {
            try {
                this.produce();
                Thread.sleep(new Random().nextInt(350) + 500); // Que produzca un POCO mas rapido
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void produce() throws InterruptedException {
        if (this.products.size() == this.maxStock) {
            this.wait();
        }

        this.products.add(1);
        System.out.println("Productor produce");

        if (this.products.size() == this.maxStock - 1)
            this.notify();
    }
}
