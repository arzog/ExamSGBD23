package iramps.mvconstruction.dao.implement;

import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao<User> {

	public UserDao(Connection connection) {
		super(connection);
	}

	@Override
	public boolean create(User user) {
		try {
			PreparedStatement statement = connection.prepareStatement("insert into users(firstname,lastname,username,passwd,isActive) values (?,?,?,?,?)");
			statement.setString(1, user.getFirstname());
			statement.setString(2, user.getLastname());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPswd());
			statement.setBoolean(5, user.getActive());

			statement.executeUpdate();
			statement.close();
			System.out.println("success");
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean deleteByObject(User user) {
		try {
			PreparedStatement statement = connection.prepareStatement("delete from users where id = ?");
			statement.setInt(1, user.getId());

			statement.executeUpdate();
			statement.close();
			System.out.println("success");
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<User> readAll() {
		List<User> users = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("select * from users");

			ResultSet set = statement.executeQuery();
			statement.close();

			while (set.next()) {
				users.add(new User(set.getInt("id"), set.getString("firstname"), set.getString("lastname"), set.getString("username"), set.getString("passwd"), set.getBoolean("isActive")));
			}
			System.out.println("success");
			return users;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User readById(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from users where id = ?");
			statement.setInt(1, id);

			ResultSet set = statement.executeQuery();
			statement.close();

			if (set.first()) {
				return new User(id, set.getString("firstname"), set.getString("lastname"), set.getString("username"), set.getString("passwd"), set.getBoolean("isActive"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public User readByName(String name) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from users where username like ?");
			statement.setString(1, name);

			ResultSet set = statement.executeQuery();

			if (set.next()) {
				return new User(set.getInt("id"), set.getString("firstname"), name, set.getString("username"), set.getString("passwd"), set.getBoolean("isActive"));
			}
			System.out.println("success");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public User updateById(User user) {
		try {
			PreparedStatement statement = connection.prepareStatement("update users set firstname = ?, lastname = ?, username = ?, passwd = ?, isActive = ? where id = ?");
			statement.setString(1, user.getFirstname());
			statement.setString(2, user.getLastname());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPswd());
			statement.setBoolean(5, user.getActive());
			statement.setInt(6, user.getId());

			statement.executeUpdate();
			statement.close();
			System.out.println("success");
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
