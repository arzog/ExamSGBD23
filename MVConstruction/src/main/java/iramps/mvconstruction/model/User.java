package iramps.mvconstruction.model;

public class User {

	//region properties
	private int id;
	private String firstname;
	private String lastname;
	private String username;
	private String pswd;
	private Boolean isActive;
	//endregion

	//region constructors
	public User() {
	}
	public User(String firstname, String lastname, String username, String pswd, Boolean isActive) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.pswd = pswd;
		this.isActive = isActive;
	}
	public User(int id, String firstname, String lastname, String username, String pswd, Boolean isActive) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.pswd = pswd;
		this.isActive = isActive;
	}
	//endregion

	//region getters
	public int getId() {
		return id;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public String getUsername() {
		return username;
	}
	public String getPswd() {
		return pswd;
	}
	public Boolean getActive() {
		return isActive;
	}
	//endregion

	//region setters
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public void setActive(Boolean active) {
		isActive = active;
	}
	//endregion
}
