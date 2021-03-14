package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class GameWindow implements KeyListener{
    public JFrame window; 
    public JPanel leftPanel; 
    public JPanel rightPanel;
    public JPanel ball;
    public double fieldHeight;
    public double[] resolution;
    public int ballSize = 20;
    public int xDirection = 1;
    public int yDirection = 1;
    
    public GameWindow(Player player, Bot bot){
        ImageIcon img = new ImageIcon("images/PongImg.png");
        resolution = GetResolution();
        window = new JFrame("Pong - Game");    
        window.add(GetLeftPanel(resolution));  
        window.add(GetRightPanel(resolution));
        window.add(GetBall(resolution));
        window.setSize((int)resolution[0],(int)resolution[1]);    
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        window.getContentPane().setBackground(Color.black);
        window.addKeyListener(this);
        window.setIconImage(img.getImage());
        
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            public void run() {
                MoveBall();
            }
        };
        timer.schedule(tt, 1, 3);
    }
    
    public void MoveBall()
    {
        Rectangle ballBounds = ball.getBounds();
        CheckBoundsToBorder(ballBounds);
        CheckBallConnectsToPanel(ballBounds);
        ball.setLocation(ballBounds.x+xDirection,ballBounds.y+yDirection);
    }
    
    public void CheckBoundsToBorder(Rectangle ballBounds){
        if(ballBounds.x > resolution[0] - ballSize) 
            xDirection *= -1;   
        if(ballBounds.y > resolution[1] - ballSize) 
            yDirection *= -1;  
        if(ballBounds.x < 0) 
            xDirection *= -1;
        if(ballBounds.y < 0)
            yDirection *= -1; 
    }
    
    public void CheckBallConnectsToPanel(Rectangle ballBounds){   
        //Doesn't work perfect
        Rectangle leftPanelBounds = leftPanel.getBounds();
        if(ballBounds.x < leftPanelBounds.x+ leftPanelBounds.width)
            if(leftPanelBounds.y < ballBounds.y)
                if(leftPanelBounds.y + leftPanelBounds.height > ballBounds.y + ballSize ) {
                    xDirection *= -1;
                }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        Rectangle leftPanelBounds = leftPanel.getBounds();
        int inputKey = e.getKeyCode();
        
        if(inputKey == KeyEvent.VK_W && leftPanelBounds.y >1)
            leftPanel.setLocation(leftPanelBounds.x,leftPanelBounds.y-30);
        if(inputKey == KeyEvent.VK_S && leftPanelBounds.y + fieldHeight * 0.3  < fieldHeight-30) //number needs to removed here later
            leftPanel.setLocation(leftPanelBounds.x,leftPanelBounds.y+30);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        return;
    }
    
    private double[] GetResolution(){
    Rectangle windowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    fieldHeight = windowBounds.height;
    return new double[] {windowBounds.width, windowBounds.height};
    }
    
    private JPanel GetLeftPanel(double[] resolution){
        double topLeft = resolution[0] / 100 *2;
        double width = resolution[0] - resolution[0] * 0.99;
        double panelHeight = resolution[1] * 0.3;
        double mid = resolution[1] / 2 - panelHeight / 2;
        leftPanel = new JPanel();  
        leftPanel.setBounds((int)topLeft,(int)mid,(int)width, (int )panelHeight);    
        leftPanel.setBackground(Color.white);  
        return leftPanel;
    }
    
    private JPanel GetRightPanel(double[] resolution){
        double width = resolution[0] - resolution[0] * 0.99;
        double topRight = resolution[0] - resolution[0] / 100 *2 - width;
        double panelHeight = resolution[1] * 0.3;
        double mid = resolution[1] / 2 - panelHeight / 2;
        rightPanel = new JPanel(); 
        rightPanel.setBounds((int)topRight,(int)mid,(int)width, (int )panelHeight);    
        rightPanel.setBackground(Color.white);  
        return rightPanel;
    }

    private JPanel GetBall(double[] resolution) {
      double x = resolution[0] / 2 - ballSize /2;
      double y = resolution[1] / 2 - ballSize /2;
      ball = new JPanel(); 
      ball.setBounds((int)x,(int)y,20,20);    
      ball.setBackground(Color.white);  
      //Get middle of the field
      return ball;
    }
}