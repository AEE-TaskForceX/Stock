package projet.bin;

public class Article {
	private String id;
	private String nomfourni;
	private String nomcat;
	private String dateachat;
	private int quantite;
	private String prix;
	private String ref;
	private String desc;

	public Article(String id, String nomcat,String nomfourni , String dateachat,int quantite,String prix,String ref,String desc) {
		super();
		this.id=id;
		this.nomfourni=nomfourni;
		this.nomcat=nomcat;
		this.dateachat=dateachat;
		this.quantite=quantite;
		this.prix=prix;
		this.ref=ref;
		this.desc=desc;

	}
	public Article() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNomfourni() {
		return nomfourni;
	}
	public void setNomfourni(String nomfourni) {
		this.nomfourni = nomfourni;
	}
	public String getNomcat() {
		return nomcat;
	}
	public void setNomcat(String nomcat) {
		this.nomcat = nomcat;
	}
	public String getDateachat() {
		return dateachat;
	}
	public void setDateachat(String dateachat) {
		this.dateachat = dateachat;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
