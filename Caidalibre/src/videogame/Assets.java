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
    public static BufferedImage enemy;
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
        background = ImageLoader.loadImage("/images/Background.jpeg");
        player = ImageLoader.loadImage("/images/mario.png");
        enemy=ImageLoader.loadImage("/images/enemigp.png");
        ally=ImageLoader.loadImage("/images/circulo.png");
        gameover=ImageLoader.loadImage("/images/gameover.jpg");
        backSound = new SoundClip("/sounds/back.wav");
        gunShot = new SoundClip("/sounds/Gunshot.wav");
        nice= new SoundClip("/sounds/nice.wav");
        no= new SoundClip("/sounds/no.wav");
    }

}
