public class Car implements Runnable {
    private String name;

    private Puente puente;

    public Car(String name, Puente puente) {
        this.name = name;
        this.puente = puente;
    }

    @Override
    public void run() {
        puente.nextCar(name);
    }
}