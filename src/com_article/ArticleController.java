package com_article;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com_connection.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import projet.bin.Article;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class ArticleController implements Initializable {
	@FXML
	private TextField txtquant;
	@FXML
	private TextField txtprix;
	@FXML
	private TextField txtref;
	@FXML
	private TextArea txtdet;
	@FXML
	private ComboBox<String> combocat;
	@FXML
	private DatePicker datefield;
	@FXML
	private ComboBox<String> combofourn;
	@FXML
	private Label exit;
	@FXML
	private TableView<Article> tab;
	@FXML
	private TableColumn<Article,String> col_id;
	@FXML
	private TableColumn<Article,String> col_cat;
	@FXML
	private TableColumn<Article,String> col_fourn;
	@FXML
	private TableColumn<Article,String> col_date;
	@FXML
	private TableColumn<Article,String> col_quant;
	@FXML
	private TableColumn<Article,String> col_prix;
	@FXML
	private TableColumn<Article,String> col_ref;
	@FXML
	private TableColumn<Article,String> col_desc;
	@FXML
	private TextField searchfield;
	
	public ObservableList<Article> data= FXCollections.observableArrayList();
    ObservableList<Article> dataList;
    public ObservableList<String> data1= FXCollections.observableArrayList();
    public ObservableList<String> data2= FXCollections.observableArrayList();
    ObservableList<String> dataList1;
    
    
    public void FillComboCat() {
  	  
  	  data1 = ConnectionDB.getDataCat();

              	combocat.setItems(data1);
       
    }
    public void FillComboFourn() {
  	  
  	  data2 = ConnectionDB.getDataFourn();

              	combofourn.setItems(data2);
       
    }

    //methode select individu
    String i;
    int index=-1;
    @FXML
    void getSelected (MouseEvent event) {
    	index=tab.getSelectionModel().getSelectedIndex();
    	if (index<=-1) {
    	
    		return;
    	}
        Article l =tab.getItems().get(tab.getSelectionModel().getSelectedIndex());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String DT=l.getDateachat();
        LocalDate DATECli = LocalDate.parse(DT, formatter);

        datefield.setValue(DATECli);
   		
   	 

       
       
       
       
       // TODO Auto-generated method stub
        i=col_id.getCellData(index).toString();   
       combocat.setValue(l.getNomcat());
       combofourn.setValue(l.getNomfourni());
       txtprix.setText(l.getPrix());
       txtref.setText(l.getRef());
       txtdet.setText(l.getDesc());
       String QUTT = Integer.toString(l.getQuantite());
       txtquant.setText(QUTT);
       
       
       
       
       
    }
    @FXML
    void search() {          
     	col_id.setCellValueFactory(new PropertyValueFactory<Article, String>("id"));
    	col_cat.setCellValueFactory(new PropertyValueFactory<Article, String>("nomcat"));
    	col_fourn.setCellValueFactory(new PropertyValueFactory<Article, String>("nomfourni"));
    	col_date.setCellValueFactory(new PropertyValueFactory<Article, String>("dateachat"));
    	col_quant.setCellValueFactory(new PropertyValueFactory<Article, String>("quantite"));
    	col_prix.setCellValueFactory(new PropertyValueFactory<Article, String>("prix"));
    	col_ref.setCellValueFactory(new PropertyValueFactory<Article, String>("ref"));
    	col_desc.setCellValueFactory(new PropertyValueFactory<Article, String>("desc"));



        
        dataList =ConnectionDB.getDataArt();
        tab.setItems(dataList);
        FilteredList<Article> filteredData = new FilteredList<>(dataList, b -> true);  
        searchfield.textProperty().addListener((observable, oldValue, newValue) -> {
        	filteredData.setPredicate(Article -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (Article.getId().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
    	return true; // recherche par id log
    } else if (Article.getRef().toLowerCase().indexOf(lowerCaseFilter) != -1) {
    	return true; // recherche par id locataire
    } else if (Article.getNomcat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
    	return true; // recherche par type
    }else if (Article.getNomfourni().toLowerCase().indexOf(lowerCaseFilter) != -1) {
    	return true; // recherche par quart
    }
    
                                
     else  
          	return false; // n'exits pas
   });
  });  
  SortedList<Article> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tab.comparatorProperty());  
  tab.setItems(sortedData);      
    }
    
    public void Update(){
    	col_id.setCellValueFactory(new PropertyValueFactory<Article, String>("id"));
    	col_cat.setCellValueFactory(new PropertyValueFactory<Article, String>("id_indiv"));
    	col_fourn.setCellValueFactory(new PropertyValueFactory<Article, String>("nomfourni"));
    	col_date.setCellValueFactory(new PropertyValueFactory<Article, String>("dateachat"));
    	col_quant.setCellValueFactory(new PropertyValueFactory<Article, String>("quantite"));
    	col_prix.setCellValueFactory(new PropertyValueFactory<Article, String>("prix"));
    	col_ref.setCellValueFactory(new PropertyValueFactory<Article, String>("ref"));
    	col_desc.setCellValueFactory(new PropertyValueFactory<Article, String>("desc"));

        data = ConnectionDB.getDataArt();
        tab.setItems(data);

    } 
    
    
    
    
    
    
	// Event Listener on Button.onAction
	@FXML
	public void Modifier(ActionEvent event) {
		// TODO Autogenerated
				Connection con= ConnectionDB.conDB();
		    	PreparedStatement st = null;
		    	Article a = new Article();
		    	String cat = combocat.getValue() ; 
		    	String fourn = combofourn.getValue() ; 
		    	LocalDate datee=datefield.getValue();
		    	String date=datee.toString();
		    	String ref = txtref.getText();
		    	String quante = txtquant.getText();
		    	int quant=Integer.parseInt(quante);
		    	String prix = txtprix.getText();
		    	String det = txtdet.getText();
		    	a.setNomcat(cat);
		    	a.setNomfourni(fourn);
		    	a.setDateachat(date);
		    	a.setRef(ref);
		    	a.setQuantite(quant);
		    	a.setPrix(prix);
		    	a.setDesc(det);
		   
		    	String rqt ="Update  article set  Nom_Cat =? , Nom_Fournisseur=? ,Date_Achat=?,Quantite=?,Prix=?,Reference=?,Description = ?  Where Id_Article=? ";
		    	try {
					st = con.prepareStatement(rqt);
					
					st.setString(1, a.getNomcat());
					st.setString(2, a.getNomfourni());
					st.setString(3, a.getDateachat());
					st.setInt(4, a.getQuantite());
					st.setString(5, a.getPrix());
					st.setString(6, a.getRef());
					st.setString(7, a.getDesc());
					st.setString(8, i);
					st.executeUpdate();
					con.close();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("C'est fait");
					alert.setHeaderText(null);
					alert.setContentText("Article bien modifié");
					alert.showAndWait();			
					Update();
					search();
					FillComboFourn();
					FillComboCat();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("ERREUR");
				alert.setHeaderText(null);
					alert.setContentText("Article n'a pas été modifié");
					System.out.println(e.getMessage()); 
					alert.showAndWait();
					e.printStackTrace();
				}
		
		
	}
	
	
	
	
	
	
	
	// Event Listener on Button.onAction
	@FXML
	public void Supprimer(ActionEvent event) {
		// TODO Autogenerated
		Connection con= ConnectionDB.conDB();
    	PreparedStatement st = null;
    	String rqt ="delete from article where Id_Article=?";
    	
    		
    		try {
    			st = con.prepareStatement(rqt);
				st.setString(1, i);
				st.executeUpdate();
	    		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("C'est fait");
				alert.setHeaderText(null);
				alert.setContentText("Article bien supprimé");
				alert.showAndWait();
				Update();
				search();
				FillComboCat();
				FillComboFourn();

	    	
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("ERREUR");
				alert.setHeaderText(null);
				alert.setContentText("Article n'a pas été supprimé");
				alert.showAndWait();
			}
		
		
		
	}
	// Event Listener on Button.onAction
	
	
	
	
	
	
	
	
	@FXML
	public void Ajouter(ActionEvent event) {
		// TODO Autogenerated
		Connection con= ConnectionDB.conDB();
    	PreparedStatement st = null;
    	Article a = new Article();
    	String cat = combocat.getValue() ; 
    	String fourn = combofourn.getValue() ; 
    	LocalDate datee=datefield.getValue();
    	String date=datee.toString();
    	String ref = txtref.getText();
    	String quante = txtquant.getText();
    	int quant=Integer.parseInt(quante);
    	String prix = txtprix.getText();
    	String det = txtdet.getText();
    	a.setNomcat(cat);
    	a.setNomfourni(fourn);
    	a.setDateachat(date);
    	a.setRef(ref);
    	a.setQuantite(quant);
    	a.setPrix(prix);
    	a.setDesc(det);
   
    	String rqt ="Insert into article ( Nom_Cat ,Nom_Fournisseur,Date_Achat,Quantite,Prix, Reference , Description)  values(?,?,?,?,?,?,?) ";;
    	try {
			st = con.prepareStatement(rqt);
			
			st.setString(1, a.getNomcat());
			st.setString(2, a.getNomfourni());
			st.setString(3, a.getDateachat());
			st.setInt(4, a.getQuantite());
			st.setString(5, a.getPrix());
			st.setString(6, a.getRef());
			st.setString(7, a.getRef());
		
			st.executeUpdate();
			con.close();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("C'est fait");
			alert.setHeaderText(null);
			alert.setContentText("Article bien ajouté");
			alert.showAndWait();			
			Update();
			search();
			FillComboFourn();
			FillComboCat();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ERREUR");
		alert.setHeaderText(null);
			alert.setContentText("Article n'a pas été ajouté");
			System.out.println(e.getMessage()); 
			alert.showAndWait();
		}
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Update();
		search();
		FillComboFourn();
		FillComboCat();
		
		
	}
	@FXML
	public void back(MouseEvent event) throws IOException {
		// TODO Autogenerated
		Parent homePage = FXMLLoader.load(getClass().getResource("/vendeur/Vendeur.fxml"));
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