/**
 * @file PlayerRepository.java
 * @author Alex Rodriguez
 * @brief PlayerRepository class specification.
 */
package repository;

import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONObject;

import util.Pair;

/**
 * @class PlayerRepository
 * @brief Implements various CRUD operations to work with the Player repository.
 * By Alex Rodriguez.
 * @see repository.Repository
 */
public class PlayerRepository extends Repository {
    /* ATTRIBUTES */

    /* CONSTRUCTORS */

    /**
     * @brief Create a PlayerRepository instance.
     * @pre The Player repository JSON files exists.
     * @post A PlayerRepository instance is created.
     */
    public PlayerRepository() {
        super(RepositoryType.PLAYER);
    }

    /* METHODS */

    /**
     * @brief Save a Player into the player database.
     * @pre The Player repository JSON files exists.
     * @post The Player is saved into the player database.
     * @param player Player to be saved.
     */
    public void save(JSONObject player) {
        String id = player.getString("id");
        this.createOrUpdate(id, player);
    }

    /**
     * @brief Delete a Player by ID from the player database. Soft delete if it is a User and hard delete if it is a Bot.
     * @pre The Player repository JSON files exists.
     * @post The Player is soft or hard deleted from the player database by ID depending whether it is a User or a Bot.
     * @param id ID of the Player to be deleted.
     */
    public void delete(String id) {
        JSONObject player = this.get(id);
        if (player == null)
            return;

        if (player.getString("type").equals("BOT")) {
            this.remove(id);
            return;
        }

        player.put("is_deleted", true);
        this.save(player);
    }

    /**
     * @brief Get the Player by ID from the player database or null if it does not exist.
     * @pre The Player repository JSON files exists.
     * @post A JSONObject representing the Player by ID from the player database is returned or null if it does not exist.
     * @param id ID of the Player to be getted.
     * @return JSONObject that represents the Player by ID from the player database or null if it does not exist.
     */
    public JSONObject get(String id) {
        return super.get(id);
    }

    /**
     * @brief Get the Player by name from the player database or null if it does not exist.
     * @pre The Player repository JSON files exists.
     * @post A JSONObject representing the Player by name from the player database is returned or null if it does not exist.
     * @param name Name of the Player to be getted.
     * @return JSONObject that represents the Player by name from the player database or null if it does not exist.
     */
    public JSONObject getByName(String name) {
        JSONObject all = this.list();

        JSONObject current;
        for (String key : all.keySet()) {
            current = all.getJSONObject(key);
            if (current.getString("name").equals(name))
                return current;
        }

        return null;
    }

    /**
     * @brief List all non-deleted Users of the player database.
     * @pre The Player repository JSON files exists.
     * @post An ArrayList containing the non-deleted User names and IDs of the player database is returned.
     * @return ArrayList of the non-deleted User names and IDs of the player database.
     */
    public ArrayList<Pair<String, UUID>> listUsers() {
        ArrayList<Pair<String, UUID>> list = new ArrayList<Pair<String, UUID>>();
        JSONObject all = this.list();

        JSONObject current;
        for (String key : all.keySet()) {
            current = all.getJSONObject(key);
            if (current.getString("type").equals("USER") && !current.getBoolean("is_deleted"))
                list.add(new Pair<String, UUID>(current.getString("name"), UUID.fromString(key)));
        }

        return list;
    }

    /**
     * @brief List all non-deleted Bots of the player database.
     * @pre The Player repository JSON files exists.
     * @post An ArrayList containing the non-deleted Bots names and IDs of the player database is returned.
     * @return ArrayList of the non-deleted Bots names and IDs of the player database.
     */
    public ArrayList<Pair<String, UUID>> listBots() {
        ArrayList<Pair<String, UUID>> list = new ArrayList<Pair<String, UUID>>();
        JSONObject all = this.list();

        JSONObject current;
        for (String key : all.keySet()) {
            current = all.getJSONObject(key);
            if (current.getString("type").equals("BOT") && !current.getBoolean("is_deleted"))
                list.add(new Pair<String, UUID>(current.getString("name"), UUID.fromString(key)));
        }

        return list;
    }

}
