

package projet.bin;

public class Categorie {
	private String nom;
	private String intitule;




	public Categorie(String nom,String intitule) {
		super();
		this.nom=nom;
		this.intitule=intitule;
		
	

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
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
}
