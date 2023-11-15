class Customer implements Runnable {
    private BarberShop barbershop;

    public Customer(BarberShop barbershop) {
        this.barbershop = barbershop;
    }

    @Override
    public void run() {
        while (true) {
            barbershop.llegaCliente();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}