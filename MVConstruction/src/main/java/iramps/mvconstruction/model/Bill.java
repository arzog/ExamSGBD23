package iramps.mvconstruction.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;

import java.sql.Date;

public class Bill {

	//region properties
	private int id;
	private Date soldDate;
	private SoldItems items;
	private User seller;
	private Client client;
	private Company company;
	private boolean forCompany;

	private BooleanProperty forCompanyProperty;
	private ObjectProperty<SoldItems> soldItemsProperty;
	private ObjectProperty<Client> clientProperty;
	private ObjectProperty<Company> companyProperty;
	private ObjectProperty<Date> dateProperty;
	//endregion

	//region constructors
	public Bill() {
	}

	public Bill(Date soldDate, SoldItems items, User seller, Client client, Company company, boolean forCompany) {
		this.soldDate = soldDate;
		this.items = items;
		this.seller = seller;
		this.client = client;
		this.company = company;
		this.forCompany = forCompany;
	}

	public Bill(int id, Date soldDate, SoldItems items, User seller, Client client, Company company, boolean forCompany) {
		this.id = id;
		this.soldDate = soldDate;
		this.items = items;
		this.seller = seller;
		this.client = client;
		this.company = company;
		this.forCompany = forCompany;
	}

	// constructor for client
	public Bill(int id, Date soldDate, SoldItems items, User seller, Client client, boolean forCompany) {
		this.id = id;
		this.soldDate = soldDate;
		this.items = items;
		this.seller = seller;
		this.client = client;
		this.forCompany = forCompany;
	}

	//constructor for company
	public Bill(int id, Date soldDate, SoldItems items, User seller, Company company, boolean forCompany) {
		this.id = id;
		this.soldDate = soldDate;
		this.items = items;
		this.seller = seller;
		this.company = company;
		this.forCompany = forCompany;
	}

	//endregion

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public SoldItems getItems() {
		return items;
	}

	public void setItems(SoldItems items) {
		this.items = items;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Date getSoldDate() {
		return soldDate;
	}

	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
	}

	public boolean isForCompany() {
		return forCompany;
	}

	public void setForCompany(boolean forCompany) {
		this.forCompany = forCompany;
	}

	public boolean isForCompanyProperty() {
		return forCompanyProperty.get();
	}

	public BooleanProperty forCompanyPropertyProperty() {
		return forCompanyProperty;
	}

	public void setForCompanyProperty(final boolean forCompanyProperty) {
		this.forCompanyProperty.set(forCompanyProperty);
	}

	public SoldItems getSoldItemsProperty() {
		return soldItemsProperty.get();
	}

	public ObjectProperty<SoldItems> soldItemsPropertyProperty() {
		return soldItemsProperty;
	}

	public void setSoldItemsProperty(final SoldItems soldItemsProperty) {
		this.soldItemsProperty.set(soldItemsProperty);
	}

	public Client getClientProperty() {
		return clientProperty.get();
	}

	public ObjectProperty<Client> clientPropertyProperty() {
		return clientProperty;
	}

	public void setClientProperty(final Client clientProperty) {
		this.clientProperty.set(clientProperty);
	}

	public Company getCompanyProperty() {
		return companyProperty.get();
	}

	public ObjectProperty<Company> companyPropertyProperty() {
		return companyProperty;
	}

	public void setCompanyProperty(final Company companyProperty) {
		this.companyProperty.set(companyProperty);
	}

	public Date getDateProperty() {
		return dateProperty.get();
	}

	public ObjectProperty<Date> datePropertyProperty() {
		return dateProperty;
	}

	public void setDateProperty(final Date dateProperty) {
		this.dateProperty.set(dateProperty);
	}
}
