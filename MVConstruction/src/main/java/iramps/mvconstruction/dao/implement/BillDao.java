package iramps.mvconstruction.dao.implement;

import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.exception.BillNotFoundException;
import iramps.mvconstruction.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class BillDao extends Dao<Bill> {

	public BillDao(Connection connection) {
		super(connection);
	}
	@Override
	protected boolean create(Bill bill) {
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
	protected Bill readById(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from bills where id = ?");
			statement.setInt(1, id);

			ResultSet set = statement.executeQuery();
			statement.close();

			if (set.first()) {

				boolean isCompany = set.getBoolean("for_company");

				//TODO complete new user and company
				if (isCompany) {
					return new Bill(id, set.getDate("sold_date"), new SoldItems(), new User(), new Company(), true);
				} else {
					return new Bill(id, set.getDate("sold_date"), new SoldItems(), new User(), new Client(), false);
				}
			}
		} catch (SQLException e) {
			throw new BillNotFoundException(e.getMessage());
		}
		return null;
	}
	@Override
	protected List<Bill> readByName(String name) {
		return null;
	}
	@Override
	protected Bill updateById(Bill bill) {
		try {
			PreparedStatement statement = connection.prepareStatement("update bills set sold_date = ?, id_sold_items = ?, id_user = ?,id_client = ?, id_company = ?, for_company = ?");
			statement.setDate(1,bill.getSoldDate());
			statement.setInt(2,bill.getItems().getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} return null;
	}
	@Override
	protected boolean deleteByObject(Bill bill) {
		return false;
	}
}
