package com.alquiler.gestor;

import com.alquiler.modelos.Vehiculo;

public class Alquiler {
    private final Vehiculo vehiculo;
    private final int dias;
    private final double total;

    public Alquiler(Vehiculo vehiculo, int dias) {
        if (dias <= 0) throw new IllegalArgumentException("Los días deben ser mayores a 0.");
        this.vehiculo = vehiculo;
        this.dias = dias;
        this.total = vehiculo.calcularPrecioAlquiler(dias);
    }

    public Vehiculo getVehiculo() { return vehiculo; }
    public int getDias() { return dias; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return "Alquiler de %s por %d días - Total: $%,.2f".formatted(
            vehiculo, dias, total
        );
    }
}
