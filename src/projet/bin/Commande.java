package projet.bin;

public class Commande {
	private String id;
	private String idcli;
	private String datecom;
	private String nomcli;
	private String prenomcli;
	private String telcli;
	private String details;
	private String idarticle;
	private String refarticle;
	private String prix;



	public Commande(String id,String idcli,String idarticle, String datecom, String nomcli, String prenomcli , String telcli,String details,String prix,  String refarticle) {
		super();
		this.id=id;
		this.idcli=idcli;
		this.datecom=datecom;
		this.nomcli=nomcli;
		this.prenomcli=prenomcli;
		this.telcli=telcli;
		this.details=details;
		this.idarticle=idarticle;
		this.refarticle=refarticle;
		this.prix=prix;
	

	}
	public String getIdarticle() {
		return idarticle;
	}
	public void setIdarticle(String idarticle) {
		this.idarticle = idarticle;
	}
	public String getRefarticle() {
		return refarticle;
	}
	public void setRefarticle(String refarticle) {
		this.refarticle = refarticle;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
	}
	public Commande() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdcli() {
		return idcli;
	}
	public void setIdcli(String idcli) {
		this.idcli = idcli;
	}
	public String getDatecom() {
		return datecom;
	}
	public void setDatecom(String datecom) {
		this.datecom = datecom;
	}
	public String getNomcli() {
		return nomcli;
	}
	public void setNomcli(String nomcli) {
		this.nomcli = nomcli;
	}
	public String getPrenomcli() {
		return prenomcli;
	}
	public void setPrenomcli(String prenomcli) {
		this.prenomcli = prenomcli;
	}
	public String getTelcli() {
		return telcli;
	}
	public void setTelcli(String telcli) {
		this.telcli = telcli;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}
