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
public class Enemy extends Item{

    private int direction;
    private int width;
    private int height;
    private Game game;
    
    public Enemy(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
    }

    public int getDirection() {
        return direction;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    @Override
    public void tick() {
        // moving player depending on flags
         setY(getY()+ (int)Math.random()*3+3);
        // reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
            setX((int)(Math.random()*game.getWidth())-100);
        }
        else if (getX() <= 300) {
            setX((int)(Math.random()*game.getWidth())-100);
        }
        if (getY() + 80 >= game.getHeight()) {
            setY((0-(int)(Math.random()*1000)));
            setX((int)(Math.random()*game.getWidth())-100);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
    }
}
