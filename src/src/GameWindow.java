package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow implements KeyListener{
    public JFrame window; 
    public JPanel leftPanel; 
    public JPanel rightPanel;
    public GameWindow(Player player, Bot bot){
        ImageIcon img = new ImageIcon("images/PongImg.png");
        double[] resolution = GetResolution();
        window = new JFrame("Pong");    
        rightPanel = new JPanel();  
        leftPanel = new JPanel();  
        rightPanel.setBounds(200,10,15,200);    
        rightPanel.setBackground(Color.black);  
        leftPanel = GetLeftPanel(resolution);
        window.add(GetLeftPanel(resolution));  
        rightPanel = GetRightPanel(resolution);
        window.add(rightPanel);
        window.setSize((int)resolution[0],(int)resolution[1]);    
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        window.getContentPane().setBackground(Color.black);
        window.addKeyListener(this);
        window.setIconImage(img.getImage());
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        //TODO: Disable moving out of field
        Rectangle rec = leftPanel.getBounds();
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W)
            leftPanel.setLocation(rec.x,rec.y-5);
        if(key == KeyEvent.VK_S)
          leftPanel.setLocation(rec.x,rec.y+5);
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
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return new double[] {screenSize.getWidth(), screenSize.getHeight()};
    }
    
    private JPanel GetLeftPanel(double[] resolution){
        double topLeft = resolution[0] / 100 *2;
        double width = resolution[0] - resolution[0] * 0.99;
        double panelHeight = resolution[1] * 0.3;
        double mid = resolution[1] / 2 - panelHeight / 2;
        leftPanel.setBounds((int)topLeft,(int)mid,(int)width, (int )panelHeight);    
        leftPanel.setBackground(Color.white);  
        return leftPanel;
    }
    
    private JPanel GetRightPanel(double[] resolution){
        double width = resolution[0] - resolution[0] * 0.99;
        double topRight = resolution[0] - resolution[0] / 100 *2 - width;
        double panelHeight = resolution[1] * 0.3;
        double mid = resolution[1] / 2 - panelHeight / 2;
        rightPanel.setBounds((int)topRight,(int)mid,(int)width, (int )panelHeight);    
        rightPanel.setBackground(Color.white);  
        return rightPanel;
    }

}