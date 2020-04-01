/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breaker;

/**
 *
 * @author antoniomejorado
 */
import javax.swing.JFrame;
import java.awt.EventQueue;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Breaker extends JFrame {

    public Breaker() {
        initUI();
    }
    
    private void initUI() {

        add(new Board());
        setTitle("Breakout");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {

            Breaker game = new Breaker();
            game.setVisible(true);
        });
    }
}
