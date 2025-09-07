package com.alquiler.estrategias;

import com.alquiler.interfaces.EstrategiaPrecio;

public class PrecioFinde implements EstrategiaPrecio {
    @Override
    public double total(int dias, double costoBase, double seguro) {
        double base = costoBase * dias + seguro;
        if (dias >= 2) {
            base *= 0.90;
        }
        return base;
    }
}
