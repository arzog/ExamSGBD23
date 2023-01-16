package iramps.mvconstruction.model;

import java.util.Date;
public class Bill {

	//region properties
	private int id;
	private Date soldDate;
	private SoldItems items;
	private User seller;
	private Client client;
	private Company company;
	//endregion

	//region constructors
	public Bill() {
	}
	public Bill(Date soldDate, SoldItems items, User seller, Client client, Company company) {
		this.soldDate = soldDate;
		this.items = items;
		this.seller = seller;
		this.client = client;
		this.company = company;
	}
	public Bill(int id, Date soldDate, SoldItems items, User seller, Client client, Company company) {
		this.id = id;
		this.soldDate = soldDate;
		this.items = items;
		this.seller = seller;
		this.client = client;
		this.company = company;
	}
	//endregion

	//region getters
	public int getId() {
		return id;
	}
	public Date getSoldDate() {
		return soldDate;
	}
	public SoldItems getItems() {
		return items;
	}
	public User getSeller() {
		return seller;
	}
	public Client getClient() {
		return client;
	}
	public Company getCompany() {
		return company;
	}
	//endregion

	//region setters
	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
	}
	public void setItems(SoldItems items) {
		this.items = items;
	}
	public void setSeller(User seller) {
		this.seller = seller;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	//endregion
}
