package src;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public final class Window {
    public JFrame window = new JFrame("Just Pong - Game");
    public JLabel PlayerHighScore;
    public JLabel BotHighScore;
    public Window(double[] resolution, Bot bot,Player player,Ball ball,PaddleMovement paddleMovement){
        this.PlayerHighScore = DrawScore(resolution,player.getScore(),player.getHighscore(),player.getPlayertype());
        this.BotHighScore    = DrawScore(resolution,bot.getScore(),bot.getHighscore(),bot.getPlayertype());
        window.add(bot);  
        window.add(player);
        window.add(ball);
        window.add(this.PlayerHighScore);
        window.add(this.BotHighScore);
        window.setSize((int)resolution[0],(int)resolution[1]);    
        window.setResizable(false);
        window.setBackground(Color.black);
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        window.getContentPane().setBackground(Color.black);
        window.addKeyListener(paddleMovement);
        window.setIconImage(new ImageIcon("images/PongImg.png").getImage());
    }

    public JLabel DrawScore(double[]  resolution,int score,int highscoreSize,PlayerType playertype){
        double x;
        if(playertype == PlayerType.BOT){
            x = resolution[0] / 4 * 3 - highscoreSize /2;
        }else{
            x = resolution[0] / 4 - highscoreSize /2;
        }
        double y = resolution[1] / 16 - highscoreSize /2;
        
        JLabel highScore = new JLabel();
        highScore.setBounds((int)x,(int)y,highscoreSize*2,highscoreSize);   
        highScore.setText(Integer.toString(score));
        highScore.setFont(new Font("Arial", 0, 100));
        return highScore;
    }
}
