/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import java.awt.Color;

/**
 *
 * @author Brayan
 */
public class RGB {
    
    private int rojo;
    private int verde;
    private int azul;

    public RGB(int rojo, int verde, int azul) {
        this.rojo = rojo;
        this.verde = verde;
        this.azul = azul;
    }

    
    public int Generar(){
      
        if(rojo>255 || verde>255 || azul>255){
            System.out.print("Desborde de color RGB");
        }
        Color pixel = new Color(rojo, verde, azul);
        
        return pixel.getRGB();
    }
    
    public int convertRGBToInt(int red, int green, int blue) {
        // Asegurar que los valores estén en el rango de 0 a 255
        red = Math.max(0, Math.min(255, red));
        green = Math.max(0, Math.min(255, green));
        blue = Math.max(0, Math.min(255, blue));

        // Usar operaciones de bits para combinar los componentes RGB en un solo entero
        int rgbInt = (red << 16) | (green << 8) | blue;

        return rgbInt;
    }
}
