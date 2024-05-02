/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Brayan
 */
public class Estructura {
    
    private int bloques;
    
    private int pixel;
    
    private int bitsSobrantes;
    
     private int bloquePrincipal;
    
 
   

    public Estructura() {  
    }

    public int getBloques() {
        return bloques;
    }

    public void setBloques(int bloques) {
        this.bloques = bloques;
    }

    public int getPixel() {
        return pixel;
    }

    public void setPixel(int pixel) {
        this.pixel = pixel;
    }

    public int getBitsSobrantes() {
        return bitsSobrantes;
    }

    public void setBitsSobrantes(int bitsSobrantes) {
        this.bitsSobrantes = bitsSobrantes;
    }

    public Estructura(int bloques, int pixel, int bitsSobrantes) {
        this.bloques = bloques;
        this.pixel = pixel;
        this.bitsSobrantes = bitsSobrantes;
    }

    public Estructura(int bloques, int pixel, int bitsSobrantes, int bloquePrincipal) {
        this.bloques = bloques;
        this.pixel = pixel;
        this.bitsSobrantes = bitsSobrantes;
        this.bloquePrincipal = bloquePrincipal;
    }

    public int getBloquePrincipal() {
        return bloquePrincipal;
    }

    public void setBloquePrincipal(int bloquePrincipal) {
        this.bloquePrincipal = bloquePrincipal;
    }

}
