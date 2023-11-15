public class App {
    public static void main(String[] args) throws Exception {
        BarberShop barbershop = new BarberShop();

        new Thread(new Barber(barbershop)).start();
        new Thread(new Customer(barbershop)).start();
        new Thread(new Customer(barbershop)).start();
        new Thread(new Customer(barbershop)).start();
        new Thread(new Customer(barbershop)).start();
    }
}