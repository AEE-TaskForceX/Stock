package projet.bin;

public class Facture {
	private String id;
	private String idcom;
	private String idvend;
	private String nomcli;
	private String prenomcli;
	private String datefact;
	private String tel;
	private String ref;

	private double prix;
	private int quantite;
	private String methpai;
	private double tva;
	private double montant;
	private String date;


	public Facture(String id,String idcom, String idvend, String datefact, String nomcli, String prenomcli, String tel,String date,String ref,double prix,int quantite ,String methpai,double tva,double montant) {
		super();
		this.id=id;
		this.idcom=idcom;
		this.idvend=idvend;
		this.datefact=datefact;
		this.nomcli=nomcli;
		this.prenomcli=prenomcli;
		this.tel=tel;
		this.ref=ref;
		this.prix=prix;
		this.quantite=quantite;
		this.methpai=methpai;
		this.tva=tva;
		this.montant=montant;
		this.date=date;

	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Facture() {
		super();
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
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
	public double getTva() {
		return tva;
	}
	public void setTva(double tva) {
		this.tva = tva;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}

	
}
