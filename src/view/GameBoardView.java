/**
 * @file GameBoardView.java
 * @author Alex Rodriguez
 * @brief GameBoardView controller specification.
 */
package view;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import org.json.JSONObject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import util.Pair;

/**
 * @class GameBoardView
 * @brief <p> This class represents the scene controller of the game board view  .</p>
 * By Alex Rodriguez
 */
public class GameBoardView {

    /*CREATOR*/

    /**
     * @brief Class creator.
     * */
    public GameBoardView() {
    }

    /*ATTRIBUTES*/

    /**
     * @brief goToMenu button.
     * */
    @FXML
    private Text goToMenu;
    /**
     * @brief Piece located in (1, 1).
     * */
    @FXML
    private Circle f1c1;
    /**
     * @brief Piece located in (1, 2).
     * */
    @FXML
    private Circle f1c2;
    /**
     * @brief Piece located in (1, 3).
     * */
    @FXML
    private Circle f1c3;
    /**
     * @brief Piece located in (1, 4).
     * */
    @FXML
    private Circle f1c4;
    /**
     * @brief Piece located in (1, 5).
     * */
    @FXML
    private Circle f1c5;
    /**
     * @brief Piece located in (1, 6).
     * */
    @FXML
    private Circle f1c6;
    /**
     * @brief Piece located in (1, 7).
     * */
    @FXML
    private Circle f1c7;
    /**
     * @brief Piece located in (1, 8).
     * */
    @FXML
    private Circle f1c8;
    /**
     * @brief Piece located in (2, 1).
     * */
    @FXML
    private Circle f2c1;
    /**
     * @brief Piece located in (2, 2).
     * */
    @FXML
    private Circle f2c2;
    /**
     * @brief Piece located in (2, 3).
     * */
    @FXML
    private Circle f2c3;
    /**
     * @brief Piece located in (2, 4).
     * */
    @FXML
    private Circle f2c4;
    /**
     * @brief Piece located in (2, 5).
     * */
    @FXML
    private Circle f2c5;
    /**
     * @brief Piece located in (2, 6).
     * */
    @FXML
    private Circle f2c6;
    /**
     * @brief Piece located in (2, 7).
     * */
    @FXML
    private Circle f2c7;
    /**
     * @brief Piece located in (2, 8).
     * */
    @FXML
    private Circle f2c8;
    /**
     * @brief Piece located in (3, 1).
     * */
    @FXML
    private Circle f3c1;
    /**
     * @brief Piece located in (3, 2).
     * */
    @FXML
    private Circle f3c2;
    /**
     * @brief Piece located in (3, 3).
     * */
    @FXML
    private Circle f3c3;
    /**
     * @brief Piece located in (3, 4).
     * */
    @FXML
    private Circle f3c4;
    /**
     * @brief Piece located in (3, 5).
     * */
    @FXML
    private Circle f3c5;
    /**
     * @brief Piece located in (3, 6).
     * */
    @FXML
    private Circle f3c6;
    /**
     * @brief Piece located in (3, 7).
     * */
    @FXML
    private Circle f3c7;
    /**
     * @brief Piece located in (3, 8).
     * */
    @FXML
    private Circle f3c8;
    /**
     * @brief Piece located in (4, 1).
     * */
    @FXML
    private Circle f4c1;
    /**
     * @brief Piece located in (4, 2).
     * */
    @FXML
    private Circle f4c2;
    /**
     * @brief Piece located in (4, 3).
     * */
    @FXML
    private Circle f4c3;
    /**
     * @brief Piece located in (4, 4).
     * */
    @FXML
    private Circle f4c4;
    /**
     * @brief Piece located in (4, 5).
     * */
    @FXML
    private Circle f4c5;
    /**
     * @brief Piece located in (4, 6).
     * */
    @FXML
    private Circle f4c6;
    /**
     * @brief Piece located in (4, 7).
     * */
    @FXML
    private Circle f4c7;
    /**
     * @brief Piece located in (4, 8).
     * */
    @FXML
    private Circle f4c8;
    /**
     * @brief Piece located in (5, 1).
     * */
    @FXML
    private Circle f5c1;
    /**
     * @brief Piece located in (5, 2).
     * */
    @FXML
    private Circle f5c2;
    /**
     * @brief Piece located in (5, 3).
     * */
    @FXML
    private Circle f5c3;
    /**
     * @brief Piece located in (5, 4).
     * */
    @FXML
    private Circle f5c4;
    /**
     * @brief Piece located in (5, 5).
     * */
    @FXML
    private Circle f5c5;
    /**
     * @brief Piece located in (5, 6).
     * */
    @FXML
    private Circle f5c6;
    /**
     * @brief Piece located in (5, 7).
     * */
    @FXML
    private Circle f5c7;
    /**
     * @brief Piece located in (5, 8).
     * */
    @FXML
    private Circle f5c8;
    /**
     * @brief Piece located in (6, 1).
     * */
    @FXML
    private Circle f6c1;
    /**
     * @brief Piece located in (6, 2).
     * */
    @FXML
    private Circle f6c2;
    /**
     * @brief Piece located in (6, 3).
     * */
    @FXML
    private Circle f6c3;
    /**
     * @brief Piece located in (6, 4).
     * */
    @FXML
    private Circle f6c4;
    /**
     * @brief Piece located in (6, 5).
     * */
    @FXML
    private Circle f6c5;
    /**
     * @brief Piece located in (6, 6).
     * */
    @FXML
    private Circle f6c6;
    /**
     * @brief Piece located in (6, 7).
     * */
    @FXML
    private Circle f6c7;
    /**
     * @brief Piece located in (6, 8).
     * */
    @FXML
    private Circle f6c8;
    /**
     * @brief Piece located in (7, 1).
     * */
    @FXML
    private Circle f7c1;
    /**
     * @brief Piece located in (7, 2).
     * */
    @FXML
    private Circle f7c2;
    /**
     * @brief Piece located in (7, 3).
     * */
    @FXML
    private Circle f7c3;
    /**
     * @brief Piece located in (7, 4).
     * */
    @FXML
    private Circle f7c4;
    /**
     * @brief Piece located in (7, 5).
     * */
    @FXML
    private Circle f7c5;
    /**
     * @brief Piece located in (7, 6).
     * */
    @FXML
    private Circle f7c6;
    /**
     * @brief Piece located in (7, 7).
     * */
    @FXML
    private Circle f7c7;
    /**
     * @brief Piece located in (7, 8).
     * */
    @FXML
    private Circle f7c8;
    /**
     * @brief Piece located in (8, 1).
     * */
    @FXML
    private Circle f8c1;
    /**
     * @brief Piece located in (8, 2).
     * */
    @FXML
    private Circle f8c2;
    /**
     * @brief Piece located in (8, 3).
     * */
    @FXML
    private Circle f8c3;
    /**
     * @brief Piece located in (8, 4).
     * */
    @FXML
    private Circle f8c4;
    /**
     * @brief Piece located in (8, 5).
     * */
    @FXML
    private Circle f8c5;
    /**
     * @brief Piece located in (8, 6).
     * */
    @FXML
    private Circle f8c6;
    /**
     * @brief Piece located in (8, 7).
     * */
    @FXML
    private Circle f8c7;
    /**
     * @brief Piece located in (8, 8).
     * */
    @FXML
    private Circle f8c8;
    /**
     * @brief Save board button text.
     * */
    @FXML
    private Text save;
    /**
     * @brief Save board button.
     * */
    @FXML
    private Rectangle saveButton;
    /**
     * @brief Surrender board button text.
     * */
    @FXML
    private Text surrender;
    /**
     * @brief Surrender board button text.
     * */
    @FXML
    private Rectangle surrenderButton;
    /**
     * @brief Tie icon image.
     */
    @FXML
    private ImageView tieIcon;
    /**
     * Win cup icon image.
     */
    @FXML
    private ImageView winIcon;
    /**
     * Exception output message label.
     */
    @FXML
    private Label gameResult;
    /**
     * Second player name label.
     */
    @FXML
    private Label player2;
    /**
     * Second player turn label.
     */
    @FXML
    private Label player2Turn;
    /**
     * Second player number of pieces label.
     */
    @FXML
    private Label player2Pieces;
    /**
     * Second player type tag label.
     */
    @FXML
    private Label player2Type;
    /**
     * First player name label.
     */
    @FXML
    private Label player1;
    /**
     * First player turn label.
     */
    @FXML
    private Label player1Turn;
    /**
     * First player number of pieces label.
     */
    @FXML
    private Label player1Pieces;
    /**
     * First player type tag label.
     */
    @FXML
    private Label player1Type;
    /**
     * Assisted mode option radio button.
     */
    @FXML
    private RadioButton assistedMode;
    /**
     * @brief Current board.
     */
    private JSONObject board;
    /**
     * @brief Current game.
     */
    private JSONObject game;
    /**
     * @brief Current players.
     */
    private Pair<JSONObject, JSONObject> players;
    /**
     * @brief Current user.
     */
    private JSONObject user;
    /**
     * @brief Current ID of the turn's player.
     */
    private UUID turnPlayerID;
    /**
     * @brief Whether the current turn's player is a bot.
     */
    private Boolean turnPlayerIsBot;
    /**
     * @brief Current turn's piece type.
     */
    private String turnPieceType;
    /**
     * @brief Whether the current user is spectating a game.
     */
    private Boolean isSpectating;
    /**
     * @brief Whether the current user is vs bot.
     */
    private Boolean isVsBot;
    /**
     * @brief Timer to automatically perform bot placing trough runtimes threads asynchronously.
     */
    private Timer timer;

