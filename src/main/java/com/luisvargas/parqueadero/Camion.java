package com.luisvargas.parqueadero;

import java.time.LocalDateTime;

public class Camion extends Vehiculo {
    private double capacidadCarga;


    public Camion(LocalDateTime horaEntrada, String marca, String modelo, String placa, double capacidadCarga) {
            super(horaEntrada, marca, modelo, placa);

            this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
            return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
            this.capacidadCarga = capacidadCarga;
    }

    @Override
    public String toString() {
            return String.format("Camion | %s | %s | %s | %s | %s",  this.getPlaca(), this.getHoraEntrada(), this.getMarca(), this.getModelo(), this.getCapacidadCarga());
    }
}
