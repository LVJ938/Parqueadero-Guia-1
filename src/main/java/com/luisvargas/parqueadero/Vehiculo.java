
package com.luisvargas.parqueadero;

/**
 *
 * @Luis vargas
 */
import java.time.LocalDateTime;

public class Vehiculo {
    private LocalDateTime horaEntrada;
    private String marca;
    private String modelo;
    private String placa;

    public Vehiculo(LocalDateTime horaEntrada, String marca, String modelo, String placa) {
            super();

            this.horaEntrada = horaEntrada;
            this.marca = marca;
            this.modelo = modelo;
            this.placa = placa;
    }

    public LocalDateTime getHoraEntrada() {
            return horaEntrada;
    }

    public String getMarca() {
            return marca;
    }

    public String getModelo() {
            return modelo;
    }

    public String getPlaca() {
            return placa;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
            this.horaEntrada = horaEntrada;
    }

    public void setMarca(String marca) {
            this.marca = marca;
    }

    public void setModelo(String modelo) {
            this.modelo = modelo;
    }

    public void setPlaca(String placa) {
            this.placa = placa;
    }
}
