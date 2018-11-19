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

/**
 *
 * @author Labing
 */
public class daoEscuela {

    public Escuela getEscuela(int nomEscuela) throws SQLException, URISyntaxException {
    Escuela resultado = null;
       String query="Select * from Escuela Where nombre ="+ nomEscuela;
       Connection connection = conexion.getConnection();
        try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id =0;
	    String nombre = null, apellido = null;
	    if (rs.next()){
                resultado = new Escuela();
	        id = rs.getInt("codigo");
	        resultado.setCodigo(id);
	        nombre = rs.getString("nombre");
	        resultado.setNombre(nombre); 
                
	    }
	    st.close();
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener persona");
			e.printStackTrace();
		}
	    return resultado;

    
    }
    
    
}
