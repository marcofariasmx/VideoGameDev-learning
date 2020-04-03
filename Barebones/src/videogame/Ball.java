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

    private int xdir;
    private int ydir;
    private int width;
    private int height;
    private Game game;
    
    public Ball(int x, int y, int xdir,int ydir, int width, int height, Game game) {
        super(x, y);
        this.xdir = xdir;
        this.ydir= ydir;
        this.width = width;
        this.height = height;
        this.game = game;
    }

    void setXDir(int x) {

        xdir = x;
    }

    void setYDir(int y) {

        ydir = y;
    }

    int getYDir() {

        return ydir;
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
        setX(getX()+ xdir);
        setY(getY()+ ydir);

        if (x == 0) {

            setXDir(1);
        }

        if (x == (300 - 5)){

            System.out.println(5);
            setXDir(-1);
        }

        if (y == 0) {

            setYDir(1);
        }
       
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.ball, getX(), getY(), getWidth(), getHeight(), null);
    }
}
