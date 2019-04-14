package UTN.models;

import UTN.interfaces.Beber;
import UTN.interfaces.Orinar;

public class Vikingo extends Humano {

    private int bebedorProfesional;

    public Vikingo (String nombre, int edad, int peso, Beber iBeber, Orinar iOrinar){
        super(nombre, edad, peso, iBeber, iOrinar);
        bebedorProfesional = (edad > 40) ? 80 : 40;
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
            // Vikingos suman mas alchool en sangre por pinta ya que son bebedores profesionales
            alchoolEnSangre += getiBeber().beber() + bebedorProfesional;

            // Tienen 1 / 10 posibilidades de orinarse, el vikingo seguira bebiendo hasta que se orine.
            if (alchoolEnSangre % 8 == 0) {
                // Si el vikingo orina pierde, y a su alchool se le restara lo que haya orinado
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
