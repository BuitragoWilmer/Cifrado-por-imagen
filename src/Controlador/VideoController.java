/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Estructura;
import Servicio.ModificarVideo;
import Servicio.OperacionBinaria;
import Servicio.OperacionBinaria;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brayan
 */
public class VideoController {
    
    ModificarVideo videoService = new ModificarVideo();
    OperacionBinaria operacionBinario = new OperacionBinaria();
    
    public void codificarVideo(String Path, int tamañoBloque){
        try {
            byte[] video = videoService.conversionaBytes(Path);
            System.out.println("se ha convertido en byte: "+video.length);
            StringBuilder bits = operacionBinario.byteToBits(video);
            int tamañobits = bits.length();
            //Libera espacio
            video=null;
            System.out.println("se ha convertido en bits");
            String[] videoBloque = operacionBinario.toNBitBlock(bits, tamañoBloque);
            bits="";
            System.out.println("bloques de a "+tamañoBloque+" generado: "+ videoBloque.length);

            // Crear una imagen a partir del arreglo de bits
            BufferedImage imagen = videoService.crearImagenDesdeBits(videoBloque, tamañobits);
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
