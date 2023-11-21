public class App {
    public static void main(String[] args) throws Exception {
        BarberShop barbershop = new BarberShop(5);

        new Thread(new Barber(barbershop)).start();

        for (int i = 0; i < 7; i++)
            new Thread(new Customer(barbershop, "cliente " + (i + 1))).start();
    }
}