package com_connection;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.bin.Categorie;


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
	
	
	 public static ObservableList<Categorie> getDataTypes(){
         Connection con = conDB();
         ObservableList<Categorie> list = FXCollections.observableArrayList();
         try {
             PreparedStatement ps = con.prepareStatement("select * from categorie");
             ResultSet rs = ps.executeQuery();
             
             while (rs.next()){   
                 list.add(new Categorie(rs.getString("Nom_cat"), rs.getString("Description")));               
             }
         } catch (Exception e) {
         }
         return list;
     }

}
