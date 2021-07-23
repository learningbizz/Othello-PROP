/**
 * @file RecordConsultView.java
 * @author Alex Rodriguez
 * @brief Record Consult View controller specification.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import util.Pair;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;

/**
 * @class RecordConsultView
 * @brief <p> This class represents the scene controller of consult function of a record.</p>
 * By Alex Rodriguez
 */
public class RecordConsultView {

    /*CREATOR*/

    /**
     * @brief Class creator.
     * */
    public RecordConsultView() {
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
     * @brief Ranking consult button text.
     * */
    @FXML
    private Text consultRanking;
    /**
     * @brief Ranking consult button.
     * */
    @FXML
    private Rectangle consultRankingButton;
    /**
     * @brief Records consult button text.
     * */
    @FXML
    private Text consultRecord;
    /**
     * @brief Records consult button.
     * */
    @FXML
    private Rectangle consultRecordButton;
    /**
     * @brief Current user name.
     * */
    @FXML
    private Label currentUserName;
    /**
     * @brief Record table.
     * */
    @FXML
    private TableView table;
    /**
     * @brief Ranking column.
     * */
    @FXML
    private TableColumn rankingColumn;
    /**
     * @brief Value column.
     * */
    @FXML
    private TableColumn valueColumn;

    /*METHODS*/

    /**
     * @brief Initialize method which is executed when the scene is shown.
     * @pre <em>True</em>
     * @post The current username is shown. The columns are setted and the records are shown.
     */
    public void initialize() throws Exception {
        currentUserName.setText(ViewCtrl.domainCtrl.viewUser().getString("name"));
        ArrayList<Pair<String, JSONObject>> recordList = ViewCtrl.domainCtrl.listRecords();
        rankingColumn.setCellValueFactory(new PropertyValueFactory<>("first"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("second"));
        for (Pair<String, JSONObject> record : recordList) {
            Pair<String, Integer> pairRecord = new Pair<String, Integer>(record.first, record.second.getInt("value"));
            table.getItems().add(pairRecord);
        }
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
     * @brief Event method which is executed when the Play tab is clicked.
     * @pre <em>True</em>
     * @post Scene changes to PlayView.
     */
    public void play() throws IOException {
        ViewCtrl.changeScene("template/PlayView.fxml");
    }

    /**
     * @brief Event method which is executed when the Ranking consult button is clicked.
     * @pre <em>True</em>
     * @post The current scene is changed to RankingConsultView.
     */
    public void consultRankings() throws IOException {
        ViewCtrl.changeScene("template/RankingConsultView.fxml");
    }

    /**
     * @brief Event method which is executed when the Record consult button is clicked.
     * @pre <em>True</em>
     * @post The current scene is changed to RecordConsultView.
     */
    public void consultRecords() throws IOException {
        ViewCtrl.changeScene("template/RankingView.fxml");
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
