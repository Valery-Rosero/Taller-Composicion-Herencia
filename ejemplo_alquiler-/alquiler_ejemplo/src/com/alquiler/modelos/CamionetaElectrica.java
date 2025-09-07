package com.alquiler.modelos;

import com.alquiler.interfaces.Electrico;

public class CamionetaElectrica extends Camioneta implements Electrico {
    private int bateria; 

    public CamionetaElectrica(String placa, String marca, String modelo, double km, double capacidadKg) {
        super(placa, marca, modelo, km, capacidadKg);
        this.bateria = 70;
    }

    @Override
    public void recargar(int minutos) {
        if (minutos <= 0) return;
        int incremento = Math.max(1, minutos / 5);
        bateria = Math.min(100, bateria + incremento);
    }

    @Override
    public int nivelBateria() {
        return bateria;
    }
}
