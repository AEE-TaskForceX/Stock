package projet.bin;

public class Client {
	
	String id_client;
	String nom;
	String prenom;
	String adresse;
	String telephone;
	String email;
	
	
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Client(String id_client, String nom, String prenom, String adresse, String telephone, String email) {
		super();
		this.id_client = id_client;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
	}



	public String getId_client() {
		return id_client;
	}



	public void setId_client(String id_client) {
		this.id_client = id_client;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public String getTelephone() {
		return telephone;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	
	
	
	
	

}
