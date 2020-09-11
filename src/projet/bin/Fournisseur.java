package projet.bin;

public class Fournisseur {
	private String nom;
	private String adresse;
	private String tel;
	private String mail;
	public Fournisseur(String nom,String adresse, String tel , String mail) {
		super();
		this.nom=nom;
		this.adresse=adresse;
		this.tel=tel;
		this.mail=mail;
	}
	public Fournisseur() {
		super();
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
