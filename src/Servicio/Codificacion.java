/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import java.util.Arrays;
import static java.util.concurrent.ForkJoinTask.invokeAll;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author Brayan
 */
public class Codificacion extends RecursiveAction {
     private static String almacenadortxt = "";
     private static String sobrantetxt = "";
     ManejoArchivoTexto manejadorSobrante = new ManejoArchivoTexto(sobrantetxt);    
     ManejoArchivoTexto manejadorAlmacenador = new ManejoArchivoTexto(almacenadortxt); 
     private static int UMBRAL ; // Ajusta este valor según la cantidad de elementos del array que deseas procesar en paralelo.
     private final String[] cadenas;
     private final int fin;
     private final int  inicio = 0;
     private int mitad ;
    
        // Constructor
        public Codificacion(String[] arrayDeStrings) {
            this.cadenas = arrayDeStrings;
            this.fin = cadenas.length;
            this.mitad = (int)Math.ceil(fin/2);
        }

      public static void asignarVariablesCodificacion(int valorExterno, String pathAlmacenador, String pathSobrante) {
        if (UMBRAL == 0) {
            UMBRAL = valorExterno;      
        }
        if("".equals(almacenadortxt)){
            almacenadortxt = pathAlmacenador;
        }
         if("".equals(sobrantetxt)){
            sobrantetxt = pathSobrante;
        }
    }
        @Override
        protected void compute() {
        // Si la cantidad de elementos en el subarray es menor que el umbral, procesar de manera secuencial
        if (fin - inicio <= UMBRAL) {
                CodificationOfStructure(cadenas);
        } else {
            // Dividir el trabajo en dos tareas más pequeñas
            Codificacion tareaDerecha = new Codificacion(Arrays.copyOfRange(cadenas,mitad, fin));
            Codificacion tareaIzquierda = new Codificacion(Arrays.copyOfRange(cadenas, 0, mitad));
          
            // Ejecutar las tareas en paralelo
            invokeAll(tareaIzquierda, tareaDerecha);
           
        }
    }
        
        
        public void CodificationOfStructure(String[] bloques){
            StringBuilder almacenador = new StringBuilder();
             StringBuilder sobrante = new StringBuilder();

              for (String cadena : bloques) {
                  int longitudCadena = cadena.length();
                  int index = 0;

                  while (index < longitudCadena) {
                      int endIndex = Math.min(index + 8, longitudCadena);              
                      if (endIndex < longitudCadena) {
                           String subcadena = cadena.substring(index, endIndex);
                          almacenador.append(subcadena);
                      }else{
                          String subcadena = cadena.substring(index, longitudCadena);
                          sobrante.append(subcadena);
                      }
                      index += 8;
                  }
                  if(almacenador.length()==10000){
                      manejadorAlmacenador.escribirEnArchivo(almacenador.toString());
                      almacenador= new StringBuilder();
                  }
                  if(sobrante.length()==10000){
                       manejadorSobrante.escribirEnArchivo(sobrante.toString());
                      sobrante= new StringBuilder();
                  }
              }
             manejadorAlmacenador.escribirEnArchivo(almacenador.toString());
             manejadorSobrante.escribirEnArchivo(sobrante.toString());
          }
    }

