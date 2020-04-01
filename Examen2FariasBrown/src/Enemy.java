/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
    private Animation animationenemyDown;
    private Animation animationenemyUp;
    private Animation animationenemyLeft;
    private Animation animationenemyRight;
    
    public Enemy(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.animationenemyDown= new Animation(Assets.enemyDown,100);
        this.animationenemyUp= new Animation(Assets.enemyUp,100);
        this.animationenemyLeft= new Animation(Assets.enemyLeft, 100);
        this.animationenemyRight= new Animation(Assets.enemyRight, 100);
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
        // moving enemy depending on flags //FOLLOW THE FRIEND
        /*
        if (this.getX() >= game.getPlayer().getX()){
            this.setX(this.getX() - 1);
            this.animationenemyLeft.tick();
        } 
        else {
            this.setX(this.getX() + 1);
            this.animationenemyRight.tick();
        }
        
        if (this.getY() > game.getPlayer().getY()){
            this.setY(this.getY() - 1);
            this.animationenemyUp.tick();
            
        }
        else {
            this.setY(this.getY() + 1);
            this.animationenemyDown.tick();
        }
*/
        //Ir hacia abajo
        setY(getY() + 3); //1 es la velocidad
        
        // Para que no se salga de la pantalla
        /*
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
            this.animationenemyDown.tick();
        } else if (getX() <= -30) {
            setX(-30);
        }
        */
        this.animationenemyDown.tick();
        

    }

    @Override
    public void render(Graphics g){/*
        if(this.getX() >= game.getPlayer().getX()){
            g.drawImage(animationenemyLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        else if(this.getX() < game.getPlayer().getX()){
            g.drawImage(animationenemyRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        else if(this.getY() > game.getPlayer().getY()){
            g.drawImage(animationenemyUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        else if(this.getY() < game.getPlayer().getY()){
            g.drawImage(animationenemyDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
*/
        g.drawImage(animationenemyDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        
    }
}
    
