package iramps.mvconstruction.dao.implement;

import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.exception.BillNotFoundException;
import iramps.mvconstruction.model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDao extends Dao<Bill> {

	private UserDao userDao;
	private ClientDao clientDao;
	private CompanyDao companyDao;
	private SoldItemsDao soldItemsDao;

	public BillDao(Connection connection) {
		super(connection);
	}

	@Override
	public boolean create(Bill bill) {
		try {
			PreparedStatement statement = connection.prepareStatement("insert into bills(sold_date, id_sold_items, id_user, id_client, id_company) " + "values (?,?,?,?,?)");
			statement.setDate(1, bill.getSoldDate());
			statement.setInt(2, bill.getItems().getId());
			statement.setInt(3, bill.getSeller().getId());

			if (bill.isForCompany()) {
				statement.setInt(5, bill.getCompany().getId());
			} else {
				statement.setInt(4, bill.getClient().getId());
			}

			statement.executeUpdate();
			statement.close();
			return true;
		} catch (SQLException e) {
			throw new BillNotFoundException(e.getMessage());
		}
	}

	@Override
	public boolean deleteByObject(Bill bill) {
		return false;
	}

	@Override
	public List<Bill> readAll() {
		try {
			List<Bill> bills = new ArrayList<>();
			PreparedStatement statement = connection.prepareStatement("select * from bills");

			ResultSet set = statement.executeQuery();

			while (set.next()) {
				boolean isCompany = set.getBoolean("for_company");

				if (isCompany) {
					bills.add(new Bill(set.getInt("id"), set.getDate("sold_date"), soldItemsDao.readById(set.getInt("id_sold_items")), userDao.readById(set.getInt("id_user")),
									   companyDao.readById(set.getInt("id_company")), true));
				} else {
					bills.add(new Bill(set.getInt("id"), set.getDate("sold_date"), soldItemsDao.readById(set.getInt("id_sold_items")), userDao.readById(set.getInt("id_user")),
									   clientDao.readById(set.getInt("id_client")), false));
				}
			}

			return bills;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Bill readById(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from bills where id = ?");
			statement.setInt(1, id);

			ResultSet set = statement.executeQuery();
			statement.close();

			if (set.first()) {

				boolean isCompany = set.getBoolean("for_company");

				if (isCompany) {
					return new Bill(id, set.getDate("sold_date"), soldItemsDao.readById(set.getInt("id_sold_items")), userDao.readById(set.getInt("id_user")),
									companyDao.readById(set.getInt("id_company")), true);
				} else {
					return new Bill(id, set.getDate("sold_date"), soldItemsDao.readById(set.getInt("id_sold_items")), userDao.readById(set.getInt("id_user")),
									clientDao.readById(set.getInt("id_client")), false);
				}
			}
		} catch (SQLException e) {
			throw new BillNotFoundException(e.getMessage());
		}
		return null;
	}

	@Override
	public Bill readByName(String name) {
		return null;
	}

	@Override
	public Bill updateById(Bill bill) {
		try {
			PreparedStatement statement = connection.prepareStatement("update bills set sold_date = ?, id_sold_items = ?, id_user = ?,id_client = ?, id_company = ?, for_company = ?",
																	  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			statement.setDate(1, bill.getSoldDate());
			statement.setInt(2, bill.getItems().getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
