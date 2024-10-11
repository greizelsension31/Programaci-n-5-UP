package com.mycompany.race;

import javax.swing.*;
import java.awt.*;

public class Base extends JFrame {
    Panel panel;
    public Base(){
        setTitle("Carrera");
        setBounds(100, 100, 1000, 500);
        setVisible(true);
        Container contetpane = getContentPane();
        panel = new Panel();
        contetpane.add(panel);
    }

    public class Panel extends JPanel {

        public Panel() {
            this.setSize(400, 280);
        }

        public void paint(Graphics g) {
            Dimension tamanio = getSize();
            ImageIcon imagenFondo = new ImageIcon(getClass().getResource("meta.jpg"));
            g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paintComponent(g);
        }
    }

}