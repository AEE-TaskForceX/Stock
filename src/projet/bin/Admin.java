package projet.bin;

public class Admin {
	private String id;
	private String loginid;
	private String mdp;
	public Admin(String id,String loginid,String mdp) {
		super();
		
		this.loginid=loginid;
		this.mdp=mdp;
	}
	public Admin() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
