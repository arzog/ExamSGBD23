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

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public String getFirstname() {
		return firstname;
	}

	//region setters
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	//region getters
	public int getId() {
		return id;
	}

	public String getLastname() {
		return lastname;
	}
	//endregion

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	//endregion
}
