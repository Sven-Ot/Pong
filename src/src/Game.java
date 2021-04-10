package src;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import static src.Movement.MoveBall;
import static src.CheckRules.CheckIfBallIsBehindPaddle;

public final class Game{
    public double[] resolution = GetResolution();
    public double fieldHeight;
    public int[] direction = {1,1}; //xDirection and yDirection
    private final Ball ball = new Ball(resolution);
    private Timer timer = new Timer();
    private PlayerType bottype    = PlayerType.BOT;
    private PlayerType playertype = PlayerType.HUMAN;
    private Bot bot = new Bot(resolution,bottype);
    private Player player = new Player(resolution,playertype);
    PaddleMovement paddleMovement = new PaddleMovement(player,fieldHeight);
    
    public Game(){
        // Create Window
        Window window = new Window(resolution,bot,player,ball,paddleMovement);
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                //TODO: Needs to be outsourced
                direction = MoveBall(ball,bot,player,resolution,direction,timer,fieldHeight);
                Rectangle ballOutline = ball.getBounds();
                OutOfMapType outOfMap = CheckIfBallIsBehindPaddle(ballOutline, player.getPlayerBorder(),bot.getPlayerBorder());
                if(outOfMap == OutOfMapType.InField )
                    return;
                
                if(outOfMap == OutOfMapType.LEFT){
                    bot.setScore(bot.getScore() + 1);
                    window.BotHighScore.setText(Integer.toString(bot.getScore()));
                }else{
                    player.setScore(player.getScore() + 1);
                    window.PlayerHighScore.setText(Integer.toString(player.getScore()));
                }
                
                double middleX = resolution[0] / 2 - ball.getBallSize() /2;
                double middleY = resolution[1] / 2 - ball.getBallSize() /2;
                ball.setLocation((int)middleX,(int)middleY);
                direction[0] *= -1;
                // timer.cancel();          
            }
        };
        timer.schedule(tt, 1, 3);
    }
    private double[] GetResolution(){
        Rectangle windowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        fieldHeight = windowBounds.height;
        return new double[] {windowBounds.width, windowBounds.height};
    }
}