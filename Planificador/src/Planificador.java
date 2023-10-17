import java.util.ArrayList;

public class Planificador {

    public ArrayList<MiProceso> procesos;

    public Planificador(){
        this.procesos = new ArrayList<>();
    }
    public void pedirProcesos(){
        procesos.add(new MiProceso("pid_1", 6, 0));
        procesos.add(new MiProceso("pid_2", 4, 30));
        procesos.add(new MiProceso("pid_3", 2, 4));
        procesos.add(new MiProceso("pid_4", 5, 60));
    }

    public void planificarFCFS(){
        procesos.sort((MiProceso p1, MiProceso p2) -> p1.getLlegada() - p2.getLlegada());
    }

    public void planificarSJF(){
        ArrayList<MiProceso> dumpProcesos = new ArrayList<>(procesos);

        dumpProcesos.sort((MiProceso p1, MiProceso p2) -> p1.getLlegada() - p2.getLlegada());

        int exectime = 0;
        ArrayList<MiProceso> newProcesos = new ArrayList<>();
        
        while (dumpProcesos.size() > 0) {
            ArrayList<MiProceso> temp = new ArrayList<>();

            for (MiProceso proc : dumpProcesos) 
                if (proc.getLlegada() <= exectime)
                    temp.add(proc);


            temp.sort((MiProceso p1, MiProceso p2) -> p1.getEjecucion() - p2.getEjecucion());

            if (temp.size() > 0) {
                exectime += temp.get(0).getEjecucion();
                newProcesos.add(temp.get(0));
                dumpProcesos.remove(temp.get(0));
            } else {
                exectime ++;
            }
        }

        procesos = newProcesos;
    }

    public void showProcesos(){
        System.out.println("ID       LLEGADA   EJECUCION");
        System.out.println("--------------------------------");
        for (MiProceso proc : procesos) {
            System.out.println(proc.getNombre() + "       " + proc.getLlegada() + "          " + proc.getEjecucion());
        }
    }

    public void simulacion(){
        String command = "java";
        String mainClass = "MiProceso";
        for (MiProceso proc : procesos) {
            //Este es el que va a lanzar los procesos
            
            String tiempoEjec = proc.getEjecucion()+"";
            ProcessBuilder processBuilder = new ProcessBuilder(command, mainClass, tiempoEjec);

            try {
                System.out.println("Ejecutando Proceso "+proc.getNombre());
                Process process = processBuilder.start();
                System.out.println("Esperando al Proceso " + proc.getNombre());
                int exitCode = process.waitFor();
                System.out.println("Proceso " + proc.getNombre() + " ha finalizado. ExitCode:" + exitCode);
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
    }

    public static void main(String[] args){

        Planificador p = new Planificador();

        p.pedirProcesos();
        // p.planificarFCFS();
        // System.out.println("SIMULANDO CON FCFS");
        // p.simulacion();
        p.planificarSJF();
        p.showProcesos();
        // System.out.println("SIMULANDO CON SJF");
        // p.simulacion();

        
    }
}
