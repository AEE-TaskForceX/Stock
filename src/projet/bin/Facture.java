package projet.bin;

public class Facture {
	private String id;
	private String idcom;
	private String idvend;
	private String nomcli;
	private String prenomcli;
	private String datefact;
	private String methpai;
	private String tva;
	private String montant;


	public Facture(String id,String idcom, String idvend, String nomcli, String prenomcli , String datefact,String methpai,String tva,String montant) {
		super();
		this.id=id;
		this.idcom=idcom;
		this.idvend=idvend;
		this.nomcli=nomcli;
		this.prenomcli=prenomcli;
	
		this.datefact=datefact;
		this.methpai=methpai;
		this.tva=tva;
		this.montant=montant;
	

	}
	public Facture() {
		super();
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdcom() {
		return idcom;
	}
	public void setIdcom(String idcom) {
		this.idcom = idcom;
	}
	public String getIdvend() {
		return idvend;
	}
	public void setIdvend(String idvend) {
		this.idvend = idvend;
	}
	public String getDatefact() {
		return datefact;
	}
	public void setDatefact(String datefact) {
		this.datefact = datefact;
	}
	public String getMethpai() {
		return methpai;
	}
	public void setMethpai(String methpai) {
		this.methpai = methpai;
	}
	public String getTva() {
		return tva;
	}
	public void setTva(String tva) {
		this.tva = tva;
	}
	public String getMontant() {
		return montant;
	}
	public void setMontant(String montant) {
		this.montant = montant;
	}

	
}
