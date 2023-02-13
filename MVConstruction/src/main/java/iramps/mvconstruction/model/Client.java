package iramps.mvconstruction.model;

public class Client {

	//region properties
	private int id;
	private String firstname;
	private String lastname;
	private String mail;
	private String phone;
	private Address address;
	private boolean isActive;
	//endregion

	//region constructors
	public Client() {
	}

	public Client(String firstname, String lastname, String mail, String phone, Address address, boolean isActive) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.mail = mail;
		this.phone = phone;
		this.address = address;
		this.isActive = isActive;
	}

	public Client(int id, String firstname, String lastname, String mail, String phone, Address address, boolean isActive) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mail = mail;
		this.phone = phone;
		this.address = address;
		this.isActive = isActive;
	}
	//endregion

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	//endregion

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}
	//endregion
}
