package com_connection;



import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionDB {
	
	public static Connection conDB() 
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/gestion-stock?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Africa/Casablanca", "root","");
            return conn;
        } catch (Exception  ex) {
            System.err.println("erreur :"+ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

}
