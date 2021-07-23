/**
 * @file ConfigModifyView.java
 * @author Arnau pujantell
 * @brief Configuration Modify View controller specification.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.json.JSONObject;
import util.Pair;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * @class ConfigModifyView
 * @brief <p> This class represents the scene controller of modify function of a configuration.</p>
 * &nbsp; Done by Arnau Pujantell
 */
public class ConfigModifyView {

    /*CREATOR*/

    /**
     * @brief Class creator.
     * */
    public ConfigModifyView() {
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
     * @brief CanEatHorizontally selector.
     * */
    @FXML
    private RadioButton canEatHorizontally;
    /**
     * @brief CanEatVertically selector.
     * */
    @FXML
    private RadioButton canEatVertically;
    /**
     * @brief CanEatDiagonally selector.
     * */
    @FXML
    private RadioButton canEatDiagonally;
    /**
     * @brief Modify initial board button text.
     * */
    @FXML
    private Text modifyInitialBoard;
    /**
     * @brief Modify initial board button.
     * */
    @FXML
    private Rectangle modifyInitialBoardButton;
    /**
     * @brief Configuration modify confirm button text.
     * */
    @FXML
    private Text modifyConfigConfirm;
    /**
     * @brief Configuration modify confirm button.
     * */
    @FXML
    private Rectangle modifyConfigConfirmButton;
    /**
     * @brief Configuration delete button image.
     * */
    @FXML
    private ImageView deleteConfig;
    /**
     * @brief Configuration delete button.
     * */
    @FXML
    private Circle deleteConfigButton;
    /**
     * @brief Configuration choiceBox.
     * */
    @FXML
    private ChoiceBox configChooser;
    /**
     * @brief Exception output message label.
     * */
    @FXML
    private Label modifyConfigResult;
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
     * @post All The current username is shown. configuration names are inserted in the Configuration choiceBox.
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
            Pair<JSONObject, String> config = ViewCtrl.domainCtrl.getConfiguration(chosenConfig);
            if (config.second == null) {
                ViewCtrl.domainCtrl.modifyInitialBoard(config.first.getString("name")); // Load onto memory the chosen config Board
                canEatHorizontally.setSelected(config.first.getBoolean("can_eat_horizontally"));
                canEatVertically.setSelected(config.first.getBoolean("can_eat_vertically"));
                canEatDiagonally.setSelected(config.first.getBoolean("can_eat_diagonally"));
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
        ViewCtrl.changeScene("template/ConfigView.fxml");
    }

    /**
     * @brief Event method which is executed when the consultConfig button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to ConfigConsultView.
     */
    public void consultConfig() throws IOException {
        ViewCtrl.changeScene("template/ConfigConsultView.fxml");
    }

    /**
     * @brief Event method which is executed when the modifyInitialBoard button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to ModifyInitialBoardView.
     */
    public void modifyInitialBoard() throws IOException {
        String chosenConfig = (String) configChooser.getValue();
        if (chosenConfig != null) {
            ViewCtrl.newWindow("template/ModifyInitialBoardView.fxml");
        }
    }

    /**
     * @brief Event method which is executed when the modify button is clicked.
     * @pre <em>True</em>
     * @post If there is an exception, it's name is shown. If not, the new Configuration is modified.
     */
    public void modifyConfigConfirm() throws IOException {
        Alert confirm = new Alert(AlertType.CONFIRMATION, "This configuration will be modified. Are you sure?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            String chosenConfig = (String) configChooser.getValue();
            if (chosenConfig != null) {
                Pair<JSONObject, String> result = ViewCtrl.domainCtrl.modifyConfiguration(chosenConfig, canEatHorizontally.isSelected(), canEatVertically.isSelected(), canEatDiagonally.isSelected());
                if (result.second != null) {
                    switch (result.second) {
                        case "ERR_CONFIGURATION_USED":
                            modifyConfigResult.setText("This configuration has been already used in a game!");
                            break;
                        case "ERR_NOT_CREATOR":
                            modifyConfigResult.setText("You are not the creator of this configuration!");
                            break;
                        case "ERR_INEXISTING_CONFIGURATION":
                            modifyConfigResult.setText("This configuration doesn't exist!");
                            break;
                        case "ERR_INVALID_BOARD":
                            modifyConfigResult.setText("The initial board is invalid!");
                            break;
                        case "ERR_INVALID_RULES":
                            modifyConfigResult.setText("You must select at least one rule!");
                            break;
                        default:
                            modifyConfigResult.setText("Something went wrong, try again!");
                            break;
                    }
                }
                else {
                    configChooser.getItems().clear();
                    initialize();
                    configChooser.getSelectionModel().select(chosenConfig);
                    modifyConfigResult.setText("Success!");
                }
            }
        }
    }

    /**
     * @brief Event method which is executed when the delete button is clicked.
     * @pre <em>True</em>
     * @post The current configuration is deleted.
     */
    public void deleteConfig() throws IOException {
        Alert confirm = new Alert(AlertType.CONFIRMATION, "This configuration will be deleted. Are you sure?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            String chosenConfig = (String) configChooser.getValue();
            if (chosenConfig != null) {
                String result = ViewCtrl.domainCtrl.deleteConfiguration(chosenConfig);
                if (result != null) {
                    switch (result) {
                        case "ERR_INEXISTING_CONFIGURATION":
                            modifyConfigResult.setText("This configuration doesn't exist!");
                            break;
                        case "ERR_NOT_CREATOR":
                            modifyConfigResult.setText("You are not the creator of this configuration!");
                            break;
                        case "ERR_CONFIGURATION_USED":
                            modifyConfigResult.setText("This configuration has been already used in a game!");
                            break;
                        default:
                            modifyConfigResult.setText("Something went wrong, try again!");
                            break;
                    }
                }
                else {
                    configChooser.getItems().clear();
                    canEatHorizontally.setSelected(false);
                    canEatVertically.setSelected(false);
                    canEatDiagonally.setSelected(false);
                    initialize();
                    modifyConfigResult.setText("Success!");
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
