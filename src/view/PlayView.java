/**
 * @file PlayView.java
 * @author Alex Rodriguez
 * @brief PlayView controller specification.
 */
package view;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import util.Pair;


/**
 * @class PlayView
 * @brief <p> This class represents the scene controller of the Play Game .</p>
 * By Alex Rodriguez
 */
public class PlayView {

    /*CREATOR*/

    /**
     * @brief Class creator.
     * */
    public PlayView() {
    }

    /* ATTRIBUTES*/

    /**
     * @brief Menu User tab.
     * */
    @FXML
    private Text user;
    /**
     * @brief Menu Bots tab.
     * */
    @FXML
    private Text bots;
    /**
     * @brief Menu Configuration tab.
     * */
    @FXML
    private Text config;
    /**
     * @brief Menu Games tab.
     * */
    @FXML
    private Text games;
    /**
     * @brief Menu Ranking tab.
     * */
    @FXML
    private Text ranking;
    /**
     * @brief Menu Play tab.
     * */
    @FXML
    private Text play;
    /**
     * @brief Player 1 label.
     * */
    @FXML
    private Label player1;
    /**
     * @brief Player 2 label.
     * */
    @FXML
    private Label player2;
    /**
     * @brief Configuration label.
     * */
    @FXML
    private Label configuration;
    /**
     * @brief Creator label.
     * */
    @FXML
    private Label creator;
    /**
     * @brief Created At label.
     * */
    @FXML
    private Label createdAt;
    /**
     * @brief State label.
     * */
    @FXML
    private Label state;
    /**
     * @brief Info label.
     * */
    @FXML
    private Label info;
    /**
     * @brief Exception message output.
     * */
    @FXML
    private Label playResult;
    /**
     * @brief Play game button text.
     * */
    @FXML
    private Text playGame;
    /**
     * @brief Play game button text.
     * */
    @FXML
    private Rectangle playGameButton;
    /**
     * @brief goToGame button label.
     * */
    @FXML
    private Label goToGame;
    /**
     * @brief goToGame button.
     * */
    @FXML
    private Rectangle goToGameButton;
    /**
     * @brief Current user name.
     * */
    @FXML
    private Label currentUserName;
    /**
     * @brief LogOut button.
     * */
    @FXML
    private Text logOut;
    /**
     * @brief Configuration choiceBox.
     * */
    @FXML
    private ChoiceBox gameChooser;

    /* METHODS*/

    public void initialize() {
        currentUserName.setText(ViewCtrl.domainCtrl.viewUser().getString("name"));
        Pair<ArrayList<String>, String> gameList = ViewCtrl.domainCtrl.listGames();
        for(String gameName : gameList.first) gameChooser.getItems().add(gameName);
    }

    /**
     * @brief Event method which is executed when the User tab is clicked.
     * @pre <em>True</em>
     * @post Scene changes to UserView.
     */
    public void user() throws IOException {
        ViewCtrl.changeScene("template/UserView.fxml");
    }

    /**
     * @brief Event method which is executed when the Bots tab is clicked.
     * @pre <em>True</em>
     * @post Scene changes to BotsView.
     */
    public void bots() throws IOException {
        ViewCtrl.changeScene("template/BotsView.fxml");
    }

    /**
     * @brief Event method which is executed when the Configuration tab is clicked.
     * @pre <em>True</em>
     * @post Scene changes to ConfigView.
     */
    public void config() throws IOException {
        ViewCtrl.changeScene("template/ConfigView.fxml");
    }

    /**
     * @brief Event method which is executed when the Games tab is clicked.
     * @pre <em>True</em>
     * @post Scene changes to GamesView.
     */
    public void games() throws IOException {
        ViewCtrl.changeScene("template/GamesView.fxml");
    }

