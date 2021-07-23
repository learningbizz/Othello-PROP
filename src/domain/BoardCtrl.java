/**
 * @file BoardCtrl.java
 * @author Manuel Navid
 * @brief BoardCtrl class specification.
 */
package domain;

import java.util.ArrayList;

import domain.Board.PieceType;
import domain.Exceptions.InvalidBoardException;
import util.Pair;

/** 
 * @class BoardCtrl
 * @brief This class represents the controller of the Board class, which is the classs that will be used to communicate with the other controllers.<p></p>
 * &nbsp; Done by Manuel Navid
*/

public class BoardCtrl {
    /**
    * @brief Default creator method
    * @pre <em>True</em> 
    * @post Creates an instance of BoardCtrl
     */
    public BoardCtrl() {
    }

    /**
     * @brief Modifying method that adds a piece in the <em>board</em> parameter<p></p>
     * &nbsp; In addition, it applies the effect of adding that piece in the board by changing the pieces of the board taking into consideration the Configuration given.
     * @pre Parameters aren't null and <em>position</em> is between values (0,0) and (7,7).
     * @post With the given Configuration, if the <em>position</em> parameter is correct then the returned board's attribute <em>board</em> will contain the board situation with that piece added.
     * <em>Piecetype</em> in the <em>position</em> parameter and its effect considering the Configuration given (pieces changing from the different taking piece methods).
     * If the position isn't correct, the returned board <em>board</em> will not be changed.</p>
     * &nbsp; A correct position is a position in the board where given the <em>PieceType</em> parameter, we will take at least one opponent piece with the Configuration given.
     * @param board Instance of a Board class which is the one we will modify and return.
     * @param myPieceType PieceType variable that represents the player in a cell.
     * @param position Pair<Integer,Integer> that represents a position in a board.
     * @param canEatHorizontally Boolean value from Configuration that determines if we can capture pieces of the <em>myPieceType</em> opponent in a Horizontal manner..
     * @param canEatVertically Boolean value from Configuration that determines if we can capture pieces of the <em>myPieceType</em> opponent in a Vertical manner.
     * @param canEatDiagonally Boolean value from Configuration that determines if we can capture pieces of the <em>myPieceType</em> opponent in a Diagonal manner.
     */
    public Board placePiece(Board board, Configuration configuration, PieceType myPieceType,
            Pair<Integer, Integer> position) {
        board.placePiece(position, myPieceType, configuration.getCanEatHorizontally(),
                configuration.getCanEatVertically(), configuration.getCanEatDiagonally());

        return board;
    }

    /**
     * @brief Modifying method that adds a piece in the <em>board</em> parameters <em>board</em> attribute, which corresponds to an Initial Board of a Configuration.
     * @pre Parameters aren't null and <em>position</em> is between values (0,0) and (7,7).
     * @post Returns a Board with it's <em>board</em> attribute equal to the <em>board</em> parameter with the addition of the piece <em>PieceType</em> in position <em>position</em>.
     * @param board Instance of a Board class which is the one we will modify and return.
     * @param myPieceType PieceType variable that represents the player in a cell.
     * @param position Pair<Integer,Integer> that represents a position in a board.
     */
    public Board placePieceConfig(Board board, Pair<Integer, Integer> position, PieceType myPieceType) {
        board.placePieceConfig(position, myPieceType);

        return board;
    }

    /**
    * @brief Modifying method that removes a piece from the <em>board</em> parameter.
    * @pre The <em>position</em> parameter isn't null and has values between (0,0) and (7,7).
    * @post In the <em>board</em> parameter, the state of the cell in position <em>position</em> is converted to null, which means that now it's an empty cell on the board.
    * @param position Pair<Integer,Integer> that represents a position in a board.
    * @param board Instance of a Board class which is the one we will modify and return.
    */
    public Board removePiece(Board board, Pair<Integer, Integer> position) {
        board.removePiece(position);

        return board;
    }

    /**
     * @brief Get method that returns the value of the <em>board</em> parameter's <em>PiecesPlayer1</em> and <em>PiecesPlayer2</em> attributes.
     * @pre <em>True</em>
     * @post The attributes <em>piecesPlayer1</em> and <em>PiecesPlayer2</em> of the <em>board</em> parameter are returned in the first and second space of a Pair, respectively.
     * @param board Instance of a Board class which is the one we will modify and return.
     */
    public Pair<Integer, Integer> getNumPieces(Board board) {
        Pair<Integer, Integer> totalPieces = new Pair<Integer, Integer>(board.getPiecesPlayer1(),
                board.getPiecesPlayer2());

        return totalPieces;
    }

    /**
     * @brief Method that returns an Array of the valid positions in <em>board</em> of the player <em>myPieceType</em> taking into consideration the Configuration of the Game.
     * @pre All parameters aren't null.
     * @post <p>An Array of valid positions(Pair<Integer,Integer>) is returned.</p> 
     * &nbsp; A valid position in a board is one which it's cell state is equal to null (meaning an empty cell)
     * and there is at least one opponent PieceType surrounding that position (go to surroundingPieces to crystalize what the surrounding areas of a position are).
     * @param board Instance of a Board class which is the one we will modify and return.
     * @param myPieceType PieceType variable that represents the player in a cell.
     * @param configuration Instance of a Configuration class used to determine which piece capturing methods we apply in this method.
     */
    public ArrayList<Pair<Integer, Integer>> validPositions(Board board, Configuration configuration,
            PieceType myPieceType) {
        ArrayList<Pair<Integer, Integer>> validPos = new ArrayList<Pair<Integer, Integer>>();

        validPos = board.validPositions(myPieceType, configuration.getCanEatHorizontally(),
                configuration.getCanEatVertically(), configuration.getCanEatDiagonally());

        return validPos;
    }

    /**
    * @brief Method that warns us if an instance of the <em>board</em> parameters is invalid.<p></p>
    * &nbsp; An invalid Board means that no player can add a piece in the current state of the implicit parameter's <em>board</em> attribute.
    * @pre All parameters aren't null.
    * @post If the <em>board</em> parameter is invalid, InvalidBoardException will be thrown, else nothing.
    */
    public void isValid(Board board, Configuration configuration) throws InvalidBoardException {
        board.isValid(configuration.getCanEatHorizontally(), configuration.getCanEatVertically(),
                configuration.getCanEatDiagonally());
    }

}
