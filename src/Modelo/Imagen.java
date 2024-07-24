package Modelo;

import Modelo.Fichero;
import Modelo.EstructuraCodificacion;
import java.awt.image.BufferedImage;

/**
 *
 * @author Brayan
 */
public class Imagen {
    protected static int ancho = 1500;
    protected static int alto;
    private Fichero fichero;
    private BufferedImage imagen;

    public Imagen(Fichero fichero) {
        this.fichero = fichero;
        this.alto = (int) Math.ceil(fichero.Tamaño / 3.0 / ancho);
    }
    
    public BufferedImage getImagen() {
        return imagen;
    }
    
    public void Generar(){
         
        imagen =  new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
        EstructuraCodificacion estructuraImagen = new EstructuraCodificacion(imagen, ancho);
         
        // Procesar el contenido por bloques 
        fichero.CargarContenidoPorPartes(new Fichero.PartesDelArchivoCallback() {
          
            @Override
            public void procesarParte(String parte) {         
                String[] digitos = parte.split(" ");           
                for (int i = 0; i < digitos.length; i += 3) {
                    RGB pixel = new RGB(
                            Integer.parseInt(digitos[i]),
                            i + 1 < digitos.length ? Integer.parseInt(digitos[i + 1]) : 0,
                            i + 2 < digitos.length ? Integer.parseInt(digitos[i + 2]) : 0
                    );                  
                   estructuraImagen.pintarPixel(pixel.Generar());
                }
            }
        }, 50000);
    }
            
}
