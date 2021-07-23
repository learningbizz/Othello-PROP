/**
 * @file DomainCtrl.java
 * @author Manuel Navid
 * @brief DomainCtrl class specification.
 */
package domain;

import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONObject;

import domain.Board.PieceType;
import domain.Exceptions.FinishedGameException;
import domain.Exceptions.InvalidPositionException;
import domain.Ranking.RankingType;
import util.Pair;

/**
 * @class DomainCtrl
 * @brief Is the main domain controller. It keeps the current state of all the game and application.
 *        It serves as a forwarder for the specific domain class controllers, that's why most of the sub-controller methods
 *        receive as a parameter the current instance of the associated class. Moreover, it implements a few methods that do
*         not have a specific domain class binded. It also gathers all the thrown exceptions in the sub-controllers and transforms
 *        them into string messages in order to pass them to the view layer without coupling any domain specific logic.
 * By Manuel Navid
 */

public class DomainCtrl {
    /* ATTRIBUTES */

    /**
     * @brief Player Controller.
     */
    private PlayerCtrl playerCtrl;
    /**
     * @brief Configuration Controller.
     */
    private ConfigurationCtrl configurationCtrl;
    /**
     * @brief Board Controller.
     */
    private BoardCtrl boardCtrl;
    /**
     * @brief Game Controller.
     */
    private GameCtrl gameCtrl;
    /**
     * @brief Ranking Controller.
     */
    private RankingCtrl rankingCtrl;
    /**
     * @brief Difficulty Controller.
     */
    private DifficultyCtrl difficultyCtrl;
    /**
     * @brief Current logged User.
     */
    private User currentUser;
    /**
     * @brief Player 1 of the current game. Can be either a User or a Bot.
     */
    private Player currentPlayer1;
    /**
     * @brief Player 2 of the current game. Can be either a User or a Bot.
     */
    private Player currentPlayer2;
    /**
     * @brief Current loaded board from the current configuration or game.
     */
    private Board currentBoard;
    /**
     * @brief Current loaded configuration.
     */
    private Configuration currentConfiguration;
    /**
     * @brief Current loaded game.
     */
    private Game currentGame;

    /* CONSTRUCTORS */

    /**
     * @brief Default creator method
       @pre <em>True</em>
       @post An instance of domainCtrl is created.
     */
    public DomainCtrl() {
        this.playerCtrl = new PlayerCtrl();
        this.configurationCtrl = new ConfigurationCtrl();
        this.boardCtrl = new BoardCtrl();
        this.gameCtrl = new GameCtrl();
        this.rankingCtrl = new RankingCtrl();
        this.difficultyCtrl = new DifficultyCtrl();
        this.currentUser = null;
        this.currentPlayer1 = null;
        this.currentPlayer2 = null;
        this.currentBoard = null;
        this.currentConfiguration = null;
        this.currentGame = null;
    }

    /* METHODS */

    /**
     * @brief Method to exit the application
       @pre <em>True</em>
       @post We exited the application, therefore it closes.
     */
    public void exitApplication() {
        this.logout();
        System.exit(0);
    }

    /**
     * @brief Method to logout from the current User
       @pre <em>True</em>
       @post The current User is null, meaning there is no user logged in.
     */
    public void logout() {
        this.exitGame();
        this.currentUser = null;
    }

    /**
     * @brief Method to exit a Game
       @pre <em>True</em>
       @post All the current attributes of DomainCtrl used to play a Game are changed to null.
     */
    public void exitGame() {
        this.currentPlayer1 = null;
        this.currentPlayer2 = null;
        this.currentBoard = null;
        this.currentConfiguration = null;
        this.currentGame = null;
    }

    /* PLAYER */

