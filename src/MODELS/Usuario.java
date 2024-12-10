package MODELS;

import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private ArrayList<String> preguntasRealizadas;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.preguntasRealizadas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void registrarPregunta(String pregunta) {
        preguntasRealizadas.add(pregunta);
    }

    public ArrayList<String> getPreguntasRealizadas() {
        return preguntasRealizadas;
    }
}