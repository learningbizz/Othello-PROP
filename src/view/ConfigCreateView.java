/**
 * @file ConfigCreateView.java
 * @author Arnau pujantell
 * @brief Configuration Create View controller specification.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.json.JSONObject;
import util.Pair;
import java.io.IOException;

/**
 * @class ConfigCreateView
 * @brief <p> This class represents the scene controller of creation function of a configuration.</p>
 * &nbsp; Done by Arnau Pujantell
 */
public class ConfigCreateView {

    /*CREATOR*/

    /**
     * @brief Class creator.
     * */
    public ConfigCreateView() {
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
     * @brief Initial board creation button text.
     * */
    @FXML
    private Text createInitialBoard;
    /**
     * @brief Initial board creation button.
     * */
    @FXML
    private Rectangle createInitialBoardButton;
    /**
     * @brief New Configuration name text field.
     * */
    @FXML
    private TextField nconfname;
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
     * @brief Exception output message label.
     * */
    @FXML
    private Label createConfigResult;
    /**
     * @brief Configuration create confirm button text.
     * */
    @FXML
    private Text createConfigConfirm;
    /**
     * @brief Configuration create confirm button.
     * */
    @FXML
    private Rectangle createConfigConfirmButton;
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
        ViewCtrl.domainCtrl.createInitialBoard();
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
        ViewCtrl.changeScene("template/ConfigView.fxml");
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
        ViewCtrl.changeScene("template/ConfigConsultView.fxml");
    }

    /**
     * @brief Event method which is executed when the createInitialBoard button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to InitialBoardView.
     */
    public void createInitialBoard() throws IOException {
        ViewCtrl.newWindow("template/InitialBoardView.fxml");
    }

    /**
     * @brief Event method which is executed when the create button is clicked.
     * @pre <em>True</em>
     * @post If there is an exception, it's name is shown. If not, the new Configuration is created.
     */
    public void createConfigConfirm() throws IOException {        
        Pair<JSONObject, String> result = ViewCtrl.domainCtrl.createConfiguration(nconfname.getText(), canEatHorizontally.isSelected(), canEatVertically.isSelected(), canEatDiagonally.isSelected());
        if (result.second != null) {
            switch (result.second) {
                case "ERR_INVALID_NAME":
                    createConfigResult.setText("Configuration name can't be empty!");
                    break;
                case "ERR_EXISTING_CONFIGURATION":
                    createConfigResult.setText("The configuration name is already taken!");
                    break;
                case "ERR_INVALID_BOARD":
                    createConfigResult.setText("The initial board is invalid!");
                    break;
                case "ERR_INVALID_RULES":
                    createConfigResult.setText("You must select at least one rule!");
                    break;
                default:
                    createConfigResult.setText("Something went wrong, try again!");
                    break;
            }
        }
        else {
            nconfname.clear();
            canEatHorizontally.setSelected(false);
            canEatVertically.setSelected(false);
            canEatDiagonally.setSelected(false);
            initialize();
            createConfigResult.setText("Success!");
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
