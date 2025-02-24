package com.luisvargas.parqueadero;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Parqueadero {
    public final static double FRACCION_HORA_AUTOMOVIL = 6840;
    public final static double FRACCION_HORA_CAMION = 9780;
    public final static double FRACCION_HORA_MOTOCICLETA = 900;

    private List<String> historialDeVehiculos;
    private List<Vehiculo> vehiculos;

    public Parqueadero() {
            this.historialDeVehiculos = new ArrayList<String>();
            this.vehiculos = new ArrayList<Vehiculo>();
    }

    public List<Vehiculo> consultarEstado() {
            return this.vehiculos;
    }

    public String generarReporteDelDia() {
            String reporte = "";

            for (int i = historialDeVehiculos.size() - 1; i >= 0; i--) {
                    reporte += (" - " + historialDeVehiculos.get(i) + "\n");
            }

            return reporte;
    }
	
    public void registrarEntrada(Vehiculo vehiculo) {
            this.vehiculos.add(vehiculo);
    }

    public double registrarSalida(String placa) throws Exception {
            for (int i = 0; i < this.vehiculos.size(); i++) {
                    Vehiculo v = this.vehiculos.get(i);

                    if(v.getPlaca() == placa) {
                            double costoFraccionVehiculo = 0;

                            LocalDateTime entrada = v.getHoraEntrada();
                            LocalDateTime salida = LocalDateTime.now();

                            double segundos = (double) (ChronoUnit.SECONDS.between(entrada, salida));
                            double minutos = segundos/60;
                            double horas = Math.ceil(minutos/60);

                            if (v.getClass() == Automovil.class) {
                                    costoFraccionVehiculo = FRACCION_HORA_AUTOMOVIL;
                            } else if (v.getClass() == Camion.class) {
                                    costoFraccionVehiculo = FRACCION_HORA_CAMION;
                            } else {
                                    costoFraccionVehiculo = FRACCION_HORA_MOTOCICLETA;
                            }

                            this.vehiculos.remove(i);

                            double precioTotal = costoFraccionVehiculo * horas;

                            this.historialDeVehiculos.add(v.toString() + " | $" + precioTotal);

                            return precioTotal;
                    }
            }

            throw new Exception("Placa no encontrada");
    }	
}
