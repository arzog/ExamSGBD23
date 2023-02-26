package iramps.mvconstruction.dao.implement;

import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.model.Article;
import iramps.mvconstruction.model.SoldItems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SoldItemsDao extends Dao<SoldItems> {

	private final ArticleDao dao;

	public SoldItemsDao(Connection connection) {
		super(connection);
		dao = new ArticleDao(connection);
	}

	@Override
	public boolean create(SoldItems soldItems) {
		try {
			PreparedStatement statement = connection.prepareStatement("insert into sold_items(id_article,qtt,bill_num) values (?,?,?)");
			soldItems.getItemCart().forEach((item, quantity) -> {
				try {
					statement.setInt(1, item.getId());
					statement.setInt(2, quantity);
					statement.setString(3, soldItems.getBillNumber());

					statement.addBatch();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			});
			statement.executeBatch();
			statement.close();
			System.out.println("success");
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean deleteByObject(SoldItems soldItems) {
		try {
			PreparedStatement statement = connection.prepareStatement("delete from sold_items where id = ?");
			statement.setInt(1, soldItems.getId());

			statement.executeUpdate();
			statement.close();
			System.out.println("success");
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<SoldItems> readAll() {
		try {
			List<SoldItems> items = new ArrayList<>();
			PreparedStatement statement = connection.prepareStatement("select * from sold_items");

			ResultSet set = statement.executeQuery();
			statement.close();

			while (set.next()) {
				Article article = dao.readById(set.getInt("id_article"));
				items.add(new SoldItems(set.getString("bill_num"), Map.of(article, set.getInt("qtt"))));
			}
			System.out.println("success");
			return items;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public SoldItems readById(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from sold_items where id = ?");
			statement.setInt(1, id);

			ResultSet set = statement.executeQuery();
			statement.close();

			if (set.first()) {
				Article article = dao.readById(set.getInt("id_article"));
				return new SoldItems(id, set.getString("bill_num"), Map.of(article, set.getInt("qtt")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public SoldItems readByName(String name) {
		try {
			List<SoldItems> items = new ArrayList<>();
			PreparedStatement statement = connection.prepareStatement("select * from sold_items where bill_num like ?",
																	  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			statement.setString(1, name);

			ResultSet set = statement.executeQuery();
			statement.close();

			if (set.first()) {
				Article article = dao.readById(set.getInt("id_article"));
				return new SoldItems(set.getString("bill_num"), Map.of(article, set.getInt("qtt")));
			}
			System.out.println("success");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public SoldItems updateById(SoldItems soldItems) {
		try {
			PreparedStatement statement = connection.prepareStatement("update sold_items set id_article = ?, qtt = ? where id = ?",
																	  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			soldItems.getItemCart().forEach((item, qtt) -> {
				try {
					statement.setInt(1, item.getId());
					statement.setInt(2, qtt);
					statement.setInt(3, soldItems.getId());

					statement.addBatch();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			});
			statement.executeBatch();
			statement.close();
			System.out.println("success");
			return soldItems;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
