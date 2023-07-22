/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static clases.Bovino.sdf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose David
 */
public class Suculenta extends Planta {
    String tipoSuculenta;



    public String getTipoSuculenta() {
        return tipoSuculenta;
    }

    public void setTipoSuculenta(String tipoSuculenta) {
        this.tipoSuculenta = tipoSuculenta;
    }
       public Suculenta(String nombre,String formaHoja,String tipoSuculenta) {
        super(formaHoja, nombre);
        this.tipoSuculenta = tipoSuculenta;
    }
      public static void guardarSuculenta(Suculenta miconeccion, Connection cn) {
        if (cn != null) {
            try {
                Statement st = cn.createStatement();
                // Se insertan datos
                // the mysql insert statement
                String nom = miconeccion.nombre;
                String forma = miconeccion.formaHoja;
                String tipo = miconeccion.tipoSuculenta;
                String query2 = " insert into Suculentas (nombre, formaHoja, tipoSuculenta)"
                        + " values (?, ?, ?)";
                PreparedStatement preparedStmt = cn.prepareStatement(query2);
                preparedStmt.setString(1, nom);
                preparedStmt.setString(2, forma);
                preparedStmt.setString(3, tipo);
                preparedStmt.execute();
                preparedStmt.close();
                JOptionPane.showMessageDialog(null, "se agrego Informacion Suculenta");
            } catch (SQLException ex) {
                Logger.getLogger(Bovino.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
  
}
