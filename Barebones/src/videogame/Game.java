/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

/**
 *
 * @author LosMarcos
 */
public class Game implements Runnable {
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Paddle paddle;          // to use a player
    private Ball ball;              //use as the ball
    private LinkedList <Brick> bricks;//create all the bricks
    private KeyManager keyManager;  // to manage the keyboard
    private int lives;              //SAVE THE LIVES
    private int score;              //save the score
    private int lost;               //boolean if lost
    private Background background;  //Bckground obect to save it
    static int contColisiones=0;    //save quantity of collisins to finish the game
    String vidas = "Vidas = ";      //titles in game
    String puntos = "Puntaje = ";   //titles in game
    private ReadandWrite Archivo;   //saver
    
    
    /**
     * to create title, width and height and set the game is still not running
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height  to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        this.lives=(int)(Math.random() * 2) + 2;
        this.score=0;
        this.lost=0;
    }
    //returns th paddle of the game
    public Paddle getPaddle() {
        return paddle;
    }
    // returns the ball of game
    public Ball getBall() {
        return ball;
    }
    
    //return quantity of lives
    public int getNumVidas() {
        return lives;
    }
    //return quantity of collisions
    public int getContColisiones(){
        return contColisiones;
    }
    //sets quantity of collisions
    public void setContColisiones(int x){
        contColisiones=x;
    }
    //sets number of lives
    public void setNumVidas(int vidas){
        lives = vidas;
    }
    //returns the score of the game
    public int getScore() {
        return score;
    }
    //sets the score of the game
    public void setScore(int puntuacion){
        score = puntuacion;
    }
    //sets the ball to another ball
    public void setBall(Ball ballToSet){
        ball=ballToSet;
    }
    //gets the LL of bricks
    public LinkedList <Brick> getBricks(){
        return bricks;
    }
    //sets the brick to the properties of other bricks
    public void setBricks(LinkedList <Brick> bricksToSet){
        bricks = bricksToSet;
    }
    
    /**
     * To get the width of the game window
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        display.getJframe().addKeyListener(keyManager);// right or left of paddle
        background = new Background(getWidth(), getHeight(), this);
        paddle = new Paddle(200,360,1, 40, 10, this);
        ball= new Ball(230,355 , 1, -1, 5, 5, this);
        Assets.backSound.setLooping(true);
        //Assets.backSound.play();
        bricks= new LinkedList();//creation of 30 bricks
            for (int l = 0; l < 5; l++) 
            {
                for (int j = 0; j < 6; j++) {
                    Brick brick= new Brick(j * 40 + 30, l * 10 + 50, 40, 10, this);
                    bricks.add(brick);
                }
            }
    }
    
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 60;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;
            
            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta --;
            }
        }
        
        //Agregamos esto para que corra un último ciclo después de perder y alcance a actualizar.
        tick();
        render();
        
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
   

    private void tick() {
        // avancing player with colision
        keyManager.tick();
        paddle.tick();
        ball.tick();
        background.tick(lost);
        //ball goes below paddle
        if (ball.getY() > 450) {
            lives=lives-1;
            ball.setX(230);
            ball.setY(355);
            ball.setXDir(1);
            ball.setYDir(-1);
            paddle.setX(200);
            paddle.setY(355);
            if(lives<1){
                //if the game is lost
                Assets.no.play();
                lost=1;
                //background.render(g,lost);
                //background.tick(lost);
                System.out.println("Ya se PERDIÓ"); 
                paddle.setX(830);
                paddle.setY(855);
                ball.setX(830);
                ball.setY(855);
                bricks.clear();
                running=false;
            }
        }
        //if the game is won
        if (contColisiones==30){
            Assets.nice.play();
            bricks.clear();
        }
        //ticks of every brick
        for(Brick brick:bricks){
            brick.tick();
            //only if the brick is still there
            if(!brick.isDestroyed()){
                if(ball.collision(brick)){
                    Assets.gunShot.play();
                    //sound of gunshot when broken
                    //create parts of the ball
                    int ballLeft = (int) ball.getX();
                    int ballHeight = 5;
                    int ballWidth = 5;
                    int ballTop = (int) ball.getY();
                    //create point to contain for every hit with the ball
                    Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                    Point pointLeft = new Point(ballLeft - 1, ballTop);
                    Point pointTop = new Point(ballLeft, ballTop - 1);
                    Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);
                        //colision with ball and sets direction
                        if (brick.getRect(brick.getX(),brick.getY(),40,10).contains(pointRight)) {

                           ball.setXDir(-1);
                        } 
                        //colision with ball and sets direction
                        else if (brick.getRect(brick.getX(),brick.getY(),40,10).contains(pointLeft)) {

                            ball.setXDir(1);
                        }
                        //colision with ball and sets direction
                        if (brick.getRect(brick.getX(),brick.getY(),40,10).contains(pointTop)) {

                            ball.setYDir(1);
                        } 
                        //colision with ball and sets direction
                        else if (brick.getRect(brick.getX(),brick.getY(),40,10).contains(pointBottom)) {

                            ball.setYDir(-1);
                        }
                    brick.setDestroyed(true);//set the one that crashed as destroyed
                    contColisiones++;//sum the number of colisions as one more
                    score=score+10;//add score
                    
                }
            }
        }
        //when the ball hits the paddle
        if(ball.collision(paddle)){
            int paddleLPos = (int) paddle.getX();//variable og the pos of paddle
            int ballLPos = (int) ball.getX();//variable of ball pos

            int first = paddleLPos + 8;//divide the paddle for different directions
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            if (ballLPos < first) {//first changes both values of direction

                ball.setXDir(-1);
                ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {// changes both values of direction depending

                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {// changes both values of direction depending

                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {// changes both values of direction depending

                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos > fourth) {// changes both values of direction depending

                ball.setXDir(1);
                ball.setYDir(-1);
            }
        }
//lives=0;
//lost=1;
//bricks.clear();
//allies.clear();
//Assets.backSound.stop();

        if(this.getKeyManager().save){//saves the game with current properties
            Archivo.Save("DatosJuegoEx2.txt",this);
        }
        if(this.getKeyManager().load){//load the game of the file
            bricks.clear();
            Archivo.Load("DatosJuegoEx2.txt",this);
        }
        
        
    }
    
    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
        */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else
        {
            
            g = bs.getDrawGraphics();
            //render if lost
            if(lives < 1){
                g.drawImage(Assets.gameOver, 0, 0, getWidth(), getHeight(), null);
                System.out.println("Sí entró el render de gamOver"); 
                
            }
            //render if won
            else if(score == 300){
                g.drawImage(Assets.youWin, 0, 0, getWidth(), getHeight(), null);
                running=false;
            }
            else{
                
                background.render(g, lost);
            }       
            paddle.render(g);
                ball.render(g);
                g.drawString(vidas + lives, 20, 12);
                g.drawString(puntos + score, 220, 12);
                for (Brick brick: bricks){
                    if(!brick.isDestroyed()){
                        brick.render(g);
                    }
                }
                bs.show();
                g.dispose();
        }
       
    }
    
    /**
     * setting the trhead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }           
        }
    }

 
    


}
