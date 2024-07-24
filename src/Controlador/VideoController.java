/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Estructura;
import Servicio.BitsService;
import Servicio.ByteService;
import Modelo.Fichero;
import Modelo.Imagen;
import Servicio.ProcesamientoService;
import Servicio.Video;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brayan
 */
public class VideoController {
    
    BitsService bitsServicio;
    ByteService bitesServicio = new ByteService();
    ProcesamientoService procesar = new ProcesamientoService();

    public VideoController() throws IOException {
        this.bitsServicio = new BitsService();
    }
    
    public void codificarVideo(String Path, int tamañoBloque){
        try {
            
            bitesServicio.GenerarFicheroBinario(Path);
            byte[] video = bitesServicio.ConversionABytes(Path);
          
            bitsServicio.ConversionABits(video);
            bitsServicio.EscribirBitsenFichero(video);
            bitsServicio.GenerarFicheros(tamañoBloque);
   
            Fichero almacenador = new Fichero("almacenador.txt");
            Fichero sobrante = new Fichero("Sobrante.txt");
            
            Imagen almacenadorImg = new Imagen(almacenador);
            Imagen sobranteImg = new Imagen(sobrante);
            
            procesar.ImagenMultiHilo(almacenadorImg, sobranteImg);
            
            BufferedImage imagenCompleta = procesar.unificarImagenes(almacenadorImg.getImagen(), sobranteImg.getImagen());
            
            File archivo = new File("imagen.png");
            javax.imageio.ImageIO.write(imagenCompleta, "png", archivo);
      
        } catch (Exception ex) {
            Logger.getLogger(VideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DecodificarVideo(String Path, int tamañoBloque, String extension){
      /*  try {
            int tamañoOriginal = videoService.obtenerTamañoOriginal(Path);
            int bloquesPrincipal= (int)Math.ceil(tamañoOriginal/tamañoBloque);
            
            videoService.DescomponerImagen(Path);
            System.out.println("se ha descompuesto la imagen");
            
        } catch (Exception ex) {
            Logger.getLogger(VideoController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
}
