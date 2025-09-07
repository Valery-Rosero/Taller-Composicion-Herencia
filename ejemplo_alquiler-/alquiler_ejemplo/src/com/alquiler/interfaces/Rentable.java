package com.alquiler.interfaces;

public interface Rentable {
    double calcularPrecioAlquiler(int dias);

    default String condiciones() {
        return "El vehículo se entrega con tanque lleno, " +
               "debe devolverse en iguales condiciones. Multa por día de retraso.";
    }
}
