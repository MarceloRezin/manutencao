package banco;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 

public class Banco {
	
	public static Connection iniciarDb() throws SQLException, ClassNotFoundException { 
	   
	    String dbDriver = "com.mysql.jdbc.Driver"; 
	    String dbURL = "jdbc:mysql://marcelorezin.com.br:3306/"; 
	    String dbName = "manutencao"; 
	    String dbUsername = "marcelo-rezin"; 
	    String dbPassword = "TJH3RIZG"; 
	  
	    Class.forName(dbDriver);
	    return DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
	} 

}
