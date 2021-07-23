/**
 * @file ModifyInitialBoardView.java
 * @author Alex Rodriguez
 * @brief ModifyInitialBoardView controller specification.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.json.JSONObject;
import util.Pair;
import java.io.IOException;
import java.lang.reflect.Field;
import javafx.stage.Stage;


/**
 * @class ModifyInitialBoardView
 * @brief <p> This class represents the scene controller of the Initial Board.</p>
 * By Alex Rodriguez
 */
public class ModifyInitialBoardView {

    /*CREATOR*/
    /**
     * @brief Class creator.
     * */
    public ModifyInitialBoardView() {
    }

    /*ATTRIBUTES*/

    /**
     * @brief Piece located in (1, 1).
     * */
    @FXML
    private Circle f1c1;
    /**
     * @brief Piece located in (1, 2).
     * */
    @FXML
    private Circle f1c2;
    /**
     * @brief Piece located in (1, 3).
     * */
    @FXML
    private Circle f1c3;
    /**
     * @brief Piece located in (1, 4).
     * */
    @FXML
    private Circle f1c4;
    /**
     * @brief Piece located in (1, 5).
     * */
    @FXML
    private Circle f1c5;
    /**
     * @brief Piece located in (1, 6).
     * */
    @FXML
    private Circle f1c6;
    /**
     * @brief Piece located in (1, 7).
     * */
    @FXML
    private Circle f1c7;
    /**
     * @brief Piece located in (1, 8).
     * */
    @FXML
    private Circle f1c8;
    /**
     * @brief Piece located in (2, 1).
     * */
    @FXML
    private Circle f2c1;
    /**
     * @brief Piece located in (2, 2).
     * */
    @FXML
    private Circle f2c2;
    /**
     * @brief Piece located in (2, 3).
     * */
    @FXML
    private Circle f2c3;
    /**
     * @brief Piece located in (2, 4).
     * */
    @FXML
    private Circle f2c4;
    /**
     * @brief Piece located in (2, 5).
     * */
    @FXML
    private Circle f2c5;
    /**
     * @brief Piece located in (2, 6).
     * */
    @FXML
    private Circle f2c6;
    /**
     * @brief Piece located in (2, 7).
     * */
    @FXML
    private Circle f2c7;
    /**
     * @brief Piece located in (2, 8).
     * */
    @FXML
    private Circle f2c8;
    /**
     * @brief Piece located in (3, 1).
     * */
    @FXML
    private Circle f3c1;
    /**
     * @brief Piece located in (3, 2).
     * */
    @FXML
    private Circle f3c2;
    /**
     * @brief Piece located in (3, 3).
     * */
    @FXML
    private Circle f3c3;
    /**
     * @brief Piece located in (3, 4).
     * */
    @FXML
    private Circle f3c4;
    /**
     * @brief Piece located in (3, 5).
     * */
    @FXML
    private Circle f3c5;
    /**
     * @brief Piece located in (3, 6).
     * */
    @FXML
    private Circle f3c6;
    /**
     * @brief Piece located in (3, 7).
     * */
    @FXML
    private Circle f3c7;
    /**
     * @brief Piece located in (3, 8).
     * */
    @FXML
    private Circle f3c8;
    /**
     * @brief Piece located in (4, 1).
     * */
    @FXML
    private Circle f4c1;
    /**
     * @brief Piece located in (4, 2).
     * */
    @FXML
    private Circle f4c2;
    /**
     * @brief Piece located in (4, 3).
     * */
    @FXML
    private Circle f4c3;
    /**
     * @brief Piece located in (4, 4).
     * */
    @FXML
    private Circle f4c4;
    /**
     * @brief Piece located in (4, 5).
     * */
    @FXML
    private Circle f4c5;
    /**
     * @brief Piece located in (4, 6).
     * */
    @FXML
    private Circle f4c6;
    /**
     * @brief Piece located in (4, 7).
     * */
    @FXML
    private Circle f4c7;
    /**
     * @brief Piece located in (4, 8).
     * */
    @FXML
    private Circle f4c8;
    /**
     * @brief Piece located in (5, 1).
     * */
    @FXML
    private Circle f5c1;
    /**
     * @brief Piece located in (5, 2).
     * */
    @FXML
    private Circle f5c2;
    /**
     * @brief Piece located in (5, 3).
     * */
    @FXML
    private Circle f5c3;
    /**
     * @brief Piece located in (5, 4).
     * */
    @FXML
    private Circle f5c4;
    /**
     * @brief Piece located in (5, 5).
     * */
    @FXML
    private Circle f5c5;
    /**
     * @brief Piece located in (5, 6).
     * */
    @FXML
    private Circle f5c6;
    /**
     * @brief Piece located in (5, 7).
     * */
    @FXML
    private Circle f5c7;
    /**
     * @brief Piece located in (5, 8).
     * */
    @FXML
    private Circle f5c8;
    /**
     * @brief Piece located in (6, 1).
     * */
    @FXML
    private Circle f6c1;
    /**
     * @brief Piece located in (6, 2).
     * */
    @FXML
    private Circle f6c2;
    /**
     * @brief Piece located in (6, 3).
     * */
    @FXML
    private Circle f6c3;
    /**
     * @brief Piece located in (6, 4).
     * */
    @FXML
    private Circle f6c4;
    /**
     * @brief Piece located in (6, 5).
     * */
    @FXML
    private Circle f6c5;
    /**
     * @brief Piece located in (6, 6).
     * */
    @FXML
    private Circle f6c6;
    /**
     * @brief Piece located in (6, 7).
     * */
    @FXML
    private Circle f6c7;
    /**
     * @brief Piece located in (6, 8).
     * */
    @FXML
    private Circle f6c8;
    /**
     * @brief Piece located in (7, 1).
     * */
    @FXML
    private Circle f7c1;
    /**
     * @brief Piece located in (7, 2).
     * */
    @FXML
    private Circle f7c2;
    /**
     * @brief Piece located in (7, 3).
     * */
    @FXML
    private Circle f7c3;
    /**
     * @brief Piece located in (7, 4).
     * */
    @FXML
    private Circle f7c4;
    /**
     * @brief Piece located in (7, 5).
     * */
    @FXML
    private Circle f7c5;
    /**
     * @brief Piece located in (7, 6).
     * */
    @FXML
    private Circle f7c6;
    /**
     * @brief Piece located in (7, 7).
     * */
    @FXML
    private Circle f7c7;
    /**
     * @brief Piece located in (7, 8).
     * */
    @FXML
    private Circle f7c8;
    /**
     * @brief Piece located in (8, 1).
     * */
    @FXML
    private Circle f8c1;
    /**
     * @brief Piece located in (8, 2).
     * */
    @FXML
    private Circle f8c2;
    /**
     * @brief Piece located in (8, 3).
     * */
    @FXML
    private Circle f8c3;
    /**
     * @brief Piece located in (8, 4).
     * */
    @FXML
    private Circle f8c4;
    /**
     * @brief Piece located in (8, 5).
     * */
    @FXML
    private Circle f8c5;
    /**
     * @brief Piece located in (8, 6).
     * */
    @FXML
    private Circle f8c6;
    /**
     * @brief Piece located in (8, 7).
     * */
    @FXML
    private Circle f8c7;
    /**
     * @brief Piece located in (8, 8).
     * */
    @FXML
    private Circle f8c8;
    /**
     * @brief Save board button text.
     * */
    @FXML
    private Text save;
    /**
     * @brief Save board button.
     * */
    @FXML
    private Rectangle saveButton;
    /**
     * @brief White colour pieces selector.
     * */
    @FXML
    private RadioButton placeWhitePieces;
    /**
     * @brief Black colour pieces selector.
     * */
    @FXML
    private RadioButton placeBlackPieces;
    /**
     * @brief Remove pieces selector.
     * */
    @FXML
    private RadioButton quitPieces;
    /**
     * @brief Exception message output.
     * */
    @FXML
    private Label saveInitialBoardResult;
    /**
     * @brief Current board.
     */
    private JSONObject board;

