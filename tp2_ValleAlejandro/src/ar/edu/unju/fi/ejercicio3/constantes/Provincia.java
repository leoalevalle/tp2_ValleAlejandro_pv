package ar.edu.unju.fi.ejercicio3.constantes;

public enum Provincia {
    JUJUY(672260, 53219), 
    SALTA(1333365, 155488), 
    TUCUMAN(1694651, 22524), 
    CATAMARCA(367828, 102606), 
    LA_RIOJA(393531, 89680), 
    SANTIAGO_DEL_ESTERO(874006, 136351);

    private int cantidadPoblacion;
    private int superficie;

    private Provincia(int cantidadPoblacion, int superficie) {
        this.cantidadPoblacion = cantidadPoblacion;
        this.superficie = superficie;
    }

    public int getCantidadPoblacion() {
        return cantidadPoblacion;
    }

    public void setCantidadPoblacion(int cantidadPoblacion) {
        this.cantidadPoblacion = cantidadPoblacion;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public double calcularDensidadPoblacional() {
        return (double) cantidadPoblacion / superficie;
    }
}
