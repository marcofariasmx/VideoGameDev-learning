
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
public class SpreadSheet {
    private BufferedImage sheet;
    /**
     * 
     */
    public SpreadSheet(BufferedImage sheet){
        this.sheet=sheet;
    }
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x,y,width,height);
    }
    
}