    /**
     * @brief Creator that, given a name and a password, creates a new user in the
     *        repository.
     * @pre <em>True</em>
     * @param name Name of a User
     * @param password Password of a User
     * @param confirmation Confirmation of the entered password
     * @post The user is saved and  is returned in JSON format if no exceptions where triggered, else the excepction will be returned in a string.
     */
    public Pair<JSONObject, String> createUser(String name, String password, String confirmation) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            User user = this.playerCtrl.createUser(name, password, confirmation);
            result.first = user.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
     * @brief Method that, given a name, a difficulty and an ID, creates a new bot
     *        in the repository.
     * @pre currentUser is not null.
     * @param name Name of a Bot
     * @param difficulty Difficulty of a Bot
     * @post The created bot is saved and is returned in JSON format if no exceptions were triggered, else the excepction will be returned in a string.
     */
    public Pair<JSONObject, String> createBot(String name, Integer difficulty) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            Bot bot = this.playerCtrl.createBot(name, difficulty, this.currentUser.getID());
            result.first = bot.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
     * @brief Method that, given a name and a password, allows us to log in the
     *        Othello game.
     * @pre <em>True</em>
     * @param name Name of a User
     * @param password Password of a User
     * @post The user found in the repository is returned in JSON format if there is no exception triggered, else the exception will be returned in a string.
     */
    public Pair<JSONObject, String> login(String name, String password) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            User user = this.playerCtrl.login(name, password);
            this.currentUser = user;
            result.first = user.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
     * @brief Method that, given an ID, returns a user.
     * @pre <em>userID is not null</em>
     * @param userID UUID of a User
     * @post User is found in repository and returned in JSON format if there was no exceptions triggered, else the exception will be returned in a string.
     */
    public Pair<JSONObject, String> getUser(UUID userID) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            User user = this.playerCtrl.getUser(userID);
            result.first = user.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
     * @brief Method that, given an ID, returns a bot.
     * @pre botID is not null
     * @param botID UUID of a Bot
     * @post Bot is found in repository and returned in JSON format if there was no exceptions triggered, else the exception will be returned in a string.
     */
    public Pair<JSONObject, String> getBot(UUID botID) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            Bot bot = this.playerCtrl.getBot(botID);
            result.first = bot.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
     * @brief Method that, given an ID, returns a player.
     * @pre playerID is not null
     * @param playerID UUID of a Bot
     * @post Player is found in repository and returned in JSON format if there was no exceptions triggered, else the exception will be returned in a string.
     */
    public Pair<JSONObject, String> getPlayer(UUID playerID) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            try {
                User user = this.playerCtrl.getUser(playerID);
                result.first = user.serialize();
            } catch (Exception e) {
                Bot bot = this.playerCtrl.getBot(playerID);
                result.first = bot.serialize();
            }
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
     * @brief Method that lists all the users from the repository.
     * @pre <em>True</em>
     * @post All bots are returned in an ArrayList with their names and IDs.
     */
    public ArrayList<Pair<String, UUID>> listUsers() {
        return this.playerCtrl.listUsers();
    }

    /**
     * @brief Method that lists all the bots from the repository.
     * @pre <em>True</em>
     * @post All bots are returned in an ArrayList with their names and IDs.
     */
    public ArrayList<Pair<String, UUID>> listBots() {
        return this.playerCtrl.listBots();
    }

    /**
     * @brief Modifier that, given an ID, a name and a password, allows us to modify
     *        the user's credentials changing the name, the password or both.
     * @pre currentUser is not null.
     * @param name Name of a User
     * @param password Password of a User
     * @param confirmation Confirmation of the entered password
     * @post Name, password or both are changed, saved in currentUser and it's returned in JSON format if no exceptions were triggered, else the exception will be returned in a string.
     */
    public Pair<JSONObject, String> modifyUser(String name, String password, String confirmation) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            User user = this.playerCtrl.modifyUser(this.currentUser.getID(), name, password, confirmation);
            this.currentUser = user;
            result.first = user.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
    * @brief Method that, given an ID, a name, a difficulty and an ID, allows us to
    *        modify the bot's credentials changing the name, the difficulty or
    *        both.
    * @pre currentUser is not null.
    * @param botID ID of a Bot
    * @param name Name 
    * @param difficulty The difficulty of a Bot
    * @post Bot's name, difficulty or both are modified and the modified bot is
    *       returned in JSON format if no exception was triggered. Else, it will return the exception in a string.
    */
    public Pair<JSONObject, String> modifyBot(UUID botID, String name, Integer difficulty) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            Bot bot = this.playerCtrl.modifyBot(botID, name, difficulty, this.currentUser.getID());
            result.first = bot.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
    * @brief Method that, given an ID, a name and a password, allows us to delete a
    *        user.
    * @pre currentUser is not null.
    * @param password Password of the user    
    * @post The user is deleted from the repository and we logout from the User.
    */
    public String deleteUser(String password) {
        String result = null;

        try {
            this.playerCtrl.deleteUser(this.currentUser.getID(), password);
            this.logout();
        } catch (Exception e) {
            return e.getMessage();
        }

        return result;
    }

    /**
     * @brief Method that, given a name, a botID and a deleterID, allows us to
     *        delete a bot.
     * @pre currentUser is not null.
     * @post The bot is deleted from the repository.
     * @param botID ID of a bot
     */
    public String deleteBot(UUID botID) {
        String result = null;

        try {
            this.playerCtrl.deleteBot(botID, this.currentUser.getID());
        } catch (Exception e) {
            return e.getMessage();
        }

        return result;
    }

