package iramps.mvconstruction.dao.implement;

import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserDao extends Dao<User> {

	public UserDao(Connection connection) {
		super(connection);
	}

	@Override
	public boolean create(User user) {
		try {
			final AtomicBoolean isPresent = new AtomicBoolean(false);
			readAll().forEach(entry -> {
				if (entry.getUsername().equalsIgnoreCase(user.getUsername())) {
					isPresent.set(true);
				}
			});

			if (!isPresent.get()) {
				PreparedStatement statement = connection.prepareStatement("insert into users(firstname,lastname,username,passwd,isActive) values (?,?,?,?,?)");
				statement.setString(1, user.getFirstname());
				statement.setString(2, user.getLastname());
				statement.setString(3, user.getUsername());
				statement.setString(4, user.getPswd());
				statement.setBoolean(5, user.getActive());

				statement.executeUpdate();
				statement.close();
				return true;
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Utilisateur existant");
				alert.setHeaderText("Echec");
				alert.setResizable(false);
				alert.setContentText("Utilisateur existant");
				alert.showAndWait();
				return false;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean deleteByObject(User user) {
		user.setActive(false);
		User updatedUser = updateById(user);
		return updatedUser != null;
	}

	@Override
	public List<User> readAll() {
		List<User> users = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("select * from users where isActive = 1");

			ResultSet set = statement.executeQuery();

			while (set.next()) {
				users.add(new User(set.getInt("id"), set.getString("firstname"), set.getString("lastname"), set.getString("username"), set.getString("passwd"), set.getBoolean("isActive")));
			}
			statement.close();
			return users;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User readById(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from users where id = ?",
																	  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			statement.setInt(1, id);

			ResultSet set = statement.executeQuery();

			if (set.first()) {
				return new User(id, set.getString("firstname"), set.getString("lastname"), set.getString("username"), set.getString("passwd"), set.getBoolean("isActive"));
			}
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public User readByName(String name) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from users where username like ?",
																	  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			statement.setString(1, name);

			ResultSet set = statement.executeQuery();

			if (set.next()) {
				return new User(set.getInt("id"), set.getString("firstname"), name, set.getString("username"), set.getString("passwd"), set.getBoolean("isActive"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public User updateById(User user) {
		try {
			PreparedStatement statement = connection.prepareStatement("update users set firstname = ?, lastname = ?, username = ?, passwd = ?, isActive = ? where id = ?",
																	  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			statement.setString(1, user.getFirstname());
			statement.setString(2, user.getLastname());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPswd());
			statement.setBoolean(5, user.getActive());
			statement.setInt(6, user.getId());

			statement.executeUpdate();
			statement.close();
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
