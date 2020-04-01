package code;


import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author armandoroquerodriguez
 */

public class Assets {
    public static BufferedImage spriteBackground;
    public static BufferedImage background[];     // to store background image
    
    public static BufferedImage spriteBackgroundPause;
    public static BufferedImage backgroundPause[];     // to store background image
    
    public static BufferedImage player;         // to store the player image
    public static BufferedImage sprites;        // to store the sprites
    public static BufferedImage playerUp[];     // pictures to go up
    public static BufferedImage playerLeft[];   // pictures to go left
    public static BufferedImage playerDown[];   //pictures to go down
    public static BufferedImage playerRight[];   //pictures to go right
    
    public static BufferedImage spritesPlayerIdle;
    public static BufferedImage playerIdle[];
    
    public static BufferedImage spriteFriend;        // to store the sprites of friend (star)
    public static BufferedImage friend[]; // star
    
    public static BufferedImage spriteEnemy;        // to store the sprites of enemy (bullet)
    public static BufferedImage enemy[]; // cannon bullet
    
    public static BufferedImage gameOver; // final background for when game is over
    public static SoundClip backSound; // background music
    public static SoundClip enemyHit; // sound enemy hit
    public static SoundClip friendHit; // sound friend hit
    public static SoundClip gameOverSound; // game over sound
    
    /**
     * initializing the images of the game
     */
    public static void init() { 
        
        // for game background
        spriteBackground = ImageLoader.loadImage("/images/background-animated.png");
        SpriteSheet spritesheetBackground = new SpriteSheet(spriteBackground);
        
        background = new BufferedImage[8];
        for (int i = 0; i < 8; i ++) {
            background[i] = spritesheetBackground.crop(0, i * 322, 640, 322);
        }
        
        // for pause background
        spriteBackgroundPause = ImageLoader.loadImage("/images/PAUSE-VG.png");
        SpriteSheet spritesheetBackgroundPause = new SpriteSheet(spriteBackgroundPause);
        
        backgroundPause = new BufferedImage[8];
        for (int i = 0; i < 8; i ++) {
            backgroundPause[i] = spritesheetBackgroundPause.crop(0, i * 322, 640, 322);
        }
        
        // for main plauer
        spritesPlayerIdle = ImageLoader.loadImage("/images/bird-idle.png");
        SpriteSheet spritesheetPlayerIdle = new SpriteSheet(spritesPlayerIdle);
        
        playerIdle = new BufferedImage[3];
        for (int i = 0; i < 3; i ++) {
            playerIdle[i] = spritesheetPlayerIdle.crop(i * 29, 0, 29, 20);
        }
        
        
        sprites = ImageLoader.loadImage("/images/kirby-vg.png");
        SpriteSheet spritesheet = new SpriteSheet(sprites);
        player = spritesheet.crop(0,0,29,36); // static player
        
        //getting the sprites from the picture
        sprites = ImageLoader.loadImage("/images/kirby-vg.png");
        
        // creating array of images before animations
        playerUp = new BufferedImage[8];
        playerDown = new BufferedImage[5];
        playerLeft = new BufferedImage[12];
        playerRight = new BufferedImage[12];
        
        // cropping the pictures from the sheet into the array
        // for playerDown
        for (int i = 0; i < 5; i ++) {
            playerDown[i] = spritesheet.crop(i * 30 + 60, 0, 30, 37);
        }
        // for playerUp
        for (int i = 0; i < 8; i ++) {
            playerUp[i] = spritesheet.crop(i * 30, 41, 30, 38);
        }
        //for playerLeft and playerRight
        for (int i = 0; i < 12; i ++) {
            playerRight[i] = spritesheet.crop(i * 30, 83, 30, 36);
            playerLeft[i] = spritesheet.crop(i * 30, 123, 30, 36);
        }
        
        // for friend
        spriteFriend = ImageLoader.loadImage("/images/star.png");
        //getting the friend sprites from the picture
        SpriteSheet spritesheetFriend = new SpriteSheet(spriteFriend);
        
        friend = new BufferedImage[8];
        
        //filling the array with pictures
        for (int i = 0; i < 8; i ++) {
            friend[i] = spritesheetFriend.crop(i * 24, 0, 24, 22);
        }
        
        // for enemy
        spriteEnemy = ImageLoader.loadImage("/images/bala.png");
        //getting the enemy sprites from the picture
        SpriteSheet spritesheetEnemy = new SpriteSheet(spriteEnemy);
        
        enemy = new BufferedImage[25];
        for (int i = 0; i < 25; i ++) {
            enemy[i] = spritesheetEnemy.crop(0, i * 54, 72, 54);
        }
             
        gameOver = ImageLoader.loadImage("/images/game-over.png");
        backSound = new SoundClip("/sounds/mkt.wav");
        enemyHit = new SoundClip("/sounds/enemy-hit.wav");
        friendHit = new SoundClip("/sounds/friend-hit.wav");
        gameOverSound = new SoundClip("/sounds/game-over.wav");
    }
}