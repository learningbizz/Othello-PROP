/**
* @file ConfigurationCtrl.java
* @author Alex Rodriguez
* @brief ConfigurationCtrl class specification.
*/
package domain;

import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONObject;

import domain.Exceptions.ConfigurationUsedException;
import domain.Exceptions.ExistingConfigurationException;
import domain.Exceptions.InexistingConfigurationException;
import domain.Exceptions.InvalidBoardException;
import domain.Exceptions.InvalidNameException;
import domain.Exceptions.InvalidRulesException;
import domain.Exceptions.NotCreatorException;
import repository.ConfigurationRepositoryCtrl;
import repository.GameRepositoryCtrl;

/**
* @class ConfigurationCtrl
* @brief Configuration domain sub-controller. It communicates with the main domain controller, the configuration repository
* controller and the game repository controller for certain integrity checks. It is also in charge of retrieving the initial boards associated with the configurations.
* 
* By Alex Rodriguez.
* @see domain.Configuration
*/
public class ConfigurationCtrl {
    /* ATTRIBUTES */

    /**
    * @brief Configuration repository controller.
    */
    private ConfigurationRepositoryCtrl repositoryCtrl;
   /**
    * @brief Game repository controller.
    */
    private GameRepositoryCtrl gameRepositoryCtrl;

    /* CONSTRUCTORS */

    /**
    * @brief Creator method that creates an instance of Configuration Controller.
    * @pre <em>True</em>
    * @post An instance of ConfigirationCtrl is created.
    */
    public ConfigurationCtrl() {
        this.repositoryCtrl = new ConfigurationRepositoryCtrl();
        this.gameRepositoryCtrl = new GameRepositoryCtrl();
    }

    /* METHODS */

    /**
    * @brief Lets the current user create a new configuration with a name, rules and the initial board.
    * @pre canEatHorizontally, vertically and diagonally aren't null
    * @post The created Configuration is returned if no exception is thrown. Else, an exception will be thrown
    * @param name name of a Configuration
    * @param canEatHorizontally Boolean that represents if you can capture pieces in a horizontal manner.
    * @param canEatVertically Boolean that represents if you can capture pieces in a vertical manner.
    * @param canEatDiagonally Boolean that represents if you can capture pieces in a diagonal manner.
    * @param initialBoard Instance of a Board
    * @param creatorID UUID of the creator.
    * @return Configuration.
    */
    public Configuration create(String name, Boolean canEatHorizontally, Boolean canEatVertically,
            Boolean canEatDiagonally, Board initialBoard, UUID creatorID)
            throws InvalidNameException, ExistingConfigurationException, InvalidBoardException, InvalidRulesException {
        if (name.isBlank())
            throw new InvalidNameException();

        if (this.repositoryCtrl.getConfiguration(name) != null)
            throw new ExistingConfigurationException();

        return this.save(name, canEatHorizontally, canEatVertically, canEatDiagonally, initialBoard, creatorID);
    }

    /**
    * @brief Lets the current user modify a configuration he/she created. The configuration’s rules and the initial board can be changed.
    * @pre <em>True</em>
    * @post Modified Configuration is returned if no exception is thrown. Else, an exception will be thrown
    * @param name name of a Configuration
    * @param canEatHorizontally Boolean that represents if you can capture pieces in a horizontal manner.
    * @param canEatVertically Boolean that represents if you can capture pieces in a vertical manner.
    * @param canEatDiagonally Boolean that represents if you can capture pieces in a diagonal manner.
    * @param initialBoard Instance of a Board
    * @param modificatorID Modifier Player UUID
    * @return Configuration
    */
    public Configuration modify(String name, Boolean canEatHorizontally, Boolean canEatVertically,
            Boolean canEatDiagonally, Board initialBoard, UUID modificatorID) throws NotCreatorException,
            ConfigurationUsedException, InvalidBoardException, InvalidRulesException, InexistingConfigurationException {
        Configuration original = this.getConfiguration(name);
        Board originalInitialBoard = this.getInitialBoard(name);

        if (!original.getCreatorID().equals(modificatorID))
            throw new NotCreatorException();

        if (this.gameRepositoryCtrl.existsGameByConfigurationName(name))
            throw new ConfigurationUsedException();

        if (canEatHorizontally != null)
            original.setCanEatHorizontally(canEatHorizontally);

        if (canEatVertically != null)
            original.setCanEatVertically(canEatVertically);

        if (canEatDiagonally != null)
            original.setCanEatDiagonally(canEatDiagonally);

        if (initialBoard != null)
            originalInitialBoard = initialBoard;

        return this.save(original.getName(), original.getCanEatHorizontally(), original.getCanEatVertically(),
                original.getCanEatDiagonally(), originalInitialBoard, original.getCreatorID());
    }

