package code;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

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
    //private KeyManager keyManager;  // to manage the keyboard
    
    private MouseManager mouseManager; // to manage the mouse
    
    private Background background; // to have a background that can change animations
    
    private Player player;          // to use a player
    
    
    private int vidas;              // to keep track of lives left
    private int puntos;             // to track score of player
    
    
    private Font font;              // to count how many friends player hits
    
    private int gameStatus; // to keep track fo game status -> 0 is for game over, 1 for running, 2 for pause
    
    private boolean savedGame; // to know if there is a previous game saved
    private String dateSavedGame; // to know when the previous game is from
    
    private String fileGame; // name of the file to store the game's status
    
    private boolean previousGameLoaded; // to know if previous game has been restored
    private boolean thisGameSaved; // to know if current game has been saved
    
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
        mouseManager = new MouseManager(this);
        running = false;
        
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
     * To get the game status
     * @return an <code>int</code> value with the game status
     */
    public int getGameStatus() {
        return gameStatus;
    }
    
    /**
     * Set gameStatus value
     * @param gameStatus value to modify
     */
    public void setGameStatus(int gameStatus) {
        this.gameStatus = gameStatus;
    }
    
    /**
     * To get if there is a previously saved game
     * @return a <code>boolean</code> value get if there is a previously saved game
     */
    public boolean isSavedGame() {
        return savedGame;
    }
    
    /**
     * To set if there is a previously saved game
     * @param savedGame to modify
     */
    public void setSavedGame(boolean savedGame) {
        this.savedGame = savedGame;
    }
    
    /**
     * To get if the current game has been saved
     * @return a <code>boolean</code> value to get if the current game has been saved
     */
    public boolean isThisGameSaved() {
        return thisGameSaved;
    }
    
    /**
     * To set if the current game has been saved
     * @param thisGameSaved to modify
     */
    public void setThisGameSaved(boolean thisGameSaved) {
        this.thisGameSaved = thisGameSaved;
    }
    
    /**
     * To get if the previously saved game has been loaded
     * @return a <code>boolean</code> value to get if the previously saved game has been loaded
     */
    public boolean isPreviousGameLoaded() {
        return previousGameLoaded;
    }
    
    /**
     * To set if the previously saved game has been loaded
     * @param previousGameLoaded to modify
     */
    public void setPreviousGameLoaded(boolean previousGameLoaded) {
        this.previousGameLoaded = previousGameLoaded;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    /**
     * initializing the display window of the game
     */
    private void init() {
        
        gameStatus = 1; // game is running
        
        display = new Display(title, getWidth(), getHeight());  
        Assets.init();

        background = new Background(getWidth(), getHeight(), this); // create background object

        player = new Player((getWidth() / 2) - (50), (getHeight() / 2) - 50, 58, 40, this); // place player in screen center
        
        display.getJframe().addMouseListener(mouseManager);
        display.getJframe().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
         
         
        vidas = (int) ((Math.random() * 3) + 3); // random number of lives

        


        //Assets.backSound.setLooping(true); // set loop
        //Assets.backSound.play(); // play background music

        font = new Font("SansSerif", 1, 16); // set font
        
        fileGame = "game.txt"; // set the name of file where data is stored
        
        savedGame = false; 
        dateSavedGame = "";
        previousGameLoaded = false;
        thisGameSaved = false;
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
    
    /**
     * to get the mouse manager
     * @return the mouse manager
     */
    public MouseManager getMouseManager() {
        return mouseManager;
    }
   
    /**
     * To make sound when enemy hits player
     */
    public void enemyHitSound() {
        Assets.enemyHit.play();
    }
    
    /**
     * To make sound when friend hits player
     */
    public void friendHitSound() {
        Assets.friendHit.play();
    }
    
   
    
    private void tick() {
        
        
        background.tick(gameStatus); // refresh background according to the current status
        
        // game running
        if (gameStatus == 1) {
            player.tick();
        }
        else if (gameStatus == 2) { // game in pause
            // código de pause
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
            
            g.setFont(font);
            
            background.render(g, gameStatus); // render background according to game status
            
            if (gameStatus == 1) { // game running
                player.render(g); // render player
                
            }
            else if (gameStatus == 0) { 
                // display things when game over
                
            }
            else if (gameStatus == 2) {
                // display things when pause
                
                
            }
            
            // display game data
            g.setColor(Color.WHITE);
            g.drawString("Enemigos chocados en esta vida: ", 10, 20); // draw number of dropped objects
            g.drawString("Vidas: " + vidas, getWidth() - 100, 20); // draw number of lives left
            g.setColor(Color.RED);
            g.drawString("Score: " + puntos, getWidth() / 2 - 35, 20); // draw score of player
            
            
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
    }}
