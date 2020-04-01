package code;


import java.awt.Graphics;
// Java program for sin() method 
import java.util.*; 

/**
 *
 * @author antoniomejorado
 */
public class Player extends Item{

    private int direction;
    private int width;
    private int height;
    private Game game;
    private int speed = 3;
    
    private Animation animationIdle;
    
    private Animation animationUp;
    private Animation animationLeft;
    private Animation animationDown;
    private Animation animationRight;
    
    
    private boolean grabbed;
    private boolean tiroParabolicoActivado;
    private int offsetX;
    private int offsetY;
    double tiempoTiroParab; //tiempo para contar, estará basado en los frames x segundo (50 frames).
    int velocidadTiro;
    int gradosTiro;
    int x0;
    int y0;
    int v0x;
    int v0y;
    
    /**
     * set the initial value for the player
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game 
     */
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.animationIdle = new Animation(Assets.playerIdle, 350);
        this.animationUp = new Animation(Assets.playerUp, 100);
        this.animationLeft = new Animation(Assets.playerLeft, 80);
        this.animationDown = new Animation(Assets.playerDown, 100);
        this.animationRight = new Animation(Assets.playerRight, 80);
        grabbed = false;
        tiroParabolicoActivado = false;
        velocidadTiro = 1;
        gradosTiro = 45;
        tiempoTiroParab = 0;
        x0 = 0;
        y0 = 0;
        v0x = 5;
        v0y = 5;
    }

    public void setGrabbed(boolean grabbed) {
        this.grabbed = grabbed;
    }
    
    public boolean isGrabbed() {
        return grabbed;
    }
    
    public boolean isTiroParabolicoActivado() {
        return tiroParabolicoActivado;
    }
    
    public int getOffsetX() {
        return offsetX;
    }
    
    public int getOffsetY() {
        return offsetY;
    }
    
    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }
    
    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }
    
    @Override
    public void tick() {
        this.animationIdle.tick();
        
        // moving player depending on flags
        if (game.getMouseManager().isIzquierdo()) {
            if (grabbed) {
                setX(game.getMouseManager().getX() - getOffsetX());
                setY(game.getMouseManager().getY() - getOffsetY());
                
            }
            
        }
        
        
        //Creamos "ReadyToGoArea"
        if (getX() < (game.getWidth() / 5)){
            System.out.println("Estamos en ReadyToGoArea");
            
            //Activamos el tiro parabólico del personaje
            if(!grabbed){ //cuando lo suelten
                //x=v0·cosθ·t
                //y=v0·senθ·t-gt2/2
                tiroParabolicoActivado = true;
                
            }
            
            
        }
        else{
            System.out.println("Fuera de ReadyToGoArea");
        }
        
        //setX(game.getMouseManager().getX());
        //setY(game.getMouseManager().getY());
        
        //Implementamos lógica de tiro parabólico
        if(tiroParabolicoActivado){
            
            //x = x0 + v0x * t
            //y = y0 + v0y*t - (g*t^2)/2
            
            //Movimiento en X
            x0 = getX();
            setX((int) (x0 + (v0x * tiempoTiroParab)));            
            
            //Movimiento en Y
            y0 = getY();
            setY((int) (y0 - (v0y*tiempoTiroParab - (4.9 * tiempoTiroParab * tiempoTiroParab))));
            
            //Ecuaciones
            https://www.fisicalab.com/apartado/movimiento-parabolico
            
            //Actualizamos tiempo
            tiempoTiroParab = tiempoTiroParab + .02;
            System.out.println("TiroPaACTIV");
                        
        }
        else{ //reseteamos los parámetros de tiro parabólico.
            tiempoTiroParab = 0;
        }
        
        // reset x position and y position if colision with border
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
            tiroParabolicoActivado = false; //Terminamos el tiro parabólico 
        }
        else if (getX() <= -30) {
            //setX(-30);
            setX(getWidth()/2);
            tiroParabolicoActivado = false; //Terminamos el tiro parabólico
        }
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
            tiroParabolicoActivado = false; //Terminamos el tiro parabólico
        }
        else if (getY() <= -20) {
            setY(-20);
            tiroParabolicoActivado = false; //Terminamos el tiro parabólico
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animationIdle.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        
        
    }
}

