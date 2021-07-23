/**
 * @file Game.java
 * @author Alex Rodriguez
 * @brief Game class specification.
 */
package domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.json.JSONObject;

import domain.Board.PieceType;
import domain.Exceptions.FinishedGameException;
import domain.Exceptions.InvalidConfigurationException;
import domain.Exceptions.InvalidNameException;
import domain.Exceptions.NotPlayerException;
import domain.Exceptions.NotPlayerPieceException;
import domain.Exceptions.NotPlayerTurnException;
import domain.Exceptions.NotStartedGameException;

/**
 * @class Game
 * @brief Represents the state of an Othello game including its name, players, the current turn, the state,
 * the configuration used, the winner if any, its creator and the creation timestamp.
 * By Alex Rodriguez.
 */
public class Game {
    /**
    * @brief State of a Game. Whether it has not started, it is currently being played or it has already finished.
    */
    public enum GameState {
        NOT_STARTED, IN_PROGRESS, FINISHED
    };

    /* ATTRIBUTES */

    /**
    * @brief Name of the Game.
    */
    private String name;
    /**
    * @brief First player ID of the Game.
    */
    private UUID player1ID;
    /**
    * @brief Second player ID of the Game.
    */
    private UUID player2ID;
    /**
    * @brief Name of the Configuration used to create the Game.
    */
    private String configurationName;
    /**
    * @brief Current turn of the Game.
    */
    private PieceType turn;
    /**
    * @brief Current state of the Game.
    */
    private GameState state;
    /**
    * @brief Winner, if any, of the Game. If the state is FINISHED and it is null, it means the Game ended in a draw.
    */
    private UUID winnerID;
    /**
    * @brief Player ID of the Game's creator.
    */
    private UUID creatorID;
    /**
    * @brief Game creation timestamp.
    */
    private LocalDateTime createdAt;

    /* CONSTRUCTORS */

    /**
     * @brief Create a Game instance.
     * @pre <em>True</em>
     * @post A Game instance is created and its implicits name, player1ID, player2ID, configurationName and creatorID attributes are setted.
     * The current turn is setted to PLAYER1, the state to NOT_STARTED, the winnerID to null and the createdAt to the current timestamp.
     * @param name Name of the Game.
     * @param player1ID First player ID of the Game.
     * @param player2ID Second player ID of the Game.
     * @param configurationName Name of the Configuration used to create the Game.
     * @param creatorID Player ID of the Game's creator.
     */
    public Game(String name, UUID player1ID, UUID player2ID, String configurationName, UUID creatorID) {
        this.name = name;
        this.player1ID = player1ID;
        this.player2ID = player2ID;
        this.configurationName = configurationName;
        this.turn = PieceType.PLAYER1;
        this.state = GameState.NOT_STARTED;
        this.winnerID = null;
        this.creatorID = creatorID;
        this.createdAt = LocalDateTime.now();
    }

    /**
     * @brief Create a Game instance from a JSONObject representation of a Game.
     * @pre <em>True</em>
     * @post A Game instance is created and its implicits name, player1ID, player2ID, configurationName and creatorID attributes are setted.
     * The current turn is setted to PLAYER1, the state to NOT_STARTED, the winnerID to null and the createdAt to the current timestamp.
     * @param game JSONObject representation of a Game.
     */
    public Game(JSONObject game) {
        this.name = game.getString("name");
        this.player1ID = UUID.fromString(game.getString("player1_id"));
        this.player2ID = UUID.fromString(game.getString("player2_id"));
        this.configurationName = game.getString("configuration_name");
        this.turn = game.getEnum(PieceType.class, "turn");
        this.state = game.getEnum(GameState.class, "state");

        this.winnerID = null;
        String winnerID = game.optString("winner_id", null);
        if (winnerID != null)
            this.winnerID = UUID.fromString(winnerID);

        this.creatorID = UUID.fromString(game.getString("creator_id"));
        this.createdAt = LocalDateTime.parse(game.getString("created_at"));
    }

    /* METHODS */

