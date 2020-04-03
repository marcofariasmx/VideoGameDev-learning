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

    public static BufferedImage spriteBackground;
    public static BufferedImage background[];     // to store background image
    
    public static BufferedImage paddle;     // to store the player image
    public static BufferedImage brick;
    public static BufferedImage ball;
    public static BufferedImage gameover;
    public static SoundClip backSound;
    public static SoundClip gunShot;
    public static SoundClip nice;
    public static SoundClip no;


    /**
     * initializing the images of the game
     */
    public static void init() {
        spriteBackground = ImageLoader.loadImage("/images/mejor.png");
        SpriteSheet spritesheetBackground = new SpriteSheet(spriteBackground);
        
        background = new BufferedImage[8];
        for (int i = 0; i < 7; i ++) {
            background[i] = spritesheetBackground.crop(i*300, 0, 300, 400);
        }
        paddle = ImageLoader.loadImage("/images/paddle.png");
        brick=ImageLoader.loadImage("/images/brick.png");
        ball=ImageLoader.loadImage("/images/ball.png");
        backSound = new SoundClip("/sounds/back.wav");
        gunShot = new SoundClip("/sounds/Gunshot.wav");
        nice= new SoundClip("/sounds/nice.wav");
        no= new SoundClip("/sounds/no.wav");
    }

}
