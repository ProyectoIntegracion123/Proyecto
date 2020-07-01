/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class administrador {
    String nombre;
    String apellido;
    String usuario;
    String password;
    int idFacultad;

    /*public administrador(String nombre, String apellido, String usuario, String password, int idFacultad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.password = password;
        this.idFacultad = idFacultad;
    }*/
     
    public String login(String user,String password){
        String texto="";
        String query="select * from administrador where usuario='"+user+"' and passw='"+password+"'";
                String nombre ="";
                String apellido="";
                String usuario="";
                int idfacultad=0;
        try{
            Connection conexion=null;
            Conexion Con=new Conexion();
            conexion=Con.getConnection();
            Statement stmt1 = null;
            stmt1 = conexion.createStatement();
            ResultSet result1 = stmt1.executeQuery(query);
            while(result1.next()){
                 nombre = result1.getString("nombre");
                 apellido=result1.getString("apellido");
                 usuario=result1.getString("usuario");
                 idfacultad=result1.getInt("idF");
                
            }
            System.out.println(nombre+apellido+usuario+idfacultad);
            administrador ADMIN= new administrador();
            administrador.this.nombre=nombre;
            administrador.this.apellido=apellido;
            administrador.this.usuario=usuario;
            administrador.this.idFacultad=idfacultad;
        }catch(Exception e){
            texto="ERROR";
            System.out.println(e);
        }
        return texto;
    }
}
