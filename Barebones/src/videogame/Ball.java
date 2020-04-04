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
public class Ball extends Item{

    private int xdir;//direction of x
    private int ydir;//direction of y
    private int width;//width of the ball
    private int height;//height of ball
    private Game game;
    private Animation anballsmove;//animation fo the ball
    
    public Ball(int x, int y, int xdir,int ydir, int width, int height, Game game) {
        super(x, y);
        this.xdir = xdir;
        this.ydir= ydir;
        this.width = width;
        this.height = height;
        this.game = game;
        this.anballsmove= new Animation(Assets.ballsmove,100);
    }
    //set x on x
    void setXDir(int x) {

        xdir = x;
    }
    //SET Y ON Y
    void setYDir(int y) {

        ydir = y;
    }
    //set direction y
    int getYDir() {

        return ydir;
    }
    //get direction x
    int getXDir() {

        return xdir;
    }
    //get width of ball
    public int getWidth() {
        return width;
    }
    //get height of ball
    public int getHeight() {
        return height;
    }
    //set width to width
    public void setWidth(int width) {
        this.width = width;
    }
    //set height to height
    public void setHeight(int height) {
        this.height = height;
    }


    @Override
    public void tick() {
        //move the ball depending of its direction of x and y
        setX(getX()+ xdir);
        setY(getY()+ ydir);
        this.anballsmove.tick();
        if (x == 0) {
            //change direction when x=0
            setXDir(1);
        }

        if (x == (300 - 5)){
            //change direction when right border
            setXDir(-1);
        }

        if (y == 0) {
            //change direction when top border
            setYDir(1);
        }
       
    }
    //render the animation saved
    @Override
    public void render(Graphics g) {
        g.drawImage(anballsmove.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
