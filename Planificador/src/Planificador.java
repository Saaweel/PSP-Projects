import java.util.ArrayList;
import java.util.Scanner;

public class Planificador {
    private static int PROCESS_ID = 0;

    public ArrayList<MiProceso> procesos;

    public Planificador(){
        this.procesos = new ArrayList<>();
    }

    public void pedirProcesos(){
        Scanner sc = new Scanner(System.in);

        int inTime = -1;
        int execTime = -1;

        do {
            System.out.print("Ingrese el tiempo de llegada del proceso (-1 para cancelar): ");
            inTime = sc.nextInt();
            System.out.print("Ingrese el tiempo de ejecucion del proceso (-1 para cancelar): ");
            execTime = sc.nextInt();
            if (inTime >= 0 && execTime >= 0) {
                PROCESS_ID++;
                procesos.add(new MiProceso("pid_" + PROCESS_ID, execTime, inTime));
            }
        } while (inTime >= 0 && execTime >= 0);

        sc.close();
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

    public void simulacion(){
        String command = "java";
        String mainClass = "MiProceso";
        for (MiProceso proc : procesos) {
            
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
        p.planificarFCFS();
        System.out.println("SIMULANDO CON FCFS");
        p.simulacion();
        p.planificarSJF();
        System.out.println("SIMULANDO CON SJF");
        p.simulacion();
    }
}
