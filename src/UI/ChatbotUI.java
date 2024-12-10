package UI;

import MODELS.Chatbot;
import MODELS.Historial;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatbotUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private JButton historialButton; // Botón de historial
    private Chatbot chatbot;

    public ChatbotUI() {
        // Inicializar el chatbot
        chatbot = new Chatbot();

        // Configuración de la ventana
        setTitle("UniBot");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear componentes
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        sendButton = new JButton("Enviar");
        historialButton = new JButton("Historial"); // Inicializar botón de historial

        // Panel inferior para el campo de entrada y botones
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        bottomPanel.add(historialButton, BorderLayout.WEST); // Agregar botón de historial

        // Agregar componentes a la ventana
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Agregar saludo inicial al área de chat
        chatArea.append("¡Hola!, Soy un Chatbot personalizado para la UFPSO.\n");
        chatArea.append("Dime en qué puedo ayudarte.\n\n");

        // Evento del botón enviar
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });

        // Evento de Enter en el campo de texto
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });

        // Evento del botón historial
        historialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarHistorial();
            }
        });
    }

    private void enviarMensaje() {
        String pregunta = inputField.getText().trim();
        if (!pregunta.isEmpty()) {
            // Mostrar la pregunta del usuario
            chatArea.append("Tú: " + pregunta + "\n");

            // Obtener la respuesta del chatbot
            String respuesta = chatbot.obtenerRespuesta(pregunta);

            // Mostrar la respuesta
            chatArea.append("Chatbot: " + respuesta + "\n\n");

            // Limpiar el campo de texto
            inputField.setText("");
        }
    }

    private void mostrarHistorial() {
        // Obtener el historial de preguntas y respuestas
        Historial historial = chatbot.getHistorial(); // Crear un método getter en Chatbot
        StringBuilder sb = new StringBuilder("Historial:\n");
        for (String entrada : historial.obtenerHistorial()) {
            sb.append(entrada).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Historial de Conversaciones", JOptionPane.INFORMATION_MESSAGE);
    }
}
