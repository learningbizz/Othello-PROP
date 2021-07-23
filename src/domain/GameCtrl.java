/**
* @file GameCtrl.java
* @author Alex Rodriguez
* @brief GameCtrl class specification.
*/
package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONObject;

import domain.Board.PieceType;
import domain.Exceptions.FinishedGameException;
import domain.Exceptions.InvalidBoardException;
import domain.Exceptions.InvalidConfigurationException;
import domain.Exceptions.InvalidPlayersException;
import domain.Exceptions.NotPlayerException;
import domain.Exceptions.NotPlayerPieceException;
import domain.Exceptions.NotPlayerTurnException;
import domain.Exceptions.NotStartedGameException;
import repository.ConfigurationRepositoryCtrl;
import repository.GameRepositoryCtrl;
import repository.PlayerRepositoryCtrl;

/**
* @class GameCtrl
* @brief Game domain sub-controller. It communicates with the main domain controller, the game repository controller,
* the configuration repository controller and the player repository controller in order to source the necessary components to manage games.
* 
* By Alex Rodriguez.
* @see domain.Game
*/
public class GameCtrl {
    /* ATTRIBUTES */

    /**
    * @brief Game repository controller.
    */
    private GameRepositoryCtrl repositoryCtrl;

    /**
    * @brief Configuration repository controller.
    */
    private ConfigurationRepositoryCtrl configurationRepositoryCtrl;

    /**
    * @brief Player repository controller.
    */
    private PlayerRepositoryCtrl playerRepositoryCtrl;

    /* CONSTRUCTORS */

    /**
     * @brief Creator method that creates an instance of Game Controller.
     * @pre <em>True</em>
     * @post Instance of GameCtrl is created with the default values
     */
    public GameCtrl() {
        this.repositoryCtrl = new GameRepositoryCtrl();
        this.configurationRepositoryCtrl = new ConfigurationRepositoryCtrl();
        this.playerRepositoryCtrl = new PlayerRepositoryCtrl();
    }

    /* METHODS */

    /**
     * @brief Lets the current user create a new game, selecting both players and a configuration of rules and initial board.
     * @pre <em>True</em>
     * @post A Game is returned with its specified attributes if no exception is thrown. Else, an exception will be thrown
     * @param player1ID UUID of Player1
     * @param player2ID UUID of Player2
     * @param configuration Instance of a Configuration
     * @param creatorID UUID of the creator User.
     * @return Game
     */
    public Game create(UUID player1ID, UUID player2ID, String configurationName, UUID creatorID)
            throws InvalidPlayersException, InvalidConfigurationException, InvalidBoardException {

        if (player1ID.equals(player2ID))
            throw new InvalidPlayersException();

        JSONObject rawPlayer1 = this.playerRepositoryCtrl.get(player1ID);
        if (rawPlayer1 == null)
            throw new InvalidPlayersException();

        JSONObject rawPlayer2 = this.playerRepositoryCtrl.get(player2ID);
        if (rawPlayer2 == null)
            throw new InvalidPlayersException();

        if (rawPlayer1.getBoolean("is_deleted") || rawPlayer2.getBoolean("is_deleted"))
            throw new InvalidPlayersException();

        if (rawPlayer1.getString("type").equals("BOT") && rawPlayer2.getString("type").equals("BOT"))
            if (rawPlayer1.getInt("difficulty") == rawPlayer2.getInt("difficulty"))
                throw new InvalidPlayersException();

        JSONObject rawConfiguration = this.configurationRepositoryCtrl.getConfiguration(configurationName);
        if (rawConfiguration == null)
            throw new InvalidConfigurationException();

        JSONObject rawBoard = this.configurationRepositoryCtrl.getBoard(configurationName);
        if (rawBoard == null)
            throw new InvalidBoardException();

        Board playingBoard = new Board(rawBoard);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String name = String.format("%s VS %s | %s", rawPlayer1.getString("name"), rawPlayer2.getString("name"),
                now.format(dateFormat));

        Game game = new Game(name, player1ID, player2ID, configurationName, creatorID);

        this.repositoryCtrl.save(game.serialize(), playingBoard.serialize());
        return game;
    }

    /**
     * @brief Returns the game identified by its name and any of the participant player IDs.
     * @pre <em>True</em>
     * @post Game is returned specified by its name and a Players UUID if no excepti
     * @param name Name of a Game
     * @param playerID UUID of a Player
     * @return Game
     */
    public Game getGame(String name, UUID playerID) throws NotPlayerException {
        if (name.isBlank())
            throw new NotPlayerException();

        JSONObject rawGame = this.repositoryCtrl.getGame(name);

        if (rawGame == null)
            throw new NotPlayerException();

        Game game = new Game(rawGame);

        if (!game.getPlayer1ID().equals(playerID) && !game.getPlayer2ID().equals(playerID)
                && !game.getCreatorID().equals(playerID))
            throw new NotPlayerException();

        return game;
    }

