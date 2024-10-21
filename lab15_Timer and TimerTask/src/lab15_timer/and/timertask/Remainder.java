
package lab15_timer.and.timertask;

import java.util.TimerTask;

public class Remainder extends TimerTask {
    private String message;
    private PrintWindow window; // Referencia a la ventana

    public Remainder(String message, PrintWindow window) {
        this.message = message;
        this.window = window;
    }

    @Override
    public void run() {
        // Mostrar el mensaje en la ventana
        window.appendMessage(message);
    }
}

