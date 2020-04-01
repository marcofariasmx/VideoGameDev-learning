
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MARBROCU
 */
public class KeyManager implements KeyListener{
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean pause;
    public boolean save;
    public boolean load;
    
    private boolean keys[]; //store all the flags for every key
    public KeyManager(){
        keys= new boolean[256];
    }

    /**
     * enable o disable moves on every tick
     */
    public void tick(){
        up=keys[KeyEvent.VK_UP];
        down= keys[KeyEvent.VK_DOWN];
        left= keys[KeyEvent.VK_LEFT];
        right= keys[KeyEvent.VK_RIGHT];
        pause= keys[KeyEvent.VK_P];
        save= keys[KeyEvent.VK_G];
        load= keys[KeyEvent.VK_C];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
         keys[e.getKeyCode()]=true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()]=false;
    }
    
}
