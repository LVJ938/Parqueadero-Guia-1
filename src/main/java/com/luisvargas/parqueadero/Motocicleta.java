package com.luisvargas.parqueadero;

import java.time.LocalDateTime;
public class Motocicleta extends Vehiculo{
    private int cilindraje;

    public Motocicleta(LocalDateTime horaEntrada, String marca, String modelo, String placa, int cilindraje) {
            super(horaEntrada, marca, modelo, placa);

            this.cilindraje = cilindraje;
    }

    public int getCilindraje() {
            return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
            this.cilindraje = cilindraje;
    }

    @Override
    public String toString() {
            return String.format("Motocicleta | %s | %s | %s | %s | %s",  this.getPlaca(), this.getHoraEntrada(), this.getMarca(), this.getModelo(), this.getCilindraje());
    }
}
