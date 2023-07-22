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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pkgfinal.Interfaz1;
import pkgfinal.IngresarSerVivo;
/**
 *
 * @author Jose David
 */
public abstract class Servivo {
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Servivo(String nombre) {
        this.nombre = nombre;
    }
    
    public static void IngresarSerVivo (Connection cn,String nom,int p,String m) {
            
        if (cn != null) {
            try {
                Statement st = cn.createStatement();
                // Se insertan datos
                // the mysql insert statement
                String nomb = nom;
                int cant = p;
                String zon = m;
                String query2 = " insert into servivo (nombre, cantidad, zona)"
                        + " values (?, ?, ?)";
                PreparedStatement preparedStmt = cn.prepareStatement(query2);
                preparedStmt.setString(1, nomb);
                preparedStmt.setString(3,zon );
                preparedStmt.setInt(2, cant);
                preparedStmt.execute();
                preparedStmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(Servivo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    public static DefaultTableModel buscarTabla (Connection cn){
   
        String [] datos = {"nombre","cantidad","zona"};
        String [] registros = new String[22];
        DefaultTableModel model = new DefaultTableModel(null,datos);
        if (cn != null) {
            try {
                String sql = " SELECT * FROM servivo";
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    registros[0] = rs.getString("nombre");
                    registros[1] = rs.getString("cantidad");
                    registros[2] = rs.getString("zona");
                   model.addRow(registros);
                }
             
            } catch (SQLException ex) {
                Logger.getLogger(Servivo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
       return model;
    }
    public static ArrayList<String> RevisarZona(Connection cn) {
        
        ArrayList<String> zona = new ArrayList<>();
        try {
            String sql = " SELECT * FROM servivo";
            Statement st1 = (Statement) cn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql);
            while (rs1.next()){
                zona.add(rs1.getString("zona"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Servivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return zona;
    }
    public static ArrayList<String> Revisarnombre(Connection cn) {
        
        ArrayList<String> cantidad = new ArrayList<>();
        try {
            String sql = " SELECT * FROM servivo";
            Statement st1 = (Statement) cn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql);
            while (rs1.next()){
                cantidad.add(rs1.getString("nombre"));
                cantidad.add(rs1.getString("cantidad"));
                
               
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Servivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }
    public static DefaultTableModel ImprimirTabla (Connection cn,int consulta){
        String [] registros = new String[22];
        String a="";
        String b="";
        String c="";
        String d="";
        if (consulta == 1) {
            a="nombre";
            b="fecha";
            c="calorias";
            d="Bovinos";
        }else if (consulta == 2) {
            a="nombre";
            b="fecha";
            c="jugueteFavorito";
            d="Felinos";
        }else if (consulta == 3) {
            a="nombre";
            b="formaHoja";
            c="TipoSuculenta";
            d="Suculentas";
        }else if (consulta == 4) {
            a="nombre";
            b="formaHoja";
            c="calorias";
            d="Verduras";
        }
        String [] datos = {a,b,c};
        
        DefaultTableModel model = new DefaultTableModel(null,datos);
        if (cn != null) {
            try {
                String sql = " SELECT * FROM "+d;
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                       registros[0] = rs.getString(a);
                       registros[1] = rs.getString(b);
                       registros[2] = rs.getString(c);
                   model.addRow(registros);
                }
             
            } catch (SQLException ex) {
                Logger.getLogger(Servivo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
       return model;
    }
    public static String Borrar (Connection cn,String b){
        try {
            String sql = "DELETE FROM servivo where servivo.zona='"+b+"'";
            PreparedStatement preparedStmt = cn.prepareStatement(sql);
            preparedStmt.execute();
            preparedStmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Servivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }
    public static int cantidadRegistro(Connection cn, String b){
        int contador = 0;
        if (cn != null) {  
            try {
                String sql = " SELECT * FROM "+b;
                Statement st = (Statement) cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                  contador = contador + 1;  
                }
            } catch (SQLException ex) {
                Logger.getLogger(Servivo.class.getName()).log(Level.SEVERE, null, ex);
            }
               
    
        }
        
        return contador;
}
}
        
    

