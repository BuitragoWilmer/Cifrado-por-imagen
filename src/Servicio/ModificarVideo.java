/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import Modelo.Estructura;
import Modelo.EstructuraCodificacion;
import Servicio.CargaPerezosa.PartesDelArchivoCallback;
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
public class ModificarVideo {
    private OperacionBinaria operacionBinario = new OperacionBinaria();
    private  EstructuraCodificacion estructuraPrincipal= new EstructuraCodificacion(0, 0, new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB));
    private boolean firstPixel=false;
    public byte[] conversionaBytes(String path) throws Exception{
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
    
   public BufferedImage crearImagenDesdeBits(String[] bloques,int bits) {
       
        // Tamaño de la imagen
        int width = 1500; // Ancho en píxeles
        int height =(int)Math.ceil((bits/24)/width)+ 1; // Altura en píxeles      
        int cont=0;
        int contSobrante=0;
        int longitudPrincipal=0;
        int longitudSobrante=0;
        
        BufferedImage imagen = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        //codificacion del tamaño original
        String codificacionTamaño = Integer.toBinaryString(bits);
        
        codificacionTamaño = ensureFixedLength(codificacionTamaño, 48);
        int color1 = operacionBinario.codificacionColor(new StringBuilder(codificacionTamaño.substring(0, 24)));
        int color2 = operacionBinario.codificacionColor(new StringBuilder(codificacionTamaño.substring(24, 48)));
        imagen.setRGB(0, 0, color1);
        imagen.setRGB(1, 0, color2);
        
        
        
        String pathAlmacenador = "almacenador.txt";
        String pathSobrante = "Sobrante.txt";
        
        ManejoArchivoTexto archivoAlmacenador = new ManejoArchivoTexto(pathAlmacenador);
        ManejoArchivoTexto archivoSobrante = new ManejoArchivoTexto(pathSobrante); 
        
        //elimina los archivos si estos existen 
        archivoAlmacenador.eliminarArchivo();
        archivoSobrante.eliminarArchivo();
        
       //asigna el umbreal para la codificacion
        Codificacion.asignarVariablesCodificacion((int)Math.ceil(bloques.length/2), pathAlmacenador, pathSobrante);
        
        // Crea la codificacion y el pool de hilos       
        Codificacion codificarEstructura = new Codificacion(bloques);
        
        //Limpiar memoria
        bloques=null;
        ForkJoinPool pool = new ForkJoinPool();

        // Ejecutar la tarea en el pool de hilos
        pool.invoke(codificarEstructura);

        CargaPerezosa archivo = new CargaPerezosa(pathAlmacenador);
        
        // Procesar el contenido por bloques de 200,000 caracteres
        archivo.procesarContenido(new PartesDelArchivoCallback() {
           EstructuraCodificacion estructura= new EstructuraCodificacion(0, 0, imagen);
            @Override
            public void procesarParte(String parte) {
                // Aquí puedes realizar operaciones con la parte actual del archivo
                estructura= GenerarImagen(width, height, parte, estructura);
                asignacionEstructura (estructura);
            }
        
        }, 180000);

        // completar fila 
        String filaInicial = archivoSobrante.loadFirstNCharacters((width-estructuraPrincipal.getX())*24);
        for (int x = estructuraPrincipal.getX(); x < 1500; x++) { 
            if(contSobrante<filaInicial.length()){
                int endIndex = Math.min(contSobrante + 24, filaInicial.length());
                StringBuilder pixel = new StringBuilder(filaInicial.substring(contSobrante, endIndex));
                int color = operacionBinario.codificacionColor(pixel);
                estructuraPrincipal.getImagen().setRGB(x, estructuraPrincipal.getY(), color);            
                contSobrante+=24;  
            }else{
                System.out.println("aqui");
            }
        }        
        estructuraPrincipal.setY(estructuraPrincipal.getY()+1);
        filaInicial= null;
        
        StringBuilder sobrante = archivoSobrante.leerArchivo();
        sobrante =new StringBuilder(sobrante.substring((width-estructuraPrincipal.getX())*24));
        cont=0;
        longitudPrincipal=sobrante.length();
        for (int y = estructuraPrincipal.getY(); y < height; y++) {
            for (int x = 0; x < width; x++) { 
                  //Genera con los almacenados
                if(cont<longitudPrincipal){
                    int endIndex = Math.min(cont + 24, longitudPrincipal);
                    StringBuilder pixel = new StringBuilder(sobrante.substring(cont, endIndex));
                    int color = operacionBinario.codificacionColor(pixel);
                    imagen.setRGB(x, y, color);               
                    cont+=24;  
                }else {
                   imagen.setRGB(x, y, -1); 
                }
            }        
        } 

        return estructuraPrincipal.getImagen();
    }
   
    public void asignacionEstructura (EstructuraCodificacion p){
        estructuraPrincipal=p;
    }
   
   public EstructuraCodificacion GenerarImagen(int width, int height, String cadena, EstructuraCodificacion estructura){
       int cont=0; 
       int longitud=cadena.length();
       boolean flag =false;
        
       for (int y = estructura.getY(); y < height; y++) {           
            for (int x = 0; x < width; x++) { 
                  //Genera con los almacenados
                 if(y==0  && x==0){
                     firstPixel=true;
                     continue;
                 }
                 if(y==0  && x==1){
                     firstPixel=true;
                     continue;
                 }
                if(cont<longitud){
                    int endIndex = Math.min(cont + 24, longitud);
                    StringBuilder pixel = new StringBuilder(cadena.substring(cont, endIndex));
                    int color = operacionBinario.codificacionColor(pixel);
                    estructura.getImagen().setRGB(x, y, color);               
                    cont+=24;
                }else{
                    estructura.setX(x);
                    estructura.setY(y);
                    return estructura;
                }
            }        
        } 
        return estructura;
   }

}
