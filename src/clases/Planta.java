/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Jose David
 */
public abstract class Planta extends Servivo{
    String formaHoja;

    public String getFormaHoja() {
        return formaHoja;
    }

    public void setFormaHoja(String formaHoja) {
        this.formaHoja = formaHoja;
    }

    public Planta(String formaHoja, String nombre) {
        super(nombre);
        this.formaHoja = formaHoja;
    }
    
}
