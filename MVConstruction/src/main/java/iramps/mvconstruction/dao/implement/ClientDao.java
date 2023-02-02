package iramps.mvconstruction.dao.implement;

import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDao extends Dao<Client> {

	AddressDao address = new AddressDao(connection);

	public ClientDao(Connection connection) {
		super(connection);
	}

	@Override
	protected boolean create(Client client) {
		try {
			PreparedStatement statement = connection.prepareStatement("insert into clients(firstname,lastname,mail,phone,id_address,isActive) values (?,?,?,?,?,?)");
			statement.setString(1, client.getFirstname());
			statement.setString(2, client.getLastname());
			statement.setString(3, client.getMail());
			statement.setString(4, client.getPhone());
			statement.setInt(5, client.getAddress().getId());
			statement.setBoolean(6, client.isActive());

			statement.executeUpdate();
			statement.close();
			System.out.println("success");
			return true;
		} catch (SQLException e) {
			//TODO define custom exception
			throw new RuntimeException(e);
		}
	}

	@Override
	protected Client readById(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from clients where id = ?");
			statement.setInt(1, id);

			ResultSet set = statement.executeQuery();
			statement.close();

			if (set.first()) {

				return new Client(id, set.getString("firstname"), set.getString("lastname"), set.getString("mail"), set.getString("phone"), address.readById(set.getInt("id_address")), set.getBoolean("isActive"));
			}
			System.out.println("success");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	protected List<Client> readByName(String name) {
		List<Client> clients = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("select * from clients where lastname like ?");
			statement.setString(1, name);

			ResultSet set = statement.executeQuery();
			statement.close();

			while (set.next()) {
				clients.add(new Client(set.getInt("id"), set.getString("firstname"), name, set.getString("mail"), set.getString("phone"), address.readById(set.getInt("id_address")), set.getBoolean("isActive")));
			}
			System.out.println("success");
			return clients;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected Client updateById(Client client) {
		try {
			PreparedStatement statement = connection.prepareStatement("update clients set firstname = ?, lastname = ?, mail = ?, phone = ?, id_address = ?, isActive = ? where id = ?");
			statement.setString(1, client.getFirstname());
			statement.setString(2, client.getLastname());
			statement.setString(3, client.getMail());
			statement.setString(4, client.getPhone());
			statement.setInt(5, client.getAddress().getId());
			statement.setBoolean(6, client.isActive());
			statement.setInt(7, client.getId());

			statement.executeUpdate();
			statement.close();

			System.out.println("success");
			return client;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected boolean deleteByObject(Client client) {
		try {
			PreparedStatement statement = connection.prepareStatement("delete from clients where id = ?");
			statement.setInt(1, client.getId());

			statement.close();
			System.out.println("success");
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
