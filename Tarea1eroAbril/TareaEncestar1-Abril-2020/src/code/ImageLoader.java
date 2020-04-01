package code;


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
 * to get an image from the file path
 * @param path it is the path of the file
 * @return the <bold>BufferedImage</bold> object
 * @author armandoroquerodriguez
 */
public class ImageLoader {
    public static BufferedImage loadImage(String path) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException ex) {
            Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        return bi;
    }
}
