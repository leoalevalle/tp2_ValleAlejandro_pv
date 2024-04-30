package ar.edu.unju.fi.ejercicio2.main;

import ar.edu.unju.fi.ejercicio2.model.Efemeride;
import ar.edu.unju.fi.ejercicio2.constantes.Mes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Efemeride> efemerides = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1 - Crear efeméride");
            System.out.println("2 - Mostrar efemérides");
            System.out.println("3 - Eliminar efeméride");
            System.out.println("4 - Modificar efeméride");
            System.out.println("5 - Salir");
            System.out.print("Ingrese su opción: ");
            opcion = obtenerEntero(scanner);

            switch (opcion) {
                case 1:
                    crearEfemeride(scanner, efemerides);
                    break;
                case 2:
                    mostrarEfemerides(efemerides);
                    break;
                case 3:
                    eliminarEfemeride(scanner, efemerides);
                    break;
                case 4:
                    modificarEfemeride(scanner, efemerides);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese un número del 1 al 5.");
            }
        } while (opcion != 5);

        scanner.close();
    }

    private static int obtenerEntero(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un número entero válido.");
                scanner.next();
            }
        }
    }

    private static void crearEfemeride(Scanner scanner, ArrayList<Efemeride> efemerides) {
        System.out.println("Ingrese los detalles de la efeméride:");

        System.out.print("Código: ");
        int codigo = obtenerEntero(scanner);

        System.out.print("Mes (1-12): ");
        int numeroMes = obtenerEntero(scanner);
        while (numeroMes < 1 || numeroMes > 12) {
            System.out.println("Error: Ingrese un número válido entre 1 y 12.");
            System.out.print("Mes (1-12): ");
            numeroMes = obtenerEntero(scanner);
        }
        Mes mes = Mes.values()[numeroMes - 1];

        System.out.print("Día: ");
        int dia = obtenerEntero(scanner);

        System.out.print("Detalle: ");
        String detalle = scanner.next();

        efemerides.add(new Efemeride(codigo, mes, dia, detalle));
        System.out.println("Efeméride creada correctamente.");
    }

    private static void mostrarEfemerides(ArrayList<Efemeride> efemerides) {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemérides para mostrar.");
            return;
        }

        System.out.println("Listado de efemérides:");
        for (Efemeride efemeride : efemerides) {
            System.out.print(efemeride.getCodigo()+", ");
            System.out.print(efemeride.getDetalle()+", ");
            System.out.print(efemeride.getDia()+", ");
            System.out.print(efemeride.getMes()+", ");
            System.out.println();
            
        }
    }

    private static void eliminarEfemeride(Scanner scanner, ArrayList<Efemeride> efemerides) {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemérides para eliminar.");
            return;
        }

        System.out.println("Ingrese el código de la efeméride que desea eliminar:");
        int codigo = obtenerEntero(scanner);

        Efemeride efemerideEliminar = null;
        for (Efemeride efemeride : efemerides) {
            if (efemeride.getCodigo() == codigo) {
                efemerideEliminar = efemeride;
                break;
            }
        }

        if (efemerideEliminar == null) {
            System.out.println("No se encontró ninguna efeméride con ese código.");
            return;
        }

        efemerides.remove(efemerideEliminar);
        System.out.println("Efeméride eliminada correctamente.");
    }

    private static void modificarEfemeride(Scanner scanner, ArrayList<Efemeride> efemerides) {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemérides para modificar.");
            return;
        }

        System.out.println("Ingrese el código de la efeméride que desea modificar:");
        int codigo = obtenerEntero(scanner);

        Efemeride efemerideModificar = null;
        for (Efemeride efemeride : efemerides) {
            if (efemeride.getCodigo() == codigo) {
                efemerideModificar = efemeride;
                break;
            }
        }

        if (efemerideModificar == null) {
            System.out.println("No se encontró ninguna efeméride con ese código.");
            return;
        }

        System.out.println("Seleccione el atributo que desea modificar:");
        System.out.println("1 - Mes");
        System.out.println("2 - Día");
        System.out.println("3 - Detalle");
        int opcionModificacion = obtenerEntero(scanner);

        switch (opcionModificacion) {
            case 1:
                System.out.print("Nuevo mes (1-12): ");
                int numeroMes = obtenerEntero(scanner);
                while (numeroMes < 1 || numeroMes > 12) {
                    System.out.println("Error: Ingrese un número válido entre 1 y 12.");
                    System.out.print("Nuevo mes (1-12): ");
                    numeroMes = obtenerEntero(scanner);
                }
                efemerideModificar.setMes(Mes.values()[numeroMes - 1]);
                break;
            case 2:
                System.out.print("Nuevo día: ");
                efemerideModificar.setDia(obtenerEntero(scanner));
                break;
            case 3:
                System.out.print("Nuevo detalle: ");
                efemerideModificar.setDetalle(scanner.next());
                break;
            default:
                System.out.println("Opción inválida.");
        }

        System.out.println("Efeméride modificada correctamente.");
    }
}
