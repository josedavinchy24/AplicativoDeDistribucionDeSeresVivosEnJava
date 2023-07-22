/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static clases.Bovino.sdf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pkgfinal.Interfaz1;

/**
 *
 * @author Jose David
 */
public class Felino extends Animal {
    String juguetefavorito;
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public String getJuguetefavorito() {
        return juguetefavorito;
    }

    public void setJuguetefavorito(String juguetefavorito) {
        this.juguetefavorito = juguetefavorito;
    }

  public Felino(String nombre,Date fechaNacimiento,String juguetefavorito) {
        super(nombre, fechaNacimiento);
        this.juguetefavorito = juguetefavorito;
    }
   public static void guardarFelino(Felino miconeccion, Connection cn){
        if (cn != null) {
            try {
                Statement st = cn.createStatement();
                // Se insertan datos
                // the mysql insert statement
                String nom = miconeccion.nombre;
                Date fecha = miconeccion.fechaNacimiento;
                String juguete = miconeccion.juguetefavorito;
                String conv = sdf.format(fecha);
                String query2 = " insert into Felinos (nombre, fecha, jugueteFavorito)"
                        + " values (?, ?, ?)";
                PreparedStatement preparedStmt = cn.prepareStatement(query2);
                preparedStmt.setString(1, nom);
                preparedStmt.setString(2, conv);
                preparedStmt.setString(3, juguete);
                preparedStmt.execute();
                preparedStmt.close();
                JOptionPane.showMessageDialog(null, "se agrego Informacion Felino");
            } catch (SQLException ex) {
                Logger.getLogger(Felino.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
}
   @Override
    public void EdadPromedio(Connection cn){ 
        int acumulador=0;
        int registro = 0;
        String guardar ="";
        String [] registros = new String[22];
        java.util.Date actual = new Date();
        String fechactual = sdf.format(actual);
        String[] conv = fechactual.split("-");
        int ahno = Integer.parseInt(conv[2]);
        int mes = Integer.parseInt(conv[1]);
        int dia =Integer.parseInt(conv[0]);
        
        try {
            String sql = " SELECT * FROM Felinos";
            Statement st1 = (Statement) cn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql);
            while (rs1.next()){
                int suma = 0;
                registros[0]=rs1.getString("nombre");
                registros[1]=rs1.getString("fecha");
                String nom =registros[0];
                String dat = registros[1];
                String[] numeros = dat.split("-");
                int ahno2 = Integer.parseInt(numeros[2]);
                int mes2 = Integer.parseInt(numeros[1]);
                int dia2 = Integer.parseInt(numeros[0]);
                suma = ahno-ahno2;
                int restames= mes-mes2;
                int restadia= dia - dia2;
                if (restames<0 || (restames==0 && restadia<0)){
                    suma=suma-1;
                }
                acumulador = acumulador + suma;
                registro = registro + 1;
                guardar = guardar +"la edad de "+nom+" es: "+suma+"   \n";
            }
            int promedio = acumulador/registro;
            guardar = guardar + "\n La edad promedio de los Felinos es "+promedio+" años  ";
           JOptionPane.showMessageDialog(null, guardar);
        } catch (SQLException ex) {
            Logger.getLogger(Felino.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    }
}