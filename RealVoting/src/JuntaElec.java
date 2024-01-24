import java.util.ArrayList;

public class JuntaElec {
    public static void main(String[] args) throws Exception {
        Mesa[] mesas = new Mesa[3];
        ArrayList<Partido> partidos = new ArrayList<>();
        partidos.add(new Partido("PSOE"));
        partidos.add(new Partido("PODEMOS"));
        partidos.add(new Partido("SUMAR"));
        partidos.add(new Partido("VOX"));
        for (int i = 0; i < mesas.length; i++) {
            ArrayList<Partido> partidosMesa = new ArrayList<>();
            partidosMesa.add(new Partido("PSOE"));
            partidosMesa.add(new Partido("PODEMOS"));
            partidosMesa.add(new Partido("SUMAR"));
            partidosMesa.add(new Partido("VOX"));
            mesas[i] = new Mesa(partidosMesa);
        }
        for (int i = 0; i < 50; i++) {
            Votante v = new Votante("votante_"+i, mesas[i%mesas.length], partidos.get(i%mesas.length).getNombre());
            new Thread(v).start();
        }
        Thread.sleep(3000);
        for (Mesa m : mesas) {
            m.cerrarMesa();
            for (Partido p : m.getPartidos()) {
                String nombre = p.getNombre();
                for (Partido partido : partidos) {
                    if(partido.getNombre().equals(nombre)){
                        partido.numVotos += p.getNumVotos();
                        break;
                    }
                }
            }
        }
        System.out.println(partidos);
        
    }
}
