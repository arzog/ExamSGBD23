package iramps.mvconstruction.controller.mgmt;

import iramps.mvconstruction.controller.MgmtController;
import iramps.mvconstruction.dao.implement.ArticleDao;
import iramps.mvconstruction.factory.DaoFactory;
import iramps.mvconstruction.model.Article;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.Optional;

public class ArticlesController extends MgmtController {

	//region
	private ArticleDao dao;
	private boolean updating = false;
	private Article selectedArticle;
	@FXML
	private Label label, price, stock, minStock;
	@FXML
	private TextField tfName, tfPrice, tfCurrentStock, tfMinStock;
	@FXML
	private Button add, update, delete;
	@FXML
	private TableView<Article> articleTable;
	@FXML
	private TableColumn<Article, String> labelColumn;
	//endregion

	@FXML
	public void initialize() {
		dao = (ArticleDao) DaoFactory.createArticleDao();

		tfName.setVisible(false);
		tfPrice.setVisible(false);
		tfCurrentStock.setVisible(false);
		tfMinStock.setVisible(false);

		articleTable.setItems(FXCollections.observableList(dao.readAll()));
		labelColumn.setCellValueFactory(cellData -> cellData.getValue().labelPropertyProperty());

		showDetail(null);

		articleTable.getSelectionModel().selectedItemProperty().addListener(
				((observableValue, oldValue, newValue) -> {
					showDetail(newValue);
					selectedArticle = newValue;
				}));
	}

	public void onAddClick() {
		if (!updating) {
			callModal("/iramps.mvconstruction/mgmt/crud/addArticle.fxml", "Ajouter un article", add);
		} else {
			Article article = extractArticle();
			if (article != null) {
				selectedArticle = dao.updateById(article);
				Alert success = new Alert(AlertType.INFORMATION);
				success.setTitle("Mise à jour validée");
				success.setHeaderText("Mise à jour validée");
				success.setResizable(false);
				success.setContentText("Entrée mise à jour");
				Optional<ButtonType> result = success.showAndWait();
				if (!result.isPresent() || result.get() == ButtonType.OK) {
					refresh();
				}
			}
		}
	}

	public void onDeleteClick() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation de suppression");
		alert.setHeaderText("Confirmation de suppression");
		alert.setResizable(false);
		alert.setContentText("Etes vous certain de vouloir désactiver cet article");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			dao.deleteByObject(selectedArticle);
			refresh();
		}
	}

	public void onUpdateClick() {
		if (!updating) {
			label.setVisible(false);
			price.setVisible(false);
			stock.setVisible(false);
			minStock.setVisible(false);

			tfName.setVisible(true);
			tfPrice.setVisible(true);
			tfCurrentStock.setVisible(true);
			tfMinStock.setVisible(true);

			updating = true;
		} else {
			label.setVisible(true);
			price.setVisible(true);
			stock.setVisible(true);
			minStock.setVisible(true);

			tfName.setVisible(false);
			tfPrice.setVisible(false);
			tfCurrentStock.setVisible(false);
			tfMinStock.setVisible(false);

			updating = false;
		}
	}

	public void refresh() {
		initialize();
	}

	private Article extractArticle() {
		Article article = dao.readByName(tfName.getText());

		if (article != null) {
			return new Article(
					article.getId(),
					tfName.getText(),
					Double.parseDouble(tfPrice.getText()),
					Integer.parseInt(tfCurrentStock.getText()),
					Integer.parseInt(tfMinStock.getText()),
					true
			);
		} else {
			updating = false;
			return null;
		}
	}

	private void showDetail(Article article) {
		if (article != null) {
			label.setText(article.getLabel());
			price.setText(String.valueOf(article.getPrice()));
			stock.setText(String.valueOf(article.getCurrentStockProperty()));
			minStock.setText(String.valueOf(article.getMinStock()));

			tfName.setText(article.getLabel());
			tfPrice.setText(String.valueOf(article.getPrice()));
			tfCurrentStock.setText(String.valueOf(article.getCurrentStockProperty()));
			tfMinStock.setText(String.valueOf(article.getMinStock()));
		} else {
			label.setText("");
			price.setText("");
			stock.setText("");
			minStock.setText("");
		}
	}
}
