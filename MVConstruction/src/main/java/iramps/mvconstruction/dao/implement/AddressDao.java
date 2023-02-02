package iramps.mvconstruction.dao.implement;

import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.exception.AddressNotFoundException;
import iramps.mvconstruction.model.Address;

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
	protected boolean create(Address address) {
		try {
			PreparedStatement statement = connection.prepareStatement("insert into addresses(country, city, zip_code, street, number) " + "values (?,?,?,?,?)");
			statement.setString(1, address.getCountry());
			statement.setString(2, address.getCity());
			statement.setInt(3, address.getZipCode());
			statement.setString(4, address.getStreet());
			statement.setString(5, address.getNumber());

			statement.executeUpdate();
			statement.close();
			System.out.println("Address successfully created");
			return true;
		} catch (SQLException e) {
			System.out.println("An issue occured while creating the address");
			System.out.println(e.getMessage());
			return false;
		}
	}
	@Override
	protected Address readById(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * " + "from addresses " + "where id = ?");
			statement.setInt(1, id);

			ResultSet set = statement.executeQuery();
			statement.close();

			if (set.first()) {
				return new Address(set.getInt("id"), set.getString("country"), set.getString("city"), set.getInt("zip_code"), set.getString("street"), set.getString("number"));
			}
		} catch (SQLException e) {
			System.out.println("An issue occured while searching address of whom id: " + id);
			System.out.println(e.getMessage());
			throw new AddressNotFoundException(e.getMessage());
		}
		return null;
	}
	@Override
	protected List<Address> readByName(String name) {
		try {
			List<Address> addresses = new ArrayList<>();
			PreparedStatement statement = connection.prepareStatement("select * " + "from addresses " + "where country like ?");
			statement.setString(1, name);

			ResultSet set = statement.executeQuery();
			statement.close();
			while (set.next()) {
				addresses.add(new Address(set.getInt("id"), set.getString("country"), set.getString("city"), set.getInt("zip_code"), set.getString("street"), set.getString("number")));
			}
			return addresses;
		} catch (SQLException e) {
			System.out.println("An issue occured while searching addresses of whom name: " + name);
			System.out.println(e.getMessage());
			throw new AddressNotFoundException(e.getMessage());
		}
	}
	@Override
	protected Address updateById(Address address) {
		try {
			PreparedStatement statement = connection.prepareStatement("update addresses " + "set country = ?, city  = ?, zip_code = ?, street = ?, number = ? where id = ?");
			statement.setString(1, address.getCountry());
			statement.setString(2, address.getCity());
			statement.setInt(3, address.getZipCode());
			statement.setString(4, address.getStreet());
			statement.setString(5, address.getNumber());
			statement.setInt(6, address.getId());

			statement.executeUpdate();
			statement.close();
			System.out.println("Address successfully updated");
			return address;
		} catch (SQLException e) {
			throw new AddressNotFoundException(e.getMessage());
		}
	}
	@Override
	protected boolean deleteByObject(Address address) {
		try {
			PreparedStatement statement = connection.prepareStatement("delete from addresses where id = ?");
			statement.setInt(1, address.getId());

			statement.executeUpdate();
			statement.close();
			System.out.println("Address successfully deleted");
			return true;
		} catch (SQLException e) {
			throw new AddressNotFoundException(e.getMessage());
		}
	}
}
