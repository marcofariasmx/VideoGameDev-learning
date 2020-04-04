/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author Marcos
 */
public class Paddle extends Item{

    private int direction;  //direction of paddle
    private int width;      //saves width of paddle
    private int height;     //saves height of paddle
    private Game game;      //game it represents
    
    
    public Paddle(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
    }
    //gets direction
    public int getDirection() {
        return direction;
    }
    //gets width of paddle
    public int getWidth() {
        return width;
    }
    //GET Height of paddle
    public int getHeight() {
        return height;
    }
    //sets direction of paddle
    public void setDirection(int direction) {
        this.direction = direction;
    }
    //sets width of paddle
    public void setWidth(int width) {
        this.width = width;
    }
    //sets height of paddle
    public void setHeight(int height) {
        this.height = height;
    }


    @Override
    public void tick() {
        //moving to the left
        if (game.getKeyManager().left) {
           setX(getX() - 1);
        }
        //moving to the right
        if (game.getKeyManager().right) {
           setX(getX() + 1);         
        }
        //borders of the game
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
