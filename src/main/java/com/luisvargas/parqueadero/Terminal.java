
package com.luisvargas.parqueadero;

/**
 *
 * @author IOpc
 */
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;


public class Terminal {
    public static void main(String[] args) {
            Terminal terminal = new Terminal();
            terminal.Ejecutar();
    }

    private final static String MENU_PRINCIPAL = "MENU_PRINCIPAL";

    private final static String DIVISION = "========== ========== ==========";

    private boolean ejecutando;
    private String estado;
    private Parqueadero parqueadero;
    
    //constructor
    public Terminal() {
            ejecutando = true;
            estado = MENU_PRINCIPAL;
            parqueadero = new Parqueadero();
    }
	
    public void Ejecutar() {

            while(ejecutando) {
                    if(estado == MENU_PRINCIPAL) {
                            ImprimirMenu();
                    }
            }
    }
    //metodo para generar el marco del menu
    public String ImprimirSeccion(boolean conRespuesta, String titulo, String str) {
            System.out.println("========== " + titulo + " ==========");
            System.out.println(str);
            System.out.println(DIVISION);

            if(conRespuesta) {
                    System.out.print("Selección: ");
                    Scanner scanner = new Scanner(System.in);
                    String respuestaUsuario = scanner.nextLine();
                    return respuestaUsuario;
            } 
            else {
                    return "";
            }
    }
	
    public void ImprimirEstado() {
            List<Vehiculo> vehiculos = this.parqueadero.consultarEstado();
            String lista = "";

            for (int i = 0; i < vehiculos.size(); i++) {
                    Vehiculo v = vehiculos.get(i);
                    lista += (" * " + v.toString() + "\n");
            }

            ImprimirSeccion(false, "Lista actual de vehiculos (Placa - Tipo - Hora Ingreso)", lista);
    }
	
    public void ImprimirMenu() {
            String menu = """
                            1. Ingresar automovil
                            2. Ingresar camion
                            3. Ingresar motocicleta
                            4. Registrar salida
                            5. Consultar estado
                            6. Generar reporte del día
                            7. Salir""";
            //control de excepcion para garantizar no generar un cierre inesperado al colocar un valor que no sea numero
            try {
                    int respuestaUsuario = Integer.parseInt(ImprimirSeccion(true, "Menu", menu));
                    switch(respuestaUsuario){
                        case 1:
                            IngresarAutomovil();
                            break;
                        case 2:
                           IngresarCamion();
                           break;
                        case 3:
                           IngresarMotocicleta();
                           break;
                        case 4:
                           RegistrarSalida();
                           break;
                        case 5:
                           ImprimirEstado();
                           break;
                        case 6:
                          ImprimirReporteDelDia();
                          break;
                        case 7:
                          Terminar();
                          break;
                        default:
                            
                            break;
                    }
                    
                    
            } catch (NumberFormatException e) {
                    System.out.println("Comando no valido, intente de nuevo...");
                    ImprimirMenu();
            } catch (Exception e) {
                    System.out.println("Error intente de nuevo...");
                    ImprimirMenu();
            }
    }
	
   

    public void IngresarAutomovil() {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingrese la marca del vehiculo: ");
            String marca = scanner.nextLine();

            System.out.println("Ingrese el modelo del vehiculo: ");
            String modelo = scanner.nextLine();

            System.out.println("Ingrese la placa del vehiculo: ");
            String placa = scanner.nextLine();

            System.out.println("Ingrese el tipo de combustible del vehiculo: ");
            String tipoCombustible = scanner.nextLine();

            Automovil a = new Automovil(LocalDateTime.now(), marca, modelo, placa, tipoCombustible);

            this.parqueadero.registrarEntrada(a);

            System.out.println("Automovil con placa '" + placa + "' ingresado...");
    }
    
    public void IngresarCamion() throws Exception {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingrese la marca del camion: ");
            String marca = scanner.nextLine();

            System.out.println("Ingrese el modelo del camion: ");
            String modelo = scanner.nextLine();

            System.out.println("Ingrese la placa del camion: ");
            String placa = scanner.nextLine();

            System.out.println("Ingrese la capacidad de carga del camion: ");
            String stringCapacidadDeCarga = scanner.nextLine();

            try {
                    double capacidadDeCarga = Double.parseDouble(stringCapacidadDeCarga);

                    Camion c = new Camion(LocalDateTime.now(), marca, modelo, placa, capacidadDeCarga);

                    this.parqueadero.registrarEntrada(c);

                    System.out.println("Camion con placa '" + placa + "' y capacidad de '" + capacidadDeCarga + "' ingresado...");
            } catch (NumberFormatException e) {
                    System.out.println("La capacidad de carga ingresada no es un numero valido...");
                    throw new Exception();
            }
    }
	
    public void IngresarMotocicleta() throws Exception {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingrese la marca de la motocicleta: ");
            String marca = scanner.nextLine();

            System.out.println("Ingrese el modelo de la motocicleta: ");
            String modelo = scanner.nextLine();

            System.out.println("Ingrese la placa de la motocicleta: ");
            String placa = scanner.nextLine();

            System.out.println("Ingrese el cilindraje de la motocicleta: ");
            String stringCilindraje = scanner.nextLine();

            try {
                    int cilindraje = Integer.parseInt(stringCilindraje);

                    Motocicleta m = new Motocicleta(LocalDateTime.now(), marca, modelo, placa, cilindraje);

                    this.parqueadero.registrarEntrada(m);

                    System.out.println("Motocicleta con placa '" + placa + "' y cilindraje de '" + cilindraje + " CC' ingresado...");
                } catch (NumberFormatException e) {
                    System.out.println("El cilindraje ingresado no es un numero valido...");
                    throw new Exception();
            }
    }

    

    public void RegistrarSalida() throws Exception {
            List<Vehiculo> vehiculos = this.parqueadero.consultarEstado();
            String lista = "";

            for (int i = 0; i < vehiculos.size(); i++) {
                    Vehiculo v = vehiculos.get(i);
                    lista += (" " + (i + 1) + ". " + v.toString() + "\n");
            }

            int respuestaUsuario = Integer.parseInt(ImprimirSeccion(true, "Seleccione el vehiculo", lista));
            Vehiculo vehiculo = vehiculos.get(respuestaUsuario - 1);
            double precio = this.parqueadero.registrarSalida(vehiculo.getPlaca());

            System.out.println("El vehiculo de placa '" + vehiculo.getPlaca() + "' debe pagar un total de: $" + precio);
    }
    
public void ImprimirReporteDelDia() {
            ImprimirSeccion(false, "Reporte del dia", this.parqueadero.generarReporteDelDia());
    }

   public void Terminar() {
            System.out.println("Cerrando parqueadero, hasta la proxima...");
            this.ejecutando = false;
    }
	
	
}
