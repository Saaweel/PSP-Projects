import java.util.Random;

class Customer implements Runnable {
    private BarberShop barbershop;
    private String name;

    public Customer(BarberShop barbershop, String name) {
        this.barbershop = barbershop;
        this.name = name;
    }

    @Override
    public void run() {
        while(true){
            this.barbershop.newClient(name);
            try {
                Thread.sleep(new Random().nextInt(200) + 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}