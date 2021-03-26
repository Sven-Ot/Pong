package src;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import static src.Movement.MoveBall;
import static src.CheckRules.CheckOutOfMap;

public final class GameWindow{

    public JFrame window = new JFrame("Just Pong - Game");
    public double fieldHeight;
    public double[] resolution = GetResolution();
    public int highscoreSize = 80;
    public JLabel highscorePlayer;
    public JLabel highscoreBot;
    public int[] direction = {1,1}; //xDirection and yDirection
    private final Ball ball = new Ball(resolution);
    Timer timer = new Timer();
    PlayerType bottype    = PlayerType.BOT;
    PlayerType playertype = PlayerType.HUMAN;
    private Bot bot = new Bot(resolution,bottype);
    private Player player = new Player(resolution,playertype);
    PaddleMovement paddleMovement = new PaddleMovement(player,fieldHeight);
    public GameWindow(){ 
        window.add(bot);  
        window.add(player);
        window.add(ball);
        window.add(DrawScorePlayer(resolution,player.getScore()));
        window.add(DrawScoreBot(resolution,bot.getScore()));
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
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                direction = MoveBall(ball,bot,player,resolution,direction,timer,fieldHeight);
                double middleX = resolution[0] / 2 - ball.getBallSize() /2;
                double middleY = resolution[1] / 2 - ball.getBallSize() /2;
                Rectangle ballOutline = ball.getBounds();
                OutOfMap outofmap = CheckOutOfMap(ballOutline, player.getPlayerBorder(),bot.getPlayerBorder());
                if(outofmap != OutOfMap.FALSE ){
                     if(outofmap == OutOfMap.LEFT){
                         bot.setScore(bot.getScore() + 1);
                         highscoreBot.setText(Integer.toString(bot.getScore()));
                         ball.setLocation((int)middleX,(int)middleY);
                         direction[0] *= -1;
                     }else{
                         player.setScore(player.getScore() + 1);
                         highscorePlayer.setText(Integer.toString(player.getScore()));
                         ball.setLocation((int)middleX,(int)middleY);
                         direction[0] *= -1;
                     }
                     // timer.cancel();
                 }
            }
        };
        timer.schedule(tt, 1, 3);
    }
    public JLabel DrawScorePlayer(double[]  resolution,int playerScore){
        double x = resolution[0] / 4 - highscoreSize /2;
        double y = resolution[1] / 16 - highscoreSize /2;
        
        highscorePlayer = new JLabel();
        highscorePlayer.setBounds((int)x,(int)y,highscoreSize*2,highscoreSize);   
        highscorePlayer.setText(Integer.toString(playerScore));
        highscorePlayer.setFont(new Font("Arial", 0, 100));
        return highscorePlayer;
    }
    public JLabel DrawScoreBot(double[]  resolution,int botScore){
        double x = resolution[0] / 4 * 3 - highscoreSize /2;
        double y = resolution[1] / 16 - highscoreSize /2;
        highscoreBot = new JLabel();
        highscoreBot.setBounds((int)x,(int)y,highscoreSize*2,highscoreSize);   
        highscoreBot.setText(Integer.toString(botScore));
        highscoreBot.setFont(new Font("Arial", 0, 100));
        return highscoreBot;
    }
    
    private double[] GetResolution(){
        Rectangle windowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        fieldHeight = windowBounds.height;
        return new double[] {windowBounds.width, windowBounds.height};
    }
}