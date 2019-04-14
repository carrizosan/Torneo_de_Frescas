package UTN.interfaces;

public class OrinarEspartanoImp implements Orinar{

    @Override
    public int orinar() {
        // Los espartanos orinan entre 200 y 500 de alchool de lo que han tomado

        return ( (int) (Math.random() * 300) + 200 );
    }
}
