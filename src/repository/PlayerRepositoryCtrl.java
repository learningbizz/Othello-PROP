/**
 * @file PlayerRepositoryCtrl.java
 * @author Alex Rodriguez
 * @brief PlayerRepositoryCtrl class specification.
 */
package repository;

import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONObject;

import util.Pair;

/**
 * @class PlayerRepositoryCtrl
 * @brief Implements various CRUD operations to work with the Player repository.
 * By Alex Rodriguez.
 * @see repository.PlayerRepository
 */
public class PlayerRepositoryCtrl {
    /* ATTRIBUTES */

    /**
    * @brief PlayerRepository instance.
    */
    private PlayerRepository repository;

    /* CONSTRUCTORS */

    /**
     * @brief Create a PlayerRepositoryCtrl instance.
     * @pre The Player repository JSON files exists.
     * @post A PlayerRepositoryCtrl instance is created.
     */
    public PlayerRepositoryCtrl() {
        this.repository = new PlayerRepository();
    }

    /* METHODS */

    /**
     * @brief Save a Player into the player database.
     * @pre The Player repository JSON files exists.
     * @post The Player is saved into the player database.
     * @param player Player to be saved.
     */
    public void save(JSONObject player) {
        this.repository.save(player);
    }

    /**
     * @brief Delete a Player by ID from the player database. Soft delete if it is a User and hard delete if it is a Bot.
     * @pre The Player repository JSON files exists.
     * @post The Player is soft or hard deleted from the player database by ID depending whether it is a User or a Bot.
     * @param id ID of the Player to be deleted.
     */
    public void delete(UUID id) {
        this.repository.delete(id.toString());
    }

    /**
     * @brief Get the Player by ID from the player database or null if it does not exist.
     * @pre The Player repository JSON files exists.
     * @post A JSONObject representing the Player by ID from the player database is returned or null if it does not exist.
     * @param id ID of the Player to be getted.
     * @return JSONObject that represents the Player by ID from the player database or null if it does not exist.
     */
    public JSONObject get(UUID id) {
        return this.repository.get(id.toString());
    }

    /**
     * @brief Get the Player by name from the player database or null if it does not exist.
     * @pre The Player repository JSON files exists.
     * @post A JSONObject representing the Player by name from the player database is returned or null if it does not exist.
     * @param name Name of the Player to be getted.
     * @return JSONObject that represents the Player by name from the player database or null if it does not exist.
     */
    public JSONObject getByName(String name) {
        return this.repository.getByName(name);
    }

    /**
     * @brief List all non-deleted Users of the player database.
     * @pre The Player repository JSON files exists.
     * @post An ArrayList containing the non-deleted User names and IDs of the player database is returned.
     * @return ArrayList of the non-deleted User names and IDs of the player database.
     */
    public ArrayList<Pair<String, UUID>> listUsers() {
        return this.repository.listUsers();
    }

    /**
     * @brief List all non-deleted Bots of the player database.
     * @pre The Player repository JSON files exists.
     * @post An ArrayList containing the non-deleted Bots names and IDs of the player database is returned.
     * @return ArrayList of the non-deleted Bots names and IDs of the player database.
     */
    public ArrayList<Pair<String, UUID>> listBots() {
        return this.repository.listBots();
    }
}
