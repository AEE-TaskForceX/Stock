package com_facture;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.LoginController;

import com_connection.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projet.bin.Article;
import projet.bin.Client;
import projet.bin.Commande;
import projet.bin.Facture;
import projet.bin.Vendeur;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TableColumn;

public class FactureController implements Initializable {
	private FXMLLoader loader;
	//
	double xOffset, yOffset;
	@FXML
	private TableView<Commande> tabcom;
	@FXML
	private TableColumn<Commande , String> idcommcol;
	@FXML
	private TableColumn<Commande ,  String>  idcliencol;
	@FXML
	private TableColumn<Commande ,  String>  idarticlcl;
	@FXML
	private TableColumn<Commande ,  String> datecl;
	@FXML
	private TableColumn<Commande ,  String>  Nomcl;
	@FXML
	private TableColumn<Commande ,  String>  prenomcl;
	@FXML
	private TableColumn<Commande ,  String>  telcl;
	@FXML
	private TableColumn<Commande ,  String>  referencecl;
	@FXML
	private TableColumn<Commande ,  String>  prixcl;
	@FXML
	private TableColumn<Commande ,  String>  quantitecl;
	@FXML
	private TableColumn<Commande ,  String>  detailcl;
	@FXML
	private TextField rechercher;
	@FXML
	private Label exit;
	@FXML
	private TextField RechArt;
	@FXML
	private TableView<Facture> tabfac;
	@FXML
	private TableColumn<Facture ,  String> idfaccl1;
	@FXML
	private TableColumn<Facture ,  String> idcommcl1;
	@FXML
	private TableColumn<Facture ,  String> idvendcl1;
	@FXML
	private TableColumn<Facture , String> datecl1;
	@FXML
	private TableColumn<Facture , String> datefac;
	@FXML
	private TableColumn<Facture , String> Nomcl1;
	@FXML
	private TableColumn<Facture , String> prenomcl1;
	@FXML
	private TableColumn<Facture ,  String> telcl1;
	@FXML
	private TableColumn<Facture ,  String> referencecl1;
	@FXML
	private TableColumn <Facture ,  String>prixcl1;
	@FXML
	private TableColumn<Facture , String> quantitecl1;
	@FXML
	private TableColumn<Facture , String> methcl1;
	@FXML
	private TableColumn<Facture ,  String> tvacl1;
	@FXML
	private TableColumn<Facture ,  String> montantcl1;
	public ObservableList<Commande> dataCom = FXCollections.observableArrayList();
	public ObservableList<Facture> datafac = FXCollections.observableArrayList();

	
	 @FXML
	    private TextField teltxt;
	 @FXML
	    private TextField idcommtxt;

	    @FXML
	    private DatePicker datefield;
	   

	    @FXML
	    private TextField referencetxt;

	    @FXML
	    private TextField idvendtxt;
	    @FXML
	    private TextField prenomtxt;
	    @FXML
	    private TextField idfactxt;

	    @FXML
	    private TextField prixtxt;
	    @FXML
	    private TextField nomtxt;
	    @FXML
	    private TextField qutttxt;

	    @FXML
	    private TextField montatxt;
	    @FXML
	    private ComboBox<String> mdp;
	    @FXML
	    private ComboBox<Double> tva;
	    private String methode[]={"Espèces","Carte bancaire","Paypal","Autre"};
	    private Double tax[]={0.0,0.05,0.10,0.15,0.20,0.25,0.30,0.35,0.40};


