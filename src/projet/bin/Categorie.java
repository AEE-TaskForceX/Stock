

package projet.bin;

public class Categorie {
	private String nom;
	private String desc;




	public Categorie(String nom,String desc) {
		super();
		this.nom=nom;
		this.desc=desc;
		
	

	}
	public Categorie() {
		super();
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
