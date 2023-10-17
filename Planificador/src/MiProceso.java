public class MiProceso {
    private String nombre;
    private int ejecucion;
    private int llegada;

    public MiProceso(String nombre, int ejecucion, int llegada) {
        this.nombre = nombre;
        this.ejecucion = ejecucion;
        this.llegada = llegada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEjecucion() {
        return ejecucion;
    }

    public void setEjecucion(int ejecucion) {
        this.ejecucion = ejecucion;
    }

    public int getLlegada() {
        return llegada;
    }

    public void setLlegada(int llegada) {
        this.llegada = llegada;
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            int segundos = Integer.parseInt(args[0]);
            try {
                Thread.sleep(segundos*1000);
            } catch (InterruptedException e) {
                e.fillInStackTrace();
            }
        }
    }
}
