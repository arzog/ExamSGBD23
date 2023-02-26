package iramps.mvconstruction.dao.implement;

import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.model.Client;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientDao extends Dao<Client> {

	AddressDao address = new AddressDao(connection);

	public ClientDao(Connection connection) {
		super(connection);
	}

	@Override
	public boolean create(Client client) {
		try {
			final AtomicBoolean isPresent = new AtomicBoolean(false);

			address.create(client.getAddress());
			int id_address = address.readByAddress(client.getAddress()).getId();

			readAll().forEach(cli -> {
				if (cli.getLastname().equalsIgnoreCase(client.getLastname()) && cli.getFirstname().equalsIgnoreCase(client.getFirstname())) {
					isPresent.set(true);
				}
			});

			if (!isPresent.get()) {

				PreparedStatement statement = connection.prepareStatement("insert into clients(firstname,lastname,mail,phone,id_address,isActive) values (?,?,?,?,?,?)");
				statement.setString(1, client.getFirstname());
				statement.setString(2, client.getLastname());
				statement.setString(3, client.getMail());
				statement.setString(4, client.getPhone());
				statement.setInt(5, id_address);
				statement.setBoolean(6, client.isActive());

				statement.executeUpdate();
				statement.close();
				return true;
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Client existant");
				alert.setHeaderText("Echec");
				alert.setResizable(false);
				alert.setContentText("Client existant");
				alert.showAndWait();
				return false;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean deleteByObject(Client client) {
		client.setActive(false);
		Client updatedClient = updateById(client);
		return updatedClient != null;
	}

	@Override
	public List<Client> readAll() {
		List<Client> clients = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("select * from clients where isActive = 1");

			ResultSet set = statement.executeQuery();

			while (set.next()) {
				clients.add(
						new Client(set.getInt("id"), set.getString("firstname"), set.getString("lastname"), set.getString("mail"), set.getString("phone"), address.readById(set.getInt("id_address")),
								   set.getBoolean("isActive")));
			}
			statement.close();
			return clients;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Client readById(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from clients where id = ?");
			statement.setInt(1, id);

			ResultSet set = statement.executeQuery();
			statement.close();

			if (set.first()) {

				return new Client(id, set.getString("firstname"), set.getString("lastname"), set.getString("mail"), set.getString("phone"), address.readById(set.getInt("id_address")),
								  set.getBoolean("isActive"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public Client readByName(String name) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from clients where lastname like ?",
																	  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			statement.setString(1, name);

			ResultSet set = statement.executeQuery();
			statement.close();

			if (set.first()) {
				return new Client(set.getInt("id"), set.getString("firstname"), name, set.getString("mail"), set.getString("phone"), address.readById(set.getInt("id_address")),
								  set.getBoolean("isActive"));
			}
			System.out.println("success");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public Client readByName(String name, String firstname) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from clients where lastname like ? and firstname like ?",
																	  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			statement.setString(1, name);
			statement.setString(2, firstname);

			ResultSet set = statement.executeQuery();

			if (set.first()) {
				return new Client(set.getInt("id"), set.getString("firstname"), name, set.getString("mail"), set.getString("phone"), address.readById(set.getInt("id_address")),
								  set.getBoolean("isActive"));
			}
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public Client updateById(Client client) {
		try {
			PreparedStatement statement = connection.prepareStatement("update clients set firstname = ?, lastname = ?, mail = ?, phone = ?, id_address = ?, isActive = ? where id = ?",
																	  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			statement.setString(1, client.getFirstname());
			statement.setString(2, client.getLastname());
			statement.setString(3, client.getMail());
			statement.setString(4, client.getPhone());
			statement.setInt(5, client.getAddress().getId());
			statement.setBoolean(6, client.isActive());
			statement.setInt(7, client.getId());

			statement.executeUpdate();
			statement.close();
			address.updateById(client.getAddress());

			return client;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
