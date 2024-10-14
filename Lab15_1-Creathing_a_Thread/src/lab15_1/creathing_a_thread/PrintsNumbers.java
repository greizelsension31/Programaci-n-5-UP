package lab15_1.creathing_a_thread;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintsNumbers implements Runnable {
    private boolean keepGoing;
    private int limit;
    private JTextArea displayArea;

    public PrintsNumbers(int limit, JTextArea displayArea) {
        this.keepGoing = true;
        this.limit = limit;
        this.displayArea = displayArea;
    }

    @Override
    public void run() {
        int contador = 0;
        while (keepGoing && contador < limit) {
            contador = contador + 1;
            displayArea.append("El número es: " + contador + "\n");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(PrintsNumbers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stopPrinting() {
        keepGoing = false;
    }
}
