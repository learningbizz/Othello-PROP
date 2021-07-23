/**
 * @file ConfigConsultView.java
 * @author Arnau pujantell
 * @brief Configuration Consult View controller specification.
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

/**
 * @class ConfigConsultView
 * @brief <p> This class represents the scene controller of consult function of a configuration.</p>
 * &nbsp; Done by Arnau Pujantell
 */
public class ConfigConsultView {

    /*CREATOR*/

    /**
     * @brief Class creator.
     * */
    public ConfigConsultView() {
    }

    /*ATTRIBUTES*/
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
     * @brief Configuration create button text.
     * */
    @FXML
    private Text createConfig;
    /**
     * @brief Configuration create button.
     * */
    @FXML
    private Rectangle createConfigButton;
    /**
     * @brief Configuration modify button text.
     * */
    @FXML
    private Text modifyConfig;
    /**
     * @brief Configuration modify button.
     * */
    @FXML
    private Rectangle modifyConfigButton;
    /**
     * @brief Configuration consult button text.
     * */
    @FXML
    private Text consultConfig;
    /**
     * @brief Configuration consult button.
     * */
    @FXML
    private Rectangle consultConfigButton;
    /**
     * @brief Configuration consult confirm text button.
     * */
    @FXML
    private Text consultConfigConfirm;
    /**
     * @brief Configuration consult confirm button.
     * */
    @FXML
    private Rectangle consultConfigConfirmButton;
    /**
     * @brief Configuration choiceBox.
     * */
    @FXML
    private ChoiceBox configChooser;
    /**
     * @brief Configuration name label.
     * */
    @FXML
    private Label name;
    /**
     * @brief Configuration CanEatHorizontally label.
     * */
    @FXML
    private Label ceh;
    /**
     * @brief Configuration CanEatVertically label.
     * */
    @FXML
    private Label cev;
    /**
     * @brief Configuration CanEatDiagonally label.
     * */
    @FXML
    private Label ced;
    /**
     * @brief Initial board check text button.
     * */
    @FXML
    private Text checkInitialBoard;
    /**
     * @brief Initial board check button.
     * */
    @FXML
    private Rectangle checkInitialBoardButton;
    /**
     * @brief Creator name label.
     * */
    @FXML
    private Label creator;
    /**
     * @brief Exception output message label.
     * */
    @FXML
    private Label consultConfigResult;
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
     * @post The current username is shown. All configuration names are inserted in the Configuration choiceBox.
     */
    public void initialize() {
        currentUserName.setText(ViewCtrl.domainCtrl.viewUser().getString("name"));
        ArrayList<String> configList = ViewCtrl.domainCtrl.listConfigurations().first;
        for(String configName : configList) configChooser.getItems().add(configName);
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
     * @brief Event method which is executed when the createConfig button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to ConfigCreateView.
     */
    public void createConfig() throws IOException {
        ViewCtrl.changeScene("template/ConfigCreateView.fxml");
    }

    /**
     * @brief Event method which is executed when the modifyConfig button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to ConfigModifyView.
     */
    public void modifyConfig() throws IOException {
        ViewCtrl.changeScene("template/ConfigModifyView.fxml");
    }

    /**
     * @brief Event method which is executed when the consultConfig button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to ConfigConsultView.
     */
    public void consultConfig() throws IOException {
        ViewCtrl.changeScene("template/ConfigView.fxml");
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
     * @brief Event method which is executed when the Configuration chooser is clicked.
     * @pre <em>True</em>
     * @post Configuration information is shown.
     */
    public void onChangeConfigChooser() throws IOException {
        String chosenConfig = (String) configChooser.getValue();
        if (chosenConfig != null) {
            Pair<JSONObject, String> result = ViewCtrl.domainCtrl.getConfiguration(chosenConfig);
            if (result.second != null) {
                switch (result.second) {
                    case "ERR_INEXISTING_CONFIGURATION":
                        consultConfigResult.setText("The configuration doesn't exist!");
                        break;
                    default:
                        consultConfigResult.setText("Something went wrong, try again!");
                        break;
                }
            }
            else {
                consultConfigResult.setText("");
                ViewCtrl.domainCtrl.modifyInitialBoard(result.first.getString("name")); // Load onto memory the chosen config Board
                name.setText(result.first.getString("name"));
                ceh.setText((result.first.getBoolean("can_eat_horizontally") ? "Can Eat Horizontally" : ""));
                cev.setText((result.first.getBoolean("can_eat_vertically") ? "Can Eat Vertically" : ""));
                ced.setText((result.first.getBoolean("can_eat_diagonally") ? "Can Eat Diagonally" : ""));
                Pair<JSONObject, String> user = ViewCtrl.domainCtrl.getUser(UUID.fromString(result.first.getString("creator_id")));
                creator.setText((user.first != null ? user.first.getString("name") : "Unknown"));
            }
        }
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
     * @brief Event method which is executed when the checkInitialBoard button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to ConsultInitialBoardView.
     */
    public void checkInitialBoard() throws IOException {
        String chosenConfig = (String) configChooser.getValue();
        if (chosenConfig != null) {
            ViewCtrl.newWindow("template/ConsultInitialBoardView.fxml");
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
