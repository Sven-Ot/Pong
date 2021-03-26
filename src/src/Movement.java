package src;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Timer;
import static src.CheckRules.CheckBallConnectsToPanel;
import static src.CheckRules.CheckBoundsToBorder;

public class Movement {

    /**
     *
     * @param ball
     * @param rightPaddle
     * @param leftPaddle
     * @param resolution
     * @param direction
     * @param timer
     * @param fieldHeight
     * @return new direction
     */
    public static int[] MoveBall(Ball ball, Paddle rightPaddle,Paddle leftPaddle,double[] resolution,int [] direction,Timer timer,double fieldHeight){
        //double middleX = resolution[0] / 2 - ball.getBallSize() /2;
        //double middleY = resolution[1] / 2 - ball.getBallSize() /2;
        
        Rectangle ballOutline = ball.getBounds();
        Rectangle panelLeftOutline = leftPaddle.getBounds();
        Rectangle panelRightOutline = rightPaddle.getBounds();
        
        if(CheckBoundsToBorder(ballOutline,resolution, ball)){
            direction[1] *= -1;
        }
        
        if(CheckBallConnectsToPanel(ballOutline,panelLeftOutline)){
            ball.setBackground(Color.white);
            direction[0] *= -1;
        }
        if(CheckBallConnectsToPanel(ballOutline,panelRightOutline)){
            ball.setBackground(Color.red);
            direction[0] *= -1;
        }
        BotMovement(ballOutline,rightPaddle,fieldHeight);
        ball.setLocation(ballOutline.x+direction[0],ballOutline.y+direction[1]);
        
        return direction;
    }

    public static void BotMovement(Rectangle ballBounds,Paddle Paddle,double fieldHeight){
        Rectangle rightPanelBounce = Paddle.getBounds();
        
        if(rightPanelBounce.y + rightPanelBounce.height / 2 > 
            ballBounds.y +  ballBounds.height / 2 && rightPanelBounce.y >0)
            Paddle.setLocation(rightPanelBounce.x, rightPanelBounce.y -3);
        else 
            if(rightPanelBounce.y + fieldHeight * 0.3  < fieldHeight)
                Paddle.setLocation(rightPanelBounce.x, rightPanelBounce.y +3);
    }
    
}
