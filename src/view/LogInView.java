/**
 * @file LogInView.java
 * @author Arnau pujantell
 * @brief LogInView controller specification.
 */
package view;

import java.io.IOException;
import org.json.JSONObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import util.Pair;

/**
 * @class LogInView
 * @brief <p> This class represents the scene controller of the LogIn.</p>
 * &nbsp; Done by Arnau Pujantell
 */
public class LogInView {

    /*CREATOR*/

    /**
     * @brief Class creator.
     * */
    public LogInView() {
    }

    /* ATTRIBUTES*/

    /**
     * @brief logIn view change button.
     * */
    @FXML
    private Text logIn;
    /**
     * @brief signUp view change button.
     * */
    @FXML
    private Text signUp;
    /**
     * @brief User name text field.
     * */
    @FXML
    private TextField username;
    /**
     * @brief User password field.
     * */
    @FXML
    private PasswordField password;
    /**
     * @brief Exception output message label.
     * */
    @FXML
    private Label logInResult;
    /**
     * @brief logIn button text.
     * */
    @FXML
    private Text signIn;
    /**
     * @brief logIn button.
     * */
    @FXML
    private Rectangle signInButton;

    /* METHODS*/

    /**
     * @brief Event method which is executed when the logIn button is clicked.
     * @pre <em>True</em>
     * @post If there is an exception, it's name is shown. If not, scene changes to BotsView.
     */
    public void signIn() throws IOException {
        Pair<JSONObject, String> result = ViewCtrl.domainCtrl.login(username.getText(), password.getText());
        if (result.second != null) {
            switch (result.second) {
                case "ERR_INVALID_NAME":
                    logInResult.setText("Username can't be empty!");
                    break;
                    case "ERR_INVALID_PASSWORD":
                    logInResult.setText("Password can't be empty!");
                    break;
                    case "ERR_INEXISTING_PLAYER":
                    logInResult.setText("The player does not exist!");
                    break;
                    case "ERR_INCORRECT_CREDENTIALS":
                    logInResult.setText("Wrong username or password!");
                    break;
                default:
                logInResult.setText("Something went wrong, try again!");
                    break;
            }
        } else {
            ViewCtrl.changeScene("template/UserView.fxml");
        }
    }

    /**
     * @brief Event method which is executed when the signUp button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to SignUpView.
     */
    public void signUp() throws IOException {
        ViewCtrl.changeScene("template/SignUpView.fxml");
    }
}
