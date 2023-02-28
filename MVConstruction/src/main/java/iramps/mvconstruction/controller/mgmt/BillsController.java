package iramps.mvconstruction.controller.mgmt;

import iramps.mvconstruction.controller.MgmtController;
import iramps.mvconstruction.dao.implement.BillDao;
import iramps.mvconstruction.dao.implement.SoldItemsDao;
import iramps.mvconstruction.factory.DaoFactory;
import iramps.mvconstruction.model.Bill;
import iramps.mvconstruction.model.SoldItems;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BillsController extends MgmtController {

	private boolean updating = false;
	private Bill selectedBill;

	private BillDao billDao;
	private SoldItemsDao soldItemsDao;

	@FXML
	private TableView<Bill> summary;
	@FXML
	private TableView<SoldItems> itemsList;
	@FXML
	private TableColumn<Bill, String> buyerList;
	@FXML
	private TableColumn<SoldItems, String> billNumberList;
	@FXML
	private TabPane ticketPane, itemsPane;
	@FXML
	private Label billNumber, soldDate, buyer, clientOrCompany, seller, totalPrice;
	@FXML
	private TextField tfBillNum, tfSoldDate, tfBuyer, tfSeller, tfTotalPrice;
	@FXML
	private Button add, update, delete, refresh;

	@FXML
	public void initialize() {
		billDao = (BillDao) DaoFactory.createBillDao();

		summary.setItems(FXCollections.observableList(billDao.readAll()));
		buyerList.setCellValueFactory(cellData -> {

			if (cellData.getValue().isForCompany()) {
				return cellData.getValue().getCompany().namePropertyProperty();
			} else {
				return cellData.getValue().getClient().lastnamePropertyProperty();
			}
		});
	}
}
