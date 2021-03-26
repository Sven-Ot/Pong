/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Arthur
 */
public class PaddleMovement implements KeyListener {
    private Player player;
    private double fieldHeight;
    
    public PaddleMovement(Player player, double fieldHeight){
        this.player = player;
        this.fieldHeight = fieldHeight;
    
    }
    @Override
    public void keyPressed(KeyEvent e) {
        Rectangle leftPanelBounds = player.getBounds();
        int inputKey = e.getKeyCode();

        if(inputKey == KeyEvent.VK_W && leftPanelBounds.y >1)
            player.setLocation(leftPanelBounds.x,leftPanelBounds.y-30);
        if(inputKey == KeyEvent.VK_S && leftPanelBounds.y + fieldHeight * 0.3  < fieldHeight-30) //number needs to removed here later
            player.setLocation(leftPanelBounds.x,leftPanelBounds.y+30);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
