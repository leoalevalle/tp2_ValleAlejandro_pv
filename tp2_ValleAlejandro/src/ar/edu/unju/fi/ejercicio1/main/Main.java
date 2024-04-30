package ar.edu.unju.fi.ejercicio1.main;

import ar.edu.unju.fi.ejercicio1.model.Producto;
import ar.edu.unju.fi.ejercicio1.model.Categoria;
import ar.edu.unju.fi.ejercicio1.model.OrigenFabricacion;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
        ArrayList<Producto> productos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1 - Crear Producto");
            System.out.println("2 - Mostrar productos");
            System.out.println("3 - Modificar producto");
            System.out.println("4 - Salir");
            System.out.print("Ingrese su opción: ");
            opcion = obtenerEntero(scanner);

            switch (opcion) {
                case 1:
                    crearProducto(scanner, productos);
                    break;
                case 2:
                    mostrarProductos(productos);
                    break;
                case 3:
                    modificarProducto(scanner, productos);
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese un número del 1 al 4.");
            }
        } while (opcion != 4);

        scanner.close();
    }

	public static void crearProducto(Scanner scanner, ArrayList<Producto> productos) {
		// TODO Auto-generated method stub

		System.out.println("Ingrese los detalles del producto:");

        System.out.print("Código: ");
        int codigo = obtenerEntero(scanner);

        System.out.print("Descripción: ");
        String descripcion = scanner.next();

        System.out.print("Precio unitario: ");
        double precio = scanner.nextDouble();
        
        mostrarOrigen(productos);
        System.out.print("Elija una opción: ");
        int opcionOrigen = obtenerEntero(scanner);
        OrigenFabricacion origen = OrigenFabricacion.values()[opcionOrigen - 1];
        
        mostrarCategoria(productos);
        System.out.print("Elija una opción: ");
        int opcionCategoria = obtenerEntero(scanner);
        Categoria categoria = Categoria.values()[opcionCategoria - 1];
        
        productos.add(new Producto(codigo, descripcion, precio, origen, categoria));
        System.out.println("Producto creado correctamente.");
	}
	

	public static void modificarProducto(Scanner scanner, ArrayList<Producto> productos) {
		// TODO Auto-generated method stub
		if (productos.isEmpty()) {
            System.out.println("No hay productos para modificar.");
            return;
        }

        System.out.println("Ingrese el código del producto que desea modificar:");
        int codigo = obtenerEntero(scanner);

        Producto productoModificar = null;
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                productoModificar = producto;
                break;
            }
        }

        if (productoModificar == null) {
            System.out.println("No se encontró ningún producto con ese código.");
            return;
        }

        System.out.println("Seleccione el atributo que desea modificar:");
        System.out.println("1 - Descripción");
        System.out.println("2 - Precio unitario");
        System.out.println("3 - Origen de fabricación");
        System.out.println("4 - Categoría");
        int opcionModificacion = obtenerEntero(scanner);

        switch (opcionModificacion) {
            case 1:
                System.out.print("Nueva descripción: ");
                productoModificar.setDescripcion(scanner.next());
                break;
            case 2:
                System.out.print("Nuevo precio unitario: ");
                productoModificar.setPrecioUnitario(scanner.nextDouble());
                break;
            case 3:
                System.out.println("----- Nuevo origen de fabricación -----");
                for (int i = 0; i < OrigenFabricacion.values().length; i++) {
                    System.out.println((i + 1) + " - " + OrigenFabricacion.values()[i]);
                }
                System.out.print("Elija una opción: ");
                int opcionOrigen = obtenerEntero(scanner);
                productoModificar.setOrigenFabricacion(OrigenFabricacion.values()[opcionOrigen - 1]);
                break;
            case 4:
                System.out.println("----- Nueva categoría -----");
                for (int i = 0; i < Categoria.values().length; i++) {
                    System.out.println((i + 1) + " - " + Categoria.values()[i]);
                }
                System.out.print("Elija una opción: ");
                int opcionCategoria = obtenerEntero(scanner);
                productoModificar.setCategoria(Categoria.values()[opcionCategoria - 1]);
                break;
            default:
                System.out.println("Opción inválida.");
        }
        System.out.println("Producto modificado correctamente.");
	}

	public static void mostrarProductos(ArrayList<Producto> productos) {
		// TODO Auto-generated method stub
		if (productos.isEmpty()) {
            System.out.println("No hay productos para mostrar.");
            return;
        }

        System.out.println("Listado de productos:");
        for (Producto producto : productos) {
            System.out.print(producto.getCodigo()+", ");
            System.out.print(producto.getDescripcion()+", ");
            System.out.print(producto.getPrecioUnitario()+"$, ");
            System.out.print(producto.getCategoria()+", ");
            System.out.print(producto.getOrigenFabricacion());
            System.out.println();
        }
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

	public static void mostrarCategoria(ArrayList<Producto> productos) {
		// TODO Auto-generated method stub
		System.out.println("----- Origen de fabricación -----");
        for (int i = 0; i < OrigenFabricacion.values().length; i++) {
            System.out.println((i + 1) + " - " + OrigenFabricacion.values()[i]);
        }
	}

	public static void mostrarOrigen(ArrayList<Producto> productos) {
		// TODO Auto-generated method stub
        System.out.println("------ Categoría ------");
        for (int i = 0; i < Categoria.values().length; i++) {
            System.out.println((i + 1) + " - " + Categoria.values()[i]);
        }
	}
}
