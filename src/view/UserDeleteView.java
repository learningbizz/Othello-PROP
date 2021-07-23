/**
 * @file UserDeleteView.java
 * @author Arnau pujantell
 * @brief User delete View controller specification.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * @class UserDeleteView
 * @brief <p> This class represents the scene controller of delete function of a user.</p>
 * &nbsp; Done by Arnau Pujantell
 */
public class UserDeleteView {

    /*CREATOR*/

    /**
     * @brief Class creator.
     * */
    public UserDeleteView() {
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
     * @brief New User password field.
     * */
    @FXML
    private PasswordField password;
    /**
     * @brief Exception output message label.
     * */
    @FXML
    private Label deleteUserResult;
    /**
     * @brief User delete confirm text button.
     * */
    @FXML
    private Text deleteUserConfirm;
    /**
     * @brief User delete confirm button.
     * */
    @FXML
    private Rectangle deleteUserConfirmButton;
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
     * @post Scene changes to UserModifyView.
     */
    public void modifyUser() throws IOException {
        ViewCtrl.changeScene("template/UserModifyView.fxml");
    }

    /**
     * @brief Event method which is executed when the deleteUser button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to UserModifyView.
     */
    public void deleteUser() throws IOException {
        ViewCtrl.changeScene("template/UserView.fxml");
    }

    /**
     * @brief Event method which is executed when the delete button is clicked.
     * @pre <em>True</em>
     * @post If there is an exception, it's name is shown. If not, the current user is deleted. Finally, scene changes to LogInView.
     */
    public void deleteUserConfirm() throws IOException {
        Alert confirm = new Alert(AlertType.CONFIRMATION, "Your account will be deleted. Are you sure?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            String result = ViewCtrl.domainCtrl.deleteUser(password.getText());
            if (result != null) {
                switch (result) {
                    case "ERR_INCORRECT_CREDENTIALS":
                        deleteUserResult.setText("Wrong password!");
                        break;
                    case "ERR_INEXISTING_PLAYER":
                        deleteUserResult.setText("The player doesn't exist!");
                        break;
                    default:
                        deleteUserResult.setText("Something went wrong, try again!");
                        break;
                }
            }
            else {
                ViewCtrl.changeScene("template/LogInView.fxml");
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
