

import java.util.concurrent.*;

public class Puente {
    private boolean isBoatTryingToPass = false;

    private int secondsCar;

    private int maxVehs;

    private int secondsBoat;

    private Semaphore semaphore;

    public Puente(int vehs, int maxVehs, int secondsCarTryPass, int secondsCar, int secondsBoatTryPass, int secondsBoat) {
        this.secondsCar = secondsCar;

        this.maxVehs = maxVehs;

        this.secondsBoat = secondsBoat;

        this.semaphore = new Semaphore(maxVehs);

        // Creamos X coches que llegan al puente y lo cruzan.
        for (int i = 0; i < vehs; i++) {
            final int index = i + 1;
            new Thread(new Car("Coche " + index, this)).start();
        }

        // Cada X segundos, un barco llega al puente y lo cruza.
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(secondsBoatTryPass * 1000);

                    isBoatTryingToPass = true;
                    nextBoat();
                    isBoatTryingToPass = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public synchronized void nextCar(String name) {
        try {
            System.out.println("Coche " + name + " llega al puente.");

            semaphore.acquire();

            if (isBoatTryingToPass) {
                semaphore.release();
                wait();
                semaphore.acquire();
            }

            System.out.println("Coche " + name + " cruzando el puente...");

            Thread.sleep(secondsCar * 1000);

            semaphore.release();

            System.out.println("Coche " + name + " sale del puente.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void nextBoat() {
        try {
            System.out.println("Barco llega. Cortando tráfico para elevar el puente...");

            semaphore.acquire(maxVehs);

            System.out.println("Ya no hay coches. Barco eleva puente y cruza...");

            Thread.sleep(secondsBoat * 1000);

            semaphore.release();

            notifyAll();

            System.out.println("Barco ha cruzado y ha bajado el puente. Reanudando tráfico.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}