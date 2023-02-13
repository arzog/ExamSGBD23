package iramps.mvconstruction.model;

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

	//region getters
	public int getId() {
		return id;
	}

	public SoldItems getItems() {
		return items;
	}

	public void setItems(SoldItems items) {
		this.items = items;
	}
	//endregion

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Date getSoldDate() {
		return soldDate;
	}

	//region setters
	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
	}

	public boolean isForCompany() {
		return forCompany;
	}

	public void setForCompany(boolean forCompany) {
		this.forCompany = forCompany;
	}
	//endregion
}
