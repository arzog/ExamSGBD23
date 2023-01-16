package iramps.mvconstruction.model;

public class Client {

	//region properties
	private int id;
	private String firstname;
	private String lastname;
	private String mail;
	private String phone;
	private Address address;
	//endregion

	//region constructors
	public Client() {
	}
	public Client(String firstname, String lastname, String mail, String phone, Address address) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.mail = mail;
		this.phone = phone;
		this.address = address;
	}
	public Client(int id, String firstname, String lastname, String mail, String phone, Address address) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mail = mail;
		this.phone = phone;
		this.address = address;
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
	public String getMail() {
		return mail;
	}
	public String getPhone() {
		return phone;
	}
	public Address getAddress() {
		return address;
	}
	//endregion

	//region setters
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	//endregion
}
