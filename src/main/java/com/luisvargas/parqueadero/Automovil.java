package com.luisvargas.parqueadero;

import java.time.LocalDateTime;
public class Automovil  extends Vehiculo{
    
    private String tipoCombustible;

    public Automovil(LocalDateTime horaEntrada, String marca, String modelo, String placa, String tipoCombustible) {
            super(horaEntrada, marca, modelo, placa);

            this.tipoCombustible = tipoCombustible;
    }

    public String getTipoCombustible() {
            return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
            this.tipoCombustible = tipoCombustible;
    }

    @Override
    public String toString() {
            return String.format("Automovil | %s | %s | %s | %s | %s",  this.getPlaca(), this.getHoraEntrada(), this.getMarca(), this.getModelo(), this.getTipoCombustible());
    }
    
}
