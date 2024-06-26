/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Brayan
 */
public class Fichero {
    
    private String rutaArchivo;

    public Fichero(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
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
    
    interface PartesDelArchivoCallback {
        void procesarParte(String parte);
    }
    
}
