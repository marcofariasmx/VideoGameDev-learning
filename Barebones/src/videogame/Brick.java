/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author antoniomejorado
 */
public class Brick extends Item{

    private int width;//width of brick
    private int height;//height of brick
    private Game game;
    private boolean destroyed;//to know if the current brick has been destroyed
    private Animation anbrickchange;//animation of bricks
    
    
    public Brick(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        destroyed=false;
        this.anbrickchange= new Animation(Assets.brickchange,100);
    }
    //get the value of destroyed
    boolean isDestroyed() {
        
        return destroyed;
    }
    ///set the value of destroyed
    void setDestroyed(boolean val) {
        
        destroyed = val;
    }
    //get width of brick
    public int getWidth() {
        return width;
    }
    //get height of brick
    public int getHeight() {
        return height;
    }
    //getx OF BRICK
    public int getX() {
        return x;
    }
    //get y of brick
    public int getY() {
        return y;
    }
    //set Width to  width
    public void setWidth(int width) {
        this.width = width;
    }
    //set height to height
    public void setHeight(int height) {
        this.height = height;
    }


    @Override
    //change the animation of brick
    public void tick() {
        this.anbrickchange.tick();
    }
    //render the current saved animation of brick
    @Override
    public void render(Graphics g) {
        g.drawImage(anbrickchange.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
