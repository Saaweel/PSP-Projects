public class BarberShop {
    private int availableChairs;

    private final int CHAIRS;

    public BarberShop(int maxChairs) {
        this.CHAIRS = maxChairs;
        this.availableChairs = maxChairs;
    }

    public synchronized void newClient(String nombre) {
        if (this.availableChairs > 0) {
            this.availableChairs--;

            if (this.availableChairs == CHAIRS - 1) {
                System.out.println("El " + nombre + " despierta al barbero");
                notify();
            }
        } else {
            System.out.println("El " + nombre + " se pira, no hay sitio");
        }
    }

    public synchronized void nextClient() {
        while (this.availableChairs < this.CHAIRS) {
            this.availableChairs++;
            System.out.println("Barbero pela");
        }

        System.out.println("Barbero se duerme");

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getCHAIRS() {
        return this.CHAIRS;
    }

    public int getAvailableChairs() {
        return this.availableChairs;
    }
}
