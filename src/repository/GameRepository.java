/**
 * @file GameRepository.java
 * @author Alex Rodriguez
 * @brief GameRepository class specification.
 */
package repository;

import java.util.ArrayList;

import org.json.JSONObject;

/**
 * @class GameRepository
 * @brief Implements various CRUD operations to work with the Game repository.
 * By Alex Rodriguez.
 * @see repository.Repository
 */
public class GameRepository extends Repository {
    /* ATTRIBUTES */

    /* CONSTRUCTORS */

    /**
     * @brief Create a GameRepository instance.
     * @pre The Game repository JSON files exists.
     * @post A GameRepository instance is created.
     */
    public GameRepository() {
        super(RepositoryType.GAME);
    }

    /* METHODS */

    /**
     * @brief Save a Game into the game database.
     * @pre The Game repository JSON files exists.
     * @post The Game and its playing Board are saved into the game database.
     * @param game Game to be saved.
     * @param board Playing Board of the Game to be saved.
     */
    public void save(JSONObject game, JSONObject board) {
        String name = game.getString("name");
        game.put("board", board);
        this.createOrUpdate(name, game);
    }

    /**
     * @brief Get the Game by name from the game database or null if it does not exist.
     * @pre The Game repository JSON files exists.
     * @post A JSONObject representing the Game by name from the game database is returned or null if it does not exist.
     * @param name Name of the Game to be getted.
     * @return JSONObject that represents the Game by name from the game database or null if it does not exist.
     */
    public JSONObject getGame(String name) {
        JSONObject game = this.get(name);
        if (game == null)
            return null;

        game.remove("board");
        return game;
    }

    /**
     * @brief Get the playing Board of a Game by name from the game database or null if it does not exist.
     * @pre The Game repository JSON files exists.
     * @post A JSONObject representing the playing Board of a Game by name from the game database is returned or null if it does not exist.
     * @param name Name of the playing Board's Game to be getted.
     * @return JSONObject that represents the playing Board of a Game by name from the game database or null if it does not exist.
     */
    public JSONObject getBoard(String name) {
        JSONObject game = this.get(name);
        if (game == null)
            return null;

        return game.getJSONObject("board");
    }

    /**
     * @brief Check whether there exists a Game with the given Configuration name in the game database.
     * @pre The Game repository JSON files exists.
     * @post If there exists a Game with the given Configuration name in the game database is returned true otherwise false.
     * @param configurationName Name of the Game's Configuration to be searched.
     * @return Whether there exists a Game with the given Configuration name in the game database.
     */
    public Boolean existsGameByConfigurationName(String configurationName) {
        JSONObject all = this.list();

        JSONObject current;
        for (String key : all.keySet()) {
            current = all.getJSONObject(key);
            if (current.getString("configuration_name").equals(configurationName))
                return true;
        }

        return false;
    }

    /**
     * @brief Check whether there exists a Game with the given Player ID in the game database.
     * @pre The Game repository JSON files exists.
     * @post If there exists a Game with the given Player ID in the game database is returned true otherwise false.
     * @param playerID Name of the Game's Player to be searched.
     * @return Whether there exists a Game with the given Player ID in the game database.
     */
    public Boolean existsGameByPlayerID(String playerID) {
        JSONObject all = this.list();

        JSONObject current;
        for (String key : all.keySet()) {
            current = all.getJSONObject(key);
            if (current.getString("player1_id").equals(playerID) || current.getString("player2_id").equals(playerID)
                    || current.getString("creator_id").equals(playerID))
                return true;
        }

        return false;
    }

    /**
     * @brief List all Games by Player ID of the game database.
     * @pre The Game repository JSON files exists.
     * @post An ArrayList containing the Game names by Player ID of the game database is returned.
     * @param playerID Player ID of a player in the Games to be getted.
     * @return ArrayList of the Game names by Player ID of the game database.
     */
    public ArrayList<String> listGames(String playerID) {
        ArrayList<String> list = new ArrayList<String>();
        JSONObject all = this.list();

        JSONObject current;
        for (String key : all.keySet()) {
            current = all.getJSONObject(key);
            if (current.getString("player1_id").equals(playerID) || current.getString("player2_id").equals(playerID)
                    || current.getString("creator_id").equals(playerID))
                list.add(key);
        }

        return list;
    }
}
