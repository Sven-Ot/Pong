
package src;

import java.awt.Color;
import javax.swing.JPanel;

public class Paddle extends JPanel {
    double width;
    double panelHeight;
    double mid; 
    double top;
    double PlayerBorder;
    public int score = 0;
    public int highscore = 80;
    PlayerType playertype;

    public PlayerType getPlayertype() {
        return playertype;
    }

    public void setPlayertype(PlayerType playertype) {
        this.playertype = playertype;
    }
    public int getHighscore() {
        return highscore;
    }

    Paddle(double[] resolution, PlayerType playertype){
       this.width = resolution[0] - resolution[0] * 0.99;
       this.panelHeight = resolution[1] * 0.3;
       this.mid = resolution[1] / 2 - panelHeight / 2; 
       this.playertype = playertype;
       
       if(playertype == PlayerType.HUMAN){
           this.top = resolution[0] / 50 *2;
           this.PlayerBorder = top;
       }else{
           this.top = resolution[0] - resolution[0] / 50 *2 - width;
           this.PlayerBorder = top + width;
       }
       super.setBounds((int)this.top,(int)this.mid,(int)this.width, (int )this.panelHeight);    
       super.setBackground(Color.white);
    }
    
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getPlayerBorder() {
        return PlayerBorder;
    }  
}