    /**
    * @brief Returns the playing board associated with the given game name and any of the participant player IDs.
    * @pre <em>True</em>
    * @post Returns the playing board of a game if no exception is thrown. Else, an exception will be thrown.
    * @param name Name of a Game
    * @param playerID UUID of a Player
    * @return Board
    */
    public Board getPlayingBoard(String name, UUID playerID) throws NotPlayerException {
        if (name.isBlank())
            throw new NotPlayerException();

        JSONObject rawPlayingBoard = this.repositoryCtrl.getBoard(name);

        if (rawPlayingBoard == null)
            throw new NotPlayerException();

        return new Board(rawPlayingBoard);
    }

    /**
    * @brief Returns a list of all games names identified by any of the participant player IDs.
    * @pre <em>True</em>
    * @post An ArrayList of all the names of the Games will be returned.
    * @param playerID UUID of a Player.
    * @return ArrayList<String>
    */
    public ArrayList<String> list(UUID playerID) {
        return this.repositoryCtrl.listGames(playerID);
    }

    /**
    * @brief Lets the current user manually save the current game and playing board state.
    * @pre game and playingBoard aren't null
    * @post The saved Game is returned if no exception is thrown. Else, an exception will be thrown
    * @param game Game instance
    * @param playingBoard Board instance
    * @param playerID UUID instance.
    * @return Game.
    */
    public Game save(Game game, Board playingBoard, UUID playerID) throws NotPlayerException {
        if (!game.getPlayer1ID().equals(playerID) && !game.getPlayer2ID().equals(playerID)
                && !game.getCreatorID().equals(playerID))
            throw new NotPlayerException();

        this.repositoryCtrl.save(game.serialize(), playingBoard.serialize());
        return game;
    }

    /**
    * @brief Lets the current user load a saved game or a newly created one, and start playing on it.
    * @pre game is not null
    * @post The playing Game is returned if no exception was thrown. Else, an exception will be thrown.
    * @param game Game instance
    * @return Playing Game
    */
    public Game play(Game game) throws FinishedGameException {
        game.play();
        return game;
    }

    /**
    * @brief Lets a player of the current game surrender, setting the winner as the opponent.
    * @pre game is not null
    * @post The surrendered Game is returned if no exception was thrown. Else, an exception will be thrown.
    * @param game Game instance
    * @param surrendeeID UUID of Player
    * @return Game
    */
    public Game surrender(Game game, UUID surrendeeID)
            throws NotPlayerException, FinishedGameException, NotStartedGameException {
        game.surrender(surrendeeID);
        return game;
    }

    /**
    * @brief Lets the system to automatically finish the game when any players win or when there
    * aren’t any valid positions left to place a piece on the board, setting the winner of the game or setting that the game has ended in a draw.
    * @pre game is not null
    * @post The finished Game is returned if no exception was thrown. Else, an exception will be thrown.
    * @param game Game instance
    * @param winnerID UUID of Player
    * @return Game
    */
    public Game finish(Game game, UUID winnerID)
            throws NotPlayerException, FinishedGameException, NotStartedGameException {
        game.finish(winnerID);
        return game;
    }

    /**
    * @brief Lets the system check whether the player that wants to place a piece on the board of the current game is able to do so,
    * that is, its his/her turn and the piece type its his/hers.
    * @pre game is not null
    * @post If the Player is able to place a piece, nothing happens. Else, an exception will be thrown.
    * @param game Game instance
    * @param winnerID UUID of Player
    * @param pieceType PieceType
    */
    public void checkPlaceRights(Game game, UUID playerID, PieceType pieceType) throws NotPlayerException,
            NotPlayerPieceException, NotPlayerTurnException, FinishedGameException, NotStartedGameException {
        game.checkPlaceRights(playerID, pieceType);
    }

    /**
    * @brief Lets the system to automatically pass the turn of the current game.
    * @pre game is not null
    * @post Returns the Game with the next turn if no exception was thrown. Else, an exception will be thrown. 
    * @param game Instance of a Game
    * @return Game
    */
    public Game nextTurn(Game game) throws FinishedGameException, NotStartedGameException {
        game.nextTurn();
        return game;
    }
}
