/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Brayan
 */
public class Fichero {
    
    private String rutaArchivo;
    
    public int Tamaño;

    public Fichero(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.Tamaño=CalcularTamaño();
    }
    
    public void CargarContenidoPorPartes(PartesDelArchivoCallback callback, int tamanoBloque) {
        char[] buffer = new char[tamanoBloque];

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            int caracteresLeidos;

            while ((caracteresLeidos = br.read(buffer, 0, tamanoBloque)) != -1) {
                // Llamada al callback con el bloque actual
                callback.procesarParte(new String(buffer, 0, caracteresLeidos));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private int CalcularTamaño(){
        int bytes=0;
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            while ((line = lector.readLine()) != null) {
                bytes += line.split("\\s+").length;
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
    
    interface PartesDelArchivoCallback {
        void procesarParte(String parte);
    }
    
}
