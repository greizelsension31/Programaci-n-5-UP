package lab15_1.creathing_a_thread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrintWindow extends JFrame {
    private JTextField countLimitField;
    private JButton startButton, stopButton, clearButton;
    private JTextArea displayArea;
    private JScrollPane scrollPane;  // Panel de desplazamiento
    private PrintsNumbers printsNumbers;
    private Thread thread;

    public PrintWindow() {
        setTitle("Contador de Números");
        
        // Ajustar el tamaño de la ventana
        setSize(500, 400);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Campo de texto para ingresar el número máximo
        countLimitField = new JTextField();
        countLimitField.setBounds(20, 20, 100, 25);
        add(countLimitField);

        // Botón de inicio
        startButton = new JButton("Iniciar");
        startButton.setBounds(150, 20, 100, 25);
        add(startButton);

        // Botón de detener
        stopButton = new JButton("Detener");
        stopButton.setBounds(260, 20, 100, 25);
        stopButton.setEnabled(false); // Deshabilitado al inicio
        add(stopButton);

        // Botón de limpiar
        clearButton = new JButton("Limpiar");
        clearButton.setBounds(150, 60, 100, 25);
        add(clearButton);

        // Área de texto para mostrar el conteo
        displayArea = new JTextArea();
        displayArea.setEditable(false);  // El área de texto no es editable

        // Crear el JScrollPane para agregar el área de texto con scroll
        scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(20, 100, 440, 200);  // Ajustar posición y tamaño
        add(scrollPane);  // Agregar el scrollPane en lugar del JTextArea directamente

        // Acción del botón iniciar
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startCounting();
            }
        });

        // Acción del botón detener
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopCounting();
            }
        });

        // Acción del botón limpiar
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearDisplay();
            }
        });
    }

    // Método para iniciar el conteo
    private void startCounting() {
        try {
            int limit = Integer.parseInt(countLimitField.getText());
            printsNumbers = new PrintsNumbers(limit, displayArea);
            thread = new Thread(printsNumbers);
            thread.start();

            startButton.setEnabled(false);  // Deshabilitar botón iniciar
            stopButton.setEnabled(true);    // Habilitar botón detener
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido.");
        }
    }

    // Método para detener el conteo
    private void stopCounting() {
        if (printsNumbers != null) {
            printsNumbers.stopPrinting();
            startButton.setEnabled(true);  // Habilitar botón iniciar
            stopButton.setEnabled(false);  // Deshabilitar botón detener
        }
    }

    // Método para limpiar el área de texto
    private void clearDisplay() {
        displayArea.setText("");
        countLimitField.setText("");
    }

    public static void main(String[] args) {
        PrintWindow window = new PrintWindow();
        window.setVisible(true);
    }
}
