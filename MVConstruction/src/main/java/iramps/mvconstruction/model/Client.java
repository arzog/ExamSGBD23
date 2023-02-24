package iramps.mvconstruction.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {

	//region properties
	private int id;
	private String firstname, lastname, mail, phone;
	private Address address;
	private boolean isActive;
	private StringProperty firstnameProperty, lastnameProperty, mailProperty, phoneProperty;
	private BooleanProperty isActiveProperty;
	private ObjectProperty<Address> addressProperty;
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

		firstnameProperty = new SimpleStringProperty(firstname);
		lastnameProperty = new SimpleStringProperty(lastname);
		mailProperty = new SimpleStringProperty(mail);
		phoneProperty = new SimpleStringProperty(phone);
		isActiveProperty = new SimpleBooleanProperty(isActive);
		addressProperty = new SimpleObjectProperty<>(address);
	}

	public Client(int id, String firstname, String lastname, String mail, String phone, Address address, boolean isActive) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mail = mail;
		this.phone = phone;
		this.address = address;
		this.isActive = isActive;

		firstnameProperty = new SimpleStringProperty(firstname);
		lastnameProperty = new SimpleStringProperty(lastname);
		mailProperty = new SimpleStringProperty(mail);
		phoneProperty = new SimpleStringProperty(phone);
		isActiveProperty = new SimpleBooleanProperty(isActive);
		addressProperty = new SimpleObjectProperty<>(address);
	}
	//endregion

	public ObjectProperty<Address> addressPropertyProperty() {
		return addressProperty;
	}

	public StringProperty firstnamePropertyProperty() {
		return firstnameProperty;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddressProperty() {
		return addressProperty.get();
	}

	public void setAddressProperty(final Address addressProperty) {
		this.addressProperty.set(addressProperty);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFirstnameProperty() {
		return firstnameProperty.get();
	}

	public void setFirstnameProperty(final String firstnameProperty) {
		this.firstnameProperty.set(firstnameProperty);
	}

	public int getId() {
		return id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLastnameProperty() {
		return lastnameProperty.get();
	}

	public void setLastnameProperty(final String lastnameProperty) {
		this.lastnameProperty.set(lastnameProperty);
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMailProperty() {
		return mailProperty.get();
	}

	public void setMailProperty(final String mailProperty) {
		this.mailProperty.set(mailProperty);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneProperty() {
		return phoneProperty.get();
	}

	public void setPhoneProperty(final String phoneProperty) {
		this.phoneProperty.set(phoneProperty);
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public BooleanProperty isActivePropertyProperty() {
		return isActiveProperty;
	}

	public boolean isIsActiveProperty() {
		return isActiveProperty.get();
	}

	public void setIsActiveProperty(final boolean isActiveProperty) {
		this.isActiveProperty.set(isActiveProperty);
	}

	public StringProperty lastnamePropertyProperty() {
		return lastnameProperty;
	}

	public StringProperty mailPropertyProperty() {
		return mailProperty;
	}

	public StringProperty phonePropertyProperty() {
		return phoneProperty;
	}
}
