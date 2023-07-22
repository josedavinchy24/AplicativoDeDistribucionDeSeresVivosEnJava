/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

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
import pkgfinal.ConsumoMinimo;
import pkgfinal.ConsumoMaximo;
import static pkgfinal.Interfaz1.cn;


/**
 *
 * @author Jose David
 */
public class Bovino extends Animal implements Comestible{
    int caloriasxporcion;
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    public int getCaloriasxporcion() {
        return caloriasxporcion;
    }

    public void setCaloriasxporcion(int caloriasxporcion) {
        this.caloriasxporcion = caloriasxporcion;
    }

    public Bovino(String nombre,Date fechaNacimiento,int caloriasxporcion) {
        super(nombre, fechaNacimiento);
        this.caloriasxporcion = caloriasxporcion;
    }

   public static void guardarBovino(Bovino miconeccion, Connection cn){
        if (cn != null) {
            try {
                Statement st = cn.createStatement();
                // Se insertan datos
                // the mysql insert statement
                String nom = miconeccion.nombre;
                Date fecha = miconeccion.fechaNacimiento;
                int calorias= miconeccion.caloriasxporcion;
                String conv = sdf.format(fecha);
                String query2 = " insert into Bovinos (nombre, fecha, calorias)"
                        + " values (?, ?, ?)";
                PreparedStatement preparedStmt = cn.prepareStatement(query2);
                preparedStmt.setString(1, nom);
                preparedStmt.setString(2, conv);
                preparedStmt.setInt(3, calorias);
                preparedStmt.execute();
                preparedStmt.close();
                JOptionPane.showMessageDialog(null, "se agrego Informacion Bovino");
                
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
        if (traerservivo == "Bovinos") {
                sql = " select Bovinos.calorias,Bovinos.nombre from Bovinos";

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
            if (traerservivo == "Bovinos") {
                sql = " select Bovinos.calorias,Bovinos.nombre from Bovinos";

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
            String sql = " SELECT * FROM Bovinos";
            Statement st1 = (Statement) cn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql);
            while (rs1.next()){
                int suma = 0;
                registros[0]=rs1.getString("nombre");
                registros[1]=rs1.getString("fecha");
                String dat = registros[1];
                String nom =registros[0];
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
            guardar = guardar + "\n La edad promedio de los Bovinos es "+promedio+" años  ";
           JOptionPane.showMessageDialog(null, guardar);
        } catch (SQLException ex) {
            Logger.getLogger(Bovino.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    }


    


    
}
