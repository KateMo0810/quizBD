/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Labing
 */
public class daoEstudiante {
    private daoEscuela daoe;
    public ArrayList<Estudiante> listar(int nomEscuela) throws SQLException, URISyntaxException{
        daoe= new daoEscuela();
        int codigo=daoe.getEscuela(nomEscuela).getCodigo() ;
        ArrayList<Estudiante> personas= null;
       
       String query="SELECT estudiante.codigo,estudiante.nombre,AVG(nota)AS promedio FROM registro JOIN estudiante ON registro.codEstudiante=estudiante.codigo  WHERE semestre!='2018_II' AND estudiante.codEscuela="+codigo+" GROUP BY estudiante.codigo,estudiante.nombre HAVING promedio>4.5";
       	
	    Connection connection = conexion.getConnection();
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id =0;
	    String nombre = null, apellido = null;
	    while (rs.next()){
	    	if(personas == null){
	    		personas= new ArrayList<Estudiante>();
	    	}
	      
	        Estudiante registro= new Estudiante();
	        id = rs.getInt("codigo");
	        registro.setCodigo(id);
	        nombre = rs.getString("nombre");
	        registro.setNombre(nombre); 
	        apellido = rs.getString("promedio");
                registro.setPromedio(Double.parseDouble(apellido));
                personas.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de estudiantes");
			e.printStackTrace();
		}
	    
	    return personas;
    }
}
