/**
 * @file ConfigurationRepository.java
 * @author Alex Rodriguez
 * @brief ConfigurationRepository class specification.
 */
package repository;

import java.util.ArrayList;

import org.json.JSONObject;

/**
 * @class ConfigurationRepository
 * @brief Implements various CRUD operations to work with the Configuration repository.
 * By Alex Rodriguez.
 * @see repository.Repository
 */
public class ConfigurationRepository extends Repository {
    /* ATTRIBUTES */

    /* CONSTRUCTORS */

    /**
     * @brief Create a ConfigurationRepository instance.
     * @pre The Configuration repository JSON files exists.
     * @post A ConfigurationRepository instance is created.
     */
    public ConfigurationRepository() {
        super(RepositoryType.CONFIGURATION);
    }

    /* METHODS */

    /**
     * @brief Save a Configuration into the configuration database.
     * @pre The Configuration repository JSON files exists.
     * @post The Configuration and its initial Board are saved into the configuration database.
     * @param configuration Configuration to be saved.
     * @param board Initial Board of the Configuration to be saved.
     */
    public void save(JSONObject configuration, JSONObject board) {
        String name = configuration.getString("name");
        configuration.put("board", board);
        this.createOrUpdate(name, configuration);
    }

    /**
     * @brief Delete a Configuration by name from the configuration database.
     * @pre The Configuration repository JSON files exists.
     * @post The Configuration and its initial Board are deleted from the configuration database by name.
     * @param name Name of the Configuration to be deleted.
     */
    public void delete(String name) {
        this.remove(name);
    }

    /**
     * @brief Get the Configuration by name from the configuration database or null if it does not exist.
     * @pre The Configuration repository JSON files exists.
     * @post A JSONObject representing the Configuration by name from the configuration database is returned or null if it does not exist.
     * @param name Name of the Configuration to be getted.
     * @return JSONObject that represents the Configuration by name from the configuration database or null if it does not exist.
     */
    public JSONObject getConfiguration(String name) {
        JSONObject configuration = this.get(name);
        if (configuration == null)
            return null;

        configuration.remove("board");
        return configuration;
    }

    /**
     * @brief Get the initial Board of a Configuration by name from the configuration database or null if it does not exist.
     * @pre The Configuration repository JSON files exists.
     * @post A JSONObject representing the initial Board of a Configuration by name from the configuration database is returned or null if it does not exist.
     * @param name Name of the initial Board's Configuration to be getted.
     * @return JSONObject that represents the initial Board of a Configuration by name from the configuration database or null if it does not exist.
     */
    public JSONObject getBoard(String name) {
        JSONObject configuration = this.get(name);
        if (configuration == null)
            return null;

        return configuration.getJSONObject("board");
    }

    /**
     * @brief List all Configurations of the configuration database.
     * @pre The Configuration repository JSON files exists.
     * @post An ArrayList containing the Configuration names of the configuration database is returned.
     * @return ArrayList of the Configuration names of the configuration database.
     */
    public ArrayList<String> listConfigurations() {
        return new ArrayList<String>(this.list().keySet());
    }
}
