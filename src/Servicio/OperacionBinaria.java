/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import Modelo.Auxiliar;
import Modelo.Estructura;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Brayan
 */
public class OperacionBinaria {
    
    private OperacionMatematica serviceMath;
    protected int N = 0;
    protected int cantidadBloqueAux = 0;

    public OperacionBinaria() {
        this.serviceMath = new OperacionMatematica();
    }
    
    public StringBuilder byteToBits(byte[] value) {
        StringBuilder bits = new StringBuilder();
        // Recorrer cada byte en el array de bytes
        for (byte b : value) {
            // Convertir el byte a una cadena binaria de 8 bits
            String binary = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            // Agregar la cadena binaria al resultado
            bits.append(binary);
        }  
        return bits;
    }
    
    
    public byte bitsToByte(String bits) {
        // Convierte la cadena de bits a un entero decimal
        int decimalValue = Integer.parseInt(bits, 2);       
        // Convierte el entero decimal a un byte (puede requerir casting)
        byte byteValue = (byte) decimalValue;   
        return byteValue;
    }
    
    public int codificacionColor(StringBuilder bloque){
        int red=0, green=0, blue=0;
        int longitud = bloque.length();
        
        if(longitud<24){
            int diferencia = 24 - longitud ;
            System.out.println("entra long:"+longitud);
            for (int i = 0; i < diferencia; i++) {
                bloque.append('0'); // Agrega espacios en blanco
            }
         }
        
        String rojo = bloque.substring(0, 8);
        String verde = bloque.substring(8, 16);
        String azul = bloque.substring(16);
        
        red=binarioADecimal(rojo);
        green=binarioADecimal(verde);
        blue=binarioADecimal(azul);
        
        if(red>255 || green>255 || blue>255){
            System.out.print("Desborde de color RGB");
        }
        Color pixel = new Color(red, green, blue); // Color 
              
        int rgb = pixel.getRGB();
        return rgb;
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
    
    
    public String[] toNBitBlock(String bits, int tamaño){  
        String[] videoNbits = new String[ (int) Math.ceil(bits.length()/tamaño)+1];
        int cont =0;
         for (int i = 0; i < bits.length(); i+=tamaño) {
            int end =Math.min(i+tamaño, bits.length());
            String bloque = bits.substring(i, end);
            videoNbits[cont]=bloque;
            cont++;
        }
         if(videoNbits[videoNbits.length-1]==null){
               // Crear un nuevo vector con un tamaño uno menos que el original
                 String[] videobits = Arrays.copyOfRange(videoNbits, 0, videoNbits.length - 1);
                  return videobits;
         }
         
         StringBuilder paddedString = new StringBuilder(videoNbits[videoNbits.length-1]);
         if(paddedString.length()<tamaño){
              int diferencia = tamaño - paddedString.length();
                for (int i = 0; i < diferencia; i++) {
                    paddedString.append('0'); // Agrega espacios en blanco
                }
                videoNbits[videoNbits.length-1]=paddedString.toString();
         }
         
         return videoNbits;
    } 
    
    public int binarioADecimal(String binario) {
        int decimal = 0;
        int longitud = binario.length();
        // Recorrer el string de derecha a izquierda y convertir a decimal
        for (int i = 0; i < longitud; i++) {
            char bit = binario.charAt(longitud - 1 - i);
            if (bit == '1') {
                decimal += (1 << i);
            } else if (bit != '0' && bit != '\n' && bit != '\r') {
                throw new IllegalArgumentException("El string contiene caracteres inválidos: " + bit);
            }            
        }
        return decimal;
    }
}
