package UTN.models;

import UTN.interfaces.Beber;
import UTN.interfaces.Orinar;

public class Espartano extends Humano{

    private int toleranciaExtra;

    public Espartano(String nombre, int edad, int peso, Beber iBeber, Orinar iOrinar){
        super(nombre, edad, peso, iBeber, iOrinar);
        toleranciaExtra = (peso > 90) ? 5 : 2;
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
            // Espartanos son bebedores ocacionales y no suman puntos extra por pinta
            alchoolEnSangre += getiBeber().beber();

            // Tienen 1 / 10 posibilidades de orinarse, sin embargo, al tener tolerancia disminuiran aun mas
            // sus posibilidades de orinarse. el espartano seguira bebiendo hasta que se orine.
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
