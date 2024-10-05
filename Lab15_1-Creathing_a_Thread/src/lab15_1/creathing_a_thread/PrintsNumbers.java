/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab15_1.creathing_a_thread;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class PrintsNumbers implements Runnable{
    
    private boolean keepGoing;
    
    public  PrintsNumbers(){
         keepGoing = true;
    }
    @Override
    public void run() {
     int contador=0;
    while (keepGoing){
        contador = contador + 1;
        System.out.println("el numero es:" + contador);
         try {
             Thread.sleep(1000);
         } catch (InterruptedException ex) {
             Logger.getLogger(PrintsNumbers.class.getName()).log(Level.SEVERE, null, ex);
         }
         }
} 
    public void stopPrinting(){
         keepGoing = false;
    }
    
}
