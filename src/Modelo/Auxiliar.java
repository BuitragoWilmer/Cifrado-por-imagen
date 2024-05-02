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
public class Auxiliar {
    private int tomadeBloque;
    private int sobrantes;

    public int getTomadeBloque() {
        return tomadeBloque;
    }

    public void setTomadeBloque(int tomadeBloque) {
        this.tomadeBloque = tomadeBloque;
    }

    public int getSobrantes() {
        return sobrantes;
    }

    public void setSobrantes(int sobrantes) {
        this.sobrantes = sobrantes;
    }

    public Auxiliar(int tomadeBloque, int sobrantes) {
        this.tomadeBloque = tomadeBloque;
        this.sobrantes = sobrantes;
    }
    
}
