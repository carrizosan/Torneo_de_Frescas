package UTN.interfaces;

public class OrinarEspartanoImp implements Orinar{

    @Override
    public int orinar() {
        // Los espartanos orinan entre 20 y 50 de alchool de lo que han tomado

        return ( (int) (Math.random() * 30) + 20 );
    }
}
