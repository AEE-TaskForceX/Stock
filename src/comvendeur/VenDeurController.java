package comvendeur;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com_client.ClientController;
import com_connection.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import projet.bin.Client;
import projet.bin.Vendeur;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class VenDeurController implements Initializable {
	@FXML
	private TextField nomtxt;
	@FXML
	private TextField txtPrenom;
	@FXML
	private TextField txtmdp;
	@FXML
	private TextField txttele;
	@FXML
	private TextField txtidlog;
	@FXML
	private Label exit;
	@FXML
	private TableView<Vendeur> table;
	public ObservableList<Vendeur> data = FXCollections.observableArrayList();
	@FXML
	private TableColumn <Vendeur, String> idcolumn;
	@FXML
	private TableColumn<Vendeur, String> nomcolumn;
	@FXML
	private TableColumn<Vendeur, String> prenomcolumn;
	@FXML
	private TableColumn<Vendeur, String> idlogcolumn;
	@FXML
	private TableColumn<Vendeur, String> telecolumn;
	@FXML
	private TableColumn<Vendeur, String> mdpcolumn;
	private TableColumn<Vendeur, String> ventescolumn;

	
	@FXML
	private TextField rechercher;

	// Event Listener on FontAwesomeIcon.onMouseClicked
/*--------------------------------------------------AJOUTER----------------------------------------------------------------------------------*/	    

	
    public static int Ajouter(Vendeur cl)
    {
    	Connection conn = ConnectionDB.conDB();
    	int rs = 0;
    	try {
    		
    		String sql = "insert into Vendeur(Nom_Vendeur , Prenom_Vendeur , Tel_Vendeur , loginidv , mdpv ) values(?,?,?,?,?)";
    		PreparedStatement stm = conn.prepareStatement(sql);
    		
    		stm.setString(1, cl.getNom());
    		stm.setString(2, cl.getPrenom());
    		stm.setString(3, cl.getTel());
    		stm.setString(4, cl.getLoginid());
    		stm.setString(5, cl.getMdp());
    		
    		
    		
    		 rs = stm.executeUpdate();
    		
    		
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
    	
    return rs;	
    }
    
    
    
    public void insertdata (ActionEvent event)
    {
    	String NOM = nomtxt.getText();
    	String PRENOM = txtPrenom.getText();
    	String MDP = txtmdp.getText();
    	String TELE = txttele.getText();
    	String IDLOG = txtidlog.getText();
    	
    	
    	Vendeur cl = new Vendeur();
    	
    	
    	cl.setNom(NOM);
    	cl.setPrenom(PRENOM);
    	cl.setMdp(MDP);
    	cl.setTel(TELE);
    	cl.setLoginid(IDLOG);
    	
    	
    	
    	int etat = Ajouter(cl);
    	
    	
    	if (etat > 0) {
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Ajouter Vendeur");
    	alert.setHeaderText("Information");
    	alert.setContentText("Vendeur bien ajout�");
    	alert.showAndWait();
    	
              }
    	else
    	{
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Vendeur Client");
	    	alert.setHeaderText("Information");
	    	alert.setContentText("Vendeur Non ajout�");
	    	alert.showAndWait();	
    
    	}
    	
    	
    	viewAbonnee();
    	
    	}
	
	
/*--------------------------------------------------AFFICHER----------------------------------------------------------------------------------*/	    
    
    
    public void viewAbonnee() {
		// TODO Autogenerated
		
		try {
			
    		Connection conn = ConnectionDB.conDB();
    		String sql = "SELECT * FROM `vendeur`" ;
    		PreparedStatement stm  = conn.prepareStatement(sql);
    		ResultSet rs = stm.executeQuery();
    		table.getItems().removeAll(data);
    		while (rs.next())
    		{
    			data.add(new Vendeur(rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getDouble(5) ,  rs.getString(6),rs.getString(7) ) );
    			
    		}
    		conn.close();
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
		idcolumn.setCellValueFactory(new PropertyValueFactory<Vendeur, String>("id"));
    	nomcolumn.setCellValueFactory(new PropertyValueFactory<Vendeur, String>("nom"));
    	prenomcolumn.setCellValueFactory(new PropertyValueFactory<Vendeur, String>("prenom"));
    	idlogcolumn.setCellValueFactory(new PropertyValueFactory<Vendeur, String>("loginid"));
    	ventescolumn.setCellValueFactory(new PropertyValueFactory<Vendeur, String>("ventes"));
    	telecolumn.setCellValueFactory(new PropertyValueFactory<Vendeur, String>("tel"));
    	mdpcolumn.setCellValueFactory(new PropertyValueFactory<Vendeur, String>("mdp"));
    	
    	table.setItems(data);	
	}
    
	
	
/*--------------------------------------------------MODIFIER----------------------------------------------------------------------------------*/	    
    
    public static int modifier(Vendeur cl,String idd)
	{
		Connection conn = ConnectionDB.conDB();
    	int d = 0;
    	try {
    		
    		String sql = "update vendeur set  Nom_Vendeur = ? , Prenom_Vendeur= ?  , Tel_Vendeur = ? , loginidv = ? , mdpv = ? where Id_Vendeur = ?  ";
    		PreparedStatement stm = conn.prepareStatement(sql);
    		
    		stm.setString(1, cl.getNom());
    		stm.setString(2, cl.getPrenom());
    		stm.setString(3, cl.getTel());
    		stm.setString(4, cl.getLoginid());
    		stm.setString(5, cl.getMdp());
    		stm.setString(6, idd);
    		
    		
    		 d = stm.executeUpdate();
    		
    		
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
    	
    return d;	
		
		
		
	}
	
    
    public void update (ActionEvent event) 
	{
    	
    	String NOM = nomtxt.getText();
    	String PRENOM = txtPrenom.getText();
    	String MDP = txtmdp.getText();
    	String TELE = txttele.getText();
    	String IDLOG = txtidlog.getText();
    	
    	
    	Vendeur cl = new Vendeur();
    	
    	
    	cl.setNom(NOM);
    	cl.setPrenom(PRENOM);
    	cl.setMdp(MDP);
    	cl.setTel(TELE);
    	cl.setLoginid(IDLOG);
    	
    	
    	 Vendeur selected =table.getSelectionModel().getSelectedItem();
	        String idd = selected.getId();
    	int etat = modifier(cl,idd);
    	
    	
    	if (etat > 0) {
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Modifier Vendeur");
    	alert.setHeaderText("Information");
    	alert.setContentText("Vendeur bien Modifi�");
    	alert.showAndWait();
    	
              }
    	else
    	{
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Modifier Vendeur");
	    	alert.setHeaderText("Information");
	    	alert.setContentText("Vendeur Non Modifi�");
	    	alert.showAndWait();	
    
    	}
    	
    	viewAbonnee();
    	
	}
    
    
 /*--------------------------------------------------SUPPRIMER----------------------------------------------------------------------------------*/	    
    
    
    public static int supp(String id)
	{
		Connection conn = ConnectionDB.conDB();
		int d = 0;
		
		
		try {
			
			String sql = "delete from vendeur where Id_Vendeur = ? ";
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
    	Vendeur selected =table.getSelectionModel().getSelectedItem();
        String idd = selected.getId();
        table.getItems().removeAll(selected);
		int etat = supp(idd);
		
		if(etat > 0) 
        {
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Supprimer Vendeur");
    	alert.setHeaderText("Information");
    	alert.setContentText("Vendeur bien Supprim�");
    	alert.showAndWait();
    	
              }
    	else
    	{
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Supprimer Vendeur");
	    	alert.setHeaderText("Information");
	    	alert.setContentText("Vendeur Non Supprim�");
	    	alert.showAndWait();	
    
    	}
		viewAbonnee();
		
	}
    
    
    
   

    
/*--------------------------------------------------Vider les champs----------------------------------------------------------------------------------*/	 	    	    
    
    
    public void vider ( )
    {
    	try {
    		
	    	nomtxt.clear();
	    	txtPrenom.clear();
	    	txtidlog.clear();
	    	txttele.clear();
	    	txtmdp.clear();
	    	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
    	////
    	
    	
    }

/*------------------------------------------------Table TO TEXTFIELD-------------------------------------------------------------------------------*/
    private void TableToText() {
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				Vendeur l =table.getItems().get(table.getSelectionModel().getSelectedIndex());
			//	id.setText(l.getId());
				nomtxt.setText(l.getNom());
				txtPrenom.setText(l.getPrenom());
				txtidlog.setText(l.getLoginid());
				txttele.setText(l.getTel());
				txtmdp.setText(l.getMdp());
			
			}
			
			
		});
	}
@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	TableToText();
	search();
	viewAbonnee();
}
    
@FXML
public void back(MouseEvent event) throws IOException {
	// TODO Autogenerated
	Parent homePage = FXMLLoader.load(getClass().getResource("/admin/Admin.fxml"));
    Scene homepageScene = new Scene(homePage);
    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    appStage.setScene(homepageScene);
    homepageScene.setFill(Color.TRANSPARENT);
    appStage.show();
}
    	
	
    
private void loadDataDB() {
	data.clear();
	try {
		Connection conn = ConnectionDB.conDB();
		PreparedStatement pst=conn.prepareStatement("Select * from vendeur");
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			data.add(new Vendeur(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getString(6),rs.getString(7)));
		}
	}
	catch(SQLException ex){
		Logger.getLogger(VenDeurController.class.getName()).log(Level.SEVERE, null,ex);
	}
	table.setItems(data);
}
    
private void search() {
	rechercher.setOnKeyReleased(e->{
		if(rechercher.getText().equals("")) {
			loadDataDB();
		}
		else {
			data.clear();
			String sql= "Select * from vendeur where Id_Vendeur LIKE '%"+rechercher.getText()+"%' " + "UNION Select * from vendeur where Nom_Vendeur LIKE '%"+rechercher.getText()+"%' "+ "UNION Select * from vendeur where Prenom_Vendeur LIKE '%"+rechercher.getText()+"%' "+ "UNION Select * from vendeur where loginidv LIKE '%"+rechercher.getText()+"%' "+ "UNION Select * from vendeur where Tel_Vendeur LIKE '%"+rechercher.getText()+"%' ";
			try {
				Connection conn = ConnectionDB.conDB();
				PreparedStatement pst=conn.prepareStatement(sql);
				ResultSet rs=pst.executeQuery();
				while(rs.next()) {
					data.add(new Vendeur(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getString(6),rs.getString(7)));
				}
				table.setItems(data);
			}catch(SQLException ex) {
				Logger.getLogger(VenDeurController.class.getName()).log(Level.SEVERE, null,ex);
			}
		}
				
	}
	);
}
public void handleButtonAction(MouseEvent event) {
    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    appStage.close();
}
}
