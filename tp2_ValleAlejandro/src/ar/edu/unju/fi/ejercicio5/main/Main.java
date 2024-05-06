package ar.edu.unju.fi.ejercicio5.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio5.model.Producto;
import ar.edu.unju.fi.ejercicio5.model.PagoEfectivo;
import ar.edu.unju.fi.ejercicio5.model.PagoTarjeta;
import ar.edu.unju.fi.ejercicio5.model.Categoria;
import ar.edu.unju.fi.ejercicio5.model.OrigenFabricacion;

public class Main {
    public static void main(String[] args) {
        ArrayList<Producto> listaProductos = precargarProductos();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú de opciones:");
            System.out.println("1 - Mostrar productos");
            System.out.println("2 - Realizar compra");
            System.out.println("3 - Salir");
            System.out.print("Ingrese una opción: ");

            try {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        mostrarProductos(listaProductos);
                        break;
                    case 2:
                        realizarCompra(listaProductos);
                        break;
                    case 3:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número entero.");
                scanner.nextLine();
                opcion = 0;
            }
        } while (opcion != 3);

        scanner.close();
    }

    private static ArrayList<Producto> precargarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Teléfono móvil", 500.0, OrigenFabricacion.CHINA, Categoria.TELEFONIA, true));
        productos.add(new Producto(2, "Laptop", 1200.0, OrigenFabricacion.BRASIL, Categoria.INFORMATICA, true));
        productos.add(new Producto(3, "Licuadora", 150.0, OrigenFabricacion.URUGUAY, Categoria.ELECTROHOGAR, true));
        productos.add(new Producto(4, "Cocina", 1500.0, OrigenFabricacion.CHINA, Categoria.ELECTROHOGAR, true));
        productos.add(new Producto(5, "Batidora", 1000.0, OrigenFabricacion.BRASIL, Categoria.ELECTROHOGAR, true));
        productos.add(new Producto(6, "Lavarropas", 4500.0, OrigenFabricacion.URUGUAY, Categoria.ELECTROHOGAR, true));
        productos.add(new Producto(7, "Monitor", 1500.0, OrigenFabricacion.CHINA, Categoria.INFORMATICA, true));
        productos.add(new Producto(8, "CPU", 3200.0, OrigenFabricacion.BRASIL, Categoria.INFORMATICA, true));
        productos.add(new Producto(9, "Taclado", 500.0, OrigenFabricacion.URUGUAY, Categoria.INFORMATICA, true));
        productos.add(new Producto(10, "Taladro", 700.0, OrigenFabricacion.CHINA, Categoria.HERRAMIENTAS, true));
        productos.add(new Producto(11, "Motosierra", 1200.0, OrigenFabricacion.BRASIL, Categoria.HERRAMIENTAS, true));
        productos.add(new Producto(12, "Telefono fijo", 250.0, OrigenFabricacion.URUGUAY, Categoria.TELEFONIA, true));
        productos.add(new Producto(13, "Router", 500.0, OrigenFabricacion.CHINA, Categoria.INFORMATICA, true));
        productos.add(new Producto(14, "Tablet", 1200.0, OrigenFabricacion.BRASIL, Categoria.TELEFONIA, true));
        productos.add(new Producto(15, "Heladera", 5000.0, OrigenFabricacion.URUGUAY, Categoria.ELECTROHOGAR, true));
        return productos;
    }

    private static void mostrarProductos(ArrayList<Producto> productos) {
        System.out.println("Productos disponibles:");
        for (Producto producto : productos) {
            System.out.println(producto.getCodigo() + " - " + producto.getDescripcion() + " - Precio: $" + producto.getPrecioUnitario());
        }
    }

    @SuppressWarnings("resource")
	public static void realizarCompra(ArrayList<Producto> productos) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Producto> productosSeleccionados = new ArrayList<>();
        double totalCompra = 0;

        System.out.println("Seleccione los productos que desea comprar (Ingrese 0 para terminar):");
        int codigoProducto;
        do {
            System.out.print("Ingrese el código del producto: ");
            codigoProducto = scanner.nextInt();
            if (codigoProducto != 0) {
                Producto producto = encontrarProducto(productos, codigoProducto);
                if (producto != null) {
                    productosSeleccionados.add(producto);
                    totalCompra += producto.getPrecioUnitario();
                } else {
                    System.out.println("El código del producto no es válido.");
                }
            }
        } while (codigoProducto != 0);

        if (productosSeleccionados.isEmpty()) {
            System.out.println("No se han seleccionado productos para la compra.");
        }

        System.out.println("Total de la compra: $" + totalCompra);
        System.out.println("Opciones de pago:");
        System.out.println("1 - Pago efectivo");
        System.out.println("2 - Pago con tarjeta");
        System.out.print("Ingrese una opción de pago: ");
        int opcionPago = scanner.nextInt();

        switch (opcionPago) {
            case 1:
                PagoEfectivo pagoEfectivo = new PagoEfectivo(totalCompra, LocalDate.now());
                pagoEfectivo.realizarPago(totalCompra);
                System.out.println("----------------------------");
                pagoEfectivo.imprimirRecibo();
                System.out.println("----------------------------");
                break;
            case 2:
                System.out.println("Ingrese su tarjeta de crédito: ");
                String tarjeta = scanner.next();
                PagoTarjeta pagoTarjeta = new PagoTarjeta(tarjeta, LocalDate.now(), totalCompra);
                pagoTarjeta.realizarPago(totalCompra);
                System.out.println("----------------------------");
                pagoTarjeta.imprimirRecibo();
                System.out.println("----------------------------");
                break;
            default:
                System.out.println("Opción de pago no válida.");
                break;
        }
    }

    private static Producto encontrarProducto(ArrayList<Producto> productos, int codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }
}
