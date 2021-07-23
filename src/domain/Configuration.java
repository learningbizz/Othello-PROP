/**
 * @file Configuration.java
 * @author Alex Rodriguez
 * @brief Configuration class specification.
 */
package domain;

import java.util.UUID;

import org.json.JSONObject;

import domain.Exceptions.InvalidNameException;
import domain.Exceptions.InvalidRulesException;

/**
 * @class Configuration
 * @brief Represents the rules of an Othello game including its name, whether the pieces can be eaten horizontally,
 * vertically or diagonally, and its creator.
 * By Alex Rodriguez.
 */
public class Configuration {
    /* ATTRIBUTES */

    /**
    * @brief Name of the Configuration.
    */
    private String name;
    /**
    * @brief Player ID of the Configuration's creator.
    */
    private UUID creatorID;
    /**
    * @brief Whether the pieces of a Game can be eaten horizontally.
    */
    private boolean canEatHorizontally;
    /**
    * @brief Whether the pieces of a Game can be eaten vertically.
    */
    private boolean canEatVertically;
    /**
    * @brief Whether the pieces of a Game can be eaten diagonally.
    */
    private boolean canEatDiagonally;

    /* CONSTRUCTORS */

    /**
     * @brief Create a Configuration instance.
     * @pre <em>True</em>
     * @post A Configuration instance is created and its implicits name, creatorID, canEatHorizontally,
     * canEatVertically and canEatDiagonally attributes are setted.
     * @param name Name of the Configuration.
     * @param creatorID Player ID of the Configuration's creator.
     * @param canEatHorizontally Whether the pieces of a Game can be eaten horizontally.
     * @param canEatVertically Whether the pieces of a Game can be eaten vertically.
     * @param canEatDiagonally Whether the pieces of a Game can be eaten diagonally.
     */
    public Configuration(String name, UUID creatorID, boolean canEatHorizontally, boolean canEatVertically,
            boolean canEatDiagonally) {
        this.name = name;
        this.creatorID = creatorID;
        this.canEatHorizontally = canEatHorizontally;
        this.canEatVertically = canEatVertically;
        this.canEatDiagonally = canEatDiagonally;
    }

    /**
     * @brief Create a Configuration instance from a JSONObject representation of a Configuration.
     * @pre <em>True</em>
     * @post A Configuration instance is created and its implicits name, creatorID, canEatHorizontally,
     * canEatVertically and canEatDiagonally are setted.
     * @param configuration JSONObject representation of a Configuration.
     */
    public Configuration(JSONObject configuration) {
        this.name = configuration.getString("name");
        this.creatorID = UUID.fromString(configuration.getString("creator_id"));
        this.canEatHorizontally = configuration.getBoolean("can_eat_horizontally");
        this.canEatVertically = configuration.getBoolean("can_eat_vertically");
        this.canEatDiagonally = configuration.getBoolean("can_eat_diagonally");
    }

    /* METHODS */

    /**
     * @brief Create a JSONObject representation of a Configuration from the implicit Configuration.
     * @pre <em>True</em>
     * @post A JSONObject representing the implicit Configuration is returned.
     * @return JSONObject representation of a Configuration.
     */
    public JSONObject serialize() {
        JSONObject configuration = new JSONObject();

        configuration.put("name", this.name);
        configuration.put("creator_id", this.creatorID.toString());
        configuration.put("can_eat_horizontally", this.canEatHorizontally);
        configuration.put("can_eat_vertically", this.canEatVertically);
        configuration.put("can_eat_diagonally", this.canEatDiagonally);

        return configuration;
    }

    /**
     * @brief Get the name of the implicit Configuration.
     * @pre <em>True</em>
     * @post The name attribute of the implicit Configuration is returned.
     * @return Name of the implicit Configuration.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @brief Set the name of the implicit Configuration.
     * @pre <em>True</em>
     * @post The name attribute of the implicit Configuration is setted if it is not blank,
     * otherwise an InvalidNameException is thrown.
     * @param name Name of the Configuration.
     */
    public void setName(String name) throws InvalidNameException {
        if (name.isBlank())
            throw new InvalidNameException();

        this.name = name;
    }

    /**
     * @brief Get the creatorID of the implicit Configuration.
     * @pre <em>True</em>
     * @post The creatorID attribute of the implicit Configuration is returned.
     * @return CreatorID of the implicit Configuration.
     */
    public UUID getCreatorID() {
        return this.creatorID;
    }

    /**
     * @brief Get the canEatHorizontally of the implicit Configuration.
     * @pre <em>True</em>
     * @post The canEatHorizontally attribute of the implicit Configuration is returned.
     * @return CanEatHorizontally of the implicit Configuration.
     */
    public boolean getCanEatHorizontally() {
        return this.canEatHorizontally;
    }

    /**
     * @brief Set the canEatHorizontally of the implicit Configuration.
     * @pre <em>True</em>
     * @post The canEatHorizontally attribute of the implicit Configuration is setted if
     * all the rules aren't false, otherwise an InvalidRulesException is thrown.
     * @param canEatHorizontally Whether the pieces of a Game can be eaten horizontally.
     */
    public void setCanEatHorizontally(boolean canEatHorizontally) throws InvalidRulesException {
        if (canEatHorizontally == false && this.canEatVertically == false && this.canEatDiagonally == false)
            throw new InvalidRulesException();

        this.canEatHorizontally = canEatHorizontally;
    }

    /**
     * @brief Get the canEatVertically of the implicit Configuration.
     * @pre <em>True</em>
     * @post The canEatVertically attribute of the implicit Configuration is returned.
     * @return CanEatVertically of the implicit Configuration.
     */
    public boolean getCanEatVertically() {
        return this.canEatVertically;
    }

    /**
     * @brief Set the canEatVertically of the implicit Configuration.
     * @pre <em>True</em>
     * @post The canEatVertically attribute of the implicit Configuration is setted if
     * all the rules aren't false, otherwise an InvalidRulesException is thrown.
     * @param canEatVertically Whether the pieces of a Game can be eaten vertically.
     */
    public void setCanEatVertically(boolean canEatVertically) throws InvalidRulesException {
        if (this.canEatHorizontally == false && canEatVertically == false && this.canEatDiagonally == false)
            throw new InvalidRulesException();

        this.canEatVertically = canEatVertically;
    }

    /**
     * @brief Get the canEatDiagonally of the implicit Configuration.
     * @pre <em>True</em>
     * @post The canEatDiagonally attribute of the implicit Configuration is returned.
     * @return CanEatDiagonally of the implicit Configuration.
     */
    public boolean getCanEatDiagonally() {
        return this.canEatDiagonally;
    }

    /**
     * @brief Set the canEatDiagonally of the implicit Configuration.
     * @pre <em>True</em>
     * @post The canEatDiagonally attribute of the implicit Configuration is setted if
     * all the rules aren't false, otherwise an InvalidRulesException is thrown.
     * @param canEatDiagonally Whether the pieces of a Game can be eaten diagonally.
     */
    public void setCanEatDiagonally(boolean canEatDiagonally) throws InvalidRulesException {
        if (this.canEatHorizontally == false && this.canEatVertically == false && canEatDiagonally == false)
            throw new InvalidRulesException();

        this.canEatDiagonally = canEatDiagonally;
    }
}
