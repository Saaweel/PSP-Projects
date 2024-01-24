import java.util.concurrent.Semaphore;

public class Partido {
    private String nombre;
    public int numVotos;
    Semaphore semp;
    public Partido(String nombre) {
        this.nombre = nombre;
        this.numVotos = 0;
        this.semp = new Semaphore(1);
    }

    public void sumarVoto(){
        try {
            this.semp.acquire();
            this.numVotos++;
            this.semp.release();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getNumVotos() {
        return numVotos;
    }
    public void setNumVotos(int numVotos) {
        this.numVotos = numVotos;
    }
    public Semaphore getSemp() {
        return semp;
    }
    public void setSemp(Semaphore semp) {
        this.semp = semp;
    }

    @Override
    public String toString() {
        return "Partido [nombre=" + nombre + ", numVotos=" + numVotos + "]";
    }
    
}
