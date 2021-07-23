/**
 * @file BotsModifyView.java
 * @author Arnau pujantell
 * @brief Bot modify View controller specification.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.json.JSONObject;
import util.Pair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * @class BotsModifyView
 * @brief <p> This class represents the scene controller of modify function of a bot.</p>
 * &nbsp; Done by Arnau Pujantell
 */
public class BotsModifyView {

    /*CREATOR*/

    /**
     * @brief Class creator.
     * */
    public BotsModifyView() {
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
     * @brief Bot create button text.
     * */
    @FXML
    private Text createBot;
    /**
     * @brief Bot create button.
     * */
    @FXML
    private Rectangle createBotButton;
    /**
     * @brief Bot modify button text.
     * */
    @FXML
    private Text modifyBot;
    /**
     * @brief Bot modify button.
     * */
    @FXML
    private Rectangle modifyBotButton;
    /**
     * @brief Bot consult button text.
     * */
    @FXML
    private Text consultBot;
    /**
     * @brief Bot consult button.
     * */
    @FXML
    private Rectangle consultBotButton;
    /**
     * @brief Bot choiceBox.
     * */
    @FXML
    private ChoiceBox botChooser;
    /**
     * @brief New Bot name text field.
     * */
    @FXML
    private TextField nbotname;
    /**
     * @brief Slider that controles the difficulty level.
     * */
    @FXML
    private Slider difficultyLevel;
    /**
     * @brief Bot difficulty label.
     * */
    @FXML
    private Label difficultyNumber;
    /**
     * @brief Exception output message label.
     * */
    @FXML
    private Label modifyBotResult;
    /**
     * @brief Bot modify confirm text button.
     * */
    @FXML
    private Text modifyBotConfirm;
    /**
     * @brief Bot modify confirm button.
     * */
    @FXML
    private Rectangle modifyBotConfirmButton;
    /**
     * @brief Bot delete image.
     * */
    @FXML
    private ImageView deleteBot;
    /**
     * @brief Bot delete button.
     * */
    @FXML
    private Circle deleteBotButton;
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
     * @brief Map of bots.
     * */
    private Map<String, UUID> botMap;

    /* METHODS*/

    /**
     * @brief Initialize method which is executed when the scene is shown.
     * @pre <em>True</em>
     * @post The current username is shown. All bot names are inserted in the Bot choiceBox and Bot Map is setted.
     */
    public void initialize() {
        currentUserName.setText(ViewCtrl.domainCtrl.viewUser().getString("name"));
        botMap = new HashMap<String, UUID>();
        ArrayList<Pair<String, UUID>> botList = ViewCtrl.domainCtrl.listBots();
        for(Pair<String, UUID> bot : botList) {
            botChooser.getItems().add(bot.first);
            botMap.put(bot.first, bot.second);
        }
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
     * @brief Event method which is executed when the Bot chooser is clicked.
     * @pre <em>True</em>
     * @post Bot information is shown.
     */
    public void onChangeBotChooser() throws IOException {
        String chosenBot = (String) botChooser.getValue();
        if (chosenBot != null) {
            Pair<JSONObject, String> bot = ViewCtrl.domainCtrl.getBot(botMap.get(chosenBot));
            if (bot.second == null) {
                nbotname.setText(bot.first.getString("name"));
                difficultyLevel.setValue((double) bot.first.getInt("difficulty"));
                showLevel();
            }
        }
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
     * @brief Event method which is executed when the createBot button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to BotsCreateView.
     */
    public void createBot() throws IOException {
        ViewCtrl.changeScene("template/BotsCreateView.fxml");
    }

    /**
     * @brief Event method which is executed when the modifyBot button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to BotsModifyView.
     */
    public void modifyBot() throws IOException {
        ViewCtrl.changeScene("template/BotsView.fxml");
    }

    /**
     * @brief Event method which is executed when the consultBot button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to BotsConsultView.
     */
    public void consultBot() throws IOException {
        ViewCtrl.changeScene("template/BotsConsultView.fxml");
    }

    /**
     * @brief Event method which is executed when the value of the difficulty slider is changed.
     * @pre <em>True</em>
     * @post The label shows the difficulty level as an Integer.
     */
    public void showLevel() {
        difficultyNumber.setText(String.valueOf((int) difficultyLevel.getValue()));
    }

    /**
     * @brief Event method which is executed when the modify button is clicked.
     * @pre <em>True</em>
     * @post If there is an exception, it's name is shown. If not, the credentials introduced modifies the selected Bot. Finally, scene changes to BotsView.
     */
    public void modifyBotConfirm() throws IOException {
        Alert confirm = new Alert(AlertType.CONFIRMATION, "This bot will be modified. Are you sure?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            String chosenBot = (String) botChooser.getValue();
            if (chosenBot != null) {
                Pair<JSONObject, String> result = ViewCtrl.domainCtrl.modifyBot(botMap.get(chosenBot), nbotname.getText(), (int) difficultyLevel.getValue());
                if (result.second != null) {
                    switch (result.second) {
                        case "ERR_INVALID_NAME":
                            modifyBotResult.setText("Bot name can't be empty!");
                            break;
                        case "ERR_INVALID_DIFFICULTY":
                            modifyBotResult.setText("This is an invalid difficulty!");
                            break;
                        case "ERR_EXISTING_PLAYER":
                            modifyBotResult.setText("The name is already taken!");
                            break;
                        case "ERR_INEXISTING_PLAYER":
                            modifyBotResult.setText("The player doesn't exist!");
                            break;
                        case "ERR_BOT_USED":
                            modifyBotResult.setText("This bot is already part of a game!");
                            break;
                        case "ERR_NOT_CREATOR":
                            modifyBotResult.setText("You are not the creator of this bot!");
                            break;
                        default:
                            modifyBotResult.setText("Something went wrong, try again!");
                            break;
                    }
                }
                else {
                    botChooser.getItems().clear();
                    initialize();
                    botChooser.getSelectionModel().select(nbotname.getText());
                    modifyBotResult.setText("Success!");
                }
            }
        }
    }

    /**
     * @brief Event method which is executed when the delete button is clicked.
     * @pre <em>True</em>
     * @post The current bot is deleted and the scene is changed to BotsView.
     */
    public void deleteBot() throws IOException {
        Alert confirm = new Alert(AlertType.CONFIRMATION, "This bot will be deleted. Are you sure?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            String chosenBot = (String) botChooser.getValue();
            if (chosenBot != null) {
                String result = ViewCtrl.domainCtrl.deleteBot(botMap.get(chosenBot));
                if (result != null) {
                    switch (result) {
                        case "ERR_INEXISTING_PLAYER":
                            modifyBotResult.setText("The player doesn't exist!");
                            break;
                        case "ERR_BOT_USED":
                            modifyBotResult.setText("This bot is already part of a game!");
                            break;
                        case "ERR_NOT_CREATOR":
                            modifyBotResult.setText("You are not the creator of this bot!");
                            break;
                        default:
                            modifyBotResult.setText("Something went wrong, try again!");
                            break;
                    }
                }
                else {
                    nbotname.clear();
                    botChooser.getItems().clear();
                    difficultyLevel.setValue(1);
                    initialize();
                    showLevel();
                    modifyBotResult.setText("Success!");
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
