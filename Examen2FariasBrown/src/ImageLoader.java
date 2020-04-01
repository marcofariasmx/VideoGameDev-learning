
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MARBROCU
 */
public class ImageLoader {
    public static BufferedImage loadImage(String path){
        BufferedImage bi=null;
        try{
            System.out.println(""+ImageLoader.class.getResource(path));
            bi= ImageIO.read(ImageLoader.class.getResource(path));
        }catch(IOException ex){
            Logger.getLogger(
            ImageLoader.class.getName()).log(Level.SEVERE,null,ex);
            System.exit(1);
        }
        return bi;
    }
}
