import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de personas:");
        int n = scanner.nextInt();
        scanner.nextLine(); 
        
        Persona[] personas = new Persona[n];
        
        // Ingreso de personas
        for (int i = 0; i < n; i++) {
            System.out.println("Ingrese Persona " + (i + 1) + ":");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            
            int edad = -1; // Inicializar la edad con un valor inválido
            while (edad <= 0) { // Bucle hasta que se ingrese una edad válida
                System.out.print("Edad: ");
                if (scanner.hasNextInt()) {
                    edad = scanner.nextInt();
                    if (edad <= 0) {
                        System.out.println("La edad debe ser un número entero positivo. Intente de nuevo.");
                    }
                } else {
                    System.out.println("Entrada inválida. Debe ingresar un número entero.");
                    scanner.next(); 
                }
            }
            scanner.nextLine(); 
            personas[i] = new Persona(nombre, edad);
        }
        
        // Ordenar las personas por edad
        Arrays.sort(personas, Comparator.comparingInt(p -> p.edad));
        
        // Mostrar el arreglo de personas
        System.out.print("Arreglo de personas: ");
        for (int i = 0; i < personas.length; i++) {
            System.out.print(personas[i].edad);
            if (i < personas.length - 1) {
                System.out.print(" | "); // Separador para el arreglo ordenado 
            }
        }
        System.out.println();

        // Ingreso de la edad a buscar
        System.out.println("Ingrese la edad a buscar:");
        int edadBuscada = scanner.nextInt();
        
        // Método de búsqueda binaria
        Persona encontrada = busquedaBinaria(personas, edadBuscada, 0, personas.length - 1);
        if (encontrada != null) {
            System.out.println("La persona con la edad " + edadBuscada + " es " + encontrada.nombre);
        } else {
            System.out.println("No se encontró ninguna persona con la edad especificada.");
        }
    }
    
    public static Persona busquedaBinaria(Persona[] personas, int edad, int bajo, int alto) {
        while (bajo <= alto) {
            int centro = bajo + (alto - bajo) / 2;
            System.out.print("Bajo=" + bajo + ", Alto=" + alto + ", Centro=" + centro + ", ");
            System.out.print("ValorCentro=" + personas[centro].edad + " --> ");
            
            if (personas[centro].edad == edad) {
                System.out.println("ENCONTRADO");
                return personas[centro];
            } else if (personas[centro].edad < edad) {
                System.out.println("DERECHA");
                bajo = centro + 1;
            } else {
                System.out.println("IZQUIERDA");
                alto = centro - 1;
            }
        }
        return null;
    }
}

