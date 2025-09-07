package com.alquiler;

import java.util.*;
import com.alquiler.modelos.*;
import com.alquiler.interfaces.*;
import com.alquiler.estrategias.*;
import com.alquiler.gestor.Alquiler;

public class Main {

    private static String etiquetasInterfaces(Object v) {
        List<String> etiquetas = new ArrayList<>();
        etiquetas.add("Rentable");
        if (v instanceof Asegurable) etiquetas.add("Asegurable");
        if (v instanceof Electrico) etiquetas.add("Electrico");
        return String.join(", ", etiquetas);
    }

    private static EstrategiaPrecio elegirEstrategia(Scanner sc) {
        System.out.println("Elija estrategia de precio:");
        System.out.println("1. Estándar");
        System.out.println("2. Fin de semana (10% si dias >= 2)");
        System.out.println("3. Largo plazo (15% si dias >= 10)");
        System.out.print("Opción: ");
        int tipo = sc.nextInt();
        return switch (tipo) {
            case 2 -> new PrecioFinde();
            case 3 -> new PrecioLargoPlazo();
            default -> new PrecioEstandar();
        };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(new Auto("ABC123", "Toyota", "Corolla", 20000));
        vehiculos.add(new Moto("XYZ987", "Yamaha", "FZ", 12000));
        vehiculos.add(new Camioneta("JKL456", "Ford", "Ranger", 50000, 900));
        vehiculos.add(new AutoElectrico("ELE111", "Tesla", "Model 3", 10000));
        vehiculos.add(new CamionetaElectrica("ELE222", "BYD", "Tang", 8000, 600));

        System.out.println("=== VEHÍCULOS DISPONIBLES ===");
        for (int i = 0; i < vehiculos.size(); i++) {
            Vehiculo v = vehiculos.get(i);
            System.out.printf("%d) %-40s | Interfaces: %s%n",
                    i + 1, v.toString(), etiquetasInterfaces(v));
        }
        System.out.println();

        System.out.print("Seleccione vehículo (1-" + vehiculos.size() + "): ");
        int opcion = sc.nextInt();
        if (opcion < 1 || opcion > vehiculos.size()) {
            System.out.println("Opción inválida.");
            return;
        }
        Vehiculo seleccionado = vehiculos.get(opcion - 1);

        System.out.print("Días de alquiler: ");
        int dias = sc.nextInt();
        if (dias <= 0) {
            System.out.println("No se permiten días <= 0.");
            return;
        }

        EstrategiaPrecio estrategia = elegirEstrategia(sc);
        seleccionado.setEstrategiaPrecio(estrategia);

        Alquiler alquiler = new Alquiler(seleccionado, dias);
        System.out.println();
        System.out.println(alquiler);
        System.out.println("Condiciones: " + seleccionado.condiciones());

        if (seleccionado instanceof Electrico electrico) {
            System.out.println("\nVehículo eléctrico detectado.");
            System.out.println("Nivel de batería actual: " + electrico.nivelBateria() + "%");
            System.out.print("¿Cuántos minutos desea recargar? ");
            int min = sc.nextInt();
            electrico.recargar(min);
            System.out.println("Nivel de batería tras recarga: " + electrico.nivelBateria() + "%");
        }

        System.out.println("\n=== PRUEBAS RÁPIDAS ===");
        Rentable r1 = new Auto("AAA000", "Kia", "Rio", 1000);
        Rentable r2 = new Moto("BBB111", "Bajaj", "Pulsar", 2000);
        Rentable r3 = new Camioneta("CCC222", "Chevrolet", "D-Max", 3000, 800);
        ((Vehiculo) r1).setEstrategiaPrecio(new PrecioEstandar());
        ((Vehiculo) r2).setEstrategiaPrecio(new PrecioEstandar());
        ((Vehiculo) r3).setEstrategiaPrecio(new PrecioEstandar());
        System.out.printf("Auto (3 días): $%,.2f%n", r1.calcularPrecioAlquiler(3));
        System.out.printf("Moto (3 días): $%,.2f%n", r2.calcularPrecioAlquiler(3));
        System.out.printf("Camioneta (3 días): $%,.2f%n", r3.calcularPrecioAlquiler(3));

        Vehiculo demo = new Auto("DDD333", "Renault", "Logan", 4000);
        demo.setEstrategiaPrecio(new PrecioEstandar());
        double estandar = demo.calcularPrecioAlquiler(12);
        demo.setEstrategiaPrecio(new PrecioLargoPlazo());
        double largo = demo.calcularPrecioAlquiler(12);
        System.out.printf("Estrategia estándar (12 días): $%,.2f%n", estandar);
        System.out.printf("Estrategia largo plazo (12 días): $%,.2f%n", largo);

        Vehiculo demoMoto = new Moto("EEE444", "Honda", "CBF", 1000);
        demoMoto.setEstrategiaPrecio(new PrecioEstandar());
        System.out.printf("Moto (sin seguro) 2 días: $%,.2f%n", demoMoto.calcularPrecioAlquiler(2));

        Electrico e = new AutoElectrico("FFF555", "BYD", "Dolphin", 500);
        System.out.println("Batería antes: " + e.nivelBateria() + "%");
        e.recargar(2000); 
        System.out.println("Batería después (tope 100): " + e.nivelBateria() + "%");

        sc.close();
    }
}
