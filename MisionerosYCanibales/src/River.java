public class River {
    private final int MAX_BOAT;
    
    private String [] boat;

    public River(int boatSize) {
        this.MAX_BOAT = boatSize;
        this.boat = new String[boatSize];

        for (int i = 0; i < MAX_BOAT; i++) {
            this.boat[i] = "";
        }
    }

    public boolean isMissionaryOnBoat() {
        for (int i = 0; i < MAX_BOAT; i++) {
            if (this.boat[i].equals("Misionero")) {
                return true;
            }
        }

        return false;
    }

    public boolean isBoatFull() {
        for (int i = 0; i < MAX_BOAT; i++) {
            if (this.boat[i].equals("")) {
                return false;
            }
        }

        return true;
    }

    public synchronized void arriveMissionary() {
        System.out.println("Misionero llega al rio");

        while (isMissionaryOnBoat() || isBoatFull()) {
            try {
                System.out.println("No hay espacio para un misionero, esperando...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < MAX_BOAT; i++) {
            if (this.boat[i].equals("")) {
                this.boat[i] = "Misionero";
                System.out.println("Misionero sube al bote");
                notify();
                break;
            }
        }
    }

    public synchronized void arriveCannibal() {
        System.out.println("Canibal llega al rio");

        while (!isMissionaryOnBoat() || isBoatFull()) {
            try {
                System.out.println("No hay espacio para un canibal, esperando...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < MAX_BOAT; i++) {
            if (this.boat[i].equals("")) {
                this.boat[i] = "Canibal";
                System.out.println("Canibal sube al bote");
                notify();
                break;
            }
        }
    }

    public synchronized void clearBoat() {
        while (this.boat.length < this.MAX_BOAT) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Desembarcando...");
        for (int i = 0; i < MAX_BOAT; i++) {
            this.boat[i] = "";
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        notify();
    }
}