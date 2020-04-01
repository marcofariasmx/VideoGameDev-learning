/**
 * Armando Roque Villasana A01138717
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package code;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author antoniomejorado
 */
public abstract class Item {
    protected int x;        // to store x position
    protected int y;        // to store y position
    protected int width;    // to store width
    protected int height;   // to store height
    
    /**
     * Set the initial values to create the item
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     * @param width <b>width</b> of the object
     * @param height <b>height</b> of the object
     */
    public Item(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
     * Set x value
     * @param x to modify
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set y value
     * @param y to modify
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * set width value
     * @param width to modify
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * set height value
     * @param height to modify
     */
    public void setHeight(int height) {
        this.height = height;
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
    
    /**
     * to determine if two objects collide or not
     * @param o object with which an item collides
     * @return status a <code>boolean</code> value to determine if a player catches a falling object
     */ 
    public boolean collision(Object o) {
        boolean status = false; // it does not collide
        if (o instanceof Item) {
            Rectangle rItem = new Rectangle(this.getX(), this.getY(), 
                    this.getWidth(), this.getHeight());
            Item i = (Item) o;
            Rectangle rOther = new Rectangle(i.getX(), i.getY(), i.getWidth(), 
                    i.getHeight());
            status = rItem.intersects(rOther);
        }
        return status;
    } 
    
    public boolean contains(Object o){
        boolean status = false; // it does not collide
        if (o instanceof MouseManager) {
            Rectangle rItem = new Rectangle(this.getX(), this.getY(), 
                    this.getWidth(), this.getHeight());
            MouseManager mM = (MouseManager) o;
            status = rItem.contains(mM.getX(), mM.getY());
        }
        return status; 
    }
}
