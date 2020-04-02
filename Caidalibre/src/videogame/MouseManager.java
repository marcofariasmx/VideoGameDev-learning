/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author armandoroque
 */
public class MouseManager implements MouseListener, MouseMotionListener {
    private boolean izquierdo;
    private boolean derecho;
    private int x;
    private int y;
    
    private Game game;
    
    public MouseManager(Game game) {
        this.game = game;
    }

    /**
     * To get the x position of mouse
     * @return an <code>int</code> to get the x position of mouse
     */
    public int getX() {
        return x;
    }
    
    /**
     * To get the y position of mouse
     * @return an <code>int</code> to get the y position of mouse
     */
    public int getY() {
        return y;
    }
    
    /**
     * To get if the left button is clicked
     * @return a <code>boolean</code> to get if the left button is clicked
     */
    public boolean isIzquierdo() {
        return izquierdo;
    }
    
    /**
     * To get if the right button is clicked
     * @return a <code>boolean</code> to get if the right button is clicked
     */
    public boolean isDerecho() {
        return derecho;
    }
    
    /**
     * To set if the left button is clicked
     * @param izquierdo value to set
     */
    public void setIzquierdo(boolean izquierdo) {
        this.izquierdo = izquierdo;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Clicked");
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            izquierdo = true;
            x = e.getX();
            y = e.getY();
            if (game.getPlayer().contains(this)) {
                game.getPlayer().setGrabbed(true);
                // set offset
                game.getPlayer().setOffsetX(getX() - game.getPlayer().getX());
                game.getPlayer().setOffsetY(getY() - game.getPlayer().getY());
            }
            
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            izquierdo = false;
            x = e.getX();
            y = e.getY();
            if (game.getPlayer().isGrabbed()) {
                game.getPlayer().setGrabbed(false);
                game.getPlayer().setReleased(true);
            }
            
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            izquierdo = true;
            x = e.getX();
            y = e.getY();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
