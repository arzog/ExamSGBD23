package iramps.mvconstruction.factory;

import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.dao.implement.*;
import iramps.mvconstruction.model.*;
import iramps.mvconstruction.singleton.DBConnection;

import java.sql.Connection;
public class DaoFactory {

	protected static final Connection connection = DBConnection.getInstance();

	public static Dao<Address> createAddressDao() {
		return new AddressDao(connection);
	}
	public static Dao<Article> createArticleDao() {
		return new ArticleDao(connection);
	}
	public static Dao<Bill> createBillDao() {
		return new BillDao(connection);
	}
	public static Dao<Client> createClientDao() {
		return new ClientDao(connection);
	}
	public static Dao<Company> createCompanyDao() {
		return new CompanyDao(connection);

	}
	public static Dao<SoldItems> createSoldItemsDao() {
		return new SoldItemsDao(connection);
	}
	public static Dao<User> createUserDao() {
		return new UserDao(connection);
	}
}
