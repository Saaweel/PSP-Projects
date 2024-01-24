import java.util.ArrayList;

public class Mesa {
    private ArrayList<Partido> partidos;

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    public Mesa(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    public int contarVotos(String partido){
        for (Partido p : partidos) {
            if(p.getNombre().equals(partido)){
                return p.getNumVotos();
            }
        }
        return 0;
    }

    public synchronized void cerrarMesa(){
        for (Partido p : partidos) {
            try {
                p.semp.acquire();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public synchronized void votar(String partido){
        for (Partido p : partidos) {
            if(p.getNombre().equals(partido)){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                p.sumarVoto();
                break;
            }
        }
    }
    
}
