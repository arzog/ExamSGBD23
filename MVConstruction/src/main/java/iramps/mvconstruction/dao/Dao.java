package iramps.mvconstruction.dao;

import java.util.List;

/**
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
 * @param <T>
 */
public abstract class Dao<T> {

	/**
	 * method used to insert entries into the database
	 * @param t Generic object changing according to the implementation
	 * @return the created object
	 */
	protected abstract T create(T t);

	/**
	 * Read the entry corresponding to the given id
	 * @param id auto-incremented integer
	 * @return the entry linked to the given id
	 */
	protected abstract T readById(int id);

	/**
	 * Read the entries corresponding to the given name or label
	 * @param name string used to name a person or an article
	 * @return a list of objects matching the name
	 */
	protected abstract List<T> readByName(String name);

	/**
	 * Update the entry corresponding to the id of the given object
	 * @param t object used to update the entry in the database
	 * @return the updated object
	 * @if the entry doesn't exist in the database
	 * @then the process is redirected to "create(T t)"
	 */
	protected abstract T updateById(T t);

}
