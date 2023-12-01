public class App {
    public static void main(String[] args) throws Exception {
        int n = 5;

        // Creamos una mesa con N tenedores
        Table table = new Table(n);

        // Creamos N filosofos
        for (int i = 0; i < n; i++) {
            Thread th = new Thread(() -> {
                while (true) {
                    try {
                        table.takeForks();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " come durante 2 segundos");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    table.putForks();
                }
            });

            th.setName("Filosofo_" + i);
            th.start();
        }
    }
}
