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

    public static BufferedImage background; // to store background image
    public static BufferedImage player;     // to store the player image
    public static BufferedImage spritesEnemy;
    public static BufferedImage enemyDown[];
    public static BufferedImage ally;
    public static BufferedImage gameover;
    public static SoundClip backSound;
    public static SoundClip gunShot;
    public static SoundClip nice;
    public static SoundClip no;


    /**
     * initializing the images of the game
     */
    public static void init() {
        
        background = ImageLoader.loadImage("/images/fondobiki.jpg");
        player = ImageLoader.loadImage("/images/mario.png");
        spritesEnemy= ImageLoader.loadImage("/images/enemy.png");
        SpreadSheet enemysprites= new SpreadSheet(spritesEnemy);
        enemyDown= new BufferedImage[9];
        ally=ImageLoader.loadImage("/images/circulo.png");
        gameover=ImageLoader.loadImage("/images/gameover.jpg");
        backSound = new SoundClip("/sounds/back.wav");
        gunShot = new SoundClip("/sounds/Gunshot.wav");
        nice= new SoundClip("/sounds/nice.wav");
        no= new SoundClip("/sounds/no.wav");
        for (int h=0;h<9;h++){
            enemyDown[h]= enemysprites.crop(h*64,128,64,64);    
        }
    }

}
