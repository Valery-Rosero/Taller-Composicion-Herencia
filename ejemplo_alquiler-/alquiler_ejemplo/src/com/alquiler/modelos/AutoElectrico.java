package com.alquiler.modelos;

import com.alquiler.interfaces.Electrico;

public class AutoElectrico extends Auto implements Electrico {
    private int bateria; 

    public AutoElectrico(String placa, String marca, String modelo, double km) {
        super(placa, marca, modelo, km);
        this.bateria = 80; 
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
