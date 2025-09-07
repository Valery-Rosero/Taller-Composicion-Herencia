package com.alquiler.estrategias;

import com.alquiler.interfaces.EstrategiaPrecio;

public class PrecioLargoPlazo implements EstrategiaPrecio {
    @Override
    public double total(int dias, double costoBase, double seguro) {
        double base = costoBase * dias + seguro;
        if (dias >= 10) {
            base *= 0.85; 
        }
        return base;
    }
}
