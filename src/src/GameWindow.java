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
    public double leftPlayerBorder;
    public double rightPlayerBorder;    
    public int playerScore = 0;
    public int botScore = 0;
        
    public GameWindow(Player player, Bot bot){
        ImageIcon img = new ImageIcon("images/PongImg.png");
        resolution = GetResolution();
        window = new JFrame("Just Pong - Game");    
        window.add(GetLeftPanel(resolution));  
        window.add(GetRightPanel(resolution));
        window.add(GetBall(resolution));
        window.setSize((int)resolution[0],(int)resolution[1]);    
        window.setResizable(false);
        window.setBackground(Color.black);
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
        Rectangle ballOutline = ball.getBounds();

        CheckBoundsToBorder(ballOutline);
        CheckBallConnectsToLeftPanel(ballOutline);
        CheckBallConnectsToRightPanel(ballOutline);
        BotMovement(ballOutline);
        ball.setLocation(ballOutline.x+xDirection,ballOutline.y+yDirection);
        CheckBallOutOfMap(ballOutline);
    }
    
    private void CheckBallOutOfMap(Rectangle ballOutline) {
        double middleX = resolution[0] / 2 - ballSize /2;
        double middleY = resolution[1] / 2 - ballSize /2;
        
        if(ballOutline.x < leftPlayerBorder){
            botScore ++;
            ball.setLocation((int)middleX,(int)y);
            xDirection *= -1;   
        }
        else if(ballOutline.x + ballOutline.width > rightPlayerBorder) {
            playerScore ++;
            ball.setLocation((int)middleX,(int)y);
            xDirection *= -1;   
        }
            
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
    
    public void CheckBallConnectsToLeftPanel(Rectangle ballBounds){   
        //Doesn't work perfect
        Rectangle leftPanelBounds = leftPanel.getBounds();
        if(ballBounds.x < leftPanelBounds.x+ leftPanelBounds.width)
            if(leftPanelBounds.y < ballBounds.y)
                if(leftPanelBounds.y + leftPanelBounds.height > ballBounds.y + ballSize ) {
                    xDirection *= -1;
                }
    }
    public void CheckBallConnectsToRightPanel(Rectangle ballBounds){   
        //Doesn't work at all
        Rectangle RightPanelBounds = rightPanel.getBounds();
        if(ballBounds.x >= RightPanelBounds.x)
            if(RightPanelBounds.y < ballBounds.y)
                if(RightPanelBounds.y + RightPanelBounds.height > ballBounds.y + ballSize ) {
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
        double topLeft = resolution[0] / 50 *2;
        double width = resolution[0] - resolution[0] * 0.99;
        double panelHeight = resolution[1] * 0.3;
        double mid = resolution[1] / 2 - panelHeight / 2;
        leftPanel = new JPanel();  
        leftPanel.setBounds((int)topLeft,(int)mid,(int)width, (int )panelHeight);    
        leftPanel.setBackground(Color.white);  
        leftPlayerBorder = topLeft;
        return leftPanel;
    }
    
    private JPanel GetRightPanel(double[] resolution){
        double width = resolution[0] - resolution[0] * 0.99;
        double topRight = resolution[0] - resolution[0] / 50 *2 - width;
        double panelHeight = resolution[1] * 0.3;
        double mid = resolution[1] / 2 - panelHeight / 2;
        rightPanel = new JPanel(); 
        rightPanel.setBounds((int)topRight,(int)mid,(int)width, (int )panelHeight);    
        rightPanel.setBackground(Color.white);
        rightPlayerBorder = topRight + width;
        return rightPanel;
    }

    private JPanel GetBall(double[] resolution) {
      double x = resolution[0] / 2 - ballSize /2;
      double y = resolution[1] / 2 - ballSize /2;
      ball = new JPanel();
      ball.setBounds((int)x,(int)y,20,20);   
      ball.setBackground(Color.white);
      return ball;
    }
    
    public void BotMovement(Rectangle ballBounds){
        Rectangle rightPanelBounce = rightPanel.getBounds();
        
        if(rightPanelBounce.y + rightPanelBounce.height / 2 > 
            ballBounds.y +  ballBounds.height / 2 && rightPanelBounce.y >0)
            rightPanel.setLocation(rightPanelBounce.x, rightPanelBounce.y -3);
        else 
            if(rightPanelBounce.y + fieldHeight * 0.3  < fieldHeight)
                rightPanel.setLocation(rightPanelBounce.x, rightPanelBounce.y +3);
    }
    
        public void CheckBallConnectsToPanel(Rectangle Object1,Rectangle Object2){
        Point topLeftObject1 = new Point();
        topLeftObject1.y = Object1.y;
        topLeftObject1.x = Object1.x;

        Point bottomRightObject1 = new Point();
        bottomRightObject1.y = Object1.y+Object1.height;
        bottomRightObject1.x = Object1.x+Object1.width;

        Point topLeftObject2 = new Point();
        topLeftObject2.y = Object2.y;
        topLeftObject2.x = Object2.x;

        Point bottomRightObject2 = new Point();
        bottomRightObject2.y = Object2.y+Object2.height;
        bottomRightObject2.x = Object2.x+Object2.width;
              
//        topLeftObject1.x=0;
//        topLeftObject1.y=10; 
//        bottomRightObject1.x=10;
//        bottomRightObject1.y=0;
//        topLeftObject2.x=5;
//        topLeftObject2.y=5; 
//        bottomRightObject2.x=15;
//        bottomRightObject2.y=0;
                
        System.out.println(doOverlap(topLeftObject1, bottomRightObject1, topLeftObject2, bottomRightObject2)); 
    }
    public boolean doOverlap(
            Point topLeftObject1, 
            Point bottomRightObject1, 
            Point topLeftObject2, 
            Point bottomRightObject2) {

          if (
                topLeftObject1.x == bottomRightObject1.x || 
                topLeftObject1.y == bottomRightObject2.y || 
                topLeftObject2.x == bottomRightObject2.x || 
                topLeftObject2.y == bottomRightObject2.y
                ) 
            {
                return false;
            }
        if (topLeftObject1.x >= bottomRightObject2.x || topLeftObject2.x >= bottomRightObject1.x) {
            return false;
        }
 
        if (topLeftObject1.y <= bottomRightObject2.y || topLeftObject2.y <= bottomRightObject1.y) {
            return false;
        }
 
        ball.setBackground(Color.red);
        return true;
    }
}