    /**
     * @brief Create a JSONObject representation of a Game from the implicit Game.
     * @pre <em>True</em>
     * @post A JSONObject representing the implicit Game is returned.
     * @return JSONObject representation of a Game.
     */
    public JSONObject serialize() {
        JSONObject game = new JSONObject();

        game.put("name", this.name);
        game.put("player1_id", this.player1ID.toString());
        game.put("player2_id", this.player2ID.toString());
        game.put("configuration_name", this.configurationName);
        game.put("turn", this.turn);
        game.put("state", this.state);

        if (this.winnerID != null)
            game.put("winner_id", this.winnerID.toString());
        else
            game.put("winner_id", JSONObject.NULL);

        game.put("creator_id", this.creatorID.toString());
        game.put("created_at", this.createdAt.toString());

        return game;
    }

    /**
     * @brief Get the name of the implicit Game.
     * @pre <em>True</em>
     * @post The name attribute of the implicit Game is returned.
     * @return Name of the implicit Game.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @brief Set the name of the implicit Game.
     * @pre <em>True</em>
     * @post The name attribute of the implicit Game is setted if it is not blank,
     * otherwise an InvalidNameException is thrown.
     * @param name Name of the Game.
     */
    public void setName(String name) throws InvalidNameException {
        if (name.isBlank())
            throw new InvalidNameException();

        this.name = name;
    }

    /**
     * @brief Get the player1ID of the implicit Game.
     * @pre <em>True</em>
     * @post The player1ID attribute of the implicit Game is returned.
     * @return Player1ID of the implicit Game.
     */
    public UUID getPlayer1ID() {
        return this.player1ID;
    }

    /**
     * @brief Get the player2ID of the implicit Game.
     * @pre <em>True</em>
     * @post The player2ID attribute of the implicit Game is returned.
     * @return Player2ID of the implicit Game.
     */
    public UUID getPlayer2ID() {
        return this.player2ID;
    }

    /**
     * @brief Get the configurationName of the implicit Game.
     * @pre <em>True</em>
     * @post The configurationName attribute of the implicit Game is returned.
     * @return ConfigurationName of the implicit Game.
     */
    public String getConfigurationName() {
        return this.configurationName;
    }

    /**
     * @brief Set the configurationName of the implicit Game.
     * @pre <em>True</em>
     * @post The configurationName attribute of the implicit Game is setted if it is not blank,
     * otherwise an InvalidNameException is thrown.
     * @param configurationName Name of the Configuration used to create the Game.
     */
    public void setConfigurationName(String configurationName) throws InvalidConfigurationException {
        if (configurationName.isBlank())
            throw new InvalidConfigurationException();

        this.configurationName = configurationName;
    }

    /**
     * @brief Get the turn of the implicit Game.
     * @pre <em>True</em>
     * @post The turn attribute of the implicit Game is returned.
     * @return Turn of the implicit Game.
     */
    public PieceType getTurn() {
        return this.turn;
    }

    /**
     * @brief Set the turn of the implicit Game.
     * @pre <em>True</em>
     * @post The turn attribute of the implicit Game is setted.
     * @param turn Current turn of the Game.
     */
    public void setTurn(PieceType turn) {
        this.turn = turn;
    }

    /**
     * @brief Get the state of the implicit Game.
     * @pre <em>True</em>
     * @post The state attribute of the implicit Game is returned.
     * @return State of the implicit Game.
     */
    public GameState getState() {
        return this.state;
    }

    /**
     * @brief Set the state of the implicit Game.
     * @pre <em>True</em>
     * @post The state attribute of the implicit Game is setted.
     * @param state Current state of the Game.
     */
    public void setState(GameState state) {
        this.state = state;
    }

    /**
     * @brief Get the winnerID of the implicit Game.
     * @pre <em>True</em>
     * @post The winnerID attribute of the implicit Game is returned.
     * @return WinnerID of the implicit Game.
     */
    public UUID getWinnerID() {
        return this.winnerID;
    }

    /**
     * @brief Get the creatorID of the implicit Game.
     * @pre <em>True</em>
     * @post The creatorID attribute of the implicit Game is returned.
     * @return CreatorID of the implicit Game.
     */
    public UUID getCreatorID() {
        return this.creatorID;
    }

    /**
     * @brief Get the createdAt of the implicit Game.
     * @pre <em>True</em>
     * @post The createdAt attribute of the implicit Game is returned.
     * @return CreatedAt of the implicit Game.
     */
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * @brief Start playing in the implicit Game.
     * @pre The state attribute of the implicit Game is NOT_STARTED.
     * @post The state attribute of the implicit Game is setted to IN_PROGRESS if any of the following exceptions are not thrown:
     * - FinishedGameException if the implicit Game has already finished.
     */
    public void play() throws FinishedGameException {
        if (this.state == GameState.FINISHED)
            throw new FinishedGameException();

        this.state = GameState.IN_PROGRESS;
    }

