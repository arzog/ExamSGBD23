package iramps.mvconstruction.dao.implement;

import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao extends Dao<Company> {

	AddressDao address = new AddressDao(connection);

	public CompanyDao(Connection connection) {
		super(connection);
	}

	@Override
	public boolean create(Company company) {
		try {
			PreparedStatement statement = connection.prepareStatement("insert into companies(name,vat,mail,phone,isActive,id_address) values (?,?,?,?,?,?)");
			statement.setString(1, company.getName());
			statement.setString(2, company.getVat());
			statement.setString(3, company.getMail());
			statement.setString(4, company.getPhone());
			statement.setBoolean(5, company.getIsActive());
			statement.setInt(6, company.getAddress().getId());

			statement.executeUpdate();
			statement.close();
			System.out.println("success");
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Company> readAll() {
		List<Company> companies = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("select * from companies where name like ?");

			ResultSet set = statement.executeQuery();
			statement.close();

			while (set.next()) {
				companies.add(new Company(set.getInt("id"), set.getString("name"), set.getString("vat"), set.getString("mail"), set.getString("phone"), set.getBoolean("isActive"), address.readById(set.getInt("id_address"))));
			}
			System.out.println("success");
			return companies;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public Company readById(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from companies where id = ?");
			statement.setInt(1, id);

			ResultSet set = statement.executeQuery();
			statement.close();

			if (set.first()) {
				return new Company(id, set.getString("name"), set.getString("vat"), set.getString("mail"), set.getString("phone"), set.getBoolean("isActive"), address.readById(set.getInt("id_address")));
			}
			System.out.println("success");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public Company readByName(String name) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from companies where name like ?");
			statement.setString(1, name);

			ResultSet set = statement.executeQuery();
			statement.close();

			if (set.first()) {
				return new Company(set.getInt("id"), name, set.getString("vat"), set.getString("mail"), set.getString("phone"), set.getBoolean("isActive"), address.readById(set.getInt("id_address")));
			}
			System.out.println("success");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public Company updateById(Company company) {
		try {
			PreparedStatement statement = connection.prepareStatement("update companies set name = ?, vat = ?, mail = ?, phone = ?, isActive = ?, id_address = ? where id = ?");
			statement.setString(1, company.getName());
			statement.setString(2, company.getVat());
			statement.setString(3, company.getMail());
			statement.setString(4, company.getPhone());
			statement.setBoolean(5, company.getIsActive());
			statement.setInt(6, company.getAddress().getId());
			statement.setInt(7, company.getId());

			statement.executeUpdate();
			statement.close();
			System.out.println("success");
			return company;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean deleteByObject(Company company) {
		try {
			PreparedStatement statement = connection.prepareStatement("delete from companies where id = ?");
			statement.setInt(1, company.getId());

			statement.executeUpdate();
			statement.close();
			System.out.println("success");
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
