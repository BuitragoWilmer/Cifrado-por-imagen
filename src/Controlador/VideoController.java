/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Estructura;
import Servicio.Bits;
import Servicio.Bytes;
import Servicio.Imagen;
import Servicio.Video;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brayan
 */
public class VideoController {
    
    Video videoServicio = new Video();
    Bits bitsServicio = new Bits();
    Bytes bitesServicio = new Bytes();
    
    
    public void codificarVideo(String Path, int tamañoBloque){
        try {
            
            byte[] video = videoServicio.ConversionABytes(Path);
          
            StringBuilder bits = bitesServicio.ConversionABits(video);
            
            int tamañobits = bits.length();
            
            bitsServicio.GenerarFicheros(bits, tamañoBloque);
   
            // Crear una imagen
            Imagen imagen = new Imagen(tamañobits);
            
            
           // Crear hilos para leer los archivos en paralelo
            Thread thread1 = new Thread(() -> readAndProcessFile(filePath1));
            Thread thread2 = new Thread(() -> readAndProcessFile(filePath2));

            // Iniciar los hilos
            thread1.start();
            thread2.start();

            // Esperar a que los hilos terminen
            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            
            BufferedImage d = imagen.Generar();
            System.out.println("Se ha generado el buffer de la imagen");
            videoBloque=null;
            // Guardar la imagen en un archivo (por ejemplo, en formato PNG)
            File archivo = new File("imagen.png");
            javax.imageio.ImageIO.write(imagen, "png", archivo);
            System.out.println("Imagen creada y guardada correctamente.");     
        } catch (Exception ex) {
            Logger.getLogger(VideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DecodificarVideo(String Path, int tamañoBloque, String extension){
        try {
            int tamañoOriginal = videoService.obtenerTamañoOriginal(Path);
            int bloquesPrincipal= (int)Math.ceil(tamañoOriginal/tamañoBloque);
            
            videoService.DescomponerImagen(Path);
            System.out.println("se ha descompuesto la imagen");
            
        } catch (Exception ex) {
            Logger.getLogger(VideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
}
