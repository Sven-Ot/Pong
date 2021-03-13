package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import jdk.internal.net.http.common.Pair;

public class GameWindow{
    public JFrame window; 
    public GameWindow(Player player, Bot bot){
        double[] resolution = GetResolution();
        window = new JFrame("Pong");    
        JPanel rightPanel =new JPanel();  
        rightPanel.setBounds(200,10,15,200);    
        rightPanel.setBackground(Color.black);  
        window.add(GetLeftPanel(resolution));  
        window.add(GetLeftPanel(resolution));  
        window.add(GetRightPanel(resolution));
        window.setSize((int)resolution[0],(int)resolution[1]);    
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
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
        JPanel leftPanel =new JPanel();  
        leftPanel.setBounds((int)topLeft,(int)mid,(int)width, (int )panelHeight);    
        leftPanel.setBackground(Color.black);  
        return leftPanel;
    }
    
    private JPanel GetRightPanel(double[] resolution){
        double width = resolution[0] - resolution[0] * 0.99;
        double topRight = resolution[0] - resolution[0] / 100 *2 - width;
        double panelHeight = resolution[1] * 0.3;
        double mid = resolution[1] / 2 - panelHeight / 2;
        JPanel leftPanel =new JPanel();  
        leftPanel.setBounds((int)topRight,(int)mid,(int)width, (int )panelHeight);    
        leftPanel.setBackground(Color.black);  
        return leftPanel;
    }
}