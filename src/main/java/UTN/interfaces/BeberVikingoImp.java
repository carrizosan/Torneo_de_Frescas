package UTN.interfaces;

public class BeberVikingoImp implements Beber{

    @Override
    public int beber() {
        // Vikingo suma un random entre 400 y 700 ml. por pinta

        return ( (int) (Math.random() * 300) + 400 );
    }
}
