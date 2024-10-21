package lab15_1.creathing_a_thread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PrintWindow extends JFrame {
    private JTextField countLimitField;
    private JButton startButton, stopButton, clearButton;
    private JTextArea displayArea;
    private JScrollPane scrollPane;  // Panel de desplazamiento
    private PrintsNumbers printsNumbers;
    private Thread thread;

    public PrintWindow() {
        setTitle("Prints Numbers");
        
        // Ajustar el tamaño de la ventana
        setSize(500, 400);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        // Campo de texto para ingresar el número máximo
        countLimitField = new JTextField();
        countLimitField.setBounds(20, 20, 100, 25);
        add(countLimitField);
        
        // Texto inicial como placeholder
        countLimitField.setText("Ingrese cantidad");

        // Listener para manejar el placeholder
        countLimitField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Eliminar el texto del placeholder al ganar el foco
                if (countLimitField.getText().equals("Ingrese cantidad")) {
                    countLimitField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Si el campo está vacío al perder el foco, restablecer el placeholder
                if (countLimitField.getText().isEmpty()) {
                    countLimitField.setText("Ingrese cantidad");
                }
            }
        });

        // Botón de inicio
        startButton = new JButton("Iniciar");
        startButton.setBounds(250, 300, 100, 25);
        add(startButton);

        // Botón de detener
        stopButton = new JButton("Detener");
        stopButton.setBounds(360, 300, 100, 25);
        stopButton.setEnabled(false); // Deshabilitado al inicio
        add(stopButton);

        // Botón de limpiar
        clearButton = new JButton("Limpiar");
        clearButton.setBounds(140, 300, 100, 25);
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
        countLimitField.setText("Ingrese cantidad");  // Restablecer el placeholder
    }

    public static void main(String[] args) {
        PrintWindow window = new PrintWindow();
        window.setVisible(true);
    }
}
