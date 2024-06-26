/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import Modelo.Auxiliar;
import Modelo.Estructura;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Brayan
 */
public class Bits{
    
    private OperacionMatematica serviceMath;
    protected int N = 0;
    protected int cantidadBloqueAux = 0;

    public Bits() {
        this.serviceMath = new OperacionMatematica();
    }
    
    public byte ConversionAByte(String bits) {
        // Convierte la cadena de bits a un entero decimal
        int decimalValue = Integer.parseInt(bits, 2);       
        // Convierte el entero decimal a un byte (puede requerir casting)
        byte byteValue = (byte) decimalValue;   
        return byteValue;
    }
    
    
     public void GenerarFicheros(StringBuilder bits, int dimensionBloque){
        try {
            // Ruta al ejecutable del script en C++
            String rutaEjecutable = "Division.exe"; 
            
            // Crear el proceso para ejecutar el script en C++
            ProcessBuilder pb = new ProcessBuilder(rutaEjecutable, bits.toString(),String.valueOf(dimensionBloque));

            // Iniciar el proceso
            Process proceso = pb.start();
            
            // Esperar a que el proceso termine
            int resultado = proceso.waitFor();
            
            // Imprimir el resultado
            System.out.println("El script en C++ ha finalizado con resultado: " + resultado);
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
     }
    
    public String[] ConversionABloque(String bits, int tamaño){  
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
}
