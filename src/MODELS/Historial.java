package MODELS;

import java.io.*;
import java.util.ArrayList;

public class Historial {
    private ArrayList<String> historial;
    private final String archivoHistorial = "historial.txt"; // Nombre del archivo

    public Historial() {
        this.historial = new ArrayList<>();
        cargarHistorial(); // Cargar historial al inicializar
    }

    public void agregarEntrada(String entrada) {
        historial.add(entrada);
        guardarHistorial(); // Guardar historial cada vez que se agrega una entrada
    }

    public ArrayList<String> obtenerHistorial() {
        return historial;
    }

    private void guardarHistorial() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoHistorial))) {
            for (String entrada : historial) {
                writer.write(entrada);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarHistorial() {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoHistorial))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                historial.add(linea);
            }
        } catch (FileNotFoundException e) {
            // El archivo no existe, lo cual es normal en el primer inicio
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

