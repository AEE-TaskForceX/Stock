package com_connection;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.bin.Article;
import projet.bin.Categorie;
import projet.bin.Commande;


public class ConnectionDB {
	static String nom;
	static String prenom;
	static String y;
	static String z;


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
	 
	 
	 public static ObservableList<String> getDataCat(){
         Connection con = conDB();
         ObservableList<String> list = FXCollections.observableArrayList();
         try {
             PreparedStatement ps = con.prepareStatement("select nom_cat from categorie");
             ResultSet rs = ps.executeQuery();

             while (rs.next()){
                 list.add(rs.getString("nom_cat"));
             }
         } catch (Exception e) {
         }
         return list;
     }
	 public static ObservableList<String> getDataFourn(){
         Connection con = conDB();
         ObservableList<String> list = FXCollections.observableArrayList();
         try {
             PreparedStatement ps = con.prepareStatement("select Nom_Fournisseur from fournisseur");
             ResultSet rs = ps.executeQuery();

             while (rs.next()){
                 list.add(rs.getString("Nom_Fournisseur"));
             }
         } catch (Exception e) {
         }
         return list;
     }
	 public static ObservableList<Article> getDataArt(){
         Connection con = conDB();
         ObservableList<Article> listl = FXCollections.observableArrayList();
         try {
             PreparedStatement ps = con.prepareStatement("select * from article");
             ResultSet rs = ps.executeQuery();
             
             while (rs.next()){   
                 
            listl.add(new Article(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8)));
             }
         } catch (Exception e) {
        	 e.printStackTrace();
         }
         return listl;
     }
	 
	 public static String getIdcli(){
         Connection con = conDB();
         try {
        	 String x= com_facture.AfficherController.getIdCom() ;
             PreparedStatement ps = con.prepareStatement("select id_client from Commande where id_commande =? ");
             ps.setString(1, x);
             ResultSet rs = ps.executeQuery();
             
             while (rs.next()){   
                 
                 y= rs.getString("id_commande");       
             }
         } catch (Exception e) {
        	 e.printStackTrace();
         }
         return (y);
     }
	 public static String getMail(){
         Connection con = conDB();
         try {
        	 String x= getIdcli() ;
             PreparedStatement ps = con.prepareStatement("select Mail_Client from client where Id_Client =? ");
             ps.setString(1, x);
             ResultSet rs = ps.executeQuery();
             
             while (rs.next()){   
                 
                 z= rs.getString("Mail_Client");       
             }
         } catch (Exception e) {
        	 e.printStackTrace();
         }
         return (z);
     }
	

}
