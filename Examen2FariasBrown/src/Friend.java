/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Graphics;

/**
 *
 * @author mx
 */
public class Friend extends Item {

    private int direction;
    private Game game;
     private int width;
    private int height;

    public Friend(int x, int y, int direction, int width, int height, Game game) {
        //super(x, y, width, height);
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
        public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    @Override
    public void tick() {
        // moving enemy depending on flags
        //Declaramos el avance random primero
        int avanceRandom = (int) (Math.random() * 3) + 2; //(rand() * b-a+1) + a   -> 4-2+1 = 3

        setY(getY() - avanceRandom);

        // reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        } else if (getX() <= -30) {
            setX(-30);
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.friend, getX(), getY(), getWidth(), getHeight(), null);
    }
}