    /* METHODS*/

    /**
     * @brief Initialize method which is executed when the scene is shown.
     * @pre <em>True</em>
     * @post The board is setted.
     */
    public void initialize() {
        board = ViewCtrl.domainCtrl.viewBoard();
        game = ViewCtrl.domainCtrl.viewGame();
        players = ViewCtrl.domainCtrl.viewPlayers();
        user = ViewCtrl.domainCtrl.viewUser();
        isSpectating = (!user.getString("id").equals(players.first.getString("id"))
                && !user.getString("id").equals(players.second.getString("id")));
        if (isSpectating) {
            surrender.setVisible(false);
            surrenderButton.setVisible(false);
            save.setVisible(false);
            saveButton.setVisible(false);
            assistedMode.setVisible(false);
        }
        isVsBot = (!isSpectating
                && (players.first.getString("type").equals("BOT") || players.second.getString("type").equals("BOT")));
        player1.setText(players.first.getString("name"));
        player1Type.setText(players.first.getString("type"));
        player2.setText(players.second.getString("name"));
        player2Type.setText(players.second.getString("type"));
        turnPieceType = game.get("turn").toString();
        tieIcon.setVisible(false);
        winIcon.setVisible(false);
        gameResult.setText("");
        renderState();
        if (game.get("state").toString().equals("FINISHED")) {
            isSpectating = true;
            String winner = game.optString("winner_id", null);
            UUID winnerID = (winner != null ? UUID.fromString(winner) : null);
            renderResult(winnerID);
        } else {
            if (turnPlayerIsBot) {
                timer = new Timer();
                timer.schedule(new BotTask(), 500);
            }
        }
    }

