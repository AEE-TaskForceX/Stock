package projet.bin;

public class Commande {
	private String id;
	private String idcli;
	private String datecom;
	private String nomcli;
	private String prenomcli;
	private String telcli;
	private String details;



	public Commande(String id,String idcli, String datecom, String nomcli, String prenomcli , String telcli,String details) {
		super();
		this.id=id;
		this.idcli=idcli;
		this.datecom=datecom;
		this.nomcli=nomcli;
		this.prenomcli=prenomcli;
		this.telcli=telcli;
		this.details=details;
	

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
