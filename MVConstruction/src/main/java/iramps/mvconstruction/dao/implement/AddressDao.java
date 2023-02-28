package iramps.mvconstruction.dao.implement;

import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.exception.AddressNotFoundException;
import iramps.mvconstruction.model.Address;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDao extends Dao<Address> {

	public AddressDao(Connection connection) {

		super(connection);
	}

	@Override
	public boolean create(Address address) {
		try {

			Address addressToCheck = readByAddress(address);
			if (addressToCheck == null) {
				PreparedStatement statement = connection.prepareStatement("insert into addresses(country, city, zip_code, street, number) " + "values (?,?,?,?,?)");
				statement.setString(1, address.getCountry());
				statement.setString(2, address.getCity());
				statement.setInt(3, address.getZipCode());
				statement.setString(4, address.getStreet());
				statement.setString(5, address.getNumber());

				statement.executeUpdate();
				statement.close();
				return true;
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Adresse existante");
				alert.setHeaderText("Echec");
				alert.setResizable(false);
				alert.setContentText("Adresse existante");
				alert.showAndWait();
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteByObject(Address address) {
		return false;
	}

	@Override
	public List<Address> readAll() {
		try {
			List<Address> addresses = new ArrayList<>();
			PreparedStatement statement = connection.prepareStatement("select * from addresses",
																	  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet set = statement.executeQuery();
			while (set.next()) {
				addresses.add(new Address(set.getInt("id"), set.getString("country"), set.getString("city"), set.getInt("zip_code"), set.getString("street"), set.getString("number")));
			}
			statement.close();
			return addresses;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new AddressNotFoundException(e.getMessage());
		}
	}

	public Address readByAddress(Address address) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from addresses where country = ? and city  = ? and zip_code = ? and street = ? and number = ?",
																	  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			statement.setString(1, address.getCountry());
			statement.setString(2, address.getCity());
			statement.setInt(3, address.getZipCode());
			statement.setString(4, address.getStreet());
			statement.setString(5, address.getNumber());

			ResultSet set = statement.executeQuery();

			if (set.first()) {
				return new Address(set.getInt("id"), set.getString("country"), set.getString("city"), set.getInt("zip_code"), set.getString("street"), set.getString("number"));
			}
			statement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new AddressNotFoundException(e.getMessage());
		}
		return null;
	}

	@Override
	public Address readById(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from addresses where id = ?",
																	  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			statement.setInt(1, id);

			ResultSet set = statement.executeQuery();

			if (set.first()) {
				return new Address(set.getInt("id"), set.getString("country"), set.getString("city"), set.getInt("zip_code"), set.getString("street"), set.getString("number"));
			}
			statement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new AddressNotFoundException(e.getMessage());
		}
		return null;
	}

	@Override
	public Address readByName(String name) {
		//UNUSED
		return null;
	}

	@Override
	public Address updateById(Address address) {
		try {
			PreparedStatement statement = connection.prepareStatement("update addresses set country = ?, city  = ?, zip_code = ?, street = ?, number = ? where id = ?",
																	  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			statement.setString(1, address.getCountry());
			statement.setString(2, address.getCity());
			statement.setInt(3, address.getZipCode());
			statement.setString(4, address.getStreet());
			statement.setString(5, address.getNumber());
			statement.setInt(6, address.getId());

			statement.executeUpdate();
			statement.close();
			return address;
		} catch (SQLException e) {
			throw new AddressNotFoundException(e.getMessage());
		}
	}
}