    /**
     * @brief Method to get the current User data.
     * @pre <em>True</em>
     * @post The current User is returned in JSON format.
     */
    public JSONObject viewUser() {
        if (this.currentUser == null)
            return null;

        return this.currentUser.serialize();
    }

    /**
     * @brief Method to get the current Players(Player1 and 2) data.
     * @pre <em>True</em>
     * @post The current Player1 and Player2 are returned in JSON format.
     */
    public Pair<JSONObject, JSONObject> viewPlayers() {
        Pair<JSONObject, JSONObject> result = new Pair<JSONObject, JSONObject>(null, null);

        if (this.currentPlayer1 instanceof User)
            result.first = ((User) this.currentPlayer1).serialize();
        else if (this.currentPlayer1 instanceof Bot)
            result.first = ((Bot) this.currentPlayer1).serialize();

        if (this.currentPlayer2 instanceof User)
            result.second = ((User) this.currentPlayer2).serialize();
        else if (this.currentPlayer2 instanceof Bot)
            result.second = ((Bot) this.currentPlayer2).serialize();

        return result;
    }

    /* CONFIGURATION */

    /**
    * @brief Lets the current user to create a new configuration with a name, rules and the initial board.
    * @pre CurrentUser and currentBoard is not null
    * @param name Name of the Configuration
    * @param canEatHorizontally Boolean that determines if you can capture pieces in a horizontal manner.
    * @param canEatVertically Boolean that determines if you can capture pieces in a vertical manner.
    * @param canEatDiagonally  Boolean that determines if you can capture pieces in a diagonal manner.
    * @post The created Configuration is returned in JSON format if no exception is triggered. Else, the exception is returned in a String.
    */
    public Pair<JSONObject, String> createConfiguration(String name, Boolean canEatHorizontally,
            Boolean canEatVertically, Boolean canEatDiagonally) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            Configuration configuration = new Configuration(name, this.currentUser.getID(), canEatHorizontally,
                    canEatVertically, canEatDiagonally);
            configuration.setCanEatHorizontally(canEatHorizontally); // To ensure raising the rules exception
            configuration.setCanEatVertically(canEatVertically);
            configuration.setCanEatDiagonally(canEatDiagonally);
            this.boardCtrl.isValid(this.currentBoard, configuration);
            configuration = this.configurationCtrl.create(name, canEatHorizontally, canEatVertically, canEatDiagonally,
                    this.currentBoard, this.currentUser.getID());
            result.first = configuration.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
    * @brief Lets the current user create a default initial board to start modifying it in the configuration’s creation.
    * @pre <em>True</em>
    * @post The exception is returned in a String if any are triggered.
    */
    public String createInitialBoard() {
        String result = null;

        try {
            this.currentBoard = new Board();
        } catch (Exception e) {
            return e.getMessage();
        }

        return result;
    }

    /**
    * @brief Lets the current user modify a configuration he/she created. The configuration’s rules and the initial board can be changed.
    * @pre CurrentUser is not null.
    * @param canEatHorizontally Boolean that determines if you can capture pieces in a horizontal manner.
    * @param canEatVertically Boolean that determines if you can capture pieces in a vertical manner.
    * @param canEatDiagonally  Boolean that determines if you can capture pieces in a diagonal manner.
    * @post The modified Configuration is returned in JSON format if no exception is triggered. Else, the exception is returned in a String.
    */
    public Pair<JSONObject, String> modifyConfiguration(String name, Boolean canEatHorizontally,
            Boolean canEatVertically, Boolean canEatDiagonally) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            Configuration configuration = new Configuration(name, this.currentUser.getID(), canEatHorizontally,
                    canEatVertically, canEatDiagonally);
            configuration.setCanEatHorizontally(canEatHorizontally); // To ensure raising the rules exception
            configuration.setCanEatVertically(canEatVertically);
            configuration.setCanEatDiagonally(canEatDiagonally);
            this.boardCtrl.isValid(this.currentBoard, configuration);
            configuration = this.configurationCtrl.modify(name, canEatHorizontally, canEatVertically, canEatDiagonally,
                    this.currentBoard, this.currentUser.getID());
            result.first = configuration.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
    * @brief Lets the current user to modify the initial board of a configuration he/she created.
    * @pre <em>True</em>
    * @param name Name of the Configuration
    * @post The exception is returned in a String if any are triggered.
    */
    public String modifyInitialBoard(String name) {
        String result = null;

        try {
            this.currentBoard = this.configurationCtrl.getInitialBoard(name);
        } catch (Exception e) {
            return e.getMessage();
        }

        return result;
    }

