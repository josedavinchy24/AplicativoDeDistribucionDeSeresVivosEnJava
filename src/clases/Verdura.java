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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pkgfinal.ConsumoMaximo;
import pkgfinal.ConsumoMinimo;
import static pkgfinal.Interfaz1.cn;

/**
 *
 * @author Jose David
 */
public class Verdura extends Planta implements Comestible{
    int caloriasxporcion;

    public int getCaloriasxporcion() {
        return caloriasxporcion;
    }

    public void setCaloriasxporcion(int caloriasxporcion) {
        this.caloriasxporcion = caloriasxporcion;
    }

    public Verdura(String nombre,String formaHoja,int caloriasxporcion) {
        super(formaHoja, nombre);
        this.caloriasxporcion = caloriasxporcion;
    }
       public static void guardarVerdura(Verdura miconeccion, Connection cn){
        if (cn != null) {
            try {
                Statement st = cn.createStatement();
                // Se insertan datos
                // the mysql insert statement
                String nom = miconeccion.nombre;
                String forma = miconeccion.formaHoja;
                int calorias= miconeccion.caloriasxporcion;
                String query2 = " insert into Verduras (nombre, formaHoja, calorias)"
                        + " values (?, ?, ?)";
                PreparedStatement preparedStmt = cn.prepareStatement(query2);
                preparedStmt.setString(1, nom);
                preparedStmt.setString(2, forma);
                preparedStmt.setInt(3, calorias);
                preparedStmt.execute();
                preparedStmt.close();
                JOptionPane.showMessageDialog(null, "se agrego Informacion Verdura");
            } catch (SQLException ex) {
                Logger.getLogger(Bovino.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
   }
       @Override
    public void consumoMaximo() {
        int acumulador=0;
        int registro = 0;
        String sql="";
        String mostrar ="";
        String traerservivo = (String) ConsumoMaximo.jcCaloriasMaximas.getSelectedItem();
        if (traerservivo == "Verduras") {
                sql = " select Verduras.calorias,Verduras.nombre from Verduras";

            try {
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    
                    int suma = 0;
                    int i = 0;
                    String b = rs.getString("calorias");
                    String r = rs.getString("nombre");
                    int conv = Integer.parseInt(b);
                    while (suma < 1000) {
                        suma = suma + conv;
                        i = i + 1;
                        
                    }
                    
                    acumulador = acumulador + i;
                    registro = registro + 1;
                    mostrar =mostrar + "La cantidad maxima de porciones de " + r + " son : " + i + "\n";
                }
                 int promedio = acumulador/registro;
                 mostrar= mostrar +"\n El consumo maximo promedio de "+promedio+" porciones  ";
                JOptionPane.showMessageDialog(null, mostrar);
            } catch (SQLException ex) {
                Logger.getLogger(Bovino.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void consumoMinimo() {
        int acumulador=0;
        int registro = 0;
        String sql="";
        String mostrar ="";
        String traerservivo = (String) ConsumoMinimo.jcConsumoMin.getSelectedItem();
            if (traerservivo == "Verduras") {
            sql = " select Verduras.calorias,Verduras.nombre from Verduras";

            try {
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    int suma = 0;
                    int i = 0;
                    String b = rs.getString("calorias");
                    String r = rs.getString("nombre");
                    int conv = Integer.parseInt(b);
                    while (suma < 300) {
                        suma = suma + conv;
                        i = i + 1;
                    }
                    acumulador = acumulador + i;
                    registro = registro + 1;
                    mostrar =mostrar + "La cantidad minima de porciones de " + r + " son : " + i + "\n";
                }
                int promedio = acumulador/registro;
                mostrar= mostrar +"\n El consumo maximo promedio de "+promedio+" porciones  ";
                JOptionPane.showMessageDialog(null, mostrar);
            } catch (SQLException ex) {
                Logger.getLogger(Bovino.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
}
