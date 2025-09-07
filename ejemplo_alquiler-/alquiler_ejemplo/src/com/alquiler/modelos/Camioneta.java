package com.alquiler.modelos;

import com.alquiler.interfaces.Asegurable;

public class Camioneta extends Vehiculo implements Asegurable {
    private final double capacidadKg;

    public Camioneta(String placa, String marca, String modelo, double km, double capacidadKg) {
        super(placa, marca, modelo, km);
        this.capacidadKg = capacidadKg;
    }

    public double getCapacidadKg() { return capacidadKg; }

    @Override
    public double costoBaseDia() {
        return 180_000; 
    }

    @Override
    public double calcularSeguro(int dias) {
        return costoBaseDia() * 0.12 * dias;
    }
}
