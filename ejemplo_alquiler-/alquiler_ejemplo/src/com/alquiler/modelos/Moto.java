package com.alquiler.modelos;


public class Moto extends Vehiculo {
    private final int asientos = 2;

    public Moto(String placa, String marca, String modelo, double km) {
        super(placa, marca, modelo, km);
    }

    public int getAsientos() { return asientos; }

    @Override
    public double costoBaseDia() {
        return 60_000; 
    }
}
