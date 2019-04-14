package UTN.interfaces;

public class OrinarVikingoImp implements Orinar {

    @Override
    public int orinar() {
        // Los vikingos orinan entre 30 y 80 de alchool de lo que han tomado

        return (int) (Math.random() * 50) + 30;
    }
}
