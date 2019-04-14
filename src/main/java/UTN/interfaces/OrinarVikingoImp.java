package UTN.interfaces;

public class OrinarVikingoImp implements Orinar {

    @Override
    public int orinar() {
        // Los vikingos orinan entre 400 y 800 ml. de alchool de lo que han tomado

        return (int) (Math.random() * 400) + 400;
    }
}
