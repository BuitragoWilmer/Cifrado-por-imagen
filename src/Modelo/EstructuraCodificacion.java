/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.image.BufferedImage;

/**
 *
 * @author Brayan
 */
public class EstructuraCodificacion {
    private int Y = 0;
    private int X = 0;
    private BufferedImage imagen;
    private int ancho;

    public EstructuraCodificacion(BufferedImage imagen,int ancho) {
        this.imagen = imagen;
        this.ancho = ancho;
    }

    public int getY() {
        return Y;
    }

    public int getX() {
        return X;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }
    
    public void pintarPixel(int color){
        if(X<ancho){
            X++;
        }else{
            Y++;
            X=0;
        }
        imagen.setRGB(X, Y, color);
    }
}
