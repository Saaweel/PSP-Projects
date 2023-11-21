public class App {
    public static void main(String[] args) throws Exception {
        BarberShop barbershop = new BarberShop(5);

        new Thread(new Barber(barbershop)).start();

        for (int i = 0; i < 7; i++)
            new Thread(new Customer(barbershop, "cliente " + (i + 1))).start();

        while (true) {
            Thread.sleep(1000);
            System.out.print("|");
   
            for(int i = 0; i < barbershop.getCHAIRS(); ++i) {
               System.out.print("-");
            }
   
            System.out.println("|");
            System.out.print("|");
   
            for(int i = 0; i < barbershop.getCHAIRS(); ++i) {
               if (i < barbershop.getAvailableChairs()) {
                  System.out.print("\u00b7");
               } else {
                  System.out.print(" ");
               }
            }
   
            System.out.println("|");
            System.out.print("|");
   
            for(int i = 0; i < barbershop.getCHAIRS(); ++i) {
               System.out.print("-");
            }
   
            System.out.println("|");
            System.out.println();
        }
    }
}