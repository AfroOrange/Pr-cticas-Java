import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Método para mostrar todos los datos del archivo
    static void mostrarUsuarios(BufferedReader file) throws IOException {
        String line;
        while ((line = file.readLine()) != null) {
            System.out.println(line);
        }
    }

    // Método para buscar usuarios a través del DNI
    static void buscarUsuario(String dni)  {
        try (BufferedReader filePath = new BufferedReader(new FileReader("modificacion de ficheros/usuarios.txt"))) {
            String line;
            while ((line = filePath.readLine()) != null) {
                if (line.contains(dni)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para agregar usuarios con los datos introducidos
    static void agregarUsuarios(String dni, String nombre, String apellidos, String correo) {
        try (FileWriter filePath = new FileWriter("modificacion de ficheros/usuarios.txt", true)) {
            filePath.write("\n" + dni + ", " + nombre + ", " + apellidos + ", " + correo + "\n");
            System.out.println("Datos agregados: " + dni + ", " + nombre + ", " + apellidos + ", " + correo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para eliminar usuarios a través del DNI
    static void eliminarUsuario(String dni)  {
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader filePath = new BufferedReader(new FileReader("modificacion de ficheros/usuarios.txt"))) {
            String line;
            while ((line = filePath.readLine()) != null) {
                if (!line.contains(dni)) {
                    data.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileWriter filePath = new FileWriter("modificacion de ficheros/usuarios.txt")) {
            for (String line : data) {
                filePath.write(line + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // menú que despliega la opción que quiere realizar el usuario
    public static void main(String[] args)  {
        BufferedReader archivo = null;
        try {
            archivo = new BufferedReader(new FileReader("modificacion de ficheros/usuarios.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // "Menú"
        System.out.println("--------- Inicio de Sesión ---------");
        System.out.println("| 1 --> Ver la lista de usuarios  |  2 --> Buscar un usuario por DNI  |  3 --> Agregar un nuevo usuario  |  4 --> Eliminar un usuario  |");
        System.out.print("¿Qué desea hacer?: ");
        Scanner scanner = new Scanner(System.in);
        int respuesta = scanner.nextInt();

        // Acciones según la respuesta del usuario
        while (true) {
            if (respuesta == 1) {
                try {
                    mostrarUsuarios(archivo);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (respuesta == 2) {
                System.out.print("Introducir DNI: ");
                buscarUsuario(scanner.next());
            }
            if (respuesta == 3) {
                System.out.print("DNI: ");
                String dni = scanner.next();
                System.out.print("Nombre: ");
                String nombre = scanner.next();
                System.out.print("Apellidos: ");
                String apellidos = scanner.next();
                System.out.print("Correo electrónico: ");
                String correo = scanner.next();
                agregarUsuarios(dni, nombre, apellidos, correo);
            }
            if (respuesta == 4) {
                System.out.print("Introduce el DNI de la persona que desee eliminar: ");
                eliminarUsuario(scanner.next());
            }

            System.out.println("\n");
            System.out.print("¿Quiere hacer otra opción? --- | S / N | ");
            String finalizar = scanner.next();
            if (finalizar.equalsIgnoreCase("N")) {
                break;
            } else {
                System.out.println("--------- Menú ---------");
                System.out.println("| 1 --> Ver la lista de usuarios  |  2 --> Buscar un usuario por DNI  |  3 --> Agregar un nuevo usuario  |  4 --> Eliminar un usuario  |");
                System.out.print("Qué desea hacer?: ");
                respuesta = scanner.nextInt();
            }
        }
        scanner.close();
    }
}
