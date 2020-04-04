/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author antoniomejorado
 */
public class Assets {

    public static BufferedImage spriteBackground;// create the sprites
    public static BufferedImage background[];     // to store background image
    
    
    public static BufferedImage paddle;     // to store the player image
    
    public static BufferedImage spritebricks;// sprites of the bricks
    public static BufferedImage brickchange[];// store the brick imae
    
    //public static BufferedImage ball;
    public static BufferedImage spritesballs;// create the sprite of ball
    public static BufferedImage ballsmove[];// store sprite of 
    
    public static BufferedImage gameOver;//image of gameover
    public static BufferedImage youWin;//image of win
    public static SoundClip backSound;//sounds
    public static SoundClip gunShot;//sounds of block breaking
    public static SoundClip nice;//sounds of win
    public static SoundClip no;//sounds of lost


    /**
     * initializing the images of the game
     */
    public static void init() {
        spriteBackground = ImageLoader.loadImage("/images/mejor.png");//upload image
        SpriteSheet spritesheetBackground = new SpriteSheet(spriteBackground);
        
        background = new BufferedImage[8];// set the saved image for it to load
        for (int i = 0; i < 7; i ++) {
            background[i] = spritesheetBackground.crop(i*300, 0, 300, 400);
        }
        paddle = ImageLoader.loadImage("/images/paddle.png");//upload image
        
        spritebricks=ImageLoader.loadImage("/images/bricks.png");//upload image
        SpriteSheet spritesheetBricks = new SpriteSheet(spritebricks);
        brickchange= new BufferedImage[3];// set the saved image for it to load
        for(int k=0;k<3;k++){
            for(int l=0;l<4;l++){
                brickchange[k] = spritesheetBricks.crop(k*16,l*8,16,8);
            }
        }
        
        gameOver=ImageLoader.loadImage("/images/gameover.png");//upload image
        youWin=ImageLoader.loadImage("/images/youWin.png");//upload image
        
        //ball=ImageLoader.loadImage("/images/ball.png");
        spritesballs= ImageLoader.loadImage("/images/balls.png");//upload image
        SpriteSheet spritesheetBall = new SpriteSheet(spritesballs);
        ballsmove= new BufferedImage[34];// set the saved image for it to load
        for (int h=0;h<34;h++){
            for(int j=0;j<16;j++){
                ballsmove[h] = spritesheetBall.crop(h*16,j*16,16,16);
//            ballscollision[h]= spritesheetBall.crop(h*64,0,64,64);
            }
        }
        
        backSound = new SoundClip("/sounds/back.wav");
        gunShot = new SoundClip("/sounds/Gunshot.wav");
        nice= new SoundClip("/sounds/nice.wav");
        no= new SoundClip("/sounds/no.wav");
    }

}
