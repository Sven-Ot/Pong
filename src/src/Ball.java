package src;

import java.awt.Color;
import javax.swing.JPanel;

public class Ball extends JPanel{
    int positionX;

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getFarbe() {
        return farbe;
    }

    public void setFarbe(int farbe) {
        this.farbe = farbe;
    }

    public int getBallSize() {
        return ballSize;
    }

    public void setBallSize(int ballSize) {
        this.ballSize = ballSize;
    }
    int farbe;
    int ballSize = 20;
    public JPanel ball;

    Ball(double[] resolution){
        double x = resolution[0] / 2 - ballSize /2;
        double y = resolution[1] / 2 - ballSize /2;

        super.setBounds((int)x,(int)y,20,20);   
        super.setBackground(Color.white);
    }
}
