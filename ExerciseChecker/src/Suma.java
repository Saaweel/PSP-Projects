public class Suma {
    public static void main(String[] args) throws Exception {
        if (args.length > 1)
            System.out.println(Integer.parseInt(args[0]) + Integer.parseInt(args[1]));
        else
            System.out.println("Parametros invalidos requeridos: <int> <int>");
    }
}