    /**
     * @brief Event method which is executed when the goToMenu button is clicked.
     * @pre <em>True</em>
     * @post The scene is changed to PlayView.
     */
    public void goToMenu() throws IOException {
        if (!game.get("state").toString().equals("FINISHED")) {
            Alert confirm = new Alert(AlertType.CONFIRMATION, "You will exit without saving. Are you sure?",
                    ButtonType.YES, ButtonType.NO);
            confirm.showAndWait();
            if (confirm.getResult() == ButtonType.YES) {
                ViewCtrl.domainCtrl.exitGame();
                ViewCtrl.changeScene("template/PlayView.fxml");
            }
        } else {
            ViewCtrl.domainCtrl.exitGame();
            ViewCtrl.changeScene("template/PlayView.fxml");
        }
    }

    /**
     * @brief Event method which is executed when the save button is clicked.
     * @pre <em>True</em>
     * @post The game is saved and user can close the game.
     */
    public void save() throws IOException {
        Pair<JSONObject, String> result = ViewCtrl.domainCtrl.saveGame();
        if (result.second != null) {
            switch (result.second) {
                case "ERR_NOT_PLAYER":
                    gameResult.setText("You are not part of this game!");
                    break;
                default:
                    gameResult.setText("Something went wrong, try again!");
                    break;
            }
        } else {
            gameResult.setText("");
            Alert confirm = new Alert(AlertType.CONFIRMATION, "Do you also want to exit the current game?",
                    ButtonType.YES, ButtonType.NO);
            confirm.showAndWait();
            if (confirm.getResult() == ButtonType.YES) {
                ViewCtrl.changeScene("template/PlayView.fxml");
            }
        }
    }

    /**
     * @brief Event method which is executed when the surrender button is clicked.
     * @pre <em>True</em>
     * @post The game is finished and user automatically loses the game.
     */
    public void surrender() throws IOException {
        if (!isSpectating) {
            if (!isVsBot || turnPlayerID.equals(UUID.fromString(user.getString("id")))) {
                Alert confirm = new Alert(AlertType.CONFIRMATION,
                        "You will surrender and the game will be saved. Are you sure?", ButtonType.YES, ButtonType.NO);
                confirm.showAndWait();
                if (confirm.getResult() == ButtonType.YES) {
                    Pair<JSONObject, String> result = ViewCtrl.domainCtrl.surrender(turnPlayerID);
                    if (result.second != null) {
                        switch (result.second) {
                            case "ERR_NOT_PLAYER":
                                gameResult.setText("You are not part of this game!");
                                break;
                            case "ERR_FINISHED_GAME":
                                gameResult.setText("This game is already finished!");
                                break;
                            case "ERR_NOT_STARTED_GAME":
                                gameResult.setText("This game has not yet started!");
                                break;
                            default:
                                gameResult.setText("Something went wrong, try again!");
                                break;
                        }
                    } else {
                        gameResult.setText("");
                        game = result.first;
                        renderState();
                        renderResult(UUID.fromString(game.getString("winner_id")));
                        ViewCtrl.domainCtrl.saveGame();
                        confirm = new Alert(AlertType.CONFIRMATION, "Do you also want to exit the current game?",
                                ButtonType.YES, ButtonType.NO);
                        confirm.showAndWait();
                        if (confirm.getResult() == ButtonType.YES) {
                            ViewCtrl.changeScene("template/PlayView.fxml");
                        }
                    }
                }
            }
        }
    }

    /**
     * @brief Event method which is executed when a piece is clicked.
     * @pre <em>True</em>
     * @post The piece changes into white or black.
     */
    public void transform(MouseEvent mouseEvent) {
        if (mouseEvent == null || !isSpectating) {
            if (mouseEvent == null || !isVsBot || turnPlayerID.equals(UUID.fromString(user.getString("id")))) {
                Pair<Integer, Integer> pos = (!turnPlayerIsBot ? getClickedPos(mouseEvent) : null);
                Pair<Pair<JSONObject, String>, String> result = ViewCtrl.domainCtrl.placePiece(pos, turnPlayerID,
                        turnPieceType);
                if (result.second != null) {
                    switch (result.second) {
                        case "ERR_NOT_PLAYER":
                            gameResult.setText("You are not part of this game!");
                            break;
                        case "ERR_NOT_PLAYER_PIECE":
                            gameResult.setText("Is not your turn!");
                            break;
                        case "ERR_NOT_PLAYER_TURN":
                            gameResult.setText("Is not your turn!");
                            break;
                        case "ERR_NOT_STARTED_GAME":
                            gameResult.setText("This game has not yet started!");
                            break;
                        case "ERR_INVALID_POSITION":
                            gameResult.setText("Can't place there!");
                            break;
                        case "ERR_FINISHED_GAME":
                            gameResult.setText("");
                            board = result.first.first;
                            turnPieceType = result.first.second;
                            game = ViewCtrl.domainCtrl.viewGame();
                            renderState();
                            String winner = game.optString("winner_id", null);
                            UUID winnerID = (winner != null ? UUID.fromString(winner) : null);
                            ViewCtrl.domainCtrl.saveGame();
                            renderResult(winnerID);
                            break;
                        default:
                            gameResult.setText("Something went wrong, try again!");
                            break;
                    }
                } else {
                    try {
                        board = result.first.first;
                        turnPieceType = result.first.second;
                        gameResult.setText("");
                        renderState();
                        if (turnPlayerIsBot) {
                            timer = new Timer();
                            timer.schedule(new BotTask(), 500);
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    /**
     * @brief Render the current game state.
     * @pre <em>True</em>
     * @post The current game state is rendered onto the view.
     */
    private void renderState() {
        if (turnPieceType == "PLAYER1") {
            turnPlayerID = UUID.fromString(players.first.getString("id"));
            turnPlayerIsBot = (players.first.getString("type").equals("BOT"));
            player1Turn.setVisible(true);
            player2Turn.setVisible(false);
        } else if (turnPieceType == "PLAYER2") {
            turnPlayerID = UUID.fromString(players.second.getString("id"));
            turnPlayerIsBot = (players.second.getString("type").equals("BOT"));
            player1Turn.setVisible(false);
            player2Turn.setVisible(true);
        } else {
            player1Turn.setVisible(false);
            player2Turn.setVisible(false);
        }

        Pair<Integer, Integer> numPieces = ViewCtrl.domainCtrl.getNumPieces();
        player1Pieces.setText(String.format("x%d", numPieces.first));
        player2Pieces.setText(String.format("x%d", numPieces.second));

        render();
        if (!(turnPlayerIsBot || game.get("state").toString().equals("FINISHED"))) {
            ArrayList<Pair<Integer, Integer>> validPositions = ViewCtrl.domainCtrl.validPositions(turnPieceType);
            for (Pair<Integer, Integer> pos : validPositions)
                drawPiece(pos, (turnPieceType == "PLAYER1" ? 'B' : 'N'), true);
            if (assistedMode.isSelected()) {
                Pair<Integer, Integer> bestPos = ViewCtrl.domainCtrl.getBestPosition(10, turnPieceType);
                if (bestPos != null)
                    drawPiece(bestPos, 'X', true);
            }
        }
    }

    /**
     * @brief Render the result of a game.
     * @pre <em>True</em>
     * @post The current game's result is rendered onto the view.
     */
    private void renderResult(UUID winnerID) {
        surrender.setVisible(false);
        surrenderButton.setVisible(false);
        save.setVisible(false);
        saveButton.setVisible(false);
        assistedMode.setVisible(false);
        player1Turn.setVisible(false);
        player2Turn.setVisible(false);
        isSpectating = true;

        if (winnerID == null) {
            gameResult.setText("The game has ended in a draw.");
            tieIcon.setVisible(true);
        } else if (winnerID.equals(UUID.fromString(players.first.getString("id")))) {
            gameResult.setTextFill(Color.web("0xFFFFFF", 1.0));
            gameResult.setText(String.format("%s has won the game.", players.first.getString("name")));
            winIcon.setVisible(true);
        } else {
            gameResult.setTextFill(Color.web("0x000000", 1.0));
            gameResult.setText(String.format("%s has won the game.", players.second.getString("name")));
            winIcon.setVisible(true);
        }
    }

    /**
     * @brief Method executed everytime there is a change in the board.
     * @pre <em>True</em>
     * @post The change is setted in the board.
     */
    private void render() {
        for (int i = 0; i < 8; i++) {
            char[] row = board.getString(String.format("row%d", i)).toCharArray();
            for (int j = 0; j < 8; j++)
                drawPiece(new Pair<Integer, Integer>(i, j), row[j], false);
        }
    }

    /**
     * @brief Painting method executed everytime there is a change in the board.
     * @pre <em>True</em>
     * @post Pieces change to the correct color.
     */
    private void drawPiece(Pair<Integer, Integer> pos, char pieceType, boolean stroke) {
        Circle circle = getCircle(pos);
        switch (pieceType) {
            case 'B':
                if (!stroke) {
                    circle.setFill(Color.web("0xFFFFFF", 1.0));
                    circle.setStrokeWidth(0);
                } else {
                    circle.setStrokeWidth(3);
                    circle.setStrokeType(StrokeType.INSIDE);
                    circle.setStroke(Color.web("0xFFFFFF", 1.0));
                }
                break;
            case 'N':
                if (!stroke) {
                    circle.setFill(Color.web("0x000000", 1.0));
                    circle.setStrokeWidth(0);
                } else {
                    circle.setStrokeWidth(3);
                    circle.setStrokeType(StrokeType.INSIDE);
                    circle.setStroke(Color.web("0x000000", 1.0));
                }
                break;
            case 'X':
                circle.setStrokeWidth(3);
                circle.setStrokeType(StrokeType.INSIDE);
                circle.setStroke(Color.web("0x0059ff", 1.0));
                break;
            case '?':
                circle.setFill(Color.web("0x34d399", 1.0));
                circle.setStrokeWidth(0);
                break;
            default:
                break;
        }
    }

    /**
     * @brief Painting method executed everytime a player clicks on the board.
     * @pre <em>True</em>
     * @post The piece clicked is transformed into a pair.
     */
    private Pair<Integer, Integer> getClickedPos(MouseEvent mouseEvent) {
        Pair<Integer, Integer> pos = new Pair<Integer, Integer>(-1, -1);
        String piece = ((Circle) mouseEvent.getPickResult().getIntersectedNode()).getId();
        pos.first = Character.getNumericValue(piece.charAt(1)) - 1;
        pos.second = Character.getNumericValue(piece.charAt(3)) - 1;
        return pos;
    }

    /**
     * @brief Method executed everytime there is a change in the board.
     * @pre <em>True</em>
     * @post Return the circle which belongs to the position.
     */
    private Circle getCircle(Pair<Integer, Integer> pos) {
        try {
            Field field = this.getClass().getDeclaredField(String.format("f%sc%s", pos.first + 1, pos.second + 1));
            field.setAccessible(true);
            return (Circle) field.get(this);
        } catch (Exception e) {
            return new Circle();
        }
    }

    /**
     * @brief Task linked to an asynchronous timer to automatically perform bot placing trough runtimes threads.
     */
    class BotTask extends TimerTask {
        public void run() {
            Platform.runLater(() -> transform(null));
            timer.cancel();
        }
    }

    /**
     * @brief Method executed everytime there is a change in the Assisted mode radio button.
     * @pre <em>True</em>
     * @post Whether the assisted mode visual help is rendered onto the current board.
     */
    public void onChangeAssistedMode() {
        renderState();
    }
}
