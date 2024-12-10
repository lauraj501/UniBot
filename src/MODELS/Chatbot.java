package MODELS;

import DATA.BaseData;
import java.util.ArrayList;
import java.util.List;

public class Chatbot {
    private BaseData baseDatos;
    private Historial historial; // Agregar historial

    public Chatbot() {
        this.baseDatos = new BaseData();
        this.historial = new Historial(); // Inicializar historial
    }

    public String obtenerRespuesta(String pregunta) {
        pregunta = limpiarPregunta(pregunta);
        List<String> usuario = convertirALista(pregunta);

        String[][] datos = baseDatos.getDatos(); // Obtener datos
        String respuesta = "Lo siento, no tengo información al respecto.";
        double coincidenciaMayor = 0;
        int indice = 0;

        for (int i = 0; i < datos.length; i++) {
            List<String> chatbot = convertirALista(datos[i][0]);
            double coincidenciaActual = porcentajeCoincidencia(usuario, chatbot);

            if (coincidenciaActual > coincidenciaMayor) {
                coincidenciaMayor = coincidenciaActual;
                indice = i;
            }
        }

        if (coincidenciaMayor >= 0.5) {
            respuesta = datos[indice][1];
        }

        // Agregar entrada al historial
        historial.agregarEntrada("Usuario: " + pregunta + " | Chatbot: " + respuesta);
        return respuesta;
    }

    private String limpiarPregunta(String pregunta) {
        return pregunta.replaceAll("[,.;:¿?¡!]", "")
                       .replace('á', 'a').replace('é', 'e')
                       .replace('í', 'i').replace('ó', 'o')
                       .replace('ú', 'u');
    }

    private List<String> convertirALista(String pregunta) {
        String[] elementos = pregunta.split(" ");
        List<String> lista = new ArrayList<>();
        for (String elemento : elementos) {
            lista.add(elemento);
        }
        return lista;
    }

    private double porcentajeCoincidencia(List<String> usuario, List<String> chatbot) {
        double coincidencias = 0;
        for (String palabra : usuario) {
            if (chatbot.contains(palabra)) {
                coincidencias++;
            }
        }
        return coincidencias / chatbot.size();
    }

    public Historial getHistorial() {
        return historial; // Método getter para el historial
    }
}
