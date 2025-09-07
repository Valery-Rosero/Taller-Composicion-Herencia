package com.alquiler.estrategias;

import com.alquiler.interfaces.EstrategiaPrecio;

public class PrecioEstandar implements EstrategiaPrecio {
    @Override
    public double total(int dias, double costoBase, double seguro) {
        return costoBase * dias + seguro;
    }
}
