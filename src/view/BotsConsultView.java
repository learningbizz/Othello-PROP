/**
 * @file BotsConsultView.java
 * @author Arnau pujantell
 * @brief Bot consult View controller specification.
 */

package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
 * @class BotsConsultView
 * @brief <p> This class represents the scene controller of the consult function of a bot.</p>
 * &nbsp; Done by Arnau Pujantell
 */
public class BotsConsultView {

    /*CREATOR*/
    /**
     * @brief Class creator.
     * */
    public BotsConsultView() {
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
     * @brief Bot name label.
     * */
    @FXML
    private Label name;
    /**
     * @brief Bot difficulty label.
     * */
    @FXML
    private Label difficultyNumber;
    /**
     * @brief Creator name label.
     * */
    @FXML
    private Label consultConfigResult;
    /**
     * @brief Exception output message label.
     * */
    @FXML
    private Label consultBotResult;
    /**
     * @brief Current user name.
     * */
    @FXML
    private Label currentUserName;
    /**
     * @brief Bot creator.
     * */
    @FXML
    private Label creator;
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
     * @post The current username is shown. All bot names are inserted in the Bot choiceBox and the bot map is setted.
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
     * @brief Event method which is executed when the Bot Chooser is clicked.
     * @pre <em>True</em>
     * @post The Bot information is shown.
     */
    public void onChangeBotChooser() throws IOException {
        String chosenBot = (String) botChooser.getValue();
        if (chosenBot != null) {
            Pair<JSONObject, String> result = ViewCtrl.domainCtrl.getBot(botMap.get(chosenBot));
            if (result.second != null) {
                switch (result.second) {
                    case "ERR_INEXISTING_PLAYER":
                        consultBotResult.setText("The bot doesn't exist!");
                        break;
                    default:
                        consultBotResult.setText("Something went wrong, try again!");
                        break;
                }
            }
            else {
                consultBotResult.setText("");
                name.setText(result.first.getString("name"));
                difficultyNumber.setText(Integer.toString(result.first.getInt("difficulty")));
                Pair<JSONObject, String> user = ViewCtrl.domainCtrl.getUser(UUID.fromString(result.first.getString("creator_id")));
                creator.setText((user.first != null ? user.first.getString("name") : "Unknown"));
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
        ViewCtrl.changeScene("template/BotsModifyView.fxml");
    }

    /**
     * @brief Event method which is executed when the consultBot button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to BotsConsultView.
     */
    public void consultBot() throws IOException {
        ViewCtrl.changeScene("template/BotsView.fxml");
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
