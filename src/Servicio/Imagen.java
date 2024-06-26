/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import Modelo.EstructuraCodificacion;
import java.awt.image.BufferedImage;

/**
 *
 * @author Brayan
 */
public class Imagen {
    protected static int ancho = 1500;
    protected static int largo;
    private int cantidadBits;


    public Imagen(int cantidadBits) {
        this.largo = (int)Math.ceil((cantidadBits/24)/ancho)+ 1;
    }

    public int getCantidadBits() {
        return cantidadBits;
    }

    public void setCantidadBits(int cantidadBits) {
        this.cantidadBits = cantidadBits;
    }
    
    
     public BufferedImage Generar(String rutaArchivo){
         
        Fichero fichero = new Fichero(rutaArchivo);
        BufferedImage imagen =  new BufferedImage(ancho,largo,BufferedImage.TYPE_INT_RGB);
        EstructuraCodificacion estructuraImagen = new EstructuraCodificacion(imagen, ancho);
         
        // Procesar el contenido por bloques 
        fichero.CargarContenidoPorPartes(new Fichero.PartesDelArchivoCallback() {
          
            @Override
            public void procesarParte(String parte) {         
                String[] digitos = parte.split(" ");           
                for (int i = 0; i < digitos.length; i += 3) {
                    RGB pixel = new RGB(
                            Integer.parseInt(digitos[i]),
                            i + 1 < digitos.length ? digitos[i + 1] : 0,
                            i + 2 < digitos.length ? digitos[i + 2] : 0
                    );                  
                   estructuraImagen.pintarPixel(pixel.Generar());
                }
            }
        
        }, 50000);
        
        return imagen;
    }
            
}
