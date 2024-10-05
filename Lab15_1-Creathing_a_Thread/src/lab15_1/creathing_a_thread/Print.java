
package lab15_1.creathing_a_thread;


public class Print {

    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hola grei");
        
        PrintsNumbers printsNumbers = new PrintsNumbers();
        //Thread thread = new PrintsNumbers();//
        printsNumbers.run();
    }
    
}