    /**
     * @brief Event method which is executed when the Game chooser is clicked.
     * @pre <em>True</em>
     * @post Game information is shown.
     */
    public void onChangeGameChooser() throws IOException {
        String chosenGame = (String) gameChooser.getValue();
        if (chosenGame != null) {
            Pair<JSONObject, String> result = ViewCtrl.domainCtrl.getGame(chosenGame);
            if (result.second != null) {
                switch (result.second) {
                    case "ERR_NOT_PLAYER":
                        playResult.setText("You are not part of this game!");
                        break;
                    default:
                        playResult.setText("Something went wrong, try again!");
                        break;
                }
            }
            else {
                playResult.setText("");
                ViewCtrl.domainCtrl.selectGame(result.first.getString("name")); // Load onto memory the chosen game Board
                Pair<JSONObject, JSONObject> players = ViewCtrl.domainCtrl.viewPlayers();
                player1.setText((players.first != null ? players.first.getString("name") : "Unknown"));
                player2.setText((players.second != null ? players.second.getString("name") : "Unknown"));
                configuration.setText(result.first.getString("configuration_name"));
                Pair<JSONObject, String> userCreator = ViewCtrl.domainCtrl.getUser(UUID.fromString(result.first.getString("creator_id")));
                creator.setText((userCreator.first != null ? userCreator.first.getString("name") : "Unknown"));
                if (creator.getText().equals(currentUserName.getText()) && !player1.getText().equals(currentUserName.getText()) && !player2.getText().equals(currentUserName.getText())) {
                    goToGame.setText("SPECTATE");
                } else {
                    goToGame.setText("PLAY");
                }
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
                createdAt.setText(LocalDateTime.parse(result.first.getString("created_at")).format(dateFormat));
                state.setText(result.first.get("state").toString());
                if (result.first.get("state").toString().equals("FINISHED")) {
                    goToGame.setText("CONSULT");
                    String winner = null;
                    winner = result.first.optString("winner_id", null);
                    if (winner == null) {
                        info.setText("The game has ended in a draw.");
                    } else {
                        if (winner.equals(players.first.getString("id"))) {
                            info.setText(String.format("%s has won the game.", player1.getText()));
                        } else {
                            info.setText(String.format("%s has won the game.", player2.getText()));
                        }
                    }
                } else {
                    if (result.first.get("turn").toString().equals("PLAYER1")) {
                        info.setText(String.format("%s has the current turn.", player1.getText()));
                    } else {
                        info.setText(String.format("%s has the current turn.", player2.getText()));
                    }
                }
            }
        }
    }

    /**
     * @brief Event method which is executed when the Ranking tab is clicked.
     * @pre <em>True</em>
     * @post Scene changes to RankingView.
     */
    public void ranking() throws IOException {
        ViewCtrl.changeScene("template/RankingView.fxml");
    }

    /**
     * @brief Event method which is executed when the goToMenu button is clicked.
     * @pre <em>True</em>
     * @post The scene is changed to GameBoardView.
     */
    public void goToGame() throws IOException {
        String chosenGame = (String) gameChooser.getValue();
        if (chosenGame != null) {
            Pair<JSONObject, String> result = ViewCtrl.domainCtrl.play();
            if (result.second != null) {
                switch (result.second) {
                    case "ERR_FINISHED_GAME":
                        // playResult.setText("The game has already finished!");
                        playResult.setText("");
                        ViewCtrl.changeScene("template/GameBoardView.fxml");
                        break;
                    default:
                        playResult.setText("Something went wrong, try again!");
                        break;
                }
            }
            else {
                playResult.setText("");
                ViewCtrl.changeScene("template/GameBoardView.fxml");
            }
        }
    }

    /**
     * @brief Event method which is executed when the LogOut button is clicked.
     * @pre <em>True</em>
     * @post The current user is logged out and the scene is changed to LogInView.
     */
    public void logOut() throws IOException {
        Alert confirm = new Alert(AlertType.CONFIRMATION, "You are going to log out. Are you sure?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            ViewCtrl.domainCtrl.logout();
            ViewCtrl.changeScene("template/LogInView.fxml");
        }
    }
}
