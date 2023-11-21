public class BarberShop {
    private boolean sleeping = true;

    private int availabeChairs;

    private final int CHAIRS = 5;

    public synchronized void newClient() {
       
    }

    public synchronized void nextClient() {

    }

    public boolean isSleeping() {
        return sleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }

    public int getAvailabeChairs() {
        return availabeChairs;
    }

    public void setAvailabeChairs(int availabeChairs) {
        this.availabeChairs = availabeChairs;
    }

    public int getCHAIRS() {
        return CHAIRS;
    }
}
