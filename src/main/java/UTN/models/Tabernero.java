package UTN.models;

import UTN.interfaces.Beber;
import UTN.interfaces.Orinar;

public class Tabernero extends Humano{

    private int bebedorProfesional;
    private int toleranciaExtra;

    public Tabernero(String nombre, int edad, int peso, Beber iBeber, Orinar iOrinar){
        super(nombre, edad, peso, iBeber, iOrinar);
        toleranciaExtra = 17;
        bebedorProfesional = 200;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int beberHastaOrinarse() {

        boolean seOrino = false;
        int alchoolEnSangre = 0;
        int orinado = 0;
        while(!seOrino) {
            // El tabernero es un bebedor experto que suma puntos extra por pinta
            alchoolEnSangre += getiBeber().beber() + bebedorProfesional;

            // Tienen 1 / 10 posibilidades de orinarse, sin embargo, al tener tolerancia disminuiran aun mas
            // sus posibilidades de orinarse. El tabernero aguanta mucho y es muy dificil que se orine rapido.
            if ( alchoolEnSangre % (10 + toleranciaExtra) == 0) {
                // Si el espartano orina pierde, y a su alchool se le restara lo que haya orinado
                orinado =  getiOrinar().orinar();

                //No puede orinar mas que el alchool que ha tomado. Si orina mas es agua, pero su puntaje de alchool queda en 0.
                if(orinado >= alchoolEnSangre){
                    alchoolEnSangre = 0;
                }else {
                    alchoolEnSangre -= orinado;
                }
                seOrino = true;
            }
        }
        return alchoolEnSangre;
    }
}
