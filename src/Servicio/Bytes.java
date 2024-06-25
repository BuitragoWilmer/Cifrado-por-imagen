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
public class Bytes{
    
    public Bytes() {
    }
    
    public StringBuilder ConversionABits(byte[] value) {
        StringBuilder bits = new StringBuilder();
        // Recorrer cada byte en el array de bytes
        for (byte b : value) {
            // Convertir el byte a una cadena binaria de 8 bits
            String binario = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            // Agregar la cadena binaria al resultado
            bits.append(binario);
        }  
        return bits;
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
