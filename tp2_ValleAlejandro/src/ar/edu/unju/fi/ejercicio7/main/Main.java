package ar.edu.unju.fi.ejercicio7.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ar.edu.unju.fi.ejercicio5.model.Categoria;
import ar.edu.unju.fi.ejercicio5.model.OrigenFabricacion;
import ar.edu.unju.fi.ejercicio5.model.Producto;

public class Main {
    public static void main(String[] args) {
        List<Producto> productos = precargarProductos();

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú de opciones:");
            System.out.println("1 - Mostrar productos disponibles");
            System.out.println("2 - Mostrar productos faltantes");
            System.out.println("3 - Incrementar precios en un 20%");
            System.out.println("4 - Mostrar productos de categoría Electrohogar disponibles");
            System.out.println("5 - Ordenar productos por precio de forma descendente");
            System.out.println("6 - Mostrar nombres de productos en mayúsculas");
            System.out.println("7 - Salir");
            System.out.print("Ingrese una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    mostrarProductos(productos);
                    break;
                case 2:
                    mostrarProductosFaltantes(productos);
                    break;
                case 3:
                    incrementarPrecios(productos);
                    break;
                case 4:
                    mostrarProductosElectrohogarDisponibles(productos);
                    break;
                case 5:
                    ordenarProductosPorPrecioDescendente(productos);
                    break;
                case 6:
                    mostrarNombresEnMayusculas(productos);
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while (opcion != 7);

        scanner.close();
    }

    private static ArrayList<Producto> precargarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Teléfono móvil", 500.0, OrigenFabricacion.CHINA, Categoria.TELEFONIA, true));
        productos.add(new Producto(2, "Laptop", 1200.0, OrigenFabricacion.BRASIL, Categoria.INFORMATICA, false));
        productos.add(new Producto(3, "Licuadora", 150.0, OrigenFabricacion.URUGUAY, Categoria.ELECTROHOGAR, true));
        productos.add(new Producto(4, "Cocina", 1500.0, OrigenFabricacion.CHINA, Categoria.ELECTROHOGAR, true));
        productos.add(new Producto(5, "Batidora", 1000.0, OrigenFabricacion.BRASIL, Categoria.ELECTROHOGAR, true));
        productos.add(new Producto(6, "Lavarropas", 4500.0, OrigenFabricacion.URUGUAY, Categoria.ELECTROHOGAR, false));
        productos.add(new Producto(7, "Monitor", 1500.0, OrigenFabricacion.CHINA, Categoria.INFORMATICA, true));
        productos.add(new Producto(8, "CPU", 3200.0, OrigenFabricacion.BRASIL, Categoria.INFORMATICA, true));
        productos.add(new Producto(9, "Taclado", 500.0, OrigenFabricacion.URUGUAY, Categoria.INFORMATICA, true));
        productos.add(new Producto(10, "Taladro", 700.0, OrigenFabricacion.CHINA, Categoria.HERRAMIENTAS, false));
        productos.add(new Producto(11, "Motosierra", 1200.0, OrigenFabricacion.BRASIL, Categoria.HERRAMIENTAS, true));
        productos.add(new Producto(12, "Telefono fijo", 250.0, OrigenFabricacion.URUGUAY, Categoria.TELEFONIA, false));
        productos.add(new Producto(13, "Router", 500.0, OrigenFabricacion.CHINA, Categoria.INFORMATICA, true));
        productos.add(new Producto(14, "Tablet", 1200.0, OrigenFabricacion.BRASIL, Categoria.TELEFONIA, false));
        productos.add(new Producto(15, "Heladera", 5000.0, OrigenFabricacion.URUGUAY, Categoria.ELECTROHOGAR, true));
        return productos;
    }

    // Opción 1 - Mostrar productos disponibles
    public static void mostrarProductos(List<Producto> productos) {
        System.out.println("Mostrando productos disponibles:");
        Consumer<Producto> mostrarProducto = producto ->
        mostrar(producto); 
        productos.stream()
        .filter(Producto::isEstado)
        .forEach(mostrarProducto);
    }

    // Opción 2 - Mostrar productos faltantes
    public static void mostrarProductosFaltantes(List<Producto> productos) {
        System.out.println("Productos faltantes:");
        Predicate<Producto> estadoFaltante = producto -> !producto.isEstado();
        List<Producto> productosFaltantes = productos.stream()
                                                     .filter(estadoFaltante)
                                                     .collect(Collectors.toList());
        if (productosFaltantes.isEmpty()) {
            System.out.println("No hay productos faltantes.");
        } else {
            for (Producto producto : productosFaltantes) {
                mostrar(producto);
            }
        }
    }

    // Opción 3 - Incrementar precios en un 20%
    public static List<Producto> incrementarPrecios(List<Producto> productos) {
        System.out.println("Incrementando precios en un 20%:");
        Function<Producto, Producto> incrementoPrecio = producto -> {
            double nuevoPrecio = producto.getPrecioUnitario() * 1.20;
            return new Producto(producto.getCodigo(), producto.getDescripcion(), nuevoPrecio,
                                producto.getOrigenFabricacion(), producto.getCategoria(), producto.isEstado());
        };

        List<Producto> productosIncrementados = new ArrayList<>();
        productos.stream().map(incrementoPrecio).forEach(productosIncrementados::add);

        productosIncrementados.forEach(producto -> mostrar(producto));

        return productosIncrementados;
    }

    // Opción 4 - Mostrar productos de categoría Electrohogar disponibles
    public static void mostrarProductosElectrohogarDisponibles(List<Producto> productos) {
        System.out.println("Productos de la categoría Electrohogar disponibles:");
        Predicate<Producto> categoriaElectrohogar = producto -> producto.getCategoria() == Categoria.ELECTROHOGAR;
        Predicate<Producto> estadoDisponible = Producto::isEstado;
        List<Producto> productosElectrohogarDisponibles = productos.stream()
                                                                   .filter(categoriaElectrohogar.and(estadoDisponible))
                                                                   .collect(Collectors.toList());
        if (productosElectrohogarDisponibles.isEmpty()) {
            System.out.println("No hay productos de la categoría Electrohogar disponibles.");
        } else {
            for (Producto producto : productosElectrohogarDisponibles) {
            	mostrar(producto);
            }
        }
    }

    // Opción 5 - Ordenar productos por precio de forma descendente
    public static void ordenarProductosPorPrecioDescendente(List<Producto> productos) {
        System.out.println("Productos ordenados por precio de forma descendente:");
        productos.sort(Comparator.comparing(Producto::getPrecioUnitario).reversed());
        for (Producto producto : productos) {
        	mostrar(producto);
        }
    }

    // Opción 6 - Mostrar nombres de productos en mayúsculas
    public static void mostrarNombresEnMayusculas(List<Producto> productos) {
        System.out.println("Nombres de productos en mayúsculas:");
        productos.stream()
                .map(p -> {
                    Producto producto = new Producto(p.getCodigo(), p.getDescripcion().toUpperCase(), p.getPrecioUnitario(), p.getOrigenFabricacion(), p.getCategoria(), p.isEstado());
                    return producto;
                })
                .forEach(Main::mostrar);
    }
    
    public static void mostrar(Producto producto) {
    	System.out.println("Código: " + producto.getCodigo() + ", Descripción: " + producto.getDescripcion() + ", Precio: $" + producto.getPrecioUnitario());
    }
}
