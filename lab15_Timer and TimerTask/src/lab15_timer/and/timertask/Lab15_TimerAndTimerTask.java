
package lab15_timer.and.timertask;

import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Lab15_TimerAndTimerTask {

    public static void main(String[] args) {
        // Crear y mostrar la ventana
        PrintWindow window = new PrintWindow();
        window.setVisible(true);

        // Instanciar el Timer
        Timer timer = new Timer();

        // Instancia de objetos Remainder con la ventana
        Remainder reminder1 = new Remainder("Primer intento", window);
        Remainder reminder2 = new Remainder("Segundo intento", window);
        Remainder reminder3 = new Remainder("Tercer intento", window);

        // Programación de las tareas con el Timer
        timer.schedule(reminder1, 0);           // Inmediato
        timer.schedule(reminder2, 30000);       // 30 segundos
        timer.schedule(reminder3, 120000);      // 2 minutos
    }
}

// Clase para la ventana que muestra los mensajes
class PrintWindow extends JFrame {
    private JTextArea displayArea;

    public PrintWindow() {
        setTitle("Print Remainder Messages");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        // Área de texto para mostrar los mensajes
        displayArea = new JTextArea();
        displayArea.setEditable(false);

        // Scroll para el área de texto
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(20, 20, 450, 300);
        add(scrollPane);
    }

    // Método para agregar un mensaje al área de texto
    public void appendMessage(String message) {
        displayArea.append(message + "\n");
    }
}
