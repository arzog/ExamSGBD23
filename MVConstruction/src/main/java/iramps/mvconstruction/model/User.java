package iramps.mvconstruction.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

	//region properties
	private int id;
	private String firstname;
	private String lastname;
	private String username;
	private String pswd;
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

	//region getters
	public int getId() {
		return id;
	}

	public String getLastname() {
		return lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getPswd() {
		return pswd;
	}

	public String getUsername() {
		return username;
	}

	public Boolean getActive() {
		return isActive;
	}

	public String getFirstnameProperty() {
		return firstnameProperty.get();
	}

	public String getLastnameProperty() {
		return lastnameProperty.get();
	}

	public String getUsernameProperty() {
		return usernameProperty.get();
	}

	public String getPswdProperty() {
		return pswdProperty.get();
	}

	public Boolean getIsActiveProperty() {
		return isActiveProperty.get();
	}

	public StringProperty firstnamePropertyProperty() {
		return firstnameProperty;
	}

	public StringProperty lastnamePropertyProperty() {
		return lastnameProperty;
	}

	public StringProperty usernamePropertyProperty() {
		return usernameProperty;
	}

	public StringProperty pswdPropertyProperty() {
		return pswdProperty;
	}

	public boolean isIsActiveProperty() {
		return isActiveProperty.get();
	}

	public BooleanProperty isActivePropertyProperty() {
		return isActiveProperty;
	}
	//endregion

	//region setters
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public void setFirstnameProperty(final String firstnameProperty) {
		this.firstnameProperty.set(firstnameProperty);
	}

	public void setLastnameProperty(final String lastnameProperty) {
		this.lastnameProperty.set(lastnameProperty);
	}

	public void setUsernameProperty(final String usernameProperty) {
		this.usernameProperty.set(usernameProperty);
	}

	public void setPswdProperty(final String pswdProperty) {
		this.pswdProperty.set(pswdProperty);
	}

	public void setIsActiveProperty(final Boolean isActiveProperty) {
		this.isActiveProperty.set(isActiveProperty);
	}
	//endregion
}
