/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package videogame;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author antoniomejorado
 */
public abstract class Item {
    protected int x;        // to store x position
    protected int y;        // to store y position
    protected int width;
    protected int height;
    
    /**
     * Set the initial values to create the item
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     */
    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get x value
     * @return x 
     */
    public int getX() {
        return x;
    }

    /**
     * Get y value
     * @return y 
     */
    public int getY() {
        return y;
    }

    /**
     * Set x value
     * @param x to modify
     */
    public void setX(int x) {
        this.x = x;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Set y value
     * @param y to modify
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * To update positions of the item for every tick
     */
    public abstract void tick();
    
    /**
     * To paint the item
     * @param g <b>Graphics</b> object to paint the item
     */
    public abstract void render(Graphics g);
    
    public boolean collision(Object o){
        boolean bStatus= false; //assumo no collision
        if (o instanceof Item){
            Rectangle rThis= new Rectangle (getX(), getY(), getWidth(),getHeight());
            Item i=(Item) o;
            Rectangle rOther= new Rectangle (i.getX(), i.getY(), i.getWidth(),i.getHeight());
            bStatus=rThis.intersects(rOther);
        }
        return bStatus;
    }
    public Rectangle getRect(int x, int y, int width, int height) {

        return new Rectangle(x, y, width, height);
    }

}
