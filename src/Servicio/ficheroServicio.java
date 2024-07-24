/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Brayan
 */
public class ficheroServicio  implements AutoCloseable {
    private BufferedOutputStream out;

    public ficheroServicio(String filePath) throws IOException {
        this.out = new BufferedOutputStream(new FileOutputStream(filePath));
    }

    public void writeByte(int b) throws IOException {
        out.write(b);
    }

    public void writeBytes(byte[] bytes) throws IOException {
        out.write(bytes);
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
