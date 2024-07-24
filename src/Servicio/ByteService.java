/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import Modelo.Estructura;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Brayan
 */
public class ByteService{
    
    public ByteService() {
    }
    
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
    
    public void GenerarFicheroBinario(String path){
         File file = new File(path);
         String outputFile = "output.bin"; 

        try (FileInputStream fis = new FileInputStream(file);
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            // Definir un tamaño de búfer para la copia
            byte[] buffer = new byte[4096]; // Puedes ajustar el tamaño del búfer según tus necesidades

            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("Archivo binario generado correctamente: " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public byte ConversionAByte(String bits) {
        // Convierte la cadena de bits a un entero decimal
        int decimalValue = Integer.parseInt(bits, 2);       
        // Convierte el entero decimal a un byte (puede requerir casting)
        byte byteValue = (byte) decimalValue;   
        return byteValue;
    }
    
    public int ConversionADecimal(String binario) {
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
