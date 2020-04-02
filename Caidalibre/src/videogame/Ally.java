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
public class Ally extends Item{

    private int direction;
    private int width;
    private int height;
    private Game game;
    
    public Ally(int x, int y, int direction, int width, int height, Game game) {
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
        if (getX() + 60 >= game.getWidth()) {
            setX((int)(Math.random()*game.getWidth())-100);
        }
        else if (getX() <= -30) {
            setX((int)(Math.random()*game.getWidth())-100);
        }
        else if (getY() <= -20) {
            setX((int)(Math.random()*game.getWidth())-100);
            setY((getWidth()+(int)(Math.random()*game.getHeight())));
        }
       
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.ally, getX(), getY(), getWidth(), getHeight(), null);
    }
}
