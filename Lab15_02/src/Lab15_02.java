
import java.awt .Image;
import static java.lang.Math.random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;

// Greizel sension 8-940-530
// nota 100 
// 14/10/24
public class Lab15_02 {
    public static void main(String[] args) {   
        SwingUtilities.invokeLater(new Runnable(){ 
            
        @Override   
        public void run(){
            new VentanaCarrera();
        }
        
        }); 
    }    
}

class Hilo implements Runnable {
    
    Thread t;
    String nombre;
    JLabel personaje;
    JLabel labeFinal;
    public static int lugar;
    
    public Hilo(String nombre, JLabel personaje, JLabel labeFinal){
       this.nombre = nombre;
       this.labeFinal = labeFinal;
       this.personaje = personaje;
       t = new Thread(this, nombre); // creamos el hilo y le mandamos los parámetros 
       t.start(); // lo ponemos a andar
    }

    @Override 
    public void run(){
        int retardo;
        try {
            lugar = 1;
            retardo = (int)(Math.random() * 15) + 1; // Ajustar Math.random()
            labeFinal.setVisible(false);
            personaje.setVisible(true);
            
            for(int i = 50; i <= 500; i++) {
                personaje.setLocation(i, personaje.getY());
                Thread.sleep(retardo);
            }
            personaje.setVisible(true);
            labeFinal.setVisible(true);
            labeFinal.setText(nombre + " ha llegado en lugar: " + lugar);
            lugar++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class VentanaCarrera extends JFrame {
  
    public VentanaCarrera() {
        super("Carrera de rápido furioso");
        JLabel Greizel, Bladimir, Pino;
        JButton botonIniciarCarrera;
        setSize(500, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        Image imagen_carro1_G = new ImageIcon("src/Imagenes/carro1.png").getImage();
        ImageIcon Icon_carro1 = new ImageIcon(imagen_carro1_G.getScaledInstance(50, 50, Image.SCALE_DEFAULT)); 
        Greizel = new JLabel();
        Greizel.setIcon(Icon_carro1);
        Greizel.setBounds(50, 50, 50, 50);

        Image imagen_carro2_B = new ImageIcon("src/Imagenes/carro2.png").getImage();
        ImageIcon Icon_carro2 = new ImageIcon(imagen_carro2_B.getScaledInstance(50, 50, Image.SCALE_DEFAULT)); 
        Bladimir = new JLabel();
        Bladimir.setIcon(Icon_carro2);
        Bladimir.setBounds(50, 120, 50, 50);

        Image imagen_carro3_P = new ImageIcon("src/Imagenes/carro3.png").getImage();
        ImageIcon Icon_carro3 = new ImageIcon(imagen_carro3_P.getScaledInstance(50, 50, Image.SCALE_DEFAULT)); 
        Pino = new JLabel();
        Pino.setIcon(Icon_carro3);
        Pino.setBounds(50, 190, 50, 50);

        JLabel Greizel_pos = new JLabel();
        Greizel_pos.setBounds(150, 50, 350, 50);

        JLabel Bladimir_pos = new JLabel();
        Bladimir_pos.setBounds(150, 120, 350, 50);

        JLabel Pino_pos = new JLabel();
        Pino_pos.setBounds(150, 190, 350, 50);

        botonIniciarCarrera = new JButton("Iniciar Carrera");
        botonIniciarCarrera.setBounds(150, 260, 150, 50);

        botonIniciarCarrera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hilo tGreizel = new Hilo("Greizel", Greizel, Greizel_pos);
                Hilo tBladimir = new Hilo("Bladimir", Bladimir, Bladimir_pos);
                Hilo tPino = new Hilo("Pino", Pino, Pino_pos);
                tGreizel.t.start();
                tBladimir.t.start();
                tPino.t.start();
            }
        });

        panel.add(Greizel);
        panel.add(Greizel_pos);
        panel.add(Bladimir);
        panel.add(Bladimir_pos);
        panel.add(Pino);
        panel.add(Pino_pos);
        panel.add(botonIniciarCarrera);

        add(panel);
        setVisible(true);
    }
}
