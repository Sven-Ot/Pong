package src;

import java.awt.Point;
import java.awt.Rectangle;

public class CheckRules {
    
    private static boolean isOverlapping(
            Point topLeftObject1, 
            Point bottomRightObject1, 
            Point topLeftObject2, 
            Point bottomRightObject2) 
    {
        int leftX   = Math.max( topLeftObject1.x, topLeftObject2.x );
        int rightX  = Math.min( bottomRightObject1.x, bottomRightObject2.x );
        int topY    = Math.max( topLeftObject1.y, topLeftObject2.y );
        int bottomY = Math.min( bottomRightObject1.y, bottomRightObject2.y );

        return leftX < rightX && topY < bottomY;
    }
    
    public static boolean CheckBallConnectsToPaddle(Rectangle Object1,Rectangle Object2){
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
                
        return isOverlapping(topLeftObject1, bottomRightObject1, topLeftObject2, bottomRightObject2); 
    }

    public static OutOfMapType CheckIfBallIsBehindPanel(Rectangle ballOutline, double leftPlayerBorder, double rightPlayerBorder ) {
        if(ballOutline.x < leftPlayerBorder - ballOutline.width*5)
            return OutOfMapType.LEFT;
        else if(ballOutline.x + ballOutline.width > rightPlayerBorder + ballOutline.width*5) 
            return OutOfMapType.RIGHT;
        return OutOfMapType.InField;
    }
    public static boolean CheckBoundsToUpperAndLowerBorder(Rectangle ballBounds,double[] resolution, Ball ball ){
        return ballBounds.y > resolution[1] - ball.getBallSize() || ballBounds.y < 0;
    }
    
}
