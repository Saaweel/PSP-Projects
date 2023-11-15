public class BarberShop {
    private boolean sleeping = true;

    public synchronized void llegaCliente() {
        System.out.println("Cliente llega a la barbería");

        if (sleeping) {
            sleeping = false;

            System.out.println("Barbero dormido. Despierta al barbero.");

            notify();
        } else {
            System.out.println("Barbero ocupado. Cliente espera");

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void siguienteCliente() {
        System.out.println("Atendiendo a un cliente");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Corte de pelo completado");
        
        if (!sleeping) {
            sleeping = true;
            System.out.println("Barbero vuelve a dormir");
            notify();
        }
    }
}
