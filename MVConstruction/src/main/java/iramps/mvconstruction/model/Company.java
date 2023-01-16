package iramps.mvconstruction.model;

public class Company {

	//region properties
	private int id;
	private String name;
	private String vat;
	private String mail;
	private String phone;
	private String isActive;
	private Address address;
	//endregion

	//region constructors
	public Company() {
	}
	public Company(String name, String vat, String mail, String phone, String isActive, Address address) {
		this.name = name;
		this.vat = vat;
		this.mail = mail;
		this.phone = phone;
		this.isActive = isActive;
		this.address = address;
	}
	public Company(int id, String name, String vat, String mail, String phone, String isActive, Address address) {
		this.id = id;
		this.name = name;
		this.vat = vat;
		this.mail = mail;
		this.phone = phone;
		this.isActive = isActive;
		this.address = address;
	}
	//endregion

	//region getters
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getVat() {
		return vat;
	}
	public String getMail() {
		return mail;
	}
	public String getPhone() {
		return phone;
	}
	public String getIsActive() {
		return isActive;
	}
	public Address getAddress() {
		return address;
	}
	//endregion

	//region setters
	public void setName(String name) {
		this.name = name;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	//endregion
}
