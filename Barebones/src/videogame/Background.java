/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author armandoroque
 */
public class Background {
    
    protected int width;    // to store width
    protected int height;   // to store height
    private Game game; // the game to which it belongs
    private Animation animationPlay; // animation when playing
    private Animation animationPause; // animation when paused
    
    public Background(int width, int height, Game game) {
        this.width = width;
        this.height = height;
        this.game = game;
        
        // animation for running game
        this.animationPlay = new Animation(Assets.background, 100);
    }
    
    /**
     * get width
     * @return width
     */
    public int getWidth() {
        return width;
    }
    
    /**
      * get height
      * @return height
      */
    public int getHeight() {
        return height;
    }
    
    /**
     * ticking the enemy for movement in each frame
     */
    public void tick(int status) {
        if (status == 1) {
            this.animationPlay.tick(); // tick animation of running game
        }
    }
    
    public void render(Graphics g, int status) {
        if (status == 0) { // game is running
            g.drawImage(animationPlay.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null); // paint animation of running game
        }
        else if (status == 2) { // game is paused
            g.drawImage(animationPause.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null); // paintanimation of paused game
            
            
        }
        else if (status == 1) { // game over
            g.drawImage(Assets.gameover, 0, 0, getWidth(), getHeight(), null); // paint game over screen
        }
        
    }
    
}
