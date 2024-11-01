package lab15_1.creathing_a_thread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

//Greizel sension 8-940-530
//nota 100
//fecha 18/10/24

public class PrintWindow extends JFrame {
    private JTextField countLimitField;
    private JButton startButton, stopButton, pauseButton, clearButton;
    private JTextArea displayArea;
    private JScrollPane scrollPane;  // Panel de desplazamiento
    private PrintsNumbers printsNumbers;
    private Thread thread;

    public static void main(String[] args) {
        PrintWindow window = new PrintWindow();
        window.setVisible(true);
    }

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
                if (countLimitField.getText().equals("Ingrese cantidad")) {
                    countLimitField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
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
        stopButton.setEnabled(false);
        add(stopButton);
        
        // Botón de pausar
        pauseButton = new JButton("Pausar");
        pauseButton.setBounds(140, 300, 100, 25);
        pauseButton.setEnabled(false);
        add(pauseButton);

        // Botón de limpiar
        clearButton = new JButton("Limpiar");
        clearButton.setBounds(20, 300, 100, 25);
        add(clearButton);

        // Área de texto para mostrar el conteo
        displayArea = new JTextArea();
        displayArea.setEditable(false);

        // Crear el JScrollPanel para agregar el área de texto con scroll
        scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(20, 100, 440, 200);
        add(scrollPane);

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

        // Acción del botón pausar
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseCounting();
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

            startButton.setEnabled(false);
            pauseButton.setEnabled(true);
            stopButton.setEnabled(true);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido.");
        }
    }

    // Método para detener el conteo
    private void stopCounting() {
        if (printsNumbers != null) {
            printsNumbers.stopPrinting();
            startButton.setEnabled(true);
            pauseButton.setEnabled(false);
            stopButton.setEnabled(false);
        }
    }

    // Método para pausar el conteo
    private void pauseCounting() {
        if (printsNumbers != null) {
            printsNumbers.togglePause();
        }
    }

    // Método para limpiar el área de texto
    private void clearDisplay() {
        displayArea.setText("");
        countLimitField.setText("Ingrese cantidad");
    }
}

// Clase que maneja la impresión de números
class PrintsNumbers implements Runnable {
    private int limit;
    private JTextArea displayArea;
    private volatile boolean isPaused = false;
    private volatile boolean isStopped = false;

    public PrintsNumbers(int limit, JTextArea displayArea) {
        this.limit = limit;
        this.displayArea = displayArea;
    }

    @Override
    public void run() {
        for (int i = 1; i <= limit; i++) {
            if (isStopped) break;
            while (isPaused) {
                try {
                    Thread.sleep(100);  // Esperar mientras está en pausa
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            displayArea.append(i + "\n");
            try {
                Thread.sleep(500);  // Simulación de tiempo entre números
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Método para detener el conteo
    public void stopPrinting() {
        isStopped = true;
    }

    // Método para pausar o reanudar el conteo
    public void togglePause() {
        isPaused = !isPaused;
    }
}
