/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import Modelo.Estructura;
import Modelo.EstructuraCodificacion;
import Servicio.Fichero.PartesDelArchivoCallback;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import javax.imageio.ImageIO;

/**
 *
 * @author Brayan
 */
public class Video {
  
    //private  EstructuraCodificacion estructuraPrincipal= new EstructuraCodificacion(0, 0, new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB));
    private boolean firstPixel=false;
    
    public byte[] ConversionABytes(String path) throws Exception{
       try{
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytesArray = new byte[(int) file.length()];
            // Lee los bytes del archivo y los almacena en el array de bytes
            fileInputStream.read(bytesArray);
            fileInputStream.close();
            return bytesArray;
       }catch(FileNotFoundException i){
           System.out.println("No se ha encontrado el archivo especificado");
       }
        return null;
    }
    
    public int obtenerTamañoOriginal(String path) throws Exception{
       try {
            // Ruta de la imagen
            String imagePath = path;

            // Cargar la imagen
            BufferedImage image = ImageIO.read(new File(imagePath));

            int pixel = image.getRGB(0, 0);
            
            // Obtener componentes RGB
            int red = (pixel >> 16) & 0xFF;
            int green = (pixel >> 8) & 0xFF;
            int blue = pixel & 0xFF;
                        
            return operacionBinario.convertRGBToInt(red,green,blue);
            // Iterar a través de los píxeles y descomponer en componentes RGB
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       return 0;
    }
    
     public void DescomponerImagen (String path) throws Exception{
       try {
            // Ruta de la imagen
            String imagePath = path;

            // Cargar la imagen
            BufferedImage image = ImageIO.read(new File(imagePath));

            // Obtener dimensiones de la imagen
            int width = image.getWidth();
            int height = image.getHeight();
            int tamaño = 0;

            // Iterar a través de los píxeles y descomponer en componentes RGB
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if(y==0 && x==0){
                        int pixel = image.getRGB(x, y);
                    // Obtener componentes RGB
                        int red = (pixel >> 16) & 0xFF;
                        int green = (pixel >> 8) & 0xFF;
                        int blue = pixel & 0xFF;
                        
                        tamaño= operacionBinario.convertRGBToInt(red,green,blue);
                        
                        System.out.println("tamaño:"+tamaño);
                    }
                    int pixel = image.getRGB(x, y);

                    // Obtener componentes RGB
                    int red = (pixel >> 16) & 0xFF;
                    int green = (pixel >> 8) & 0xFF;
                    int blue = pixel & 0xFF;

                    // Hacer algo con los componentes RGB (por ejemplo, imprimirlos)
                    System.out.println("Pixel en (" + x + ", " + y + "): " +
                            "R: " + red + ", G: " + green + ", B: " + blue);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
     
    public String ensureFixedLength(String input, int targetLength) {
        if (input.length() >= targetLength) {
            // Si la cadena ya tiene o supera la longitud deseada, truncar
            return input.substring(0, targetLength);
        } else {
            // Si la cadena es más corta, agregar espacios en blanco al final
            StringBuilder paddedString = new StringBuilder(input);
            while (paddedString.length() < targetLength) {
                paddedString.append('0');
            }
            return paddedString.toString();
        }
    }
   

 
}
