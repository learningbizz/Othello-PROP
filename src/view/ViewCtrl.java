/**
 * @file ViewCtrl.java
 * @author Arnau Pujantell
 * @brief ViewCtrl class specification.
 */
package view;

import domain.DomainCtrl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.io.IOException;

/**
 * @class ViewCtrl
 * @brief <p> This class represents tha main class controller.</p>
 * By Arnau Pujantell
 */
public class ViewCtrl extends Application {
    /* ATTRIBUTES */

    /**
    * @brief Domain Controller.
    */
    public static DomainCtrl domainCtrl;

    /**
    * @brief Main stage.
    */
    private static Stage stage;

    /* CONSTRUCTORS */

    /**
     * @brief Class creator.
     * */
    public ViewCtrl() {
    }

    /* METHODS */

    /**
     * @brief Event method which is executed when the program is executed
     * @pre <em>True</em>
     * @post All stage parameters are set and the LogInView scene is shown.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewCtrl.stage = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("template/LogInView.fxml"));
        primaryStage.setTitle("OTHELLO");
        primaryStage.setScene(new Scene(root, 1464, 824));
        primaryStage.show();
    }

    /**
     * @brief Change scene. Event method which is executed when an fxml is recieved.
     * @pre <em>True</em>
     * @post The scene is changed.
     */
    public static void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(ViewCtrl.class.getResource(fxml));
        stage.getScene().setRoot(pane);
    }

    /**
     * @brief Create a new window and hide the previous one. Event method which is executed when an fxml is recieved.
     * @pre <em>True</em>
     * @post A new window is created and the previous one is hidden.
     */
    public static void newWindow(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(ViewCtrl.class.getResource(fxml));
        Stage windowStage = new Stage();
        windowStage.setScene(new Scene(pane, 1464, 824));
        windowStage.setTitle("OTHELLO");
        windowStage.setResizable(false);
        windowStage.initModality(Modality.APPLICATION_MODAL);
        stage.hide();
        windowStage.showAndWait();
        stage.show();
    }

    /**
     * @brief Main method.
     */
    public static void main(String[] args) {
        ViewCtrl.domainCtrl = new DomainCtrl();
        ViewCtrl.launch(args);
    }
}
