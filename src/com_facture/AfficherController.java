package com_facture;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
	
	private Facture Selectedfac;
	public void initData(Facture fac){
	        Selectedfac=fac;
	      l1.setText(Selectedfac.getId());
	        l2.setText(Selectedfac.getIdcom());
	        l3.setText(Selectedfac.getIdvend());
	        l4.setText(Selectedfac.getNomcli());
	        l5.setText(Selectedfac.getPrenomcli());
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
	    
	    
	    
	    @FXML
	    public void imprimer(ActionEvent event){
	        Document doc = new Document();
	        try{
	            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\S\\Desktop\\PDF\\Facture.pdf"));
	            doc.open();
	            Image img = Image.getInstance("C:\\Users\\S\\Downloads\\a.png");
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
	            Desktop.getDesktop().open(new File("C:\\Users\\S\\Desktop\\PDF\\Facture.pdf"));
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

}
