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
 * @author antoniomejorado
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
    private Ball ball;
    private LinkedList <Brick> bricks;
    private KeyManager keyManager;  // to manage the keyboard
    private int lives;
    private int score;
    private int lost;
    private Background background; 
    static int contColisiones=0;
    String vidas = "Vidas = ";
    String puntos = "Puntaje = ";
    
    
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
        this.lives=(int)(Math.random() * 2) + 5;
        this.score=0;
        this.lost=0;
    }
    public Paddle getPlayer() {
        return paddle;
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
        display.getJframe().addKeyListener(keyManager);
        background = new Background(getWidth(), getHeight(), this);
        paddle = new Paddle(200,360,1, 40, 10, this);
        ball= new Ball(230,355 , 1, -1, 5, 5, this);
        Assets.backSound.setLooping(true);
        //Assets.backSound.play();
        bricks= new LinkedList();
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
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
   

    private void tick() {
        // avancing player with colision
        background.tick(lost);
        keyManager.tick();
        paddle.tick();
        ball.tick();
        if (ball.getY() > 390) {
            //stopGame();
        }
        if (contColisiones==30){
            
        }
        for(Brick brick:bricks){
            brick.tick();
            if(!brick.isDestroyed()){
                if(ball.collision(brick)){
                    int ballLeft = (int) ball.getX();
                    int ballHeight = 5;
                    int ballWidth = 5;
                    int ballTop = (int) ball.getY();

                    Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                    Point pointLeft = new Point(ballLeft - 1, ballTop);
                    Point pointTop = new Point(ballLeft, ballTop - 1);
                    Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                        if (brick.getRect(brick.getX(),brick.getY(),40,10).contains(pointRight)) {

                           ball.setXDir(-1);
                        } else if (brick.getRect(brick.getX(),brick.getY(),40,10).contains(pointLeft)) {

                            ball.setXDir(1);
                        }

                        if (brick.getRect(brick.getX(),brick.getY(),40,10).contains(pointTop)) {

                            ball.setYDir(1);
                        } else if (brick.getRect(brick.getX(),brick.getY(),40,10).contains(pointBottom)) {

                            ball.setYDir(-1);
                        }
                    brick.setDestroyed(true);
                    contColisiones++;
                    score=score+10;
                    
                }
            }
        }
        if(ball.collision(paddle)){
            int paddleLPos = (int) paddle.getX();
            int ballLPos = (int) ball.getX();

            int first = paddleLPos + 8;
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            if (ballLPos < first) {

                ball.setXDir(-1);
                ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {

                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {

                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {

                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos > fourth) {

                ball.setXDir(1);
                ball.setYDir(-1);
            }
        }
//lives=0;
//lost=1;
//bricks.clear();
//allies.clear();
//Assets.backSound.stop();
        
        
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
            background.render(g, lost);
            paddle.render(g);
            ball.render(g);
            g.drawString(vidas + lives, 10, 25);
            g.drawString(puntos + score, 10, 50);
            for (Brick brick: bricks){
                if(!brick.isDestroyed()){
                    brick.render(g);
                }
            }
            //for (Ball ally: allies){
              //  ally.render(g);
           // }
            bs.show();
            g.dispose();
        }
       
    }
    
    /**
     * setting the thead for the game
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
