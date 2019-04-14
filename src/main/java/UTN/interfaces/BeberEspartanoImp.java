package UTN.interfaces;

public class BeberEspartanoImp implements Beber {

    @Override
    public int beber() {
        // Espartano suma un random entre 20 y 40 por pinta

        return ( (int) (Math.random() * 20) + 20 );
    }
}
