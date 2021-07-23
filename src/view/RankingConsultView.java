/**
 * @file RankingConsultView.java
 * @author Alex Rodriguez
 * @brief Ranking Consult View controller specification.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import util.Pair;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @class RankingConsultView
 * @brief <p> This class represents the scene controller of consult function of a ranking.</p>
 * By Alex Rodriguez
 */
public class RankingConsultView {

    /*CREATOR*/

    /**
     * @brief Class creator.
     * */
    public RankingConsultView() {
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
    private Text consultRankings;
    /**
     * @brief Ranking consult button.
     * */
    @FXML
    private Rectangle consultRankingsButton;
    /**
     * @brief Ranking consult confirm text button.
     * */
    @FXML
    private Text consultRankingsConfirm;
    /**
     * @brief Ranking consult confirm button.
     * */
    @FXML
    private Rectangle consultRankingsConfirmButton;
    /**
     * @brief Records consult button text.
     * */
    @FXML
    private Text consultRecords;
    /**
     * @brief Records consult button.
     * */
    @FXML
    private Rectangle consultRecordsButton;
    /**
     * @brief Ranking choiceBox.
     * */
    @FXML
    private ChoiceBox rankingChooser;
    /**
     * @brief Ranking information label.
     * */
    @FXML
    private Label rankingInfo;
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
    /**
     * @brief Ranking table.
     * */
    @FXML
    private TableView table;
    /**
     * @brief Player column.
     * */
    @FXML
    private TableColumn playerColumn;
    /**
     * @brief Value column.
     * */
    @FXML
    private TableColumn valueColumn;

    /* METHODS*/

    /**
     * @brief Initialize method which is executed when the scene is shown.
     * @pre <em>True</em>
     * @post The current username is shown. All ranking names are inserted in the Ranking choiceBox.
     */
    public void initialize() {
        currentUserName.setText(ViewCtrl.domainCtrl.viewUser().getString("name"));
        ArrayList<String> rankingList = ViewCtrl.domainCtrl.listRankings();
        for(String rankingName : rankingList) rankingChooser.getItems().add(rankingName);
        playerColumn.setCellValueFactory(new PropertyValueFactory<>("first"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("second"));
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
     * @brief Event method which is executed when the Ranking chooser is clicked.
     * @pre <em>True</em>
     * @post Ranking information is shown.
     */
    public void onChangeRankingChooser() throws IOException {
        String chosenRanking = (String) rankingChooser.getValue();
        if (rankingChooser != null) {
            table.getItems().clear();
            JSONObject ranking = ViewCtrl.domainCtrl.getRanking(chosenRanking);
            JSONArray entries = ranking.getJSONArray("entries");
            for (int i = 0; i < entries.length(); ++i) {
                JSONObject entry = entries.getJSONObject(i);
                Pair<JSONObject, String> player = ViewCtrl.domainCtrl.getPlayer(UUID.fromString(entry.getString("player_id")));
                Pair<String, Integer> pairEntry = new Pair<String, Integer>(player.first.getString("name"), entry.getInt("value"));
                if (player.first.getString("type") == "BOT")
                    pairEntry.first = pairEntry.first + " (bot)";
                if (player.first.getBoolean("is_deleted"))
                    pairEntry.first = pairEntry.first + " (deleted)";
                table.getItems().add(pairEntry);
            }
        }
    }

    /**
     * @brief Event method which is executed when the Ranking consult button is clicked.
     * @pre <em>True</em>
     * @post The current scene is changed to RankingConsultView.
     */
    public void consultRankings() throws IOException {        
        ViewCtrl.changeScene("template/RankingView.fxml");
    }

    /**
     * @brief Event method which is executed when the Record consult button is clicked.
     * @pre <em>True</em>
     * @post The current scene is changed to RecordConsultView.
     */
    public void consultRecords() throws IOException {
        ViewCtrl.changeScene("template/RecordConsultView.fxml");
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