    /**
    * @brief Lets the current user delete a configuration he/she created if it has not been used.
    * @pre currentUser is not null.
    * @param name Name of the Configuration
    * @post The exception is returned in a String if any is triggered.
    */
    public String deleteConfiguration(String name) {
        String result = null;

        try {
            this.configurationCtrl.delete(name, this.currentUser.getID());
        } catch (Exception e) {
            return e.getMessage();
        }

        return result;
    }

    /**
    * @brief Returns the configuration identified by the name.
    * @pre <em>True</em>
    * @param name Name of the Configuration
    * @post The Configuration defined by the name is returned in JSON Format if no exception is triggered. Else, the exception is returned in a String.
    */
    public Pair<JSONObject, String> getConfiguration(String name) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            Configuration configuration = this.configurationCtrl.getConfiguration(name);
            result.first = configuration.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
    * @brief Returns a list of all configurations names in the system.
    * @pre <em>True</em>
    * @post The list of Configuration names is returned in an Array of Strings if no exception is triggered. Else, the exception is returned in a String.
    */
    public Pair<ArrayList<String>, String> listConfigurations() {
        Pair<ArrayList<String>, String> result = new Pair<ArrayList<String>, String>(null, null);

        try {
            result.first = this.configurationCtrl.list();
        } catch (Exception e) {
            return new Pair<ArrayList<String>, String>(null, e.getMessage());
        }

        return result;
    }

    /**
    * @brief Get an Initial Board of a Configuration.
    * @pre <em>True</em>
    * @param name Name of the Configuration
    * @post The initial board of the Configuration identified by name is returned in JSON Format if no exception is triggered. Else, the exception is returned in a String.
    */
    public Pair<JSONObject, String> getInitialBoard(String name) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            Board initialBoard = this.configurationCtrl.getInitialBoard(name);
            result.first = initialBoard.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
     * @brief Method to get the current Configuration data.
     * @pre <em>True</em>
     * @post The current Configuration is returned in JSON format.
     */
    public JSONObject viewConfiguration() {
        if (this.currentConfiguration == null)
            return null;

        return this.currentConfiguration.serialize();
    }

    /* GAME */

