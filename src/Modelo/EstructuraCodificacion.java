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
    private int Y;
    private int X;
    private BufferedImage imagen;

    public EstructuraCodificacion(int Y, int X, BufferedImage imagen) {
        this.Y = Y;
        this.X = X;
        this.imagen = imagen;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }
    
    
}
