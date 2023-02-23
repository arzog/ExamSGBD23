package iramps.mvconstruction.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class User {

	//region properties
	private int id;
	private String firstname, lastname, username, pswd;
	private Boolean isActive;
	private StringProperty firstnameProperty, lastnameProperty, usernameProperty, pswdProperty;
	private BooleanProperty isActiveProperty;
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

		firstnameProperty = new SimpleStringProperty(firstname);
		lastnameProperty = new SimpleStringProperty(lastname);
		usernameProperty = new SimpleStringProperty(username);
		pswdProperty = new SimpleStringProperty(pswd);
		isActiveProperty = new SimpleBooleanProperty(isActive);
	}

	public User(int id, String firstname, String lastname, String username, String pswd, Boolean isActive) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.pswd = pswd;
		this.isActive = isActive;

		firstnameProperty = new SimpleStringProperty(firstname);
		lastnameProperty = new SimpleStringProperty(lastname);
		usernameProperty = new SimpleStringProperty(username);
		pswdProperty = new SimpleStringProperty(pswd);
		isActiveProperty = new SimpleBooleanProperty(isActive);
	}
	//endregion

	public int getId() {
		return id;
	}

	public StringProperty firstnamePropertyProperty() {
		return firstnameProperty;
	}

	public Boolean getActive() {
		return isActive;
	}

	public Boolean getIsActiveProperty() {
		return isActiveProperty.get();
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPswd() {
		return pswd;
	}

	public String getPswdProperty() {
		return pswdProperty.get();
	}

	public String getLastnameProperty() {
		return lastnameProperty.get();
	}

	public StringProperty lastnamePropertyProperty() {
		return lastnameProperty;
	}

	public StringProperty pswdPropertyProperty() {
		return pswdProperty;
	}

	public StringProperty usernamePropertyProperty() {
		return usernameProperty;
	}

	public String getFirstnameProperty() {
		return firstnameProperty.get();
	}

	public String getUsername() {
		return username;
	}

	public String getUsernameProperty() {
		return usernameProperty.get();
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public void setFirstnameProperty(final String firstnameProperty) {
		this.firstnameProperty.set(firstnameProperty);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUsernameProperty(final String usernameProperty) {
		this.usernameProperty.set(usernameProperty);
	}

	public BooleanProperty isActivePropertyProperty() {
		return isActiveProperty;
	}

	public boolean isIsActiveProperty() {
		return isActiveProperty.get();
	}

	public void setIsActiveProperty(final Boolean isActiveProperty) {
		this.isActiveProperty.set(isActiveProperty);
	}

	public void setLastnameProperty(final String lastnameProperty) {
		this.lastnameProperty.set(lastnameProperty);
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public void setPswdProperty(final String pswdProperty) {
		this.pswdProperty.set(pswdProperty);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final User user)) {
			return false;
		}
		return firstname.equals(user.firstname) && lastname.equals(user.lastname) && username.equals(user.username) && pswd.equals(user.pswd) && isActive.equals(user.isActive);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstname, lastname, username, pswd, isActive, firstnameProperty, lastnameProperty, usernameProperty, pswdProperty, isActiveProperty);
	}
}