    /**
    * @brief Lets the current user create a new game, selecting both players and a configuration of rules and initial board.
    * @pre CurrentUser is not null
    * @param player1ID UUID of Player1
    * @param player2ID UUID of Player1
    * @param configurationName Name of a Configuration
    * @post The created Game is returned in JSON format if no exception is triggered. Else, the exception is returned in a String.
    */
    public Pair<JSONObject, String> createGame(UUID player1ID, UUID player2ID, String configurationName) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            Game game = this.gameCtrl.create(player1ID, player2ID, configurationName, this.currentUser.getID());
            result.first = game.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
    * @brief Lets the current user manually save the current game and playing board state.
    * @pre CurrentUser, currentGame and currentBoard are not null
    * @post The current Game is saved and returned in JSON format if no exception is triggered. Else, the exception is returned in a String.
    */
    public Pair<JSONObject, String> saveGame() {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            Game game = this.gameCtrl.save(this.currentGame, this.currentBoard, this.currentUser.getID());
            result.first = game.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
    * @brief Returns the game identified by its name and any of the participant player IDs.
    * @pre CurrentUser is not null
    * @param name Name of a Game
    * @post Returns the Game identified by its name in JSON format if no exception is triggered. Else, the exception is returned in a String.
    */
    public Pair<JSONObject, String> getGame(String name) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            Game game = this.gameCtrl.getGame(name, this.currentUser.getID());
            result.first = game.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
     * @brief Returns the playing board associated with the given game name and any of the participant player IDs.
     * @pre currentUser is not null
     * @param name Name of a Game
     * @post The playing board associated with the given game name is returned in JSON format if no exception is triggered. Else, the exception is returned in a String.
     */
    public Pair<JSONObject, String> getPlayingBoard(String name) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            Board playingBoard = this.gameCtrl.getPlayingBoard(name, this.currentUser.getID());
            result.first = playingBoard.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
     * @brief Returns a list of all games names identified by any of the participant player IDs.
     * @pre currentUser is not null
     * @post The list of all game names is returned in an ArrayList of Strings if no exception is triggered. Else, the exception is returned in a String.
     */
    public Pair<ArrayList<String>, String> listGames() {
        Pair<ArrayList<String>, String> result = new Pair<ArrayList<String>, String>(null, null);

        try {
            result.first = this.gameCtrl.list(this.currentUser.getID());
        } catch (Exception e) {
            return new Pair<ArrayList<String>, String>(null, e.getMessage());
        }

        return result;
    }

    /**
     * @brief Lets the current user load a selected game by name in order to play it afterwards.
     * @pre currentUser is not null
     * @param name Name of a Game
     * @post The game selected by its name is returned in JSON format if no exception is triggered. Else, the exception is returned in a String.
     */
    public Pair<JSONObject, String> selectGame(String name) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            Game game = this.gameCtrl.getGame(name, this.currentUser.getID());
            Board board = this.gameCtrl.getPlayingBoard(name, this.currentUser.getID());
            Configuration configuration = this.configurationCtrl.getConfiguration(game.getConfigurationName());
            Player player1 = null;
            Player player2 = null;
            try { player1 = this.playerCtrl.getUser(game.getPlayer1ID()); } catch (Exception e) { player1 = this.playerCtrl.getBot(game.getPlayer1ID()); }
            try { player2 = this.playerCtrl.getUser(game.getPlayer2ID()); } catch (Exception e) { player2 = this.playerCtrl.getBot(game.getPlayer2ID()); }
            this.currentGame = game;
            this.currentBoard = board;
            this.currentConfiguration = configuration;
            this.currentPlayer1 = player1;
            this.currentPlayer2 = player2;
            result.first = game.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
     * @brief Lets the current user start playing on the current loaded game.
     * @pre currentGame is not null
     * @post The user starts to play the game and the Game is returned in JSON format if no exception is triggered. Else, the exception is returned in a String.
     */
    public Pair<JSONObject, String> play() {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            this.currentGame = this.gameCtrl.play(this.currentGame);
            result.first = this.currentGame.serialize();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
     * @brief Lets a player of the current game surrender, setting the winner as the opponent.
     * @pre currentGame is not null
     * @param surrendeeID UUID of the User
     * @post The game ends with a winner and the Game is returned in JSON format if no exception is triggered. Else, the exception is returned in a String.
     */
    public Pair<JSONObject, String> surrender(UUID surrendeeID) {
        Pair<JSONObject, String> result = new Pair<JSONObject, String>(null, null);

        try {
            this.currentGame = this.gameCtrl.surrender(this.currentGame, surrendeeID);
            result.first = this.currentGame.serialize();
            this.createEntries();
        } catch (Exception e) {
            return new Pair<JSONObject, String>(null, e.getMessage());
        }

        return result;
    }

    /**
     * @brief Method to get the current Game data.
     * @pre <em>True</em>
     * @post The current Game is returned in JSON format.
     */
    public JSONObject viewGame() {
        if (this.currentGame == null)
            return null;

        return this.currentGame.serialize();
    }

    /* BOARD */

    /**
     * @brief Method to convert an String from the presentation level to a PieceType in order to decouple domain specific knowledgement.
     * @pre <em>True</em>
     * @param pieceType String that represents a pieceType
     * @post The corresponding PieceType will be returned.
     */
    private PieceType stringToPieceType(String pieceType) {
        if (pieceType == null)
            return null;

        if (pieceType.equals(PieceType.PLAYER1.toString()))
            return PieceType.PLAYER1;

        if (pieceType.equals(PieceType.PLAYER2.toString()))
            return PieceType.PLAYER2;

        return null;
    }

    /**
     * @brief Returns the next best possible position, or null if none, to place a piece on the current for a given player.
     *        It forwards the placePiece request to the correct algorithm depending on the difficulty. This method can be used to implement the assisted mode.
     * @pre currentBoard and currentConfiguration is not null
     * @param difficulty Difficulty of the Bot
     * @param myPieceType String that represents a pieceType
     * @post The best position will be returned.
     */
    public Pair<Integer, Integer> getBestPosition(Integer difficulty, String myPieceType) {
        return this.difficultyCtrl.getBestPosition(difficulty, this.currentConfiguration, this.currentBoard,
                this.stringToPieceType(myPieceType));
    }

    /**
     * @brief Modifying method returns the board modified with the added position in
     *        JSON format.
     * @pre Parameters aren't null and <em>position</em> is between values (0,0) and
     *      (7,7).
     * @post currentBoard is modified and is returned in JSONObject format.
     * @param myPieceType PieceType variable that represents the player in a cell.
     * @param position    Pair<Integer,Integer> that represents a position in a
     *                    board.
     */
    public JSONObject placePieceConfig(Pair<Integer, Integer> position, String myPieceType) {
        this.currentBoard = this.boardCtrl.placePieceConfig(this.currentBoard, position,
                this.stringToPieceType(myPieceType));
        return this.currentBoard.serialize();
    }

    /**
     * @brief Modifying method that removes a piece from currentBoard and returns
     *        the Board in JSON format.
     * @pre The <em>position</em> parameter isn't null and has values between (0,0)
     *      and (7,7).
     * @post currentBoard is modified and is returned in JSONObject format.
     * @param position Pair<Integer,Integer> that represents a position in a board.
     */
    public JSONObject removePiece(Pair<Integer, Integer> position) {
        this.currentBoard = this.boardCtrl.removePiece(this.currentBoard, position);
        return this.currentBoard.serialize();
    }

    /**
     * @brief Get method that returns the value of the <em>board</em> parameter's
     *        <em>PiecesPlayer1</em> and <em>PiecesPlayer2</em> attributes.
     * @pre <em>True</em>
     * @post The attributes <em>piecesPlayer1</em> and <em>PiecesPlayer2</em> of the
     *       currentBoard are returned in the first and second space of a Pair,
     *       respectively.
     */
    public Pair<Integer, Integer> getNumPieces() {
        return this.boardCtrl.getNumPieces(this.currentBoard);
    }

    /**
     * @brief Method that returns an Array of the valid positions in <em>board</em>
     *        of the player <em>myPieceType</em> taking into consideration the
     *        Configuration of the currentGame.
     * @pre All parameters aren't null.
     * @post
     *       <p>
     *       An Array of valid positions(Pair<Integer,Integer>) is returned.
     *       </p>
     *       &nbsp; A valid position in a board is one which it's cell state is
     *       equal to null (meaning an empty cell) and there is at least one
     *       opponent PieceType surrounding that position (go to surroundingPieces
     *       to crystalize what the surrounding areas of a position are).
     * @param myPieceType PieceType variable that represents the player in a cell.
     */
    public ArrayList<Pair<Integer, Integer>> validPositions(String myPieceType) {
        return this.boardCtrl.validPositions(this.currentBoard, this.currentConfiguration,
                this.stringToPieceType(myPieceType));
    }

    /**
     * @brief Method that warns us if an instance of the <em>board</em> parameters
     *        is invalid.
     *        <p>
     *        </p>
     *        &nbsp; An invalid Board means that no player can add a piece in the
     *        current state of the implicit parameter's <em>board</em> attribute.
     * @pre All parameters aren't null.
     * @post If the currentBoard is invalid, InvalidBoardException will be thrown,
     *       else a null String will be returned.
     */
    public String isValidBoard() {
        String result = null;

        try {
            this.boardCtrl.isValid(this.currentBoard, this.currentConfiguration);
        } catch (Exception e) {
            return e.getMessage();
        }

        return result;
    }

    /**
     * @brief Modifying method that adds a piece in the <em>board</em> parameter
     *        <p>
     *        </p>
     *        &nbsp; In addition, it applies the effect of adding that piece in the
     *        board by changing the pieces of the board taking into consideration
     *        the Configuration given.
     * @pre Parameters aren't null and <em>position</em> is between values (0,0) and
     *      (7,7). There is an exception if the playerID corresponds to a bot, then the <em>position</em> parameter CAN BE null.
     *      currentGame, currentConfiguration, currentBoard, currentPlayer1, currentPlayer2
     * @post If the playerID is a bot, automatically the best position is calculated for that board state. If the playerID is a user, it will add a piece with the position given.
     *       The modified current board is then returned in a JSON format ready to be played by the opponent if no exception is triggered. Else, the exception will be returned in a string.
     * 
     * @param board              Instance of a Board class which is the one we will
     *                           modify and return.
     * @param myPieceType        PieceType variable that represents the player in a
     *                           cell.
     * @param position           Pair<Integer,Integer> that represents a position in
     *                           a board.
     * @param canEatHorizontally Boolean value from Configuration that determines if
     *                           we can capture pieces of the <em>myPieceType</em>
     *                           opponent in a Horizontal manner..
     * @param canEatVertically   Boolean value from Configuration that determines if
     *                           we can capture pieces of the <em>myPieceType</em>
     *                           opponent in a Vertical manner.
     * @param canEatDiagonally   Boolean value from Configuration that determines if
     *                           we can capture pieces of the <em>myPieceType</em>
     *                           opponent in a Diagonal manner.
     */
    public Pair<Pair<JSONObject, String>, String> placePiece(Pair<Integer, Integer> position, UUID playerID,
            String pieceType) {
        ArrayList<Pair<Integer, Integer>> validPos1 = new ArrayList<Pair<Integer, Integer>>();
        ArrayList<Pair<Integer, Integer>> validPos2 = new ArrayList<Pair<Integer, Integer>>();

        PieceType myPieceType = this.stringToPieceType(pieceType);

        try {
            this.gameCtrl.checkPlaceRights(this.currentGame, playerID, myPieceType);

            validPos1 = this.boardCtrl.validPositions(this.currentBoard, this.currentConfiguration, myPieceType);
            if (validPos1.isEmpty()) {
                validPos2 = this.boardCtrl.validPositions(this.currentBoard, this.currentConfiguration,
                        this.inversePieceType(myPieceType));
                if (validPos2.isEmpty())
                    return this.finishGame();
                return this.nextTurn();
            }

            Player player = (this.currentPlayer1.getID().equals(playerID) ? this.currentPlayer1 : this.currentPlayer2);
            if (player instanceof Bot) {
                Bot bot = ((Bot) player);
                position = this.difficultyCtrl.getBestPositionByBot(bot, this.currentConfiguration, this.currentBoard,
                        myPieceType);
            }

            if (!validPos1.contains(position))
                throw new InvalidPositionException();

            this.currentBoard = this.boardCtrl.placePiece(this.currentBoard, this.currentConfiguration, myPieceType,
                    position);

            validPos2 = this.boardCtrl.validPositions(this.currentBoard, this.currentConfiguration,
                    this.inversePieceType(myPieceType));
            if (!validPos2.isEmpty())
                return this.nextTurn();

            validPos1 = this.boardCtrl.validPositions(this.currentBoard, this.currentConfiguration, myPieceType);
            if (validPos1.isEmpty())
                return this.finishGame();

            return this.currentTurn();
        } catch (Exception e) {
            return new Pair<Pair<JSONObject, String>, String>(null, e.getMessage());
        }
    }

    /**
     * @brief Private method that inverts the Player's pieceType in order to get its opponent.
     * @pre pieceType is not null
     * @param PieceType PieceType variable that represents the player in a cell.
     * @post The opponent's PieceType is returned
     */
    private PieceType inversePieceType(PieceType pieceType) {
        return (pieceType == PieceType.PLAYER2 ? PieceType.PLAYER1 : PieceType.PLAYER2);
    }

    /**
     * @brief Lets the system to automatically finish the game when any players win or when there aren’t any valid positions left to place a piece on the board, setting the winner of the game or setting that the game has ended in a draw.
     * @pre currentGame and currentBoard aren't null
     * @post The game is finised and returned in JSON format if there is no exception triggered. Else, the exception is returned in a String.
     */
    private Pair<Pair<JSONObject, String>, String> finishGame() {
        Pair<Integer, Integer> numPieces = this.boardCtrl.getNumPieces(this.currentBoard);

        try {
            if (numPieces.first > numPieces.second) {
                this.currentGame = this.gameCtrl.finish(this.currentGame, this.currentGame.getPlayer1ID());
                this.createEntries();
                return new Pair<Pair<JSONObject, String>, String>(
                        new Pair<JSONObject, String>(this.currentBoard.serialize(), null),
                        new FinishedGameException().getMessage());
            } else if (numPieces.second > numPieces.first) {
                this.currentGame = this.gameCtrl.finish(this.currentGame, this.currentGame.getPlayer2ID());
                this.createEntries();
                return new Pair<Pair<JSONObject, String>, String>(
                        new Pair<JSONObject, String>(this.currentBoard.serialize(), null),
                        new FinishedGameException().getMessage());
            } else {
                this.currentGame = this.gameCtrl.finish(this.currentGame, null);
                this.createEntries();
                return new Pair<Pair<JSONObject, String>, String>(
                        new Pair<JSONObject, String>(this.currentBoard.serialize(), null),
                        new FinishedGameException().getMessage());
            }
        } catch (Exception e) {
            return new Pair<Pair<JSONObject, String>, String>(null, e.getMessage());
        }
    }

     /**
     * @brief Lets the system to automatically pass the turn of the current game.
     * @pre currentGame is not null.
     * @post he Game is returned with the next turn in JSON format if there is no exception triggered. Else, the exception is returned in a String.
     */
    private Pair<Pair<JSONObject, String>, String> nextTurn() {
        try {
            this.currentGame = this.gameCtrl.nextTurn(this.currentGame);
        } catch (Exception e) {
            return new Pair<Pair<JSONObject, String>, String>(null, e.getMessage());
        }
        return this.currentTurn();
    }

     /**
     * @brief Lets the system to automatically decide the current turn of the current game.
     * @pre currentGame and currentBoard aren't not null.
     * @post The Game is returned with the current turn in JSON format if there is no exception triggered. Else, the exception is returned in a String.
     */
    private Pair<Pair<JSONObject, String>, String> currentTurn() {
        return new Pair<Pair<JSONObject, String>, String>(
                new Pair<JSONObject, String>(this.currentBoard.serialize(), this.currentGame.getTurn().toString()),
                null);
    }

    /**
     * @brief Method to get the current Board data.
     * @pre <em>True</em>
     * @post The current Board is returned in JSON format.
     */
    public JSONObject viewBoard() {
        if (this.currentBoard == null)
            return null;

        return this.currentBoard.serialize();
    }

    /* RANKING */

      /**
     * @brief Returns the ranking identified by name.
     * @pre <em>True</em>
     * @param name of a Ranking 
     * @post The ranking identified by name is returned in JSON format.
     */
    public JSONObject getRanking(String name) {
        return this.rankingCtrl.getRanking(name).serialize();
    }

     /**
     * @brief Returns a list of all ranking names in the system. 
     * @pre <em>True</em>
     * @post The list of ranking names is returned in an ArrayList of strings.
     */
    public ArrayList<String> listRankings() {
        return this.rankingCtrl.listRankings();
    }

     /**
     * @brief Returns the entries with the highest score of the current user for each ranking in the system. 
     * @pre currentUser isn't null
     * @post The list of records and its ranking names is returned in an ArrayList of JSONObjects and Strings.
     */
    public ArrayList<Pair<String, JSONObject>> listRecords() {
        ArrayList<Pair<String, JSONObject>> result = new ArrayList<Pair<String, JSONObject>>();
        ArrayList<Pair<String, Entry>> records = this.rankingCtrl.listRecords(this.currentUser.getID());

        for (Pair<String, Entry> record : records)
            result.add(new Pair<String, JSONObject>(record.first, record.second.serialize()));

        return result;
    }

     /**
     * @brief Lets the system to automatically create the entries of the associated ranking when the current user finishes a game. 
     * @pre currentGame, currentConfiguration, currentPlayer1, currentPlayer2 and currentBoard aren't null
     * @post The entries are created in the system.
     */
    private void createEntries() {
        ArrayList<String> rules = new ArrayList<String>();

        if (this.currentConfiguration.getCanEatHorizontally())
            rules.add("horizontally");

        if (this.currentConfiguration.getCanEatVertically())
            rules.add("vertically");

        if (this.currentConfiguration.getCanEatDiagonally())
            rules.add("diagonally");

        String rankingName;

        if (this.currentGame.getWinnerID() != null) {
            Player winner = null;
            Player loser = null;

            if (this.currentGame.getWinnerID().equals(this.currentPlayer1.getID())) {
                winner = this.currentPlayer1;
                loser = this.currentPlayer2;
            } else {
                winner = this.currentPlayer2;
                loser = this.currentPlayer1;
            }

            // Games won vs <Player>
            rankingName = String.format("Games won vs %s", loser.getName());
            this.rankingCtrl.createEntry(rankingName, winner.getID(), 1, RankingType.INCREMENTAL);

            // Games won with <Rules>
            rankingName = String.format("Games won with %s rules", String.join(", ", rules));
            this.rankingCtrl.createEntry(rankingName, winner.getID(), 1, RankingType.INCREMENTAL);

            // Games lost with <Rules>
            rankingName = String.format("Games lost with %s rules", String.join(", ", rules));
            this.rankingCtrl.createEntry(rankingName, loser.getID(), 1, RankingType.INCREMENTAL);
        } else {
            // Games tied with <Rules>
            rankingName = String.format("Games tied with %s rules", String.join(", ", rules));
            this.rankingCtrl.createEntry(rankingName, this.currentPlayer1.getID(), 1, RankingType.INCREMENTAL);
            this.rankingCtrl.createEntry(rankingName, this.currentPlayer2.getID(), 1, RankingType.INCREMENTAL);
        }

        // Maximum pieces obtained in a game with <Rules>
        rankingName = String.format("Maximum pieces obtained in a game with %s rules", String.join(", ", rules));
        this.rankingCtrl.createEntry(rankingName, currentPlayer1.getID(), this.currentBoard.getPiecesPlayer1(),
                RankingType.UNIQUE);
        this.rankingCtrl.createEntry(rankingName, currentPlayer2.getID(), this.currentBoard.getPiecesPlayer2(),
                RankingType.UNIQUE);
    }
}
