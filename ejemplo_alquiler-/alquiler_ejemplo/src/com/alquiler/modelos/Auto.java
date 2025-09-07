package com.alquiler.modelos;

import com.alquiler.interfaces.Asegurable;

public class Auto extends Vehiculo implements Asegurable {
    private final int asientos = 5;

    public Auto(String placa, String marca, String modelo, double km) {
        super(placa, marca, modelo, km);
    }

    public int getAsientos() { return asientos; }

    @Override
    public double costoBaseDia() {
        return 120_000; 
    }

    @Override
    public double calcularSeguro(int dias) {
        return costoBaseDia() * 0.08 * dias;
    }
}
