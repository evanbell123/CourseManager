/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import coursemanager.Controller;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author evanb
 */
public class Main {
    public static void main(String[] args){
        
        Controller controller = new Controller();
        
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(
                        "javax.swing.plaf.metal.MetalLookAndFeel");
                //  "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                //UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            }
            new CMFrame(controller).setVisible(true);
        });

    }
}
