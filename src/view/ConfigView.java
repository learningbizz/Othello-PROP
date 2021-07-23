/**
 * @file ConfigView.java
 * @author Arnau pujantell
 * @brief ConfigView controller specification.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.io.IOException;

/**
 * @class ConfigView
 * @brief <p> This class represents the scene controller of the Configuration Menu .</p>
 * &nbsp; Done by Arnau Pujantell
 */
public class ConfigView {

    /*CREATOR*/

    /**
     * @brief Class creator.
     * */
    public ConfigView() {
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
     * @brief BConfiguration consult button text.
     * */
    @FXML
    private Text consultConfig;
    /**
     * @brief Configuration consult button.
     * */
    @FXML
    private Rectangle consultConfigButton;
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
        ViewCtrl.changeScene("template/ConfigConsultView.fxml");
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
