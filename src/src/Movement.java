package src;

import java.awt.Rectangle;
import java.util.Timer;
import static src.CheckRules.CheckBoundsToUpperAndLowerBorder;
import static src.CheckRules.CheckBallConnectsToPaddle;

public class Movement {
    public static int[] MoveBall(Ball ball, Paddle rightPaddle,Paddle leftPaddle,double[] resolution,int [] direction,Timer timer,double fieldHeight){      
        Rectangle ballOutline = ball.getBounds();
        Rectangle paddleLeftOutline = leftPaddle.getBounds();
        Rectangle paddleRightOutline = rightPaddle.getBounds();
        
        if(CheckBoundsToUpperAndLowerBorder(ballOutline,resolution, ball))
            direction[1] *= -1;
        
        if(CheckBallConnectsToPaddle(ballOutline,paddleLeftOutline) || CheckBallConnectsToPaddle(ballOutline,paddleRightOutline))
            direction[0] *= -1;

        MoveBotToCorrectPosition(ballOutline,rightPaddle,fieldHeight);
        ball.setLocation(ballOutline.x+direction[0],ballOutline.y+direction[1]);
        
        return direction;
    }

    public static void MoveBotToCorrectPosition(Rectangle ballBounds,Paddle Paddle,double fieldHeight){
        Rectangle rightPaddleBounce = Paddle.getBounds();
        
        if(rightPaddleBounce.y + rightPaddleBounce.height / 2 > 
            ballBounds.y +  ballBounds.height / 2 && rightPaddleBounce.y >0)
            Paddle.setLocation(rightPaddleBounce.x, rightPaddleBounce.y -3);

        else 
            if(rightPaddleBounce.y + fieldHeight * 0.3  < fieldHeight)
                Paddle.setLocation(rightPaddleBounce.x, rightPaddleBounce.y +3);

    }
    
}
