package ar.edu.unju.fi.ejercicio6.main;

import ar.edu.unju.fi.ejercicio6.interfaces.funcionales.Converter;
import ar.edu.unju.fi.ejercicio6.model.FelinoDomestico;
import ar.edu.unju.fi.ejercicio6.model.FelinoSalvaje;

public class Main {
    public static void main(String[] args) {
        // Creación de un felino doméstico
        FelinoDomestico garfield = new FelinoDomestico("Garfield", (byte) 45, 12f);

        // Definición de expresión lambda para convertir de FelinoDomestico a FelinoSalvaje
        Converter<FelinoDomestico, FelinoSalvaje> converter = x -> new FelinoSalvaje(x.getNombre(), x.getEdad(), x.getPeso());

        // Realización de la conversión
        FelinoSalvaje felino1 = converter.convert(garfield);

        // Mostrar los datos del objeto felino salvaje felino1
        converter.mostrarObjeto(felino1);
        
       
        //Felino Tanner
        FelinoSalvaje tanner = new FelinoSalvaje("Tanner", (byte) 20, 186f);

        if (Converter.isNotNull(tanner)) {
        	 Converter<FelinoSalvaje, FelinoDomestico> converter1 = x -> new FelinoDomestico(x.getNombre(), x.getEdad(), x.getPeso());
        	 FelinoDomestico felinoConvertido = converter1.convert(tanner);
        	 converter1.mostrarObjeto(felinoConvertido);
        }else {
            System.out.println("El objeto a convertir es nulo.");
        }
    }
}
