/**
 * @file GameRepositoryCtrl.java
 * @author Alex Rodriguez
 * @brief GameRepositoryCtrl class specification.
 */
package repository;

import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONObject;

/**
 * @class GameRepositoryCtrl
 * @brief Implements various CRUD operations to work with the Game repository.
 * By Alex Rodriguez.
 * @see repository.GameRepository
 */
public class GameRepositoryCtrl {
    /* ATTRIBUTES */

    /**
    * @brief GameRepository instance.
    */
    private GameRepository repository;

    /* CONSTRUCTORS */

    /**
     * @brief Create a GameRepositoryCtrl instance.
     * @pre The Game repository JSON files exists.
     * @post A GameRepositoryCtrl instance is created.
     */
    public GameRepositoryCtrl() {
        this.repository = new GameRepository();
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
        this.repository.save(game, board);
    }

    /**
     * @brief Get the Game by name from the game database or null if it does not exist.
     * @pre The Game repository JSON files exists.
     * @post A JSONObject representing the Game by name from the game database is returned or null if it does not exist.
     * @param name Name of the Game to be getted.
     * @return JSONObject that represents the Game by name from the game database or null if it does not exist.
     */
    public JSONObject getGame(String name) {
        return this.repository.getGame(name);
    }

    /**
     * @brief Get the playing Board of a Game by name from the game database or null if it does not exist.
     * @pre The Game repository JSON files exists.
     * @post A JSONObject representing the playing Board of a Game by name from the game database is returned or null if it does not exist.
     * @param name Name of the playing Board's Game to be getted.
     * @return JSONObject that represents the playing Board of a Game by name from the game database or null if it does not exist.
     */
    public JSONObject getBoard(String name) {
        return this.repository.getBoard(name);
    }

    /**
     * @brief Check whether there exists a Game with the given Configuration name in the game database.
     * @pre The Game repository JSON files exists.
     * @post If there exists a Game with the given Configuration name in the game database is returned true otherwise false.
     * @param configurationName Name of the Game's Configuration to be searched.
     * @return Whether there exists a Game with the given Configuration name in the game database.
     */
    public Boolean existsGameByConfigurationName(String configurationName) {
        return this.repository.existsGameByConfigurationName(configurationName);
    }

    /**
     * @brief Check whether there exists a Game with the given Player ID in the game database.
     * @pre The Game repository JSON files exists.
     * @post If there exists a Game with the given Player ID in the game database is returned true otherwise false.
     * @param playerID Name of the Game's Player to be searched.
     * @return Whether there exists a Game with the given Player ID in the game database.
     */
    public Boolean existsGameByPlayerID(UUID playerID) {
        return this.repository.existsGameByPlayerID(playerID.toString());
    }

    /**
     * @brief List all Games by Player ID of the game database.
     * @pre The Game repository JSON files exists.
     * @post An ArrayList containing the Game names by Player ID of the game database is returned.
     * @param playerID Player ID of a player in the Games to be getted.
     * @return ArrayList of the Game names by Player ID of the game database.
     */
    public ArrayList<String> listGames(UUID playerID) {
        return this.repository.listGames(playerID.toString());
    }
}
