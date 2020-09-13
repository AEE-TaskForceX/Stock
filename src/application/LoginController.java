package application;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com_connection.ConnectionDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

public class LoginController {

	@FXML
	private TextField id;
	
	@FXML
	private PasswordField mdp;
	@FXML
	private Label exit;

	@FXML Button btnlog;
	static String loginid;
	@FXML
    private void handleButtonAction(MouseEvent event) {
        if (event.getSource() == exit) {
            System.exit(0);
        }
        else if (event.getSource() == btnlog) {
            
            if (logIn().equals("Success")) {
                try {
                	
                    Alert alert = new Alert (Alert.AlertType.INFORMATION);
                    alert.setTitle("Login Admin");
                    alert.setHeaderText("Information");
                    alert.setContentText("Identification avec succès");
                    alert.showAndWait();
                 
                   
                    
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                  
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/admin/Admin.fxml")));
                    stage.setScene(scene);
                   // stage.initStyle(StageStyle.TRANSPARENT);
                    scene.setFill(Color.TRANSPARENT);
                    stage.show();
                } catch (IOException ex) {
                	
                    Alert alert = new Alert (Alert.AlertType.ERROR);
                    alert.setTitle("Login Admin");
                    alert.setHeaderText("Information");
                    alert.setContentText("Id ou mot de passe incorrects");
                    alert.showAndWait();
                    System.err.println(ex.getMessage());
                }
            }
            
         else if (logIn1().equals("Success")) {
            try {
            
                Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setTitle("Login Vendeur");
                alert.setHeaderText("Information");
                alert.setContentText("Identification avec succès");
                alert.showAndWait();
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
               
                stage.close();

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/vendeur/Vendeur.fxml")));
                stage.setScene(scene);
            //    stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                stage.show();
            } catch (IOException ex) {
            	
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setTitle("Login Vendeur");
                alert.setHeaderText("Information");
                alert.setContentText("Id ou mot de passe incorrects");
                alert.showAndWait();
                System.err.println(ex.getMessage());
            }
        }
    }
	}
        
      
	
	 public LoginController() {
	        con = ConnectionDB.conDB();
	    }
	Connection con = null;
 PreparedStatement ps = null;
 ResultSet rs = null;
 private String logIn() {
     String ID = id.getText();
     String password = mdp.getText();
  
     String sql = "SELECT * FROM `admin` WHERE Log_Id=? AND mdp=?";

     try {
         ps = con.prepareStatement(sql);
         ps.setString(1, ID);
         ps.setString(2, password);
       
         rs = ps.executeQuery();
         if (rs.next()) {


        	 loginid =rs.getString("Log_Id");


             
             System.out.println("Successful login");
             return "Success";
         } else {

           
             
             System.err.println("Logins admin incorrectes --///");
             return "error";
         }
     } catch (SQLException ex) {
         System.err.println(ex.getMessage());
         return "Exception";
     }
 }
 
 private String logIn1() {
     String ID = id.getText();
     String password = mdp.getText();
     String sql = "SELECT * FROM `vendeur` WHERE loginidv=? AND mdpv=?";

     try {
         ps = con.prepareStatement(sql);
         ps.setString(1, ID);
         ps.setString(2, password);
         rs = ps.executeQuery();
         if (rs.next()) {

         	rs.getString("loginidv");
			loginid=rs.getString("Id_Vendeur");	 
             
             System.out.println("Successful login");
             return "Success";
         } else {

        
             
             System.err.println("Logins vendeur incorrectes --///");
             return "error";
         }
     } catch (SQLException ex) {
         System.err.println(ex.getMessage());
         return "Exception";
     }
 }
   
 
 public static  String getlogid() {
 	String a=loginid;
 	return(a);
 }

}