    /* METHODS*/

    /**
     * @brief Initialize method which is executed when the scene is shown.
     * @pre <em>True</em>
     * @post The board is setted.
     */
    public void initialize() {
        board = ViewCtrl.domainCtrl.viewBoard();
        render();
    }

    /**
     * @brief Event method which is executed when a piece is clicked.
     * @pre <em>True</em>
     * @post The piece changes into white, black or is removed.
     */
    public void transform(MouseEvent mouseEvent) {
        Pair<Integer, Integer> pos = getClickedPos(mouseEvent);
        if (placeWhitePieces.isSelected()) board = ViewCtrl.domainCtrl.placePieceConfig(pos, "PLAYER1");
        else if (placeBlackPieces.isSelected()) board = ViewCtrl.domainCtrl.placePieceConfig(pos, "PLAYER2");
        else if (quitPieces.isSelected()) board = ViewCtrl.domainCtrl.removePiece(pos);
        render();
    }

    /**
     * @brief Event method which is executed when the save button is clicked.
     * @pre <em>True</em>
     * @post The game is saved and user can close the game.
     */
    public void save() throws IOException {
        Stage currentWindow = (Stage) save.getScene().getWindow();
        currentWindow.close();
    }

    /**
     * @brief Method executed everytime there is a change in the board.
     * @pre <em>True</em>
     * @post The change is setted in the board.
     */
    private void render() {
        for (int i = 0; i < 8; i++) {
            char[] row = board.getString(String.format("row%d", i)).toCharArray();
            for (int j = 0; j < 8; j++) drawPiece(new Pair<Integer, Integer>(i, j), row[j]);
        }
    }

    /**
     * @brief Painting method executed everytime there is a change in the board.
     * @pre <em>True</em>
     * @post Pieces change to the correct color.
     */
    private void drawPiece(Pair<Integer, Integer> pos, char pieceType) {
        Circle circle = getCircle(pos);
        switch (pieceType) {
            case 'B':
                circle.setFill(Color.web("0xFFFFFF", 1.0));
                break;
            case 'N':
                circle.setFill(Color.web("0x000000", 1.0));
                break;
            case '?':
                circle.setFill(Color.web("0x34d399", 1.0));
                break;
            default:
                break;
        }
    }

    /**
     * @brief Painting method executed everytime a player clicks on the board.
     * @pre <em>True</em>
     * @post The piece clicked is transformed into a pair.
     */
    private Pair<Integer, Integer> getClickedPos(MouseEvent mouseEvent) {
        Pair<Integer, Integer> pos = new Pair<Integer, Integer>(-1, -1);
        String piece = ((Circle) mouseEvent.getPickResult().getIntersectedNode()).getId();
        pos.first = Character.getNumericValue(piece.charAt(1)) - 1;
        pos.second = Character.getNumericValue(piece.charAt(3)) - 1;
        return pos;
    }

    /**
     * @brief Method executed everytime there is a change in the board.
     * @pre <em>True</em>
     * @post Return the circle which belongs to the position.
     */
    private Circle getCircle(Pair<Integer, Integer> pos) {
        try {
            Field field = this.getClass().getDeclaredField(String.format("f%sc%s", pos.first + 1, pos.second + 1));
            field.setAccessible(true);
            return (Circle) field.get(this);
        } catch (Exception e) {
            return new Circle();
        }
    }
}
