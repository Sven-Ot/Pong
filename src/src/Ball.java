package src;

import java.awt.Color;
import javax.swing.JPanel;

public class Ball extends JPanel{
    private int _positionX;
    private int _color;
    private int _ballSize = 20;
    public JPanel ball;
    
    Ball(double[] resolution){
        double x = resolution[0] / 2 - _ballSize /2;
        double y = resolution[1] / 2 - _ballSize /2;

        super.setBounds((int)x,(int)y,20,20);   
        super.setBackground(Color.white);
    }

    public int getPositionX() {
        return _positionX;
    }

    public void setPositionX(int positionX) {
        _positionX = positionX;
    }

    public int getFarbe() {
        return _color;
    }

    public void setFarbe(int color) {
        _color = color;
    }

    public int getBallSize() {
        return _ballSize;
    }

    public void setBallSize(int ballSize) {
        _ballSize = ballSize;
    }
}
