package com_adminsettings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import projet.bin.Admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com_connection.ConnectionDB;
import javafx.event.ActionEvent;

import javafx.scene.control.PasswordField;

public class AdminSettingsController {
	@FXML
    private PasswordField newpassfield;

  

  @FXML
    private TextField idfield;
   
    @FXML
    private PasswordField oldpassfield;

    @FXML
    private PasswordField renewpassfield;

	
	Connection con= ConnectionDB.conDB();
	PreparedStatement st = null;
	ResultSet r = null;

    @FXML
    void save(ActionEvent event) {

		
		
    	
    	if (checkoldpass().equals("oui")) {
    		
    		if(newpassfield.getText().equals(renewpassfield.getText())) {
    			Admin a=new Admin();
    			String logid =idfield.getText();
    			String passo=newpassfield.getText();
    			a.setLoginid(logid);
    			a.setMdp(passo);
    			
    			String l=application.LoginController.getlogid();
    	    	String rqt ="UPDATE admin SET Log_Id=? , Mdp=? WHERE id=?";
    	    	
    	    	
    			try {
    				st = con.prepareStatement(rqt);
    				st.setString(1, a.getLoginid());
        			st.setString(2, a.getMdp());
					st.setString(3, l);
					st.executeUpdate();
					con.close();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("C'est fait");
					alert.setHeaderText(null);
					alert.setContentText("Login Id et Mot De Passe sont changés");
					alert.showAndWait();	
					 
	    	  		
	    			
    				
	    			
    				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    			
    		}
    		else {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Erreur ");
    			alert.setHeaderText("Erreur!");
    			alert.setContentText("Nouveaus mots de passe non identiques");
    			alert.showAndWait();
    		}
    		
    		
    		
    		
    		
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur ");
			alert.setHeaderText("Erreur!");
			alert.setContentText("Ancient mot de passe incorrecte");
			alert.showAndWait();
    	}
   
		
		
		
		
		
		
		
		
		
		
    }
	
	
	
	
	
	
	public  String checkoldpass() {
		String rqtas ="Select * from admin Where  mdp=?";
	  	 try {
				
	  		st = con.prepareStatement(rqtas);
	  		String pass=oldpassfield.getText();
			st.setString(1, pass);
				
			
				r = st.executeQuery();
				if (r.next()){

					 return "oui";
				}
				else {
					 return "non";
				}
	  	 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 return "erreur";
			}
	  }

    @FXML
    void Retour(MouseEvent event) throws IOException {
    	Parent homePage = FXMLLoader.load(getClass().getResource("/admin/Admin.fxml"));
	    Scene homepageScene = new Scene(homePage);
	    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    appStage.setScene(homepageScene);
	    homepageScene.setFill(Color.TRANSPARENT);
	    appStage.show();
    }
    @FXML
    private void handleButtonAction(MouseEvent event) {
       
            System.exit(0);
        }
   
}
