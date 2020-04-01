
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MARBROCU
 */
public class Assets {
    public static BufferedImage background;
    public static BufferedImage player;
    public static BufferedImage sprites;
    public static BufferedImage playerUp[];
    public static BufferedImage playerLeft[];
    public static BufferedImage playerDown[];
    public static BufferedImage playerRight[];
    public static BufferedImage playerIdle[];
    public static BufferedImage spritesEnemy;
    public static BufferedImage enemyUp[];
    public static BufferedImage enemyDown[];
    public static BufferedImage enemyLeft[];
    public static BufferedImage enemyRight[];
    public static BufferedImage friend;     // to store the friend image
    
    public static void init(){
        background=ImageLoader.loadImage("/images/fondobiki.jpg");
        sprites=ImageLoader.loadImage("/images/kidsprite.png");
        spritesEnemy= ImageLoader.loadImage("/images/enemy.png");
        SpreadSheet enemysprites= new SpreadSheet(spritesEnemy);
        SpreadSheet spritesheet = new SpreadSheet(sprites);
        enemyUp= new BufferedImage[9];
        enemyLeft= new BufferedImage[9];
        enemyDown= new BufferedImage[9];
        enemyRight= new BufferedImage[9];
        playerUp= new BufferedImage[5];
        playerLeft= new BufferedImage[5];
        playerDown= new BufferedImage[5];
        playerRight =new BufferedImage[5];
        playerIdle=new BufferedImage[5];
        for (int i=0;i<5;i++){
            playerLeft[i] = spritesheet.crop(i*144,0,144,144);
            playerRight[i] = spritesheet.crop(i*144,144,144,144);
            playerDown[i] = spritesheet.crop(i*144,288,144,144);
            playerUp[i]= spritesheet.crop(i*144,432,144,144);
            playerIdle[i]=spritesheet.crop(i*144,576,144,144);
        }     
        for (int h=0;h<9;h++){
            enemyUp[h] = enemysprites.crop(h*64,0,64,64);
            enemyLeft[h] = enemysprites.crop(h*64,64,64,64);
            enemyDown[h]= enemysprites.crop(h*64,128,64,64);
            enemyRight[h] = enemysprites.crop(h*64,192,64,64);
        }
        friend = ImageLoader.loadImage("/images/corazon.png");
    }
}
