/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jose David
 */
public abstract class Animal extends Servivo {
    Date fechaNacimiento;
   
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
     public Animal(String nombre, Date fechaNacimiento) {
        super(nombre);
        this.fechaNacimiento=fechaNacimiento;
    }
     public abstract void EdadPromedio(Connection cn);

  

  


    


}