		  public void vider ( )
		    {
		    	try {
		    		
		    		teltxt.clear();
		    		idcommtxt.clear();
		    		idvendtxt.clear();
		    		nomtxt.clear();
		    		prenomtxt.clear();
		    		datefield.setValue(null);
		    		referencetxt.clear();
		    		qutttxt.clear();
		    		mdp.setValue(null);
		    		tva.setValue(null);
		    		prixtxt.clear();
		    		qutttxt.clear();

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
		    	
		    	
		    	
		    }
	    
	    private void initMet(){
	        List<String> list= new ArrayList<String>();
	        for(String content:methode){
	            list.add(content);
	        }
	        ObservableList oblist = FXCollections.observableArrayList(list);
	        mdp.setItems(oblist);
	       
	    }
	    
	    private void initTax(){
	        List<Double> list= new ArrayList<Double>();
	        for(Double content:tax){
	            list.add(content);
	        }
	        ObservableList oblist = FXCollections.observableArrayList(list);
	        tva.setItems(oblist);
	       
	    }
	    
	public void viewComm() {
		// TODO Autogenerated
		
		try {
			
    		Connection conn = ConnectionDB.conDB();
    		String sql = "SELECT * FROM `commande`" ;
    		PreparedStatement stm  = conn.prepareStatement(sql);
    		ResultSet rs = stm.executeQuery();
    		tabcom.getItems().removeAll(dataCom);
    		while (rs.next())
    		{
    			dataCom.add(new Commande(rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(5) ,  rs.getString(6) ,  rs.getString(7) ,  rs.getString(8) ,  rs.getString(9) ,  rs.getInt(10) ,   rs.getString(11) ) );
    			
    		}
    		conn.close();
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
		idcommcol.setCellValueFactory(new PropertyValueFactory<Commande, String>("id"));
    	idcliencol.setCellValueFactory(new PropertyValueFactory<Commande, String>("idcli"));
    	datecl.setCellValueFactory(new PropertyValueFactory<Commande, String>("datecom"));
    	Nomcl.setCellValueFactory(new PropertyValueFactory<Commande, String>("nomcli"));
    	prenomcl.setCellValueFactory(new PropertyValueFactory<Commande, String>("prenomcli"));
    	telcl.setCellValueFactory(new PropertyValueFactory<Commande, String>("telcli"));
    	
    	idarticlcl.setCellValueFactory(new PropertyValueFactory<Commande, String>("idarticle"));
    	referencecl.setCellValueFactory(new PropertyValueFactory<Commande, String>("refarticle"));
    	prixcl.setCellValueFactory(new PropertyValueFactory<Commande, String>("prix"));
    	quantitecl.setCellValueFactory(new PropertyValueFactory<Commande, String>("quantite"));
    	detailcl.setCellValueFactory(new PropertyValueFactory<Commande, String>("details"));
    	
    	tabcom.setItems(dataCom);	
	}
    

	
	public void viewFac() {
		// TODO Autogenerated
		
		try {
			
    		Connection conn = ConnectionDB.conDB();
    		String sql = "SELECT * FROM `facture`" ;
    		PreparedStatement stm  = conn.prepareStatement(sql);
    		ResultSet rs = stm.executeQuery();
    		tabfac.getItems().removeAll(datafac);
    		while (rs.next())
    		{
    			datafac.add(new Facture(rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(5) ,  rs.getString(6) ,  rs.getString(7) ,  rs.getString(8) , rs.getString(9) ,  rs.getDouble(10) ,  rs.getInt(11) ,   rs.getString(12) ,  rs.getDouble(13)  ,   rs.getDouble(14) ) );
    			
    		}
    		conn.close();
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
		
    	
    	
    	
    	idfaccl1.setCellValueFactory(new PropertyValueFactory<Facture, String>("id"));
    	idcommcl1.setCellValueFactory(new PropertyValueFactory<Facture, String>("idcom"));
    	idvendcl1.setCellValueFactory(new PropertyValueFactory<Facture, String>("idvend"));
    	Nomcl1.setCellValueFactory(new PropertyValueFactory<Facture, String>("nomcli"));
    	prenomcl1.setCellValueFactory(new PropertyValueFactory<Facture, String>("prenomcli"));
    	datecl1.setCellValueFactory(new PropertyValueFactory<Facture, String>("datefact"));
    	telcl1.setCellValueFactory(new PropertyValueFactory<Facture, String>("tel"));
    	referencecl1.setCellValueFactory(new PropertyValueFactory<Facture, String>("ref"));
    	prixcl1.setCellValueFactory(new PropertyValueFactory<Facture, String>("prix"));
    	quantitecl1.setCellValueFactory(new PropertyValueFactory<Facture, String>("quantite"));
    	methcl1.setCellValueFactory(new PropertyValueFactory<Facture, String>("methpai"));
    	tvacl1.setCellValueFactory(new PropertyValueFactory<Facture, String>("tva"));
    	montantcl1.setCellValueFactory(new PropertyValueFactory<Facture, String>("montant"));
    	datefac.setCellValueFactory(new PropertyValueFactory<Facture, String>("date"));
    	
    	tabfac.setItems(datafac);	
    	
    	
    	
    	
    	
    	
	}
	
	 private void TableToTextCom() {
			tabcom.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					Commande c =tabcom.getItems().get(tabcom.getSelectionModel().getSelectedIndex());
					idcommtxt.setText(c.getId());
					nomtxt.setText(c.getNomcli());
					prenomtxt.setText(c.getPrenomcli());
					teltxt.setText(c.getTelcli());
					referencetxt.setText(c.getRefarticle());
					prixtxt.setText(c.getPrix());
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String DT=c.getDatecom();
                    LocalDate DATECom = LocalDate.parse(DT, formatter);

                    datefield.setValue(DATECom);
					String QUTT = Integer.toString(c.getQuantite());
					qutttxt.setText(QUTT);
					
					
				
				}
				
				
			});
		}
	
