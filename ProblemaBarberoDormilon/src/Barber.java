import java.util.Random;

class Barber implements Runnable {
    private BarberShop barbershop;

    public Barber(BarberShop barbershop) {
        this.barbershop = barbershop;
    }

    @Override
    public void run() {
        while (true) {
            this.barbershop.nextClient();
            try {
                Thread.sleep(new Random().nextInt(200) + 600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}