    /**
    * @brief Method that, given a name, a set of rules and an initial board, allows us to save a configuration in the repository.
    * @pre name and creatorID aren't null.
    * @post Saved Configuration is returned if no exception is thrown. Else, an exception will be thrown.
    * @param name name of a Configuration
    * @param canEatHorizontally Boolean that represents if you can capture pieces in a horizontal manner.
    * @param canEatVertically Boolean that represents if you can capture pieces in a vertical manner.
    * @param canEatDiagonally Boolean that represents if you can capture pieces in a diagonal manner.
    * @param initialBoard Instance of a Board
    * @param creatorID UUID of the creator.
    * @return Configuration
    */
    private Configuration save(String name, Boolean canEatHorizontally, Boolean canEatVertically,
            Boolean canEatDiagonally, Board initialBoard, UUID creatorID)
            throws InvalidBoardException, InvalidRulesException {
        if (initialBoard == null)
            throw new InvalidBoardException();

        if (canEatHorizontally == false && canEatVertically == false && canEatDiagonally == false)
            throw new InvalidRulesException();

        Configuration configuration = new Configuration(name, creatorID, canEatHorizontally, canEatVertically,
                canEatDiagonally);

        this.repositoryCtrl.save(configuration.serialize(), initialBoard.serialize());
        return configuration;
    }

    /**
    * @brief Lets the current user delete a configuration he/she created.
    * @pre <em>True</em>
    * @post The Configuration is deleted if no exception is thrown. Else, an exception will be thrown.
    * @param name Name of a Configuration
    * @param deleterID UUID of a Player.
    */
    public void delete(String name, UUID deleterID)
            throws NotCreatorException, ConfigurationUsedException, InexistingConfigurationException {
        Configuration original = this.getConfiguration(name);

        if (!original.getCreatorID().equals(deleterID))
            throw new NotCreatorException();

        if (this.gameRepositoryCtrl.existsGameByConfigurationName(name))
            throw new ConfigurationUsedException();

        this.repositoryCtrl.delete(name);
    }

    /**
    * @brief Returns the configuration identified by the name.
    * @pre <em>True</em>
    * @post The Configuration identified by name is returned if no exception is thrown. Else, an exception will be thrown.
    * @param name Name of a Configuration
    * @return Configuration
    */
    public Configuration getConfiguration(String name) throws InexistingConfigurationException {
        JSONObject rawConfiguration = this.repositoryCtrl.getConfiguration(name);
        if (rawConfiguration == null)
            throw new InexistingConfigurationException();

        return new Configuration(rawConfiguration);
    }

    /**
    * @brief Returns the initial board associated with the given configuration name.
    * @pre <em>True</em>
    * @post The initial board associated with the given configuration name is returned if no exception is returned. Else, an exception will be returned.
    * @param name Name of a Configuration.
    * @return Board
    */
    public Board getInitialBoard(String name) throws InexistingConfigurationException {
        JSONObject rawInitialBoard = this.repositoryCtrl.getBoard(name);
        if (rawInitialBoard == null)
            throw new InexistingConfigurationException();

        return new Board(rawInitialBoard);
    }

    /**
    * @brief Returns a list of all configurations names in the system.
    * @pre <em>True/em>
    * @post ArrayList of Strings with the names of all the Configurations in the system
    * @return ArrayList<String>
    */
    public ArrayList<String> list() {
        return this.repositoryCtrl.listConfigurations();
    }
}
