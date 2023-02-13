package iramps.mvconstruction.model;

public class Company {

	//region properties
	private int id;
	private String name;
	private String vat;
	private String mail;
	private String phone;
	private boolean isActive;
	private Address address;
	//endregion

	//region constructors
	public Company() {
	}

	public Company(String name, String vat, String mail, String phone, boolean isActive, Address address) {
		this.name = name;
		this.vat = vat;
		this.mail = mail;
		this.phone = phone;
		this.isActive = isActive;
		this.address = address;
	}

	public Company(int id, String name, String vat, String mail, String phone, boolean isActive, Address address) {
		this.id = id;
		this.name = name;
		this.vat = vat;
		this.mail = mail;
		this.phone = phone;
		this.isActive = isActive;
		this.address = address;
	}
	//endregion

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}
}
