/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

/**
 *
 * @author Brayan
 */
import java.io.*;

public class ManejoArchivoTexto {

    private String rutaArchivo;

    // Constructor que establece la ruta del archivo en el directorio de trabajo actual
    public ManejoArchivoTexto(String nombreArchivo) {
        this.rutaArchivo = System.getProperty("user.dir") + File.separator + nombreArchivo;
    }

    // Método para escribir en el archivo
    public void escribirEnArchivo(String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            writer.write(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar el contenido del archivo
    public void actualizarArchivo(String nuevoContenido) {
        try (PrintWriter writer = new PrintWriter(rutaArchivo)) {
            writer.println(nuevoContenido);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para leer el contenido del archivo
    public StringBuilder leerArchivo() {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido;
    }

    // Método para eliminar el archivo
    public void eliminarArchivo() {
        File archivo = new File(rutaArchivo);
        if (!archivo.delete()) {
             System.out.println("No se pudo eliminar el archivo.");
        }
    }
    
    
    //metodo para cargar los primeros caracteres
    public String loadFirstNCharacters(int n) {
        StringBuilder result = new StringBuilder();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            char[] buffer = new char[1024];
            int charsRead;
            
            while ((charsRead = br.read(buffer)) != -1 && result.length() < n) {
                result.append(buffer, 0, Math.min(charsRead, n - result.length()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return result.toString();
    }
    
}

