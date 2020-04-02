/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
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
    private Player player;          // to use a player
    private Ally circulo;
    private LinkedList <Enemy> enemies;
    private KeyManager keyManager;  // to manage the keyboard
    private MouseManager mouseManager;
    private int lives;
    private int score;
    private int lost;
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
        mouseManager = new MouseManager(this);
        this.lives=(int)(Math.random() * 2) + 5;
        this.score=0;
        this.lost=0;
    }
    public Player getPlayer() {
        return player;
    }
    public MouseManager getMouseManager() {
        return mouseManager;
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
         enemies= new LinkedList();
         //allies=new LinkedList();
         int random = 1;
         for (int i=1; i<=random;i++){
             Enemy enemy= new Enemy(((int)(Math.random()*(getWidth()*3/5))+(getWidth()*3/5)), (0-(int)(Math.random()*getHeight())),1, 100, 100, this);
             enemies.add(enemy);
         }
         //int randomer = (int)(Math.random()*6)+10;
        // for (int i=1; i<=randomer;i++){
          //   Ally ally= new Ally(((int)(Math.random()*getWidth())-100), (getWidth()+(int)(Math.random()*getHeight())-100),1, 100, 100, this);
            // allies.add(ally);
        // }
         player = new Player(getWidth()/4, getHeight()/2-50, 1, 100, 100, this);
         circulo= new Ally(getWidth()*1/4, getHeight()/2-50, 1, 50, 50, this);
         display.getJframe().addMouseListener(mouseManager);
         display.getJframe().addMouseMotionListener(mouseManager);
         display.getCanvas().addMouseListener(mouseManager);
         display.getCanvas().addMouseMotionListener(mouseManager);
         //Assets.backSound.setLooping(true);
         //Assets.backSound.play();
    }
    
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
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
        player.tick();
        for(Enemy enemy:enemies){
            enemy.tick();
            if(player.collision(enemy)){
                contColisiones++;
                Assets.no.play();
                enemy.setY((0-(int)(Math.random()*1000)));
                player.setX(getWidth()/4);
                player.setY(getHeight()/2-50);
                enemy.setX((int)(Math.random()*(getWidth()*3/5))+(getWidth()*2/5));
                if(contColisiones==3){
                    if (lives>1){
                        lives=lives-1;
                        contColisiones=0;
                    }
                    else{
                        lives=0;
                        lost=1;
                        enemies.clear();
                        //allies.clear();
                        Assets.backSound.stop();
                    }
                }
            }
        }
        //for(Ally ally:allies){
          //  ally.tick();
            //if(player.collision(ally)){
              //  score=score+10;
                //ally.setX((int)(Math.random()*getWidth())-100);
                //ally.setY((getWidth()+(int)(Math.random()*getHeight())-100));
                //Assets.nice.play();
           // }
       // }
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
            if (lost==0){
                g.drawImage(Assets.background, 0, 0, width, height, null);
            }
            else if(lost==1){
                g.drawImage(Assets.gameover, 0, 0, width, height, null);
            }
            player.render(g);
            circulo.render(g);
            g.drawString(vidas + lives, 10, 25);
            g.drawString(puntos + score, 10, 50);
            for (Enemy enemy: enemies){
                enemy.render(g);
            }
            //for (Ally ally: allies){
              //  ally.render(g);
           // }
           
           // display game data
            g.setColor(Color.WHITE);
            g.drawString("Enemigos chocados en esta vida: ", 10, 20); // draw number of dropped objects
            g.drawString("Vidas: " + vidas, getWidth() - 100, 20); // draw number of lives left
            g.setColor(Color.RED);
            g.drawString("Score: " + puntos, getWidth() / 2 - 35, 20); // draw score of player
            
            //Parte del código para mostrar la línea de Ready To Go
            //g.fillRect(squareX,squareY,squareW,squareH);
            g.fillRect(getWidth()/4,0,5,getHeight());
           
           
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
