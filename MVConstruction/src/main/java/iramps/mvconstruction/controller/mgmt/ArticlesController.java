package iramps.mvconstruction.controller.mgmt;

import iramps.mvconstruction.controller.MgmtController;
import iramps.mvconstruction.dao.implement.ArticleDao;
import iramps.mvconstruction.factory.DaoFactory;
import iramps.mvconstruction.model.Article;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ArticlesController extends MgmtController {

	//region
	private ArticleDao dao;
	@FXML
	private Label label, price, stock, minStock;
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

		articleTable.setItems(FXCollections.observableList(dao.readAll()));
		labelColumn.setCellValueFactory(cellData -> cellData.getValue().labelPropertyProperty());

		showDetail(null);

		articleTable.getSelectionModel().selectedItemProperty().addListener(
				((observableValue, oldValue, newValue) -> showDetail(newValue)));
	}

	public void onAddClick() {
		//TODO popup add
		switchScreen("/iramps.mvconstruction/mgmt/crud/addUpdateArticle.fxml", "Ajouter un article", add);
	}

	public void onDeleteClick() {
		//TODO popup vérification suppression
	}

	public void onUpdateClick() {
		//TODO same popup add but pre filled
		switchScreen("/iramps.mvconstruction/mgmt/crud/addUpdateArticle.fxml", "Ajouter un article", add);
	}

	private void showDetail(Article article) {
		if (article != null) {
			label.setText(article.getLabel());
			price.setText(String.valueOf(article.getPrice()));
			stock.setText(String.valueOf(article.getCurrentStockProperty()));
			minStock.setText(String.valueOf(article.getMinStock()));
		} else {
			label.setText("");
			price.setText("");
			stock.setText("");
			minStock.setText("");
		}
	}
}
