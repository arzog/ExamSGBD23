package iramps.mvconstruction.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

public class Article {

	//region properties
	private int id;
	private String label;
	private double price;
	private int currentStock, minStock;
	private boolean isActive;
	@FXML
	private StringProperty labelProperty;
	@FXML
	private IntegerProperty currentStockProperty, minStockProperty;
	@FXML
	private DoubleProperty priceProperty;
	@FXML
	private BooleanProperty isActiveProperty;
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

		labelProperty = new SimpleStringProperty(label);
		currentStockProperty = new SimpleIntegerProperty(currentStock);
		minStockProperty = new SimpleIntegerProperty(minStock);
		priceProperty = new SimpleDoubleProperty(price);
		isActiveProperty = new SimpleBooleanProperty(isActive);
	}

	public Article(int id, String label, double price, int currentStock, int minStock, boolean isActive) {
		this.id = id;
		this.label = label;
		this.price = price;
		this.currentStock = currentStock;
		this.minStock = minStock;
		this.isActive = isActive;

		labelProperty = new SimpleStringProperty(label);
		currentStockProperty = new SimpleIntegerProperty(currentStock);
		minStockProperty = new SimpleIntegerProperty(minStock);
		priceProperty = new SimpleDoubleProperty(price);
		isActiveProperty = new SimpleBooleanProperty(isActive);
	}
	//endregion

	public IntegerProperty currentStockPropertyProperty() {
		return currentStockProperty;
	}

	public int getCurrentStock() {
		return currentStock;
	}

	public int getCurrentStockProperty() {
		return currentStockProperty.get();
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public BooleanProperty isActivePropertyProperty() {
		return isActiveProperty;
	}

	public boolean isIsActiveProperty() {
		return isActiveProperty.get();
	}

	public StringProperty labelPropertyProperty() {
		return labelProperty;
	}

	public IntegerProperty minStockPropertyProperty() {
		return minStockProperty;
	}

	public DoubleProperty pricePropertyProperty() {
		return priceProperty;
	}

	public double getPriceProperty() {
		return priceProperty.get();
	}

	public boolean isActive() {
		return isActive;
	}

	public String getLabelProperty() {
		return labelProperty.get();
	}

	public int getMinStock() {
		return minStock;
	}

	public double getPrice() {
		return price;
	}

	public int getMinStockProperty() {
		return minStockProperty.get();
	}

	public void setLabelProperty(final String labelProperty) {
		this.labelProperty.set(labelProperty);
	}

	public void setCurrentStockProperty(final int currentStockProperty) {
		this.currentStockProperty.set(currentStockProperty);
	}

	public void setMinStockProperty(final int minStockProperty) {
		this.minStockProperty.set(minStockProperty);
	}

	public void setPriceProperty(final double priceProperty) {
		this.priceProperty.set(priceProperty);
	}

	public void setIsActiveProperty(final boolean isActiveProperty) {
		this.isActiveProperty.set(isActiveProperty);
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}
}
