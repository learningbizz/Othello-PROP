/**
 * @file ConfigurationRepositoryCtrl.java
 * @author Alex Rodriguez
 * @brief ConfigurationRepositoryCtrl class specification.
 */
package repository;

import java.util.ArrayList;

import org.json.JSONObject;

/**
 * @class ConfigurationRepositoryCtrl
 * @brief Implements various CRUD operations to work with the Configuration repository.
 * By Alex Rodriguez.
 * @see repository.ConfigurationRepository
 */
public class ConfigurationRepositoryCtrl {
    /* ATTRIBUTES */

    /**
    * @brief ConfigurationRepository instance.
    */
    private ConfigurationRepository repository;

    /* CONSTRUCTORS */

    /**
     * @brief Create a ConfigurationRepositoryCtrl instance.
     * @pre The Configuration repository JSON files exists.
     * @post A ConfigurationRepositoryCtrl instance is created.
     */
    public ConfigurationRepositoryCtrl() {
        this.repository = new ConfigurationRepository();
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
        this.repository.save(configuration, board);
    }

    /**
     * @brief Delete a Configuration by name from the configuration database.
     * @pre The Configuration repository JSON files exists.
     * @post The Configuration and its initial Board are deleted from the configuration database by name.
     * @param name Name of the Configuration to be deleted.
     */
    public void delete(String name) {
        this.repository.delete(name);
    }

    /**
     * @brief Get the Configuration by name from the configuration database or null if it does not exist.
     * @pre The Configuration repository JSON files exists.
     * @post A JSONObject representing the Configuration by name from the configuration database is returned or null if it does not exist.
     * @param name Name of the Configuration to be getted.
     * @return JSONObject that represents the Configuration by name from the configuration database or null if it does not exist.
     */
    public JSONObject getConfiguration(String name) {
        return this.repository.getConfiguration(name);
    }

    /**
     * @brief Get the initial Board of a Configuration by name from the configuration database or null if it does not exist.
     * @pre The Configuration repository JSON files exists.
     * @post A JSONObject representing the initial Board of a Configuration by name from the configuration database is returned or null if it does not exist.
     * @param name Name of the initial Board's Configuration to be getted.
     * @return JSONObject that represents the initial Board of a Configuration by name from the configuration database or null if it does not exist.
     */
    public JSONObject getBoard(String name) {
        return this.repository.getBoard(name);
    }

    /**
     * @brief List all Configurations of the configuration database.
     * @pre The Configuration repository JSON files exists.
     * @post An ArrayList containing the Configuration names of the configuration database is returned.
     * @return ArrayList of the Configuration names of the configuration database.
     */
    public ArrayList<String> listConfigurations() {
        return this.repository.listConfigurations();
    }
}
