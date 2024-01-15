public class App {
    public static void main(String[] args) throws Exception {
        // 3. Diseña un programa que aproveche la paralelización del procesador mediante (N) hilos para optimizar las siguientes tareas:
        // • Suma de los M elementos de un array de números aleatorios (del 0 al 9). (1 punto)
        // • Búsqueda de un elemento E dentro del array de números aleatorios (del 0 al 9). (1 punto)
        // • Contar cuántas veces aparece cada número (del 0 al 9). (1 punto)

        // Numero de procesadores / hilos a la vez
        int processors = 2;

        int[] array = new int[500];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 10);
        }

        // 3.1. Suma de los M elementos de un array de números aleatorios (del 0 al 9). (1 punto)
        exerciseOne(processors, array);

        // 3.2. Búsqueda de un elemento E dentro del array de números aleatorios (del 0 al 9). (1 punto)
        exerciseTwo(processors, array, 5);

        // 3.3. Contar cuántas veces aparece cada número (del 0 al 9). (1 punto)
        exerciseThree(processors, array);
    }

    private static void exerciseOne(int processors, int[] array) throws InterruptedException {
        int[] suma = new int[processors];

        for (int i = 0; i < processors; i++) {
            int inicio = i * (array.length / processors);
            int fin = (i + 1) * (array.length / processors);

            if (i == processors - 1) {
                fin = array.length;
            }

            final int finalFin = fin;
            final int finalI = i;

            Thread thread = new Thread(() -> {
                for (int j = inicio; j < finalFin; j++) {
                    suma[finalI] += array[j];
                }
            });

            thread.start();
            thread.join();
        }


        int totalSuma = 0;

        for (int i = 0; i < processors; i++) {
            totalSuma += suma[i];
        }

        System.out.println("Suma de los " + array.length + " elementos de un array de números aleatorios (del 0 al 9): " + totalSuma);
    }

    private static void exerciseTwo(int processors, int[] array, int number) throws InterruptedException {
        int[] posicion = new int[processors];

        for (int i = 0; i < processors; i++) {
            int inicio = i * (array.length / processors);
            int fin = (i + 1) * (array.length / processors);

            if (i == processors - 1) {
                fin = array.length;
            }

            final int finalFin = fin;
            final int finalI = i;

            Thread thread = new Thread(() -> {
                for (int j = inicio; j < finalFin; j++) {
                    if (array[j] == number) {
                        posicion[finalI] = j;
                        break;
                    }
                }
            });

            thread.start();
            thread.join();
        }

        int totalPosicion = -1;

        for (int i = 0; i < processors; i++) {
            if (posicion[i] != -1) {
                totalPosicion = posicion[i];
                break;
            }
        }

        if (totalPosicion != -1) {
            System.out.println("El elemento " + number + " se encuentra en la posición " + totalPosicion + " del array.");
        } else {
            System.out.println("El elemento " + number + " no se encuentra en el array.");
        }
    }

    private static void exerciseThree(int processors, int[] array) throws InterruptedException {
        int[] contador = new int[10];

        for (int i = 0; i < processors; i++) {
            int inicio = i * (array.length / processors);
            int fin = (i + 1) * (array.length / processors);

            if (i == processors - 1) {
                fin = array.length;
            }

            final int finalFin = fin;

            Thread thread = new Thread(() -> {
                for (int j = inicio; j < finalFin; j++) {
                    contador[array[j]]++;
                }
            });

            thread.start();
            thread.join();
        }

        for (int j = 0; j < 10; j++) {
            System.out.println("El número " + j + " aparece " + contador[j] + " veces.");
        }
    }
}
