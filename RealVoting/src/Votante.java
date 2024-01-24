public class Votante implements Runnable{
    private String nombre;
    private Mesa mesa;
    private String partido;
    
    public Votante(String nombre, Mesa mesa, String partido) {
        this.nombre = nombre;
        this.mesa = mesa;
        this.partido = partido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    @Override
    public void run() {
        System.out.println("votante " + nombre + " vota");
        mesa.votar(partido);
        
    }

}
