package br.arcadia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CNXJDBC {
	
	private static final String DRIVER_CLASS = "org.hsqldb.jdbcDriver";
	private static Connection cnx = null;
	private static String usuario = "SA";
	private static String senha = "";
	private static String Pathbase = "C:\\Users\\berna\\Documents\\eclipse workspace\\Agenda\\base\\TesteDb";
	private static final String URL = "jdbc:hsqldb:file:"+ Pathbase + "; shutdown=true;hsqldb.write_delay=false; "; 
	
	public static Connection connectar() {
		if(cnx==null) {
			try {
				Class.forName(DRIVER_CLASS);
					cnx = DriverManager.getConnection(URL, usuario, senha);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return cnx;
	}
	
	public static void fecharCNX() {
		try {
			cnx.close();
			cnx= null;
		}catch(SQLException e){
			e.printStackTrace();
			
		}
	}

}
