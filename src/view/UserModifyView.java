/**
 * @file UserModifyView.java
 * @author Arnau pujantell
 * @brief User modify View controller specification.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.json.JSONObject;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import util.Pair;
import java.io.IOException;

/**
 * @class UserModifyView
 * @brief <p> This class represents the scene controller of modify function of a user.</p>
 * &nbsp; Done by Arnau Pujantell
 */
public class UserModifyView {

    /*CREATOR*/

    /**
     * @brief Class creator.
     * */
    public UserModifyView() {
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
     * @brief User modify button text.
     * */
    @FXML
    private Text modifyUser;
    /**
     * @brief User modify button.
     * */
    @FXML
    private Rectangle modifyUserButton;
    /**
     * @brief User modify button text.
     * */
    @FXML
    private Text deleteUser;
    /**
     * @brief User modify button.
     * */
    @FXML
    private Rectangle deleteUserButton;
    /**
     * @brief New User name text field.
     * */
    @FXML
    private TextField nusername;
    /**
     * @brief New User name text field.
     * */
    @FXML
    private PasswordField npassword;
    /**
     * @brief New User name text field.
     * */
    @FXML
    private PasswordField rpassword;
    /**
     * @brief Exception output message label.
     * */
    @FXML
    private Label modifyUserResult;
    /**
     * @brief User create confirm text button.
     * */
    @FXML
    private Text modifyUserConfirm;
    /**
     * @brief User create confirm button.
     * */
    @FXML
    private Rectangle modifyUserConfirmButton;
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
    public void initialize() {
        JSONObject user = ViewCtrl.domainCtrl.viewUser();
        currentUserName.setText(user.getString("name"));
        nusername.setText(user.getString("name"));
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
     * @brief Event method which is executed when the modifyUser button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to UserView.
     */
    public void modifyUser() throws IOException {
        ViewCtrl.changeScene("template/UserView.fxml");
    }

    /**
     * @brief Event method which is executed when the deleteUser button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to UserModifyView.
     */
    public void deleteUser() throws IOException {
        ViewCtrl.changeScene("template/UserDeleteView.fxml");
    }

    /**
     * @brief Event method which is executed when the modify button is clicked.
     * @pre <em>True</em>
     * @post If there is an exception, it's name is shown. If not, the credentials introduced modify the current User. Finally, scene changes to UserView.
     */
    public void modifyUserConfirm() throws IOException {
        Alert confirm = new Alert(AlertType.CONFIRMATION, "Your account will be modified. Are you sure?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            String newPassword = (!npassword.getText().isBlank() ? npassword.getText() : null);

            Pair<JSONObject, String> result = ViewCtrl.domainCtrl.modifyUser(nusername.getText(), newPassword, rpassword.getText());
            if (result.second != null) {
                switch (result.second) {
                    case "ERR_INVALID_NAME":
                        modifyUserResult.setText("Username can't be empty!");
                        break;
                    case "ERR_INVALID_PASSWORD":
                        modifyUserResult.setText("Password can't be empty!");
                        break;
                    case "ERR_BAD_CONFIRMATION":
                        modifyUserResult.setText("Confirmation password doesn't match!");
                        break;
                    case "ERR_INEXISTING_PLAYER":
                        modifyUserResult.setText("The player doesn't exist!");
                        break;
                    case "ERR_EXISTING_PLAYER":
                        modifyUserResult.setText("The username is already taken!");
                        break;
                    default:
                        modifyUserResult.setText("Something went wrong, try again!");
                        break;
                }
            }
            else {
                initialize();
                npassword.clear();
                rpassword.clear();
                modifyUserResult.setText("Success!");
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
