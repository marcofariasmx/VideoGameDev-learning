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

    private int width;
    private int height;
    private Game game;
    private boolean destroyed;
    
    public Brick(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        destroyed=false;
    }
    boolean isDestroyed() {
        
        return destroyed;
    }

    void setDestroyed(boolean val) {
        
        destroyed = val;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    @Override
    public void tick() {
    
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.brick, getX(), getY(), getWidth(), getHeight(), null);
    }
}
