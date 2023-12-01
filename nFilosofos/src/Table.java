public class Table {
    private int forksCount;

    public Table(int forksCount) {
        this.forksCount = forksCount;
    }

    public synchronized void takeForks() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " intenta tomar 2 tenedores");

        while (forksCount < 2) {
            wait();
        }

        forksCount -= 2;
    }

    public synchronized void putForks() {
        System.out.println(Thread.currentThread().getName() + " deja 2 tenedores");
        
        forksCount += 2;
        
        // Notificamos a todos los hilos por que me he dado cuenta que si notificas a uno solo hay algunos que nunca llegan a comer o tardan mucho en que les toque
        notifyAll(); 
    }
}
