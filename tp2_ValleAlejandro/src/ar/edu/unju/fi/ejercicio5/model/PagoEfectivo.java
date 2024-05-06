package ar.edu.unju.fi.ejercicio5.model;

import ar.edu.unju.fi.ejercicio5.interfaces.Pago;
import java.time.LocalDate;

public class PagoEfectivo implements Pago {
    private double montoPagado;
    private LocalDate fechaPago;

    public PagoEfectivo(double montoPagado, LocalDate fechaPago) {
        this.montoPagado = montoPagado;
        this.fechaPago = fechaPago;
    }

    @Override
    public void realizarPago(double monto) {
        this.montoPagado = monto - (monto * 0.10);
    }

    @Override
    public void imprimirRecibo() {
        System.out.println("Fecha de pago: " + fechaPago);
        System.out.println("Monto pagado: " + montoPagado);
    }

	public double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(double montoPagado) {
		this.montoPagado = montoPagado;
	}

	public LocalDate getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}
    
}
