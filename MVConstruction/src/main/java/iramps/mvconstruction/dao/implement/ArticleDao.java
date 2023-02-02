package iramps.mvconstruction.dao.implement;

import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.exception.ArticleNotFoundException;
import iramps.mvconstruction.model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ArticleDao extends Dao<Article> {

	public ArticleDao(Connection connection) {
		super(connection);
	}
	@Override
	public boolean create(Article article) {
		try {
			PreparedStatement statement = connection.prepareStatement("insert into articles(label, price, current_stock, isActive, min_stock) " + "values (?,?,?,?,?)");
			statement.setString(1, article.getLabel());
			statement.setDouble(2, article.getPrice());
			statement.setInt(3, article.getCurrentStock());
			statement.setBoolean(4, article.isActive());
			statement.setInt(5, article.getMinStock());

			statement.executeUpdate();
			statement.close();
			System.out.println("Article successfully created");
			return true;
		} catch (SQLException e) {
			System.out.println("An issue occured while creating the article");
			System.out.println(e.getMessage());
			return false;
		}
	}
	@Override
	public Article readById(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from articles where id=?");
			statement.setInt(1, id);

			ResultSet set = statement.executeQuery();

			if (set.first()) {
				return new Article(id, set.getString("label"), set.getDouble("price"), set.getInt("current_stock"), set.getInt("min_stock"), set.getBoolean("isActive"));
			}
		} catch (SQLException e) {
			System.out.println("An issue occured while creating the article");
			throw new ArticleNotFoundException(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Article> readAll() {
		List<Article> articles = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("select * from articles");

			ResultSet set = statement.executeQuery();
			while (set.next()) {
				articles.add(new Article(set.getInt("id"), set.getString("label"), set.getDouble("price"), set.getInt("current_stock"), set.getInt("min_stock"), set.getBoolean("isActive")));
			}
			return articles;
		} catch (SQLException e) {
			System.out.println("An issue occured while creating the article");
			throw new ArticleNotFoundException(e.getMessage());
		}
	}
	@Override
	public Article readByName(String name) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from articles where label like ?");
			statement.setString(1, name);

			ResultSet set = statement.executeQuery();
			if (set.first()) {
				return new Article(set.getInt("id"), name, set.getDouble("price"), set.getInt("current_stock"), set.getInt("min_stock"), set.getBoolean("isActive"));
			}
		} catch (SQLException e) {
			System.out.println("An issue occured while creating the article");
			throw new ArticleNotFoundException(e.getMessage());
		}
		return null;
	}

	@Override
	public Article updateById(Article article) {
		try {
			PreparedStatement statement = connection.prepareStatement("update articles set label = ?, price = ?, current_stock = ?, isActive = ?, min_stock = ? where id = ?");
			statement.setString(1, article.getLabel());
			statement.setDouble(2, article.getPrice());
			statement.setInt(3, article.getCurrentStock());
			statement.setBoolean(4, article.isActive());
			statement.setInt(5, article.getMinStock());
			statement.setInt(6, article.getId());

			statement.executeUpdate();

			statement.close();
			System.out.println("Article successfully updated");

			return article;
		} catch (SQLException e) {
			System.out.println("An issue occured while creating the article");
			throw new ArticleNotFoundException(e.getMessage());
		}
	}
	@Override
	public boolean deleteByObject(Article article) {
		try {
			PreparedStatement statement = connection.prepareStatement("delete from articles where id = ?");
			statement.setInt(1, article.getId());

			statement.executeUpdate();
			statement.close();
			System.out.println("Article successfully deleted");
			return true;
		} catch (SQLException e) {
			System.out.println("An issue occured while creating the article");
			throw new ArticleNotFoundException(e.getMessage());
		}
	}
}
