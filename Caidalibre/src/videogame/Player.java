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
public class Player extends Item{

    private int direction;
    private int width;
    private int height;
    private Game game;
    private boolean grabbed;
    private boolean released;
    private int offsetX;
    private int offsetY;
    private double alpha;
    private double v0X;
    private double v0Y;
    private int potentialElastic;
    private boolean tiroParabolicoActivado;
    double tiempoTiroParab; //tiempo para contar, estará basado en los frames x segundo (50 frames).
    int velocidadTiro;
    double sinRel;
    double cosRel;
    double x0;
    double y0;
    
    
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        v0X=0;
        v0Y=0;
        potentialElastic=0;
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        grabbed = false;
        released= false;
        tiroParabolicoActivado = false;
        velocidadTiro = 1;
        tiempoTiroParab = 0;
        x0 = 0;
        y0 = 0;
    }
    public double getPotential(){
        return potentialElastic;
    }
    
    public void setVelocityX(double velocity){
        v0X=velocity;
    }
    
    public double getVelocityX(){
        return v0X;
    }
    
    public double getVelocityY(){
        return v0Y;
    }
    
    public void setVelocityY(double velocity){
        v0Y=velocity;
    }
    
    public void setGrabbed(boolean grabbed) {
        this.grabbed = grabbed;
    }
    public void setReleased(boolean grabbed) {
        this.released = grabbed;
    }
     public boolean isGrabbed() {
        return grabbed;
    }
     public boolean isReleaded(){
         return released;
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
        if (game.getMouseManager().isIzquierdo()) {
            if (grabbed) {
                setX(game.getMouseManager().getX() - getOffsetX());
                setY(game.getMouseManager().getY() - getOffsetY());
                //potentialElastic=(1/2*(1*(game.getWidth()*1/4)-getX())*(game.getWidth()*1/4)-getX());
                //if((Math.abs(x)+game.getWidth()*1/4)>(Math.abs(y)+getHeight()/2-50))
                //7{
                //    sinRel=(((game.getHeight()/2-50)-Math.abs(y))*1.0)/(((game.getWidth()*1/4)-Math.abs(x))*1.0);
                //    cosRel=(((game.getWidth()*1/4)-Math.abs(x))*1.0)/((((game.getHeight()/2-50)-Math.abs(y))*1.0));
                 //   setVelocityX((Math.sqrt(Math.abs(potentialElastic)))*Math.abs(cosRel));
                  //  setVelocityY((Math.sqrt(Math.abs(potentialElastic)))*Math.abs(sinRel));
                //}
               // else{
               //     cosRel=(((game.getHeight()/2-50)-Math.abs(y))*1.0)/(((game.getWidth()*1/4)-Math.abs(x))*1.0);
               //     sinRel=(((game.getWidth()*1/4)-Math.abs(x))*1.0)/((((game.getHeight()/2-50)-Math.abs(y))*1.0));
               //     setVelocityX((Math.sqrt(Math.abs(potentialElastic)))*Math.abs(cosRel));
               //     setVelocityY((Math.sqrt(Math.abs(potentialElastic)))*Math.abs(sinRel));
                if(y<=(game.getHeight()/2-50)){
                    v0X=(200-x)*1.0/50;
                    v0Y=(((game.getHeight()/2-50)-y)/50);
                }
                else{
                    v0X=(200-x)*1.0/50;
                    v0Y=(y/50);
                    
                }
                
            }
        }
                
        if (getX() < (game.getWidth() / 4)){
            System.out.println("Estamos en ReadyToGoArea");
            System.out.println("snrelcosrel");
            System.out.println(sinRel);
            System.out.println(cosRel);
            System.out.println("vs");
            System.out.println(v0X);
            System.out.println(v0Y);
            System.out.println("PE");
            System.out.println(potentialElastic);
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
        if(tiroParabolicoActivado){
            
            //x = x0 + v0x * t
            //y = y0 + v0y*t - (g*t^2)/2
            
            //Movimiento en X
            x0 = getX();
            setX((int) (x0 + (v0X * tiempoTiroParab)));            
            
            //Movimiento en Y
            y0 = getY();
            setY((int) (y0 - (v0Y*tiempoTiroParab - (4.9 * tiempoTiroParab * tiempoTiroParab))));
            
            //Ecuaciones
            https://www.fisicalab.com/apartado/movimiento-parabolico
            
            //Actualizamos tiempo
            tiempoTiroParab = tiempoTiroParab + .02;
            System.out.println("TiroPaACTIV");
                        
        }
        else{ //reseteamos los parámetros de tiro parabólico.
            tiempoTiroParab = 0;
        }
        // moving player depending on flags
        
        // reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth()/4);
            setY(game.getHeight()/2-50);
            tiroParabolicoActivado = false;
        }
        else if (getX() <= -30) {
            setX(-30);
            tiroParabolicoActivado = false;
        }
        if (getY() + 80 >= game.getHeight()) {
            setX(game.getWidth()/4);
            setY(game.getHeight()/2-50);
            tiroParabolicoActivado = false;
        }
        else if (getY() <= -20) {
            setX(game.getWidth()/4);
            setY(game.getHeight()/2-50);
            tiroParabolicoActivado = false;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }
}
