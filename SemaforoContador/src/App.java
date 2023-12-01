public class App {
    public static void main(String [] args) {
        Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 5; i++) {
            Thread th = new Thread(() -> {
                try {
                    semaphore.acquire();

                    System.out.println("Sección crítica ejecutada por " + Thread.currentThread().getName());
                    Thread.sleep(1000);

                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            th.setName("Hilo_" + i);
            th.start();
        }
    }
}