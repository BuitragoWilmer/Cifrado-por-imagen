/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import Modelo.Estructura;
import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Brayan
 */
public class BitsService{
    
    private OperacionMatematica serviceMath;
    private ficheroServicio fs;
    private StringBuilder bitsVideo;
    
    public BitsService() throws IOException {
        this.serviceMath = new OperacionMatematica();
        this.fs = new ficheroServicio("bitsVideo.bin");
    }
    
    public void EscribirBitsenFichero(byte[] bytes) throws IOException {
        // Recorrer cada byte en el array de bytes
        System.out.println("byte: "+bytes[1]);
        fs.writeBytes(bytes);
        System.gc();
    }

      public void EscribirBytesenFichero(byte[] bytes) throws IOException {
        // Recorrer cada byte en el array de bytes
        System.out.println("byte: "+bytes[1]);
         try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("bitsVideo.bin")))) {
            for (byte b : bytes) {
                String bitString = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
                writer.write(bitString);
            }
        }
    }
    
    public void ConversionABits(byte[] value) {
        // Recorrer cada byte en el array de bytes
        for (byte b : value) {
            // Convertir el byte a una cadena binaria de 8 bits
            String binario = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            // Agregar la cadena binaria al resultado
            bitsVideo.append(binario);
        }  
        value = null;
        System.gc(); 
    }
    
     public void GenerarFicheros(int dimensionBloque){
        try {
            // Ruta al ejecutable del script en C++
            String rutaEjecutable = "Division.exe"; 
            
             System.out.println("tamaño: " + bitsVideo.length());
             String[] command = {rutaEjecutable , bitsVideo.toString(), Integer.toString(dimensionBloque)};
            // Crear el proceso para ejecutar el script en C++
            ProcessBuilder pb = new ProcessBuilder(command);

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
}

            