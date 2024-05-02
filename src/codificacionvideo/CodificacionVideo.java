/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacionvideo;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import vista.Principal;

/**
 *
 * @author Brayan
 */
public class CodificacionVideo {

    private static final Principal principal = new Principal();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException{
        // TODO code application logic here
        UIManager.setLookAndFeel(new FlatLightLaf());
        SwingUtilities.updateComponentTreeUI(principal);
        principal.setVisible(true);
        
        // Obtener el tamaño de la pantalla
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            // Obtener el tamaño de la ventana
            Dimension windowSize = principal.getSize();

            // Calcular la posición central
            int x = (screenSize.width - windowSize.width) / 2;
            int y = (screenSize.height - windowSize.height) / 2;

            // Establecer la posición de la ventana
            principal.setLocation(x, y);
    }
    
    
}
