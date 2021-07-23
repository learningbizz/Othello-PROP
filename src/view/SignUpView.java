/**
 * @file SignUpView.java
 * @author Arnau pujantell
 * @brief SignUpView controller specification.
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
 * @class SignUpView
 * @brief <p> This class represents the scene controller of the SignUp.</p>
 * &nbsp; Done by Arnau Pujantell
 */
public class SignUpView {

    /*CREATOR*/

    /**
     * @brief Class creator.
     * */
    public SignUpView() {
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
     * @brief New user name text field.
     * */
    @FXML
    private TextField nusername;
    /**
     * @brief New password field.
     * */
    @FXML
    private PasswordField npassword;
    /**
     * @brief Repeat password field.
     * */
    @FXML
    private PasswordField rpassword;
    /**
     * @brief Exception output message label.
     * */
    @FXML
    private Label signUpResult;
    /**
     * @brief signUp button text.
     * */
    @FXML
    private Text signUp2;
    /**
     * @brief signUp button.
     * */
    @FXML
    private Rectangle signUpButton;

    /* METHODS*/

    /**
     * @brief Event method which is executed when the signUp button is clicked.
     * @pre <em>True</em>
     * @post If there is an exception, it's name is shown. If not, scene changes to LogInView.
     */
    public void signUp() throws IOException {
        Pair<JSONObject, String> result = ViewCtrl.domainCtrl.createUser(nusername.getText(), npassword.getText(),
                rpassword.getText());
        if (result.second != null) {
            switch (result.second) {
                case "ERR_INVALID_NAME":
                    signUpResult.setText("Username can't be empty!");
                    break;
                case "ERR_INVALID_PASSWORD":
                    signUpResult.setText("Password can't be empty!");
                    break;
                case "ERR_EXISTING_PLAYER":
                    signUpResult.setText("The username is already taken!");
                    break;
                case "ERR_BAD_CONFIRMATION":
                    signUpResult.setText("Confirmation password doesn't match!");
                    break;
                default:
                    signUpResult.setText("Something went wrong, try again!");
                    break;
            }
        } else {
            ViewCtrl.changeScene("template/LogInView.fxml");
        }
    }

    /**
     * @brief Event method which is executed when the logIn button is clicked.
     * @pre <em>True</em>
     * @post Scene changes to logInView.
     */
    public void logIn() throws IOException {
        ViewCtrl.changeScene("template/LogInView.fxml");
    }

}
