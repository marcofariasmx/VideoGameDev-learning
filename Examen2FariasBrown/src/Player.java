
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MARBROCU
 */
public class Player extends Item{
    private int direction;
    private int width;
    private int height;
    private Game game;
    private Animation animationDown;
    private Animation animationUp;
    private Animation animationLeft;
    private Animation animationRight;
    private Animation animationIdle;
    
    public Player(int x, int y, int direction, int width, int height, Game game){
        super(x,y);
        this.direction= direction;
        this.width= width;
        this.height= height;
        this.game= game;
        
        this.animationDown= new Animation(Assets.playerDown,100);
        this.animationUp= new Animation(Assets.playerUp,100);
        this.animationLeft= new Animation(Assets.playerLeft, 100);
        this.animationRight= new Animation(Assets.playerRight, 100);
        this.animationIdle= new Animation(Assets.playerIdle, 100);
    }

    public int getDirection() {
        return direction;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Game getGame() {
        return game;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void tick() {
        
        this.animationIdle.tick();
        if (game.getKeyManager().up){
            setY(getY()-2);
            this.animationUp.tick();
        }
        if (game.getKeyManager().down){
            setY(getY()+2);
            this.animationDown.tick();
        }
        if (game.getKeyManager().left){
            setX(getX()-2);
            this.animationLeft.tick();
        }
        if (game.getKeyManager().right){
            setX(getX()+2);
            this.animationRight.tick();
        }
        //reset x position and y
        if (getX() + 60 > game.getWidth()) {
            setX(game.getWidth() - 60);
            
        }
        else if (getX() < -30) {
            setX(-30);
        
        }
        if(getY()+80>=game.getHeight()){
            setY(game.getHeight()-80);
        }
        else if(getY()<=-40){
            setY(-40);
        }
    }

    @Override
    public void render(Graphics g) {    
        if(game.getKeyManager().down){
            g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        else if(game.getKeyManager().up){
            g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        else if(game.getKeyManager().left){
            g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        else if(game.getKeyManager().right){
            g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        else{
            g.drawImage(animationIdle.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
    
    }

    /**
     *
     * @param title
     * @param width
     * @param height
     */
    
    
}
