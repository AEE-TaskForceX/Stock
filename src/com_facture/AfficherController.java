package com_facture;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projet.bin.Facture;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AfficherController implements Initializable {
	
	@FXML
	private Label l1;
	@FXML
	private Label l2;
	@FXML
	private Label l3;
	@FXML
	private Label l4;
	@FXML
	private Label l5;
	@FXML
	private Label l6;
	@FXML
	private Label l7;
	@FXML
	private Label l8;
	@FXML
	private Label l9;
	@FXML
	private Label l10;
	@FXML
	private Label l11;
	@FXML
	private Label l12;
	@FXML
	private Label l13;
	@FXML
	private Label l14;
	 static String idcommande;
	 static String name;
	 static String famname;

	private static Facture Selectedfac;
	public void initData(Facture fac){
	        Selectedfac=fac;
	      l1.setText(Selectedfac.getId());
	        l2.setText(Selectedfac.getIdcom());
	        idcommande=Selectedfac.getIdcom();
	        l3.setText(Selectedfac.getIdvend());
	        l4.setText(Selectedfac.getNomcli());
	        famname=Selectedfac.getNomcli();
	        l5.setText(Selectedfac.getPrenomcli());
	        name=Selectedfac.getNomcli();
	        l6.setText(Selectedfac.getDatefact());
	        l7.setText(Selectedfac.getTel());
	        l8.setText(Selectedfac.getRef());
	        String PRIX = Double.toString(Selectedfac.getPrix());
	        l9.setText(PRIX);
	        String QUANTITE = Integer.toString(Selectedfac.getQuantite());
	        l10.setText(QUANTITE);
	        l11.setText(Selectedfac.getMethpai());
	        String TVA = Double.toString(Selectedfac.getTva());
	        l12.setText(TVA);
	        String MONTANT = Double.toString(Selectedfac.getMontant());
	        l13.setText(MONTANT);
	        l14.setText(Selectedfac.getDate());
	    };
	    
	    public static  String getIdCom() {
	    	String a=idcommande;
	    	return(a);
	    }
	   
	    
	    
	    
	    @FXML
	    void envoyer(ActionEvent event) {
	    	String k=com_connection.ConnectionDB.getMail();
	    	System.out.println(k);
	  String to=k;
	  String msg="Bonjour monsieur,Bienvenue Chez DIZZY SQUAD STORE, Voici votre facture";
	   	sendFromGMail(to , msg);
				
	    }
	    
	    
	    
	    public static void sendFromGMail(String to, String cont) {
	    	//authentication info
	    			final String username = "dizzysquad3@gmail.com" ;
	    			final String password = "Dizzydizzy@";
	    			String fromEmail = "dizzysquad3@gmail.com";
	    			String toEmail = to;
	    			
	    			Properties properties = new Properties();
	    			properties.put("mail.smtp.auth", "true");
	    			properties.put("mail.smtp.starttls.enable", "true");
	    			properties.put("mail.smtp.host", "smtp.gmail.com");
	    			properties.put("mail.smtp.port", "587");
	    			
	    			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	    				protected PasswordAuthentication getPasswordAuthentication() {
	    					return new PasswordAuthentication(username,password);
	    				}
	    			});
	    			//Start our mail message
	    			MimeMessage msg = new MimeMessage(session);
	    			try {
	    				msg.setFrom(new InternetAddress(fromEmail));
	    				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
	    				msg.setSubject("Subject Line");
	    				
	    				Multipart emailContent = new MimeMultipart();
	    				
	    				//Text body part
	    				MimeBodyPart textBodyPart = new MimeBodyPart();
	    				textBodyPart.setText(cont);
	    				
	    				//Attachment body part.
	    				MimeBodyPart pdfAttachment = new MimeBodyPart();
	    				pdfAttachment.attachFile("C:\\Users\\LETHAL\\Desktop\\PDF\\Facture"+ Selectedfac.getId()+".pdf");
	    				
	    				//Attach body parts
	    				emailContent.addBodyPart(textBodyPart);
	    				emailContent.addBodyPart(pdfAttachment);
	    				
	    				//Attach multipart to message
	    				msg.setContent(emailContent);
	    				
	    				Transport.send(msg);
	    				Alert alert = new Alert(AlertType.INFORMATION);
	    		    	alert.setTitle("Envoie de facture");
	    		    	alert.setHeaderText("Information");
	    		    	alert.setContentText("Facture bien envoyée à "+famname+" " +name+ "\n Son mail: "+to);
	    		    	alert.showAndWait();
	    			} catch (MessagingException e) {
	    				Alert alert = new Alert (AlertType.ERROR);
	    		          alert.setTitle("Envoie de facture");
	    		          alert.setHeaderText("ERREUR");
	    		          alert.setContentText("Génere d'abord la facture PDF");
	    		          alert.showAndWait();
	    				e.printStackTrace();
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    			
	    				e.printStackTrace();
	    				 
	    			}
	}
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    @FXML
	    public void imprimer(ActionEvent event){
	        Document doc = new Document();
	        try{
	            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\LETHAL\\Desktop\\PDF\\Facture"+ Selectedfac.getId()+".pdf"));
	            doc.open();
	            Image img = Image.getInstance("C:\\Users\\LETHAL\\Desktop\\PDF\\a.png");
	            img.setAlignment(Image.ALIGN_CENTER);
	            doc.add(img);
	            doc.add(new Paragraph("FACTURE \n\r",FontFactory.getFont("Century Gothic", 20)));
	            doc.add(new Paragraph("ID FACTURE  : "+l1.getText()));
	            doc.add(new Paragraph("ID COMMANDE   : "+l2.getText()));
	            doc.add(new Paragraph("ID VENDEUR  : "+l3.getText()));
	            doc.add(new Paragraph("NOM CLIENT  : "+l4.getText()));
	            doc.add(new Paragraph("PRENOM CLIENT  : "+l5.getText()));
	            doc.add(new Paragraph("DATE FACTURE   : "+l6.getText()));
	            doc.add(new Paragraph("TELEPHONE   :" +l7.getText()));
	            doc.add(new Paragraph("DATE DE COMMANDE   :" +l14.getText()));
	            doc.add(new Paragraph("REFERENCE   :" +l8.getText()));
	            doc.add(new Paragraph("PRIX   :" +l9.getText()));
	            doc.add(new Paragraph("QUANTITE   :" +l10.getText()));
	            doc.add(new Paragraph("METHODE PAIEMENT   :" +l11.getText()));
	            doc.add(new Paragraph("TVA  :" +l12.getText()));
	            doc.add(new Paragraph("MONTANT  :" +l13.getText()));
	            doc.close();
	            Desktop.getDesktop().open(new File("C:\\Users\\LETHAL\\Desktop\\PDF\\Facture"+ Selectedfac.getId()+".pdf"));
	        }catch(FileNotFoundException e){
	            e.printStackTrace();
	        }catch(DocumentException e){
	            e.printStackTrace();
	        }catch(IOException e){
	            e.printStackTrace();
	        }
	    }
	

	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	 public void handleButtonAction(MouseEvent event) {
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage.close();
    }
}
