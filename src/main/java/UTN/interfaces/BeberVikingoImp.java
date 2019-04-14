package UTN.interfaces;

public class BeberVikingoImp implements Beber{

    @Override
    public int beber() {
        // Vikingo suma un random entre 30 y 50 por pinta

        return ( (int) (Math.random() * 20) + 30 );
    }
}
