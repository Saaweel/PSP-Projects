public class App {
    public static void main(String[] args) {
        River river = new River(2);

        // Llegada de misioneros
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                river.arriveMissionary();
            }
        }).start();

        // Llegada de canibales
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                river.arriveCannibal();
            }
        }).start();
        
        // Desembarque
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                river.clearBoat();
            }
        }).start();
    }
}