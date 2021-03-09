package src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Fenster extends JFrame implements Runnable {
    public Fenster(){
        ImageIcon img = new ImageIcon("images/PongImg.png");
        this.setSize(1000 , 700);
        this.setTitle("Pong");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.black);
        this.setIconImage(img.getImage());

    }

    @Override
    public void run() {
        while (true){
            // TODO: 3/4/2021
        }
    }
}
