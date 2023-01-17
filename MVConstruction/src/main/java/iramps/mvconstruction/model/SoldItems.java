package iramps.mvconstruction.model;

import java.util.Map;

/**
 * billNumber
 * -> for clients ==> NameFirstname_ddMMYY_xxxx
 * -> for companies ==> Name_ddMMYY_xxxx
 */
public class SoldItems {

	//region properties
	private int id;
	private String billNumber;
	private Map<Article, Integer> itemCart;
	//endregion

	//region constructors
	public SoldItems() {
	}
	public SoldItems(String billNumber, Map<Article, Integer> itemCart) {
		this.billNumber = billNumber;
		this.itemCart = itemCart;
	}
	public SoldItems(int id, String billNumber, Map<Article, Integer> itemCart) {
		this.id = id;
		this.billNumber = billNumber;
		this.itemCart = itemCart;
	}
	//endregion

	//region getters
	public int getId() {
		return id;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public Map<Article, Integer> getItemCart() {
		return itemCart;
	}
	//endregion

	//region setters
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public void setItemCart(Map<Article, Integer> itemCart) {
		this.itemCart = itemCart;
	}
	//endregion
}