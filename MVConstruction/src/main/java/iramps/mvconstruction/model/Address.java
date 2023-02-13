package iramps.mvconstruction.model;

public class Address {

	//region properties
	private int id;
	private String country;
	private String city;
	private int zipCode;
	private String street;
	private String number;
	//endregion

	//region constructors
	public Address() {
	}

	public Address(String country, String city, int zipCode, String street, String number) {
		this.country = country;
		this.city = city;
		this.zipCode = zipCode;
		this.street = street;
		this.number = number;
	}

	public Address(int id, String country, String city, int zipCode, String street, String number) {
		this.id = id;
		this.country = country;
		this.city = city;
		this.zipCode = zipCode;
		this.street = street;
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
}
