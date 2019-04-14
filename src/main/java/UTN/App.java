package UTN;

import UTN.interfaces.*;
import UTN.models.Espartano;
import UTN.models.Humano;
import UTN.models.Tabernero;
import UTN.models.Vikingo;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
////import com.sun.xml.internal.ws.api.databinding.EndpointCallBridge;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class App 
{
    public static void main( String[] args ) {

        Beber spartanBebe = new BeberEspartanoImp();
        Beber vikingBebe = new BeberVikingoImp();
        Orinar vikingOrina = new OrinarVikingoImp();
        Orinar spartanOrina = new OrinarEspartanoImp();

        List<Vikingo> vikingos = new ArrayList<Vikingo>();
        List<Espartano> espartanos = new ArrayList<Espartano>();
        Humano tabernero = new Tabernero("Mike Szyslack", 43, 135, vikingBebe, spartanOrina);

        vikingos = Arrays.asList(new Vikingo("Victor", 37, 110, vikingBebe, vikingOrina),
                                new Vikingo("Valencio", 25, 90, vikingBebe, vikingOrina),
                                new Vikingo("Victoria", 32, 66, vikingBebe, vikingOrina),
                                new Vikingo("Venezzio", 47, 145, vikingBebe, vikingOrina),
                                new Vikingo("Villar", 55, 70, vikingBebe, vikingOrina),
                                new Vikingo("Vin", 20, 55, vikingBebe, vikingOrina),
                                new Vikingo("Vanildo", 16, 99, vikingBebe, vikingOrina),
                                new Vikingo("Viccenzo", 33, 109, vikingBebe, vikingOrina),
                                new Vikingo("Vi", 39, 145, vikingBebe, vikingOrina),
                                new Vikingo("Vikinto", 27, 95, vikingBebe, vikingOrina));

        espartanos = Arrays.asList(new Espartano("Esteban", 29, 80, spartanBebe, spartanOrina),
                                new Espartano("Eduardo", 66, 79, spartanBebe, spartanOrina),
                                new Espartano("Enrique", 34, 201, spartanBebe, spartanOrina),
                                new Espartano("El Sharawy", 63, 71, spartanBebe, spartanOrina),
                                new Espartano("Enzo", 33, 112, spartanBebe, spartanOrina),
                                new Espartano("Embolo", 38, 91, spartanBebe, spartanOrina),
                                new Espartano("Eto'o", 47, 55, spartanBebe, spartanOrina),
                                new Espartano("Eladio", 19, 97, spartanBebe, spartanOrina),
                                new Espartano("Eve", 28, 86, spartanBebe, spartanOrina),
                                new Espartano("Emanuel", 27, 100, spartanBebe, spartanOrina));

        try{
            // Conectarse a la base de datos
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/torneo_frescas", "root", "");


            Humano ebrio = realizarTorneoDeFrescas(vikingos, espartanos, connection);

            Thread.sleep(2000);
            if(ebrio != null) {

                System.out.println("\n##### GANARDOR DEL TORNEO DE FRESCAS #####" +
                        ebrio + " !!! \nSE ENFRENTARA AL DUEÑO DE LA TABERNA, PRIMO DE MOE SZYSLACK " +
                        "POR LA COPA BIRRÓN");

                Thread.sleep(2000);

                    Humano ganadorDefinitivo = enfrentarAlTabernero(ebrio, tabernero);
            } else {
                System.out.println("Extrañamente todos hicieron 0 puntos o han empatado en todos los enfrentamientos." +
                        "dada la situacion el tabernero decide consagrarse ganador");
            }


        }catch (InterruptedException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            System.out.println("No se puede conectar a la base de datos");
            e.printStackTrace();
        }

    }


    public static Humano realizarTorneoDeFrescas(List<Vikingo> vikingos, List<Espartano> espartanos, Connection cn) throws InterruptedException, SQLException {
        int puntosCampeon = 0;
        Humano ganador = null;

        Thread.sleep(2000);
        System.out.println("PARTICIPANTES VIKINGOS: " + vikingos);
        Thread.sleep(2000);
        System.out.println("\n\nPARTICIPANTES ESPARTANOS: " + espartanos);

        vikingos = vikingos.stream()
                .sorted(Comparator.comparingInt(Vikingo::getPeso))
                .collect(Collectors.toList());
        espartanos = espartanos.stream()
                .sorted(Comparator.comparingInt(Espartano::getPeso))
                .collect(Collectors.toList());

        Thread.sleep(2000);
        System.out.println("\n\nVIKINGOS EN FILA POR PESO ESPERANDO SU TURNO: " + vikingos);
        Thread.sleep(2000);
        System.out.println("\n\nESPARTANOS EN FILA POR PESO ESPERANDO SU TURNO: " + espartanos);


        for(Vikingo v : vikingos) {
            for (Espartano e : espartanos) {

                // Hago nuevamente stream.collect ya que si no arroja concurrentModificationException
                vikingos = vikingos.stream().collect(Collectors.toList());
                espartanos = espartanos.stream().collect(Collectors.toList());

                Vikingo bebedorVikingo = vikingos.remove(0);
                Espartano bebedorEspartano = espartanos.remove(0);

                Thread.sleep(2000);
                System.out.println("\n### ENFRENTAMIENTO! ### VIKINGO: [ " + bebedorVikingo.getNombre() +
                        " ] VS ESPARTANO: [ " + bebedorEspartano.getNombre() + " ]");

                System.out.println("Participantes bebiendo........");

                int bebidoVikingo = bebedorVikingo.beberHastaOrinarse();
                int bebidoEspartano = bebedorEspartano.beberHastaOrinarse();

                Thread.sleep(4000);

                System.out.println("///VIKINGO: " + bebedorVikingo.getNombre() + " " + bebidoVikingo + " MILILITROS DE ALCOHOL! ////");
                Thread.sleep(1000);
                System.out.println("///ESPARTANO: " + bebedorEspartano.getNombre() + " " + bebidoEspartano + " MILILITROS DE ALCOHOL!! ////");

                Thread.sleep(2000);

                if (bebidoVikingo > bebidoEspartano) {
                    System.out.println("GANADOR de la ronda VIKINGO! " + bebedorVikingo.getNombre());
                    if(bebidoVikingo > puntosCampeon) {
                        ganador = bebedorVikingo;
                        puntosCampeon = bebidoVikingo;
                    }
                } else if (bebidoEspartano > bebidoVikingo) {
                    System.out.println("GANADOR de la ronda ESPARTANO! " + bebedorEspartano.getNombre());
                    if(bebidoEspartano > puntosCampeon) {
                        ganador = bebedorEspartano;
                        puntosCampeon = bebidoEspartano;
                    }
                } else {
                    System.out.println("EMPATE! Se han orinado al mismo tiempo");
                }

            }
        }
        if(ganador instanceof Vikingo)
            guardarGanador(cn, ganador, puntosCampeon, "Vikingo");
        else if (ganador instanceof Espartano)
            guardarGanador(cn, ganador, puntosCampeon, "Espartano");
        return ganador;
    }

    public static Humano enfrentarAlTabernero(Humano ebrioRetador, Humano tabernero) throws InterruptedException {

        System.out.println("\nTABERNERO DE TURNO: " + tabernero);

        System.out.println("\nRETADOR AMBISIOSO: " + ebrioRetador);


        int taberneroBebe = tabernero.beberHastaOrinarse();
        int retadorBebe = ebrioRetador.beberHastaOrinarse();

        Thread.sleep(3000);
        System.out.println("\nRetador: " + ebrioRetador.getNombre() + " " + retadorBebe + " MILILITROS DE ALCOHOL!");
        Thread.sleep(2000);
        System.out.println("Tabernero: " + tabernero.getNombre() + " " + taberneroBebe + " MILILITROS DE ALCOHOL!");

        if (taberneroBebe > retadorBebe) {
            System.out.println("\n####################################################");
            System.out.println("#######!!!GANADOR: EL TABERNERO! " + tabernero.getNombre() + "######");
            System.out.println("\n####################################################");

            return tabernero;
        } else if (retadorBebe > taberneroBebe) {
            System.out.println("\n####################################################");
            System.out.println("###!!!GANADOR: EL RETADOR! " + ebrioRetador.getNombre());
            System.out.println("\n####################################################");

            return ebrioRetador;
        } else {
            System.out.println("EMPATE! NINGUNO DE LOS DOS AGUANTO Y ORINARON A LA VEZ!!");
            System.out.println("EN CASO DE EMPATE EL TABERNERO TIENE VENTAJA DEPORTIVA, SE LLEVA SU PROPIO PREMIO");

            return tabernero;
        }
    }


    public static void guardarGanador(Connection cn, Humano ganador, int puntaje, String tipo) throws SQLException {
        Statement st = cn.createStatement();
        //Usamos callable statement por que tenemos parametro de salida.
        Integer id;
        PreparedStatement ps = cn.prepareStatement("CALL usp_addGanador(?,?,?,?,?)");
        ps.setString(1, ganador.getNombre());
        ps.setInt(2, ganador.getEdad());
        ps.setInt(3, ganador.getPeso());
        ps.setInt(4, puntaje);
        ps.setString(5, tipo);
        ps.executeQuery();
    }
}
