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
public class Paddle extends Item{

    private int direction;
    private int width;
    private int height;
    private Game game;
    
    
    public Paddle(int x, int y, int direction, int width, int height, Game game) {
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

        if (game.getKeyManager().left) {
           setX(getX() - 1);
        }
        if (game.getKeyManager().right) {
           setX(getX() + 1);         
        }

        if (getX() + 20 >= game.getWidth()) {
            setX(game.getWidth()-20);
        }
        else if (getX() <= -20) {
            setX(-20);
        }
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.paddle, getX(), getY(), getWidth(), getHeight(), null);
    }
}
