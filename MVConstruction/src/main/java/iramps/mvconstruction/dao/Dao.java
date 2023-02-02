package iramps.mvconstruction.dao;

import java.sql.Connection;
import java.util.List;

/**
 * @param <T>
 * @author Brice Beumier
 * Parent class gathering the different SQL methods
 * Create Read
 * Update: method used to update data from the database
 * --> UpdateById [mainly used]: update the data thanks to its id
 * --> --> returns the updated object
 * --> UpdateAllByColumn [used by dev for mass update]: udpate all data in the selected column with a new value
 * --> --> returns the number of changed data
 * Delete: method used to disable an entry in the database. For history purpose, the entries won't be deleted but will be set as INACTIVE
 * --> DeleteById: disables the entry corresponding to the id
 */
public abstract class Dao<T> {

	public Connection connection;

	public Dao(Connection connection) {
		this.connection = connection;
	}

	/**
	 * method used to insert entries into the database
	 *
	 * @param t Generic object changing according to the implementation
	 * @return the creation's result
	 */
	public abstract boolean create(T t);
	/**
	 * Read all the entries
	 *
	 * @return the entry linked to the given id
	 */
	public abstract List<T> readAll();

	/**
	 * Read the entry corresponding to the given id
	 *
	 * @param id auto-incremented integer
	 * @return the entry linked to the given id
	 */
	public abstract T readById(int id);

	/**
	 * Read the entries corresponding to the given name or label
	 *
	 * @param name string used to name a person or an article
	 * @return a list of objects matching the name
	 */
	public abstract List<T> readByName(String name);

	/**
	 * Update the entry corresponding to the id of the given object
	 *
	 * @param t object used to update the entry in the database
	 * @return the updated object
	 * @if the entry doesn't exist in the database
	 * @then the process is redirected to "create(T t)"
	 */
	public abstract T updateById(T t);

	/**
	 * disable the entry corresponding to the id given by T t
	 *
	 * @param t object used to get the id to disable
	 * @return the removing's result
	 */
	public abstract boolean deleteByObject(T t);

}
