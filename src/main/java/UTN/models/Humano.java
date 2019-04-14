package UTN.models;

import UTN.interfaces.Beber;
import UTN.interfaces.Orinar;

public abstract class Humano {

    private String nombre;
    private int edad;
    private int peso;
    Beber iBeber;
    Orinar iOrinar;

    public Humano(String nombre, int edad, int peso, Beber iBeber, Orinar iOrinar){
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.iBeber = iBeber;
        this.iOrinar = iOrinar;
    }

    public Humano() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Beber getiBeber() {
        return iBeber;
    }

    public void setiBeber(Beber iBeber) {
        this.iBeber = iBeber;
    }

    public Orinar getiOrinar() {
        return iOrinar;
    }

    public void setiOrinar(Orinar iOrinar) {
        this.iOrinar = iOrinar;
    }

    @Override
    public String toString() {
        return "\n[" +
                "Nombre: '" + nombre + '\'' +
                ", peso: " + peso +
                "]";
    }

    public abstract int beberHastaOrinarse();
}
