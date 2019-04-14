package UTN;

import UTN.interfaces.*;
import UTN.models.Espartano;
import UTN.models.Humano;
import UTN.models.Tabernero;
import UTN.models.Vikingo;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.xml.internal.ws.api.databinding.EndpointCallBridge;

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
//                                new Vikingo("Villar", 55, 70, vikingBebe, vikingOrina),
//                                new Vikingo("Vin", 20, 55, vikingBebe, vikingOrina),
//                                new Vikingo("Vanildo", 16, 99, vikingBebe, vikingOrina),
//                                new Vikingo("Viccenzo", 33, 109, vikingBebe, vikingOrina),
//                                new Vikingo("Vi", 39, 145, vikingBebe, vikingOrina),
                new Vikingo("Vikinto", 27, 95, vikingBebe, vikingOrina));

        espartanos = Arrays.asList(new Espartano("Esteban", 29, 80, spartanBebe, spartanOrina),
                new Espartano("Eduardo", 66, 79, spartanBebe, spartanOrina),
                new Espartano("Enrique", 34, 201, spartanBebe, spartanOrina),
                new Espartano("El Sharawy", 63, 71, spartanBebe, spartanOrina),
//                                new Espartano("Enzo", 33, 112, spartanBebe, spartanOrina),
//                                new Espartano("Embolo", 38, 91, spartanBebe, spartanOrina),
//                                new Espartano("Eto'o", 47, 55, spartanBebe, spartanOrina),
//                                new Espartano("Eladio", 19, 97, spartanBebe, spartanOrina),
//                                new Espartano("Eve", 28, 86, spartanBebe, spartanOrina),
                new Espartano("Emanuel", 27, 100, spartanBebe, spartanOrina));

        try{
            Humano ebrio = realizarTorneoDeFrescas(vikingos, espartanos);

            Thread.sleep(2000);

            System.out.println("\n##### GANARDOR DEL TORNEO DE FRESCAS #####\n" +
                    ebrio + " !!! \nSE ENFRENTARA AL DUEÑO DE LA TABERNA, PRIMO DE MOE SZYSLACK\n" +
                    "POR LA COPA BIRRÓN");

            Thread.sleep(2000);

            Humano ganadorDefinitivo = enfrentarAlTabernero(ebrio, tabernero);


        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static Humano realizarTorneoDeFrescas(List<Vikingo> vikingos, List<Espartano> espartanos) throws InterruptedException {
        int puntosCampeon;
        Humano ganador = null;

        Thread.sleep(2000);
        System.out.println("PARTICIPANTES VIKINGOS: " + vikingos);
        System.out.println("\nPARTICIPANTES ESPARTANOS: " + espartanos);


        for(Vikingo v : vikingos) {
            for (Espartano e : espartanos) {

                vikingos = vikingos.stream()
                        .sorted(Comparator.comparingInt(Vikingo::getPeso))
                        .collect(Collectors.toList());
                espartanos = espartanos.stream()
                        .sorted(Comparator.comparingInt(Espartano::getPeso))
                        .collect(Collectors.toList());

                Thread.sleep(2000);
                System.out.println("\nVIKINGOS EN FILA POR PESO ESPERANDO SU TURNO: " + vikingos);
                Thread.sleep(2000);
                System.out.println("\nESPARTANOS EN FILA POR PESO ESPERANDO SU TURNO: " + espartanos);

                Vikingo bebedorVikingo = vikingos.remove(0);
                Espartano bebedorEspartano = espartanos.remove(0);

                Thread.sleep(2000);
                System.out.println("\nENFRENTAMIENTO! El Vikingo: " + bebedorVikingo.getNombre() +
                        " y el Espartano: " + bebedorEspartano.getNombre());

                int bebidoVikingo = bebedorVikingo.beberHastaOrinarse();
                int bebidoEspartano = bebedorEspartano.beberHastaOrinarse();

                Thread.sleep(3000);
                System.out.println("Vikingo: " + bebedorVikingo.getNombre() + " " + bebidoVikingo + " puntos");
                Thread.sleep(2000);
                System.out.println("Espartano: " + bebedorEspartano.getNombre() + " " + bebidoEspartano + " puntos");

                Thread.sleep(2000);
                if (bebidoVikingo > bebidoEspartano) {
                    System.out.println("GANADOR de la ronda Vikingo! " + bebedorVikingo.getNombre());
                    ganador = bebedorVikingo;
                } else if (bebidoEspartano > bebidoVikingo) {
                    System.out.println("GANADOR de la ronda Espartano! " + bebedorEspartano.getNombre());
                    ganador = bebedorEspartano;
                } else {
                    System.out.println("EMPATE! Se han orinado al mismo tiempo");
                }

            }
        }
        return ganador;
    }

    public static Humano enfrentarAlTabernero(Humano ebrioRetador, Humano tabernero) throws InterruptedException {

        System.out.println("\nTABERNERO DE TURNO: " + tabernero);
        if (ebrioRetador.getClass().equals("Vikingo")){
            System.out.println("\nRETADOR VIKINGO AMBISIOSO: " + ebrioRetador);
        } else if (ebrioRetador.getClass().equals("Espartano")) {
            System.out.println("\nRETADOR ESPARTANO AMBISIOSO: " + ebrioRetador);
        } else {
            System.out.println("\nRETADOR AMBISIOSO: " + ebrioRetador);
        }

        int taberneroBebe = tabernero.beberHastaOrinarse();
        int retadorBebe = ebrioRetador.beberHastaOrinarse();

        Thread.sleep(3000);
        System.out.println("Retador: " + ebrioRetador.getNombre() + " " + retadorBebe + " puntos");
        Thread.sleep(2000);
        System.out.println("Tabernero: " + tabernero.getNombre() + " " + taberneroBebe + " puntos");


        if (taberneroBebe > retadorBebe) {
            System.out.println("###!!!GANADOR: EL TABERNERO! " + tabernero.getNombre());
            return tabernero;
        } else if (retadorBebe > taberneroBebe) {
            System.out.println("###!!!GANADOR: EL RETADOR! " + ebrioRetador.getNombre());
            return ebrioRetador;
        } else {
            System.out.println("EMPATE! NINGUNO DE LOS DOS AGUANTO Y ORINARON A LA VEZ!!");
            System.out.println("EN CASO DE EMPATE EL TABERNERO TIENE VENTAJA DEPORTIVA, SE LLEVA SU PROPIO PREMIO");
            return tabernero;
        }
    }
}
