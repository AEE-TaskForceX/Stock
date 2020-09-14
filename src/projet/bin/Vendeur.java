package projet.bin;

public class Vendeur {
	private String id;
	private String nom;
	private String prenom;
	private String tel;

	private String loginid;
	private String mdp;
	public Vendeur(String id,String nom, String prenom , String tel,String loginid,String mdp) {
		super();
		this.id=id;
		this.nom=nom;
		this.prenom=prenom;
		this.tel=tel;
	
		this.loginid=loginid;
		this.mdp=mdp;
	}
	public Vendeur() {
		super();
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
}
