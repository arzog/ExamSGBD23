package iramps.mvconstruction.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Company {

	//region properties
	private int id;
	private String name, vat, mail, phone;
	private boolean isActive;
	private Address address;

	private StringProperty nameProperty, vatProperty, mailProperty, phoneProperty;
	private BooleanProperty isActiveProperty;
	private ObjectProperty<Address> addressProperty;
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

		nameProperty = new SimpleStringProperty(name);
		vatProperty = new SimpleStringProperty(vat);
		mailProperty = new SimpleStringProperty(mail);
		phoneProperty = new SimpleStringProperty(phone);
		isActiveProperty = new SimpleBooleanProperty(isActive);
		addressProperty = new SimpleObjectProperty<>(address);
	}

	public Company(int id, String name, String vat, String mail, String phone, boolean isActive, Address address) {
		this.id = id;
		this.name = name;
		this.vat = vat;
		this.mail = mail;
		this.phone = phone;
		this.isActive = isActive;
		this.address = address;

		nameProperty = new SimpleStringProperty(name);
		vatProperty = new SimpleStringProperty(vat);
		mailProperty = new SimpleStringProperty(mail);
		phoneProperty = new SimpleStringProperty(phone);
		isActiveProperty = new SimpleBooleanProperty(isActive);
		addressProperty = new SimpleObjectProperty<>(address);
	}
	//endregion

	public ObjectProperty<Address> addressPropertyProperty() {
		return addressProperty;
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

	public String getMailProperty() {
		return mailProperty.get();
	}

	public void setMailProperty(final String mailProperty) {
		this.mailProperty.set(mailProperty);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameProperty() {
		return nameProperty.get();
	}

	public void setNameProperty(final String nameProperty) {
		this.nameProperty.set(nameProperty);
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

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getVatProperty() {
		return vatProperty.get();
	}

	public void setVatProperty(final String vatProperty) {
		this.vatProperty.set(vatProperty);
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(final boolean active) {
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

	public StringProperty mailPropertyProperty() {
		return mailProperty;
	}

	public StringProperty namePropertyProperty() {
		return nameProperty;
	}

	public StringProperty phonePropertyProperty() {
		return phoneProperty;
	}

	public StringProperty vatPropertyProperty() {
		return vatProperty;
	}
}
