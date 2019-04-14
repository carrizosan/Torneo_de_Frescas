package UTN.interfaces;

public class BeberEspartanoImp implements Beber {

    @Override
    public int beber() {
        // Espartano suma un random entre 300 y 600 ml. por pinta

        return ( (int) (Math.random() * 300) + 300 );
    }
}
