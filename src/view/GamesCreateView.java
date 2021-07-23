/**
 * @file GamesCreateView.java
 * @author Arnau pujantell
 * @brief Game Create View controller specification.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.json.JSONObject;
import util.Pair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

/**
 * @class GamesCreateView
 * @brief <p> This class represents the scene controller of creation function of a game.</p>
 * &nbsp; Done by Arnau Pujantell
 */
public class GamesCreateView {

    /*CREATOR*/

    /**
     * @brief Class creator.
     * */
    public GamesCreateView() {
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
     * @brief Game create button text.
     * */
    @FXML
    private Text createGame;
    /**
     * @brief Game create button.
     * */
    @FXML
    private Rectangle createGameButton;
    /**
     * @brief Configuration choiceBox.
     * */
    @FXML
    private ChoiceBox configChooser;
    /**
     * @brief Player 1 user selector.
     * */
    @FXML
    private RadioButton pl1User;
    /**
     * @brief Player 1 bot selector.
     * */
    @FXML
    private RadioButton pl1Bot;
    /**
     * @brief Configuration choiceBox.
     * */
    @FXML
    private ChoiceBox userChooser1;
    /**
     * @brief Configuration choiceBox.
     * */
    @FXML
    private ChoiceBox botChooser1;
    /**
     * @brief Player 2 user selector.
     * */
    @FXML
    private RadioButton pl2User;
    /**
     * @brief Player 2 bot selector.
     * */
    @FXML
    private RadioButton pl2Bot;
    /**
     * @brief Configuration choiceBox.
     * */
    @FXML
    private ChoiceBox userChooser2;
    /**
     * @brief Configuration choiceBox.
     * */
    @FXML
    private ChoiceBox botChooser2;
    /**
     * @brief Game create confirm button text.
     * */
    @FXML
    private Text createGameConfirm;
    /**
     * @brief Game create confirm button.
     * */
    @FXML
    private Rectangle createGameConfirmButton;
    /**
     * @brief Exception output message label.
     * */
    @FXML
    private Label createGameResult;
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
     * Map of users.
     * */
    private Map<String, UUID> userMap;
    /**
     * Map of bots.
     * */
    private Map<String, UUID> botMap;

    /* METHODS*/

    /**
     * @brief Initialize method which is executed when the scene is shown.
     * @pre <em>True</em>
     * @post The current username is shown. All configuration names are inserted in the Configuration choiceBox.
     */
    public void initialize() {
        currentUserName.setText(ViewCtrl.domainCtrl.viewUser().getString("name"));
        userMap = new HashMap<String, UUID>();
        botMap = new HashMap<String, UUID>();

        ArrayList<Pair<String, UUID>> userList = ViewCtrl.domainCtrl.listUsers();
        for(Pair<String, UUID> user : userList) {
            userChooser1.getItems().add(user.first);
            userChooser2.getItems().add(user.first);
            userMap.put(user.first, user.second);
        }

        ArrayList<Pair<String, UUID>> botList = ViewCtrl.domainCtrl.listBots();
        for(Pair<String, UUID> bot : botList) {
            botChooser1.getItems().add(bot.first);
            botChooser2.getItems().add(bot.first);
            botMap.put(bot.first, bot.second);
        }
  
        ArrayList<String> configList = ViewCtrl.domainCtrl.listConfigurations().first;
        for(String configName : configList) configChooser.getItems().add(configName);

        userChooser1();
        botChooser2();
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
     * @brief Event method which is executed when the Ranking tab is clicked.
     * @pre <em>True</em>
     * @post Scene changes to RankingView.
     */
    public void ranking() throws IOException {
        ViewCtrl.changeScene("template/RankingView.fxml");
    }

    /**
     * @brief Event method which is executed when the Play tab is clicked.
     * @pre <em>True</em>
     * @post Scene changes to PlayView.
     */
    public void play() throws IOException {
        ViewCtrl.changeScene("template/PlayView.fxml");
    }

    /**
     * @brief Event method which is executed when the createGame button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to GameCreateView.
     */
    public void createGame() throws IOException {
        ViewCtrl.changeScene("template/GamesView.fxml");
    }

    /**
     * @brief Method which is executed when Player1User RadioButton is selected.
     * @pre <em>True</em>
     * @post All users are inserted in userChooser1.
     */
    public void userChooser1() {
        userChooser1.setDisable(false);
        botChooser1.setDisable(true);
    }

    /**
     * @brief Method which is executed when Player2User RadioButton is selected.
     * @pre <em>True</em>
     * @post All users are inserted in userChooser2.
     */
    public void userChooser2() {
        userChooser2.setDisable(false);
        botChooser2.setDisable(true);
    }

    /**
     * @brief Method which is executed when Player1Bot RadioButton is selected.
     * @pre <em>True</em>
     * @post All users are inserted in botChooser1.
     */
    public void botChooser1() {
        userChooser1.setDisable(true);
        botChooser1.setDisable(false);
    }

    /**
     * @brief Method which is executed when Player1Bot RadioButton is selected.
     * @pre <em>True</em>
     * @post All users are inserted in botChooser2.
     */
    public void botChooser2() {
        userChooser2.setDisable(true);
        botChooser2.setDisable(false);
    }

    /**
     * @brief Event method which is executed when the create button is clicked.
     * @pre <em>True</em>
     * @post If there is an exception, it's name is shown. If not, the new Game is created.
     */
    public void createGameConfirm() throws IOException {
        String chosenConfig = (String) configChooser.getValue();
        String chosenUser1 = (String) userChooser1.getValue();
        String chosenBot1 = (String) botChooser1.getValue();
        String chosenUser2 = (String) userChooser2.getValue();
        String chosenBot2 = (String) botChooser2.getValue();

        if (chosenConfig != null) {    
            UUID player1ID = null;
            UUID player2ID = null;

            if(pl1User.isSelected() && chosenUser1 != null) player1ID = userMap.get(chosenUser1);
            if(pl1Bot.isSelected() && chosenBot1 != null) player1ID = botMap.get(chosenBot1);
            if(pl2User.isSelected() && chosenUser2 != null) player2ID = userMap.get(chosenUser2);
            if(pl2Bot.isSelected() && chosenBot2 != null) player2ID = botMap.get(chosenBot2);

            if (player1ID != null && player2ID != null) {
                Pair<JSONObject, String> result = ViewCtrl.domainCtrl.createGame(player1ID, player2ID, chosenConfig);
                if (result.second != null) {
                    switch (result.second) {
                        case "ERR_INVALID_PLAYERS":
                            createGameResult.setText("The player selection is invalid!");
                            break;
                        case "ERR_INVALID_CONFIGURATION":
                            createGameResult.setText("The selected configuration is invalid!");
                            break;
                        case "ERR_INVALID_BOARD":
                            createGameResult.setText("The playing board is invalid!");
                            break;
                        default:
                            createGameResult.setText("Something went wrong, try again!");
                            break;
                    }
                }
                else {
                    userChooser1.getSelectionModel().select(null);
                    botChooser1.getSelectionModel().select(null);
                    userChooser2.getSelectionModel().select(null);
                    botChooser2.getSelectionModel().select(null);
                    createGameResult.setText("Success!");
                }
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
