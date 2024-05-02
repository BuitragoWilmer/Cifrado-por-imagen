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
public class OperacionMatematica {
    
    // Función para verificar si un número es par
    public boolean esPar(int numero) {
        // Un número es par si su residuo al dividirlo por 2 es 0
        return numero % 2 == 0;
    }
    
     private int obtenerMaximoMultiploDe8(int n) {
        // Calcula la cantidad de múltiplos de 8 antes de n
        int cantidadMultiplos = n / 8;
        
        // El máximo múltiplo de 8 menor que n es el último múltiplo
        // Se resta 1 a la cantidad para obtener el múltiplo anterior
        int maximoMultiplo = cantidadMultiplos * 8 - 8;

        return maximoMultiplo;
    }
}
