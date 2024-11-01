package lab15_1.creathing_a_thread;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintsNumbers implements Runnable {
    private volatile boolean keepGoing;
    private volatile boolean isPaused; // Bandera para pausar
    private int limit;
    private JTextArea displayArea;

    public PrintsNumbers(int limit, JTextArea displayArea) {
        this.keepGoing = true;
        this.isPaused = false; // Inicialmente no está en pausa
        this.limit = limit;
        this.displayArea = displayArea;
    }

    @Override
    public void run() {
        int contador = 0;
        while (keepGoing && contador < limit) {
            synchronized (this) {
                while (isPaused) {
                    try {
                        wait(); // Esperar mientras esté en pausa
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        return; // Salir del hilo si es interrumpido
                    }
                }
            }

            contador++;
            displayArea.append("El número es: " + contador + "\n");

            try {
                Thread.sleep(1000); // Esperar 1 segundo entre números
            } catch (InterruptedException ex) {
                Logger.getLogger(PrintsNumbers.class.getName()).log(Level.SEVERE, null, ex);
                Thread.currentThread().interrupt();
                return; // Salir del hilo si es interrumpido
            }
        }
    }

    // Método para detener el conteo
    public void stopPrinting() {
        keepGoing = false;
        resumePrinting(); // Asegurarse de salir del estado de pausa
    }

    // Método para pausar el conteo
    public synchronized void pausePrinting() {
        isPaused = true;
    }

    // Método para reanudar el conteo
    public synchronized void resumePrinting() {
        isPaused = false;
        notify(); // Notificar para reanudar el hilo
    }

    // Método para alternar entre pausa y reanudar
    public synchronized void togglePause() {
        if (isPaused) {
            resumePrinting();
        } else {
            pausePrinting();
        }
    }
}
