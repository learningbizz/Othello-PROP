/**
 * @file BotsCreateView.java
 * @author Arnau pujantell
 * @brief Bot create View controller specification.
 */

package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import util.Pair;
import org.json.JSONObject;
import java.io.IOException;

/**
 * @class BotsCreateView
 * @brief <p> This class represents the scene controller of the create function of a bot.</p>
 * &nbsp; Done by Arnau Pujantell
 */
public class BotsCreateView {

    /*CREATOR*/

    /**
     * @brief Class creator.
     * */
    public BotsCreateView() {
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
    private Label createBotResult;
    /**
     * @brief Bot create confirm text button.
     * */
    @FXML
    private Text createBotConfirm;
    /**
     * @brief Bot create confirm button.
     * */
    @FXML
    private Rectangle createBotConfirmButton;
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

    /* METHODS*/
    
    /**
     * @brief Initialize method which is executed when the scene is shown.
     * @pre <em>True</em>
     * @post The current username is shown.
     */
    public void initialize() throws Exception {
        currentUserName.setText(ViewCtrl.domainCtrl.viewUser().getString("name"));
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
        ViewCtrl.changeScene("template/BotsView.fxml");
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
     * @brief Event method which is executed when the create button is clicked.
     * @pre <em>True</em>
     * @post If there is an exception, it's name is shown. If not, the credentials introduced create a new Bot. Finally, scene changes to BotsView.
     */
    public void createBotConfirm() throws IOException {
        Pair<JSONObject, String> result = ViewCtrl.domainCtrl.createBot(nbotname.getText(), (int) difficultyLevel.getValue());
        if (result.second != null) {
            switch (result.second) {
                case "ERR_INVALID_NAME":
                    createBotResult.setText("Bot name can't be empty!");
                    break;
                case "ERR_INVALID_DIFFICULTY":
                    createBotResult.setText("This is an invalid difficulty!");
                    break;
                case "ERR_EXISTING_PLAYER":
                    createBotResult.setText("The name is already taken!");
                    break;
                default:
                    createBotResult.setText("Something went wrong, try again!");
                    break;
            }
        }
        else {
            nbotname.clear();
            difficultyLevel.setValue(1);
            showLevel();
            createBotResult.setText("Success!");
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
