package iramps.mvconstruction.model;

public class Article {

	//region properties
	private int id;
	private String label;
	private double price;
	private int currentStock;
	private int minStock;
	private boolean isActive;
	//endregion

	//region constructors
	public Article() {
	}

	public Article(String label, double price, int currentStock, int minStock, boolean isActive) {
		this.label = label;
		this.price = price;
		this.currentStock = currentStock;
		this.minStock = minStock;
		this.isActive = isActive;
	}

	public Article(int id, String label, double price, int currentStock, int minStock, boolean isActive) {
		this.id = id;
		this.label = label;
		this.price = price;
		this.currentStock = currentStock;
		this.minStock = minStock;
		this.isActive = isActive;
	}
	//endregion

	public int getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}

	//region getters
	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	//region setters
	public void setLabel(String label) {
		this.label = label;
	}

	public int getMinStock() {
		return minStock;
	}
	//endregion

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}
	//endregion
}