	 private void TableToTextFac() {
			tabfac.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					Facture c =tabfac.getItems().get(tabfac.getSelectionModel().getSelectedIndex());
					idcommtxt.setText(c.getIdcom());
					nomtxt.setText(c.getNomcli());
					prenomtxt.setText(c.getPrenomcli());
					teltxt.setText(c.getTel());
					referencetxt.setText(c.getRef());
					String PRIX = Double.toString(c.getPrix());
					
					prixtxt.setText(PRIX);
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                 String DT=c.getDate();
                 LocalDate DATECom = LocalDate.parse(DT, formatter);

                 datefield.setValue(DATECom);
					String QUTT = Integer.toString(c.getQuantite());
					qutttxt.setText(QUTT);
					
					mdp.getSelectionModel().select(c.getMethpai());
					tva.getSelectionModel().select(c.getTva());
					
					String logid = LoginController.getlogid();
					idvendtxt.setText(logid);
				}
				
				
			});
		}
	
	
	
	
	
	
	
	// Event Listener on Label[#exit].onMouseClicked
	 @FXML
	 public void handleButtonAction(MouseEvent event) {
         Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

         appStage.close();
     }
	// Event Listener on FontAwesomeIcon.onDragDetected
	@FXML
	public void back(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on FontAwesomeIcon.onMouseClicked
	
	// Event Listener on Button.onAction
	 public static int Ajouter(Facture cl)
	    {
	    	Connection conn = ConnectionDB.conDB();
	    	int rs = 0;
	    	try {
	    		
	    		String sql = "insert into facture ( Id_Commande , 	Id_Vendeur , Date_Facture , Nom_Client , Prenom_Client, Tel_Client , Date_Commande , Ref_Article , Prix_Article, Quantite , Methpai , TVA, Montant ) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    		PreparedStatement stm = conn.prepareStatement(sql);
	    		
	    		stm.setString(1, cl.getIdcom());
	    		stm.setString(2, cl.getIdvend());
	    		stm.setString(3, cl.getDatefact());
	    		stm.setString(4, cl.getNomcli());
	    		stm.setString(5, cl.getPrenomcli());
	    		stm.setString(6, cl.getTel());
	    		stm.setString(7, cl.getDate());
	    		stm.setString(8, cl.getRef());
	    		stm.setDouble(9, cl.getPrix());
	    		stm.setInt(10, cl.getQuantite());
	    		stm.setString(11, cl.getMethpai());
	    		stm.setDouble(12, cl.getTva());
	    		stm.setDouble(13, cl.getMontant());
	    		
	    		
	    		
	    		
	    		 rs = stm.executeUpdate();
	    		
	    		
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
	    	
	    return rs;	
	    }
	    
	    
	    
	    public void insertdata (ActionEvent event)
	    {	
	    	LocalDate dateemp=LocalDate.now();
	    	String IDCOM = idcommtxt.getText();
	    	
	    	String NOM = nomtxt.getText();
	    	String PRENOM = prenomtxt.getText();
	    	
	    	String TELE = teltxt.getText();
	    	String REF = referencetxt.getText();
	    	String PRIX = prixtxt.getText();
	    	String QUTTE = qutttxt.getText();
	    	//String MONTANT = montatxt.getText();
	    	String DATE=dateemp.toString();
	    	LocalDate DATECOM=datefield.getValue();
		      String dateR=DATECOM.toString();
		      double TVA =tva.getValue();
		      int Quant=Integer.parseInt(QUTTE);
		      double quantity = Double.parseDouble(QUTTE);
		      double price = Double.parseDouble(PRIX);
		      String logid = LoginController.getlogid();
		      
		      double Total= (price*quantity) +(price*quantity)/TVA;
	    	System.out.println("total"+Total);
		      
	    	Facture cl = new Facture();
	    	
	    	
	    	cl.setIdcom(IDCOM);
	    	cl.setIdvend(logid);
	    	cl.setNomcli(NOM);
	    	cl.setPrenomcli(PRENOM);
	    	cl.setDate(dateR);
	    	cl.setTel(TELE);
	    	cl.setRef(REF);
	    	cl.setPrix(price);
	    	cl.setQuantite(Quant);
	    	cl.setMethpai(mdp.getValue());
	    	cl.setTva(tva.getValue());
	    	cl.setMontant(Total);
	    	cl.setDatefact(DATE);
	    	
	    	
	    	int etat = Ajouter(cl);
	    	
	    	
	    	if (etat > 0) {
	    	
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Ajouter Facture");
	    	alert.setHeaderText("Information");
	    	alert.setContentText("Facture bien ajoutée");
	    	alert.showAndWait();
	    	
	              }
	    	else
	    	{
	    		Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("Ajouter Facture");
		    	alert.setHeaderText("Information");
		    	alert.setContentText("Facture Non ajoutée");
		    	alert.showAndWait();	
	    
	    	}
	    	
	    	
	    	viewFac();
	    	
	    	}
		
	// Event Listener on Button.onAction
	    public static int Modifier(Facture cl,String idd)
	    {
	    	Connection conn = ConnectionDB.conDB();
	    	int rs = 0;
	    	try {
	    		
	    		String sql = "update facture set Id_Commande=? , 	Id_Vendeur=? , Date_Facture=? , Nom_Client=? , Prenom_Client=?, Tel_Client=? , Date_Commande=? , Ref_Article=? , Prix_Article=?, Quantite=? , Methpai=? , TVA=?, Montant=? where Id_Facture=? ";
	    		PreparedStatement stm = conn.prepareStatement(sql);
	    		
	    		stm.setString(1, cl.getIdcom());
	    		stm.setString(2, cl.getIdvend());
	    		stm.setString(3, cl.getDatefact());
	    		stm.setString(4, cl.getNomcli());
	    		stm.setString(5, cl.getPrenomcli());
	    		stm.setString(6, cl.getTel());
	    		stm.setString(7, cl.getDate());
	    		stm.setString(8, cl.getRef());
	    		stm.setDouble(9, cl.getPrix());
	    		stm.setInt(10, cl.getQuantite());
	    		stm.setString(11, cl.getMethpai());
	    		stm.setDouble(12, cl.getTva());
	    		stm.setDouble(13, cl.getMontant());
	    		stm.setString(14, idd);
	    		
	    		
	    		
	    		 rs = stm.executeUpdate();
	    		
	    		
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
	    	
	    return rs;	
	    }
	    
	    
	    
	    public void update (ActionEvent event)
	    {	
	    	LocalDate dateemp=LocalDate.now();
	    	String IDCOM = idcommtxt.getText();
	    	
	    	String NOM = nomtxt.getText();
	    	String PRENOM = prenomtxt.getText();
	    	
	    	String TELE = teltxt.getText();
	    	String REF = referencetxt.getText();
	    	String PRIX = prixtxt.getText();
	    	String QUTTE = qutttxt.getText();
	    	//String MONTANT = montatxt.getText();
	    	String DATE=dateemp.toString();
	    	LocalDate DATECOM=datefield.getValue();
		      String dateR=DATECOM.toString();
		      double TVA =tva.getValue();
		      int Quant=Integer.parseInt(QUTTE);
		      double quantity = Double.parseDouble(QUTTE);
		      double price = Double.parseDouble(PRIX);
		      String logid = LoginController.getlogid();
		      
		      double Total= (price*quantity) +(price*quantity)/TVA;
	    	System.out.println("total"+Total);
		      
	    	Facture cl = new Facture();
	    	
	    	
	    	cl.setIdcom(IDCOM);
	    	cl.setIdvend(logid);
	    	cl.setNomcli(NOM);
	    	cl.setPrenomcli(PRENOM);
	    	cl.setDate(dateR);
	    	cl.setTel(TELE);
	    	cl.setRef(REF);
	    	cl.setPrix(price);
	    	cl.setQuantite(Quant);
	    	cl.setMethpai(mdp.getValue());
	    	cl.setTva(tva.getValue());
	    	cl.setMontant(Total);
	    	cl.setDatefact(DATE);
	    	 Facture selected =tabfac.getSelectionModel().getSelectedItem();
		        String idd = selected.getId();
	    	
	    	int etat = Modifier(cl,idd);
	    	
	    	
	    	if (etat > 0) {
	    	
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Modifier Facture");
	    	alert.setHeaderText("Information");
	    	alert.setContentText("Facture bien modifiée");
	    	alert.showAndWait();
	    	
	              }
	    	else
	    	{
	    		Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("Modifier Facture");
		    	alert.setHeaderText("Information");
		    	alert.setContentText("Facture Non modifiée");
		    	alert.showAndWait();	
	    
	    	}
	    	
	    	
	    	viewFac();
	    	
	    	}
		
	// Event Listener on Button.onAction
	    public static int supp(String id)
		{
			Connection conn = ConnectionDB.conDB();
			int d = 0;
			
			
			try {
				
				String sql = "delete from facture where Id_Facture = ? ";
				PreparedStatement stm = conn.prepareStatement(sql);
				stm.setString(1, id);
				d = stm.executeUpdate();
				
			} catch (SQLException e) {
				// TODO: handle exception
				
				System.out.println(e.getMessage());
			}
			
			
			
			return d;
		
			
		}
	    
	    
	    public void delete (ActionEvent event)
		{
	    	Facture selected =tabfac.getSelectionModel().getSelectedItem();
	        String idd = selected.getId();
	        tabfac.getItems().removeAll(selected);
			int etat = supp(idd);
			
			if(etat > 0) 
	        {
	    	
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Supprimer Facture");
	    	alert.setHeaderText("Information");
	    	alert.setContentText("Facture bien Supprimée");
	    	alert.showAndWait();
	    	
	              }
	    	else
	    	{
	    		Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("Supprimer Facture");
		    	alert.setHeaderText("Information");
		    	alert.setContentText("Facture Non Supprimée");
		    	alert.showAndWait();	
	    
	    	}
			viewFac();
			
		}
	    
	    
	// Event Listener on FontAwesomeIcon.onMouseClicked
	@FXML
	public void vider(MouseEvent event) {
		// TODO Autogenerated
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TableToTextCom();
		TableToTextFac();
		viewComm();
		viewFac();
		String logid = LoginController.getlogid();
		idvendtxt.setText(logid);
		initMet();
		initTax();
		
		
	}

	
	
	@FXML
	    private void afficher(ActionEvent event){
	        try{
	            loader = new FXMLLoader();

	            loader.setLocation(getClass().getResource("Afficher.fxml"));
	            Parent tableViewparent=loader.load();
	            Scene tableViewscene = new Scene(tableViewparent);

	            AfficherController controller =loader.getController();
	            controller.initData(tabfac.getSelectionModel().getSelectedItem());

	            Stage window = new Stage();//(Stage)((Node)event.getSource()).getScene().getWindow()/;
	            window.initStyle(StageStyle.TRANSPARENT);
	            tableViewscene.setFill(Color.TRANSPARENT);
	            window.setScene(tableViewscene);
	            window.show();
	            
	            tableViewscene.setOnMousePressed(new EventHandler<MouseEvent>() {
	    			@Override
	    			public void handle(MouseEvent event) {
	    				xOffset = event.getSceneX();
	    				yOffset = event.getSceneY();
	    			}
	    		});
	            tableViewscene.setOnMouseDragged(new EventHandler<MouseEvent>() {
	    			@Override
	    			public void handle(MouseEvent event) {
	    				window.setX(event.getScreenX() - xOffset);
	    				window.setY(event.getScreenY() - yOffset);
	    			}
	    		});
	 

	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }

}
