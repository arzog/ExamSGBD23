package iramps.mvconstruction.dao.implement;

import iramps.mvconstruction.dao.Dao;
import iramps.mvconstruction.exception.ArticleNotFoundException;
import iramps.mvconstruction.model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public class ArticleDao extends Dao<Article> {

	public ArticleDao(Connection connection) {
		super(connection);
	}
	@Override
	protected boolean create(Article article) {
		try {
			PreparedStatement statement = connection.prepareStatement("insert into articles(label, price, current_stock, isActive, min_stock) " + "values (?,?,?,?,?)");
			statement.setString(1, article.getLabel());
			statement.setDouble(2, article.getPrice());
			statement.setInt(3, article.getCurrentStock());
			statement.setBoolean(5, article.isActive());
			statement.setInt(4, article.getMinStock());

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
	protected Article readById(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from articles where id=?");
			statement.setInt(1, id);

			ResultSet set = statement.executeQuery();

			if (set.first()) {
				return new Article(id, set.getString("label"), set.getDouble("price"), set.getInt("current_stock"), set.getInt("min_stock"), set.getBoolean("isActive"));
			}
		} catch (SQLException e) {
			throw new ArticleNotFoundException(e.getMessage());
		}
		return null;
	}
	@Override
	protected List<Article> readByName(String name) {
		return null;
	}
	@Override
	protected Article updateById(Article article) {
		return null;
	}
	@Override
	protected boolean deleteByObject(Article article) {
		return false;
	}
}
