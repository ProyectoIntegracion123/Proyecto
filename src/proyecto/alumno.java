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
public class alumno {
    String nombre;
    String apellido;
    int idFacultad;
    int edad;
    String email;
    String ciclo;
    int tiempopractica;
    String codigoalumno;

    /*public alumno(String nombre, String apellido, int idFacultad, int edad, String email, String ciclo, int tiempopractica, String codigoalumno) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.idFacultad = idFacultad;
        this.edad = edad;
        this.email = email;
        this.ciclo = ciclo;
        this.tiempopractica = tiempopractica;
        this.codigoalumno = codigoalumno;
    }*/
    
    public String buscarAlumnos(String codigo){
        String query="select a.codalu, a.NomAlu, a.ApaAlu, a.EmaAlu, a.ciclo, a.TimePract, a.EdadAlu "
                + "from alumno as a inner join administrador as AD on AD.idF= a.idF "
                + "where a.codalu="+ codigo;
        String texto="";
                String codalu = "";
                String nombre= "";
                String apellido="" ;
                String email= "";
                String ciclo= "";
                int tiempo= 0;
                int edad= 0;
        try{
            Connection conexion=null;
            Conexion Con=new Conexion();
            conexion=Con.getConnection();
            Statement stmt1 = null;
            stmt1 = conexion.createStatement();
            
            ResultSet result1 = stmt1.executeQuery(query);
            System.out.println("ALUMNO ENCONTRADO");
            while(result1.next()){
                 codalu = result1.getString("codalu");
                 nombre= result1.getString("NomAlu");
                 apellido= result1.getString("ApaAlu");
                 email= result1.getString("EmaAlu");
                 ciclo= result1.getString("ciclo");
                 tiempo= result1.getInt("TimePract");
                 edad= result1.getInt("EdadAlu");
                texto= texto + "CODIGO:"+codalu+ ",NOMBRE:"+nombre+",APELLIDO:"+apellido;
                texto=texto + "EMAIL:"+email+",CICLO:"+ ciclo+",TIEMPO:"+tiempo+",EDAD:"+edad;
            }
           System.out.println(codalu+nombre+apellido+email+ciclo+tiempo+edad);
          
        }catch(Exception e){
            System.out.println(e);
        }
        return texto;
    }
}
