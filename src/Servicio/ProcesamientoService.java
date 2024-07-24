/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import Modelo.Imagen;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Brayan
 */
public class ProcesamientoService {
    
    
    public static void ImagenMultiHilo(Imagen almacenador, Imagen sobrante ){
        // Crear hilos para leer los archivos en paralelo
            Thread generarAlmacenador = new Thread(() -> almacenador.Generar());       
            Thread generarSobrante = new Thread(() -> sobrante.Generar());

            generarAlmacenador.start();
            generarSobrante.start();

            // Esperar a que los hilos terminen
            try {
                generarAlmacenador.join();
                generarSobrante.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    
    public static BufferedImage unificarImagenes(BufferedImage img1, BufferedImage img2) {
        int width = Math.max(img1.getWidth(), img2.getWidth());
        int height = img1.getHeight() + img2.getHeight();

        BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = combinedImage.createGraphics();
        g2d.drawImage(img1, 0, 0, null);
        g2d.drawImage(img2, 0, img1.getHeight(), null);
        g2d.dispose();

    return combinedImage;
    }
}