    /**
     * @brief Surrender the implicit Game.
     * @pre <em>True</em>
     * @post The state attribute is setted to FINISHED and the winnerID of the implicit Game is setted to the oponent Player
     * if any of the following exceptions are not thrown:
     * - NotPlayerException if the player that wants to surrender is not part of the implicit Game.
     * - FinishedGameException if the implicit Game has already finished.
     * - NotStartedGameException if the implicit Game has not yet started.
     * @param surrendeeID ID of the Player that surrends.
     */
    public void surrender(UUID surrendeeID) throws NotPlayerException, FinishedGameException, NotStartedGameException {
        if (surrendeeID.equals(this.player1ID))
            this.finish(this.player2ID);
        else if (surrendeeID.equals(this.player2ID))
            this.finish(this.player1ID);
        else
            throw new NotPlayerException();
    }

    /**
     * @brief Finish the implicit Game and set a winner if the Game did not end in a draw.
     * @pre <em>True</em>
     * @post The state attribute is setted to FINISHED and the winnerID of the implicit Game is setted to the winner Player
     * or null if the Game ended in a draw, if any of the following exceptions are not thrown:
     * - NotPlayerException if the player that wants to finish is not part of the implicit Game.
     * - FinishedGameException if the implicit Game has already finished.
     * - NotStartedGameException if the implicit Game has not yet started.
     * @param winnerID ID of the Player that wins or null if the implicit Game ended in a draw.
     */
    public void finish(UUID winnerID) throws NotPlayerException, FinishedGameException, NotStartedGameException {
        if (this.state == GameState.NOT_STARTED)
            throw new NotStartedGameException();

        if (this.state == GameState.FINISHED)
            throw new FinishedGameException();

        if (winnerID != null && !winnerID.equals(this.player1ID) && !winnerID.equals(this.player2ID))
            throw new NotPlayerException();

        this.state = GameState.FINISHED;
        this.winnerID = winnerID;
    }

    /**
     * @brief Check whether a Player is able to place a piece in the implicit Game.
     * @pre <em>True</em>
     * @post It executes successfully if any of the following exceptions are not thrown:
     * - NotPlayerException if the player that wants to place a piece is not part of the implicit Game.
     * - NotPlayerPieceException if the player wants to place an opponent piece.
     * - NotPlayerTurnException if it is not the turn of the player that wants to place a piece.
     * - FinishedGameException if the implicit Game has already finished.
     * - NotStartedGameException if the implicit Game has not yet started.
     * @param playerID ID of the Player that wants to place a piece in the implicit Game.
     * @param pieceType Type of the piece that the Player wants to place in the implicit Game.
     */
    public void checkPlaceRights(UUID playerID, PieceType pieceType) throws NotPlayerException, NotPlayerPieceException,
            NotPlayerTurnException, FinishedGameException, NotStartedGameException {
        if (this.state == GameState.NOT_STARTED)
            throw new NotStartedGameException();

        if (this.state == GameState.FINISHED)
            throw new FinishedGameException();

        if (playerID.equals(this.player1ID)) {
            if (pieceType != PieceType.PLAYER1)
                throw new NotPlayerPieceException();
        } else if (playerID.equals(this.player2ID)) {
            if (pieceType != PieceType.PLAYER2)
                throw new NotPlayerPieceException();
        } else {
            throw new NotPlayerException();
        }

        if (pieceType != this.turn)
            throw new NotPlayerTurnException();
    }

    /**
     * @brief Pass the turn of the implicit Game.
     * @pre <em>True</em>
     * @post The turn attribute of the implicit Game is setted to the opponent Player if any of the following exceptions are not thrown:
     * - FinishedGameException if the implicit Game has already finished.
     * - NotStartedGameException if the implicit Game has not yet started.
     */
    public void nextTurn() throws FinishedGameException, NotStartedGameException {
        if (this.state == GameState.NOT_STARTED)
            throw new NotStartedGameException();

        if (this.state == GameState.FINISHED)
            throw new FinishedGameException();

        this.turn = (this.turn == PieceType.PLAYER1 ? PieceType.PLAYER2 : PieceType.PLAYER1);
    }
}
