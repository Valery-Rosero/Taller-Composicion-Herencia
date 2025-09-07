package com.alquiler.modelos;

import com.alquiler.interfaces.EstrategiaPrecio;
import com.alquiler.interfaces.Rentable;

public abstract class Vehiculo implements Rentable {
    private final String placa;
    private final String marca;
    private final String modelo;
    private double km;

    private EstrategiaPrecio estrategiaPrecio;

    protected Vehiculo(String placa, String marca, String modelo, double km) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.km = km;
    }

    public abstract double costoBaseDia();

    public void sumarKm(double km) {
        if (km < 0) throw new IllegalArgumentException("Los km a sumar no pueden ser negativos.");
        this.km += km;
    }

    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public double getKm() { return km; }

    public EstrategiaPrecio getEstrategiaPrecio() { return estrategiaPrecio; }
    public void setEstrategiaPrecio(EstrategiaPrecio estrategiaPrecio) { this.estrategiaPrecio = estrategiaPrecio; }

    @Override
    public double calcularPrecioAlquiler(int dias) {
        if (dias <= 0) throw new IllegalArgumentException("Los días deben ser mayores a 0.");

        double seguro = 0.0;
        // Si el vehículo implementa Asegurable, se cobra seguro; si es Moto, no.
        if (this instanceof com.alquiler.interfaces.Asegurable asegurable) {
            seguro = asegurable.calcularSeguro(dias);
        }

        if (estrategiaPrecio == null) {
            throw new IllegalStateException("No hay estrategia de precio establecida para el vehículo " + placa);
        }
        return estrategiaPrecio.total(dias, costoBaseDia(), seguro);
    }

    @Override
    public String toString() {
        return "%s [%s] %s %s (%,.0f km)".formatted(
            getClass().getSimpleName(), placa, marca, modelo, km);
    }
}
