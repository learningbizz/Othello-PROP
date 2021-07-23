/**
 * @file UserView.java
 * @author Arnau pujantell
 * @brief UserView controller specification.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.io.IOException;

/**
 * @class UserView
 * @brief <p> This class represents the scene controller of the User Menu .</p>
 * &nbsp; Done by Arnau Pujantell
 */
public class UserView {

    /*CREATOR*/

    /**
     * @brief Class creator.
     * */
    public UserView() {
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
     * @brief User name label.
     * */
    @FXML
    private Label currentUserName;
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
     * @brief User delete button text.
     * */
    @FXML
    private Text deleteUser;
    /**
     * @brief User delete button.
     * */
    @FXML
    private Rectangle deleteUserButton;
    /**
     * @brief LogOut button.
     * */
    @FXML
    private Text logOut;

    /* METHODS*/

    /**
     * @brief Initialize method which is executed when the scene is shown.
     * @pre <em>True</em>
     * @post All user names are inserted in the User choiceBox.
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
     * @post Scene changes to UserDeleteView.
     */
    public void deleteUser() throws IOException {
        ViewCtrl.changeScene("template/UserDeleteView.fxml");
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
