package ar.edu.unju.fi.ejercicio4.main;
import ar.edu.unju.fi.ejercicio4.constantes.Posicion;
import ar.edu.unju.fi.ejercicio4.model.Jugador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1 - Alta de jugador");
            System.out.println("2 - Mostrar todos los jugadores");
            System.out.println("3 - Modificar la posición de un jugador");
            System.out.println("4 - Eliminar un jugador");
            System.out.println("5 - Salir");
            System.out.print("Ingrese su opción: ");
            opcion = obtenerEntero(scanner);

            try {
                switch (opcion) {
                    case 1:
                        altaJugador(scanner, jugadores);
                        break;
                    case 2:
                        mostrarJugadores(jugadores);
                        break;
                    case 3:
                        modificarPosicion(scanner, jugadores);
                        break;
                    case 4:
                        eliminarJugador(scanner, jugadores);
                        break;
                    case 5:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, ingrese un número del 1 al 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número entero válido.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 5);

        scanner.close();
    }

    private static int obtenerEntero(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                throw new InputMismatchException("Ingrese un número entero válido.");
            }
        }
    }

    private static void altaJugador(Scanner scanner, ArrayList<Jugador> jugadores) {
        System.out.println("Ingrese los detalles del jugador:");
        System.out.print("Nombre: ");
        String nombre = scanner.next();

        System.out.print("Apellido: ");
        String apellido = scanner.next();

        System.out.print("Fecha de nacimiento (YYYY-MM-DD ej: 2000-12-31): ");
        LocalDate fechaNacimiento = LocalDate.parse(scanner.next());

        System.out.print("Nacionalidad: ");
        String nacionalidad = scanner.next();

        System.out.print("Estatura: ");
        double estatura = scanner.nextDouble();

        System.out.print("Peso: ");
        double peso = scanner.nextDouble();

        System.out.println("Posiciones:");
        for (Posicion posicion : Posicion.values()) {
            System.out.println(posicion.ordinal() + 1 + " - " + posicion);
        }
        System.out.print("Seleccione la posición del jugador: ");
        int opcionPosicion = obtenerEntero(scanner);
        Posicion posicion = Posicion.values()[opcionPosicion - 1];

        jugadores.add(new Jugador(nombre, apellido, fechaNacimiento, nacionalidad, estatura, peso, posicion));
        System.out.println("Jugador creado correctamente.");
    }

    private static void mostrarJugadores(ArrayList<Jugador> jugadores) {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores para mostrar.");
            return;
        }

        System.out.println("Listado de jugadores:");
        for (Jugador jugador : jugadores) {
            System.out.print(jugador.getApellido()+" ");
            System.out.print(jugador.getNombre()+", .");
            System.out.print(jugador.getPosicion()+". Nacimiento: ");
            System.out.print(jugador.getFechaNacimiento()+", ");
            System.out.print(jugador.getNacionalidad()+". ");
            System.out.print(jugador.getEstatura()+"mts, ");
            System.out.print(jugador.getPeso()+"kg");
            System.out.println();
        }
    }

    private static void modificarPosicion(Scanner scanner, ArrayList<Jugador> jugadores) {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores para modificar.");
            return;
        }

        System.out.print("Ingrese el nombre del jugador que desea modificar: ");
        String nombre = scanner.next();

        System.out.print("Ingrese el apellido del jugador que desea modificar: ");
        String apellido = scanner.next();

        boolean jugadorEncontrado = false;
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equalsIgnoreCase(nombre) && jugador.getApellido().equalsIgnoreCase(apellido)) {
                System.out.println("Posiciones:");
                for (Posicion posicion : Posicion.values()) {
                    System.out.println(posicion.ordinal() + 1 + " - " + posicion);
                }
                System.out.print("Seleccione la nueva posición del jugador: ");
                int opcionPosicion = obtenerEntero(scanner);
                Posicion nuevaPosicion = Posicion.values()[opcionPosicion - 1];

                jugador.setPosicion(nuevaPosicion);
                System.out.println("Posición modificada correctamente.");
                jugadorEncontrado = true;
                break;
            }
        }

        if (!jugadorEncontrado) {
            System.out.println("No se encontró ningún jugador con ese nombre y apellido.");
        }
    }

    private static void eliminarJugador(Scanner scanner, ArrayList<Jugador> jugadores) {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores para eliminar.");
            return;
        }

        System.out.print("Ingrese el nombre del jugador que desea eliminar: ");
        String nombre = scanner.next();

        System.out.print("Ingrese el apellido del jugador que desea eliminar: ");
        String apellido = scanner.next();

        Iterator<Jugador> iterator = jugadores.iterator();
        boolean jugadorEliminado = false;
        while (iterator.hasNext()) {
            Jugador jugador = iterator.next();
            if (jugador.getNombre().equalsIgnoreCase(nombre) && jugador.getApellido().equalsIgnoreCase(apellido)) {
                iterator.remove();
                System.out.println("Jugador eliminado correctamente.");
                jugadorEliminado = true;
                break;
            }
        }

        if (!jugadorEliminado) {
            System.out.println("No se encontró ningún jugador con ese nombre y apellido.");
        }
    }
}
