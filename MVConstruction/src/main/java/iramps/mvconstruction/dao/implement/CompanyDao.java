package iramps.mvconstruction.dao.implement;

import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.model.Company;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CompanyDao extends Dao<Company> {

	AddressDao address = new AddressDao(connection);

	public CompanyDao(Connection connection) {
		super(connection);
	}

	@Override
	public boolean create(Company company) {
		try {
			final AtomicBoolean isPresent = new AtomicBoolean(false);
			readAll().forEach(com -> {
				if (com.getName().equalsIgnoreCase(company.getName()) && com.getVat().equalsIgnoreCase(company.getVat())) {
					isPresent.set(true);
				}
			});

			if (!isPresent.get()) {
				address.create(company.getAddress());
				int id_address = address.readByAddress(company.getAddress()).getId();

				PreparedStatement statement = connection.prepareStatement("insert into companies(name,vat,mail,phone,isActive,id_address) values (?,?,?,?,?,?)");
				statement.setString(1, company.getName());
				statement.setString(2, company.getVat());
				statement.setString(3, company.getMail());
				statement.setString(4, company.getPhone());
				statement.setBoolean(5, company.getIsActive());
				statement.setInt(6, id_address);

				statement.executeUpdate();
				statement.close();
				return true;
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Société existante");
				alert.setHeaderText("Echec");
				alert.setResizable(false);
				alert.setContentText("Société existante");
				alert.showAndWait();
				return false;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean deleteByObject(Company company) {
		company.setActive(false);
		Company updatedCompany = updateById(company);
		return updatedCompany != null;
	}

	@Override
	public List<Company> readAll() {
		List<Company> companies = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("select * from companies where isActive = 1");

			ResultSet set = statement.executeQuery();

			while (set.next()) {
				companies.add(new Company(set.getInt("id"), set.getString("name"), set.getString("vat"), set.getString("mail"), set.getString("phone"), set.getBoolean("isActive"),
										  address.readById(set.getInt("id_address"))));
			}
			statement.close();
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
				return new Company(id, set.getString("name"), set.getString("vat"), set.getString("mail"), set.getString("phone"), set.getBoolean("isActive"),
								   address.readById(set.getInt("id_address")));
			}
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
				return new Company(set.getInt("id"), name, set.getString("vat"), set.getString("mail"), set.getString("phone"), set.getBoolean("isActive"),
								   address.readById(set.getInt("id_address")));
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
}
