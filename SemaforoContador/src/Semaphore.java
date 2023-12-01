public class Semaphore {
    private int maxThreads;
    private int currentThreads;

    public Semaphore(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public synchronized void acquire() throws InterruptedException {
        System.out.println("Hilo " + Thread.currentThread().getName() + " esperando para ejectarse");
        while (currentThreads >= maxThreads) {
            wait();
        }

        currentThreads++;
    }

    public synchronized void release() {
        currentThreads--;

        notify();

        System.out.println("Hilo " + Thread.currentThread().getName() + " liberó el semáforo");
    }
}
