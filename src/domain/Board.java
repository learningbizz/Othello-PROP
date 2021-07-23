/**
 * @file Board.java
 * @author Manuel Navid
 * @brief Board class specification.
 */
package domain;

import util.Pair;
import java.util.ArrayList;
import domain.Exceptions.InvalidBoardException;
import org.json.JSONObject;

/** 
 * @class Board
 * @brief <p> This class represents an Othello Board in our project.</p>
 * &nbsp; Done by Manuel Navid
*/
public class Board {

    /* ATTRIBUTES */
    /**
     * @brief The status of a cell of the Board.
     * An Othello Board is composed of 64 cells with their own unique position and three possible states:
     *          1. PLAYER1 -> PLAYER1 has a piece on that cell.
     *          2. PLAYER2 -> PLAYER2 has a piece on that cell.
     *          3. null -> empty cell (nobody has a piece on that cell).
     * */
    public enum PieceType {PLAYER1, PLAYER2};
    /**
     * @brief A matrix of 64 cells that composes an Othello board. Its the data structure that stores the different cells of the Board. 
     * */
    private PieceType[][] board;
    /**
     * @brief PLAYER1's total number of pieces on the Board.
     * */
    private Integer piecesPlayer1;
    /**
     * @brief PLAYER2's total number of pieces on the Board.
     * */
    private Integer piecesPlayer2;
    
    //Constructors

    /**
    * @brief Creator method that instances a default Othello Board.
    * @pre <em>True</em>
    * @post <p> A new instance of Board is instanced with the default values inserted: 2 white pieces in the middle of the board crossed by 2 black pieces. </p>
    &nbsp; Therefore, <em>piecesPlayer1</em> = 2 and <em>piecesPlayer2</em> = 2.
    */

    public Board()
    {
        this.board = new PieceType[8][8];

        //Initial Pieces
        this.board[3][3] = PieceType.PLAYER1;
        this.board[4][4] = PieceType.PLAYER1;
        this.board[4][3] = PieceType.PLAYER2;
        this.board[3][4] = PieceType.PLAYER2;

        this.piecesPlayer1 = 2;
        this.piecesPlayer2 = 2;
    }
    
    /**
     * @brief Creator method that instances a Board based off a JSON object <em>jsonBoard</em>.
     * @pre <em>True</em>
     * @post <p> A new instance of Board is instanced with the <em>board</em> attribute equal to a board given to us by the JSON object <em>jsonBoard</em>.</p>
     * &nbsp; In addition, the attributes <em>PiecesPlayer1</em> and <em>PiecesPlayer2</em> will have different values based off of the modified <em>board</em> attribute.
     * @param jsonBoard JSON object that stores a state of an Othello board (8 rows with 8 elements each with characters equal to: B,N or ?)
     */
    public Board(JSONObject jsonBoard) 
    {
        this.board = new PieceType[8][8];
        this.piecesPlayer1 = 0;
        this.piecesPlayer2 = 0;

        String row0 = jsonBoard.getString("row0");
        String row1 = jsonBoard.getString("row1");
        String row2 = jsonBoard.getString("row2");
        String row3 = jsonBoard.getString("row3");
        String row4 = jsonBoard.getString("row4");
        String row5 = jsonBoard.getString("row5");
        String row6 = jsonBoard.getString("row6");
        String row7 = jsonBoard.getString("row7");

        this.transcribeToPieceType(row0, 0);
        this.transcribeToPieceType(row1, 1);
        this.transcribeToPieceType(row2, 2);
        this.transcribeToPieceType(row3, 3);
        this.transcribeToPieceType(row4, 4);
        this.transcribeToPieceType(row5, 5);
        this.transcribeToPieceType(row6, 6);
        this.transcribeToPieceType(row7, 7);
    }

    /**
     * @brief Creator method that instances a Board based off another board container (matrix of PieceTypes).
     * @pre The parameter <em>board</em> is of size 8x8.
     * @post <p>An instance of Board is instanced with the <em>board</em> attribute equal to the <em>board</em> parameter.</p>
     * &nbsp; In addition, the attributes <em>PiecesPlayer1</em> and <em>PiecesPlayer2</em> will have different values based off of the new <em>board</em> attribute. 
     * @param board An 8x8 PieceType matrix that represents a state of an Othello board.
     */
    public Board(PieceType[][] board)
    {
        this.board = new PieceType[8][8];
        this.piecesPlayer1 = 0;
        this.piecesPlayer2 = 0;

        for(int i = 0; i < 8; ++i)
        {
            for(int j = 0; j < 8; j++)
            {
                this.board[i][j] = board[i][j];
                if(this.board[i][j] == PieceType.PLAYER1) this.piecesPlayer1 += 1;
                if(this.board[i][j] == PieceType.PLAYER2) this.piecesPlayer2 += 1;
            }
        }
    }

    //Serialize

    /**
     * @brief Method that transforms the implicit parameter's <em>board</em> into a JSON format.
     * @pre <em>True</em>
     * @post returns a JSON object that corresponds to the transformation of the implicit parameter's <em>board</em> attribute into the storing format we decided in class.
     */
    public JSONObject serialize()
    {
        ArrayList<String> boardCodified = this.transcribeToCharacters();
        JSONObject jsonBoard = new JSONObject();
        
        jsonBoard.put("row0", boardCodified.get(0));
        jsonBoard.put("row1", boardCodified.get(1));
        jsonBoard.put("row2", boardCodified.get(2));
        jsonBoard.put("row3", boardCodified.get(3));
        jsonBoard.put("row4", boardCodified.get(4));
        jsonBoard.put("row5", boardCodified.get(5));
        jsonBoard.put("row6", boardCodified.get(6));
        jsonBoard.put("row7", boardCodified.get(7));

        return jsonBoard;
    }    

    //Consultoras

    /**
     * @brief Get method that returns the implicit parameter's <em>board</em> attribute.
     * @pre <em>True</em>
     * @post The implicit parameter's <em>board</em> is returned.
     */
    public PieceType[][] getBoard()
    {
        return this.board;
    }
    
    /**
     * @brief Get method that returns the value of the implicit parameter's <em>PiecesPlayer1</em> attribute.
     * @pre <em>True</em>
     * @post The implicit parameter's <em>piecesPlayer1</em> value is returned.
     */
    public Integer getPiecesPlayer1()
    {
        return this.piecesPlayer1;
    }

    /**
     * @brief Get method that returns the value of the implicit parameter's <em>PiecesPlayer2</em> attribute.
     * @pre <em>True</em>
     * @post The implicit parameter's <em>piecesPlayer2</em> value is returned.
     */
    public Integer getPiecesPlayer2()
    {
        return this.piecesPlayer2;
    }

    /**
     * @brief Method that warns us if an instance of a Board is invalid.<p></p>
     * &nbsp; An invalid Board means that no player can add a piece in the current state of the implicit parameter's <em>board</em> attribute.
     * @pre All parameters aren't null.
     * @post If the Board instance is invalid, InvalidBoardException will be thrown, else nothing.
     */
    public void isValid(Boolean canEatHorizontally, Boolean canEatVertically, Boolean canEatDiagonally) throws InvalidBoardException
    {
        ArrayList<Pair<Integer, Integer>> player1 = validPositions(PieceType.PLAYER1, canEatHorizontally, canEatVertically, canEatDiagonally);
        ArrayList<Pair<Integer, Integer>> player2 = validPositions(PieceType.PLAYER2, canEatHorizontally, canEatVertically, canEatDiagonally);
        //If there is no possible movements == Board Invalid
        if(player1.isEmpty() && player2.isEmpty()) throw new InvalidBoardException();
    }


    /**
     * @brief Method that returns an Array of the valid positions a player <em>myPieceType</em> taking into consideration the Configuration of the Game.
     * @pre All parameters aren't null.
     * @post <p>An Array of valid positions(Pair<Integer,Integer>) is returned.</p> 
     * &nbsp; A valid position is one which it's cell state in the implicit parameter's <em>board</em> attribute is equal to null (meaning an empty cell)
     * and there is at least one opponent PieceType surrounding that position (go to surroundingPieces to crystalize what the surrounding areas of a position are).
     * @param myPieceType PieceType variable that represents the player in a cell.
     * @param canEatHorizontally Boolean value from Configuration that determines if we can capture pieces of the <em>myPieceType</em> opponent in a Horizontal manner.
     * @param canEatVertically Boolean value from Configuration that determines if we can capture pieces of the <em>myPieceType</em> opponent in a Vertical manner.
     * @param canEatDiagonally Boolean value from Configuration that determines if we can capture pieces of the <em>myPieceType</em> opponent in a Diagonal manner.
     */
    public ArrayList<Pair<Integer,Integer>> validPositions(PieceType myPieceType, Boolean canEatHorizontally, Boolean canEatVertically, Boolean canEatDiagonally)
    {
        ArrayList<Pair<Integer,Integer>> availablePos = new ArrayList<Pair<Integer,Integer>>();
        boolean posValid = false;

        for(int i = 0; i < 8; ++i)
        {
            for(int j = 0; j < 8; ++j)
            {
                Pair<Integer, Integer> iterator = new Pair<Integer,Integer>(i,j);
                posValid = false;

                if(this.board[i][j] == null && surroundingPieces(iterator, myPieceType, canEatHorizontally, canEatVertically, canEatDiagonally))
                {
                    if(canEatHorizontally)
                    {
                        ArrayList<Pair<Integer,Integer>> horizontal = canPlaceHorizontal(iterator, myPieceType); 
                        //IF NOT EMPTY
                        if(!horizontal.isEmpty()) posValid = true;
                    }
                    if(canEatVertically)
                    {
                        ArrayList<Pair<Integer,Integer>> vertical = canPlaceVertical(iterator, myPieceType);
                        //IF NOT EMPTY
                        if(!vertical.isEmpty()) posValid = true;                        
                    }
                    if(canEatDiagonally)
                    {
                        ArrayList<Pair<Integer,Integer>> diagonal = canPlaceDiagonal(iterator, myPieceType);
                        //IF NOT EMPTY
                        if(!diagonal.isEmpty()) posValid = true; 
                    }
                    //It's a valid position to add a Piece
                    if(posValid) availablePos.add(iterator);
                }
            }
        }

        return availablePos;
    }
    

    //Modifiers
    /**
     * @brief Modifying method that removes a piece from the implicit parameter's <em>board</em> attribute.
     * @pre The <em>position</em> parameter isn't null and has values between (0,0) and (7,7).
     * @post In the implicit parameter's <em>board</em>, the state of the cell in position <em>position</em> is converted to null, which means that now it's an empty cell on the board.
     * @param position Pair<Integer,Integer> that represents a position in a board.
     */
    public void removePiece(Pair<Integer, Integer> position)
    {
            Integer row = position.first;
            Integer column = position.second; 

            if(this.board[row][column] == PieceType.PLAYER1) this.piecesPlayer1 -= 1;
            if(this.board[row][column] == PieceType.PLAYER2) this.piecesPlayer2 -= 1;

            this.board[row][column] = null;  
    }
    
    /**
     * @brief Modifying method that adds a piece in the implicit parameter's <em>board</em><p></p>
     * &nbsp; In addition, it applies the effect of adding that piece in the board by changing the pieces of the board taking into consideration the Configuration given.
     * @pre Parameters aren't null and <em>position</em> is between values (0,0) and (7,7).
     * @post With the given Configuration, if the <em>position</em> parameter is correct then the implicit parameter's <em>board</em> will be modified with the addition of the piece
     * <em>Piecetype</em> in the <em>position</em> parameter and its effect considering the Configuration given (pieces changing from the different taking piece methods).
     * If the position isn't correct, the implicit parameter's <em>board</em> will not be changed.</p>
     * &nbsp; A correct position is a position in the board where given the <em>PieceType</em> parameter, we will take at least one opponent piece with the Configuration given.
     * @param myPieceType PieceType variable that represents the player in a cell.
     * @param position Pair<Integer,Integer> that represents a position in a board.
     * @param canEatHorizontally Boolean value from Configuration that determines if we can capture pieces of the <em>myPieceType</em> opponent in a Horizontal manner..
     * @param canEatVertically Boolean value from Configuration that determines if we can capture pieces of the <em>myPieceType</em> opponent in a Vertical manner.
     * @param canEatDiagonally Boolean value from Configuration that determines if we can capture pieces of the <em>myPieceType</em> opponent in a Diagonal manner.
     */
    public void placePiece(Pair<Integer, Integer> position, PieceType myPieceType, Boolean canEatHorizontally, Boolean canEatVertically, Boolean canEatDiagonally)
    {
        ArrayList<Pair<Integer,Integer>> horizontal = new ArrayList<Pair<Integer,Integer>>();
        ArrayList<Pair<Integer,Integer>> vertical = new ArrayList<Pair<Integer,Integer>>();
        ArrayList<Pair<Integer,Integer>> diagonal = new ArrayList<Pair<Integer,Integer>>();

        //if the position given to us is not null, it means it's owned by PLAYER1 or PLAYER2. Therefore, we won't add a Piece there and we will return.
        //Although this will never happen when we use this method (because we will make sure it's a valid position), 
        //  we added this so this method is more reusable for other future projects.
        if(this.board[position.first][position.second] != null) return;

        if(canEatHorizontally) //Includes eating HORIZONTALLY activated
        {
            horizontal = canPlaceHorizontal(position, myPieceType);
            for(int i = 0; i < horizontal.size(); i++) 
                changePieces(position,horizontal.get(i),myPieceType);
        }

        if (canEatVertically) //Includes eating VERTICALLY activated
        {
            vertical = canPlaceVertical(position, myPieceType);
            for(int i = 0; i < vertical.size(); i++) {
                changePieces(position,vertical.get(i),myPieceType);
            }
        }

        if (canEatDiagonally) //Includes eating DIAGONALLY activated
        {
            diagonal = canPlaceDiagonal(position, myPieceType);
            for(int i = 0; i < diagonal.size(); i++)
                changePieces(position,diagonal.get(i),myPieceType);
        }
         //If we added a piece to the board, we must add this to the piecesPlayerx attribute
         if((canEatHorizontally && !horizontal.isEmpty()) || (canEatVertically && !vertical.isEmpty()) || (canEatDiagonally && !diagonal.isEmpty())) 
         {
             if(myPieceType == PieceType.PLAYER1) this.piecesPlayer1++;
             if(myPieceType == PieceType.PLAYER2) this.piecesPlayer2++;
         }
    } 

    /**
     * @brief Modifying method that adds a piece in the in the implicit parameter's <em>board</em>, which corresponds to an Initial Board of a Configuration.
     * @pre Parameters aren't null and <em>position</em> is between values (0,0) and (7,7).
     * @post The implicit parameter's <em>board</em> will be modified with the addition of the piece <em>PieceType</em> in position <em>position</em>.
     * @param myPieceType PieceType variable that represents the player in a cell.
     * @param position Pair<Integer,Integer> that represents a position in a board.
     */
    public void placePieceConfig(Pair<Integer, Integer> position, PieceType myPieceType)
    {
        Integer row = position.first;
        Integer column = position.second;

        if(this.board[row][column] == PieceType.PLAYER1 && myPieceType == PieceType.PLAYER2)
        {
            this.piecesPlayer2 += 1;
            this.piecesPlayer1 -= 1;
        } 

        if(this.board[row][column] == PieceType.PLAYER2 && myPieceType == PieceType.PLAYER1)
        {
            this.piecesPlayer1 += 1;
            this.piecesPlayer2 -= 1;
        } 

        if(this.board[row][column] == null && myPieceType == PieceType.PLAYER1) this.piecesPlayer1 += 1;
        if(this.board[row][column] == null && myPieceType == PieceType.PLAYER2) this.piecesPlayer2 += 1;

        this.board[row][column] = myPieceType;
    }

    //Private

    /**
     * @brief Private method that returns true if there is an opponent's PieceType surrounding a position in the board taking into account the capturing methods of the Game (Horizontal,Vertical or Diagonal).<p></p>
     * &nbsp; This method is particularly useful to check if a position is valid, which means it's eligible to be chosen as a viable option to place a piece in.
     * @pre Parameters aren't null and <em>position</em> is between values (0,0) and (7,7).
     * @post <p>Returns <em>true</em> if there is an opponent's PieceType surrounding the <em>position</em> parameter in the board taking into account the capturing methods of the Game.</p>
     * &nbsp; To crystalize what a piece surrounding a position is, its all the possible positions one can reach adding or substracting 1 to the y or x value
     * (taking into consideration the board's limits obviously).
     * @param myPieceType PieceType variable that represents the player in a cell.
     * @param position Pair<Integer,Integer> that represents a position in a board.
     * @param canEatHorizontally Boolean value from Configuration that determines if we can capture pieces of the <em>myPieceType</em> opponent in a Horizontal manner.
     * @param canEatVertically Boolean value from Configuration that determines if we can capture pieces of the <em>myPieceType</em> opponent in a Vertical manner.
     * @param canEatDiagonally Boolean value from Configuration that determines if we can capture pieces of the <em>myPieceType</em> opponent in a Diagonal manner.
     */
    private Boolean surroundingPieces(Pair<Integer,Integer> position, PieceType myPieceType, Boolean canEatHorizontally, Boolean canEatVertically, Boolean canEatDiagonally) 
    {
        PieceType opponentPiece = inversePlayer(myPieceType);
        
        if(canEatDiagonally)
        {   
            //TOP LEFT
            if(position.first > 0 && position.second > 0 && this.board[position.first-1][position.second-1] == opponentPiece) return true;
            //TOP RIGHT
            if(position.first > 0 && position.second < 7 && this.board[position.first-1][position.second+1] == opponentPiece) return true;
            //BOTTOM RIGHT
            if(position.first < 7 && position.second < 7 && this.board[position.first+1][position.second+1] == opponentPiece) return true;
            //BOTTOM LEFT
            if(position.first < 7 && position.second > 0 && this.board[position.first+1][position.second-1] == opponentPiece) return true;
        }

        if(canEatVertically)
        {   
            //BOTTOM 
            if(position.first < 7 && this.board[position.first+1][position.second] == opponentPiece) return true;
            //TOP 
            if(position.first > 0 && this.board[position.first-1][position.second] == opponentPiece) return true;
        }

        if(canEatHorizontally)
        {
            //RIGHT 
            if(position.second < 7 && this.board[position.first][position.second+1] == opponentPiece) return true;
            //LEFT
            if(position.second > 0 && this.board[position.first][position.second-1] == opponentPiece) return true;
        }
        //If none are true
        return false;
    }

    /**
     * @brief Private method that returns an array of strings to transcribe the implicit parameter's <em>board</em> into a storing format.
     * @pre <em>True</em>
     * @post <p>Returns an array of Strings size 8 that transcribes the implicit parameter's <em>board</em> into the storing format decided in class.</p>
     * &nbsp; The storing format is: ? -> empty cell, B -> PLAYER1's piece, N -> PLAYER2's piece.
     */
    private ArrayList<String> transcribeToCharacters()
    {
        ArrayList<String> boardCodified = new ArrayList<String>(8);
        String operational = "";

            for(int i = 0; i < 8; ++i)
            {
                operational = "";
                for(int j = 0; j < 8; ++j)
                {
                    if(this.board[i][j] == PieceType.PLAYER1) operational = operational + "B";
                    if(this.board[i][j] == PieceType.PLAYER2) operational = operational + "N";
                    if(this.board[i][j] == null) operational = operational + "?";
                }
                boardCodified.add(operational);
            }

        return boardCodified;
    }

    /**
     * @brief Private method that adds a row of a board in the storing format into the <em>board</em> attribute of the implicit parameter.<p></p>
     * &nbsp; This method is useful to load a Board from a file.
     * @pre Parameters aren't null and numRow has a value between 0 and 7.
     * @post The implicit parameter's <em>board</em> attribute is modified with a new row number <em>numRow</em> with the values specified.
     * in the <em>row</em> parameter.
     * @param row String of a row of a board in storing format.
     * @param numRow Number of the row in the board its transcribing.
     */
    private void transcribeToPieceType(String row, Integer numRow)
    {
        for(int i = 0; i < 8; ++i)
        {  
            if(row.charAt(i) == '?') this.board[numRow][i] = null;
            if(row.charAt(i) == 'B')
            {
                this.board[numRow][i] = PieceType.PLAYER1;
                this.piecesPlayer1++;
            }
            if(row.charAt(i) == 'N')
            {
                this.board[numRow][i] = PieceType.PLAYER2;
                this.piecesPlayer2++;
            }
        }
    }

    /**
     * @brief Private method that inverts the Player's pieceType.<p></p>
     * &nbsp; This method is particularly useful to get the opponent's PieceType in another method.
     * @pre myPieceType isn't null.
     * @post Returns a PieceType that is the opponent of <em>myPieceType</em>
     * @param myPieceType PieceType variable that represents the player in a cell.
     */
    private PieceType inversePlayer(PieceType myPieceType)
    {
        if(myPieceType == PieceType.PLAYER1) return PieceType.PLAYER2;
        else return PieceType.PLAYER1;
    }

     /**
     * @brief Private method that returns an array of positions of the board in which you can conquer the pieces between them (horizontal search). 
     * @pre Parameters aren't null and <em>position</em> is between values (0,0) and (7,7).
     * @post Returns an array of positions in which you can use with the method changePieces to conquer the pieces between them and the <em>position<e/m> parameter
     * (which corresponds to the position we want to add a piece to).
     * @param position Pair<Integer,Integer> that represents a position in a board.
     * @param myPieceType PieceType variable that represents the player in a cell.
     */
    private ArrayList< Pair<Integer,Integer> > canPlaceHorizontal(Pair<Integer, Integer> position, PieceType myPieceType)
    {
        ArrayList< Pair<Integer,Integer> > result = new ArrayList<Pair<Integer, Integer>>();

        Integer row = position.first;
        Integer column = position.second;
        PieceType opponentPiece = inversePlayer(myPieceType);

        if(column > 0) //To not go out of the boards boundaries 
        {
            //to check if we can eat LEFT SIDE
            if(this.board[row][column-1] == opponentPiece) 
            {
                Integer it1 = column-1;
                Boolean found1 = false;

                while(it1 >= 0 && this.board[row][it1] != null && found1 == false)
                //go through the line of the board to see if we can place the piece we want in "position". If so, we add the position of the piece that closes in the result array.
                {
                    if(this.board[row][it1] == myPieceType) //found a piece that's mine = CAN PLACE
                    {
                        result.add(new Pair<Integer,Integer>(row,it1));
                        found1 = true;
                    }
                    else //found another piece of the opponent = CONTINUE THE HUNT
                        it1 -= 1;
                }
            }
        }
        
        if(column < 7) //To not go out of the boards boundaries
        {
            //to check if we can eat RIGHT SIDE
            if(this.board[row][column+1] == opponentPiece)
            {
                Integer it2 = column+1;
                Boolean found2 = false;

                while(it2 <= 7 && this.board[row][it2] != null && found2 == false)
                //go through the line of the board to see if we can place the piece we want in "position". If so, we add the position of the piece that closes in the result array.
                {
                    if(this.board[row][it2] == myPieceType) //found a piece that's mine = CAN PLACE
                    {
                        result.add(new Pair<Integer,Integer>(row,it2));
                        found2 = true;
                    }
                    else //found another piece of the opponent = CONTINUE THE HUNT
                    {
                        it2 += 1;                    
                    }
                }
            }
        }

        return result;
    }

     /**
     * @brief Private method that returns an array of positions of the board in which you can conquer the pieces between them (vertical search).
     * @pre Parameters aren't null and <em>position</em> is between values (0,0) and (7,7).
     * @post Returns an array of positions in which you can use with the method changePieces to conquer the pieces between them and the <em>position<e/m> parameter
     * (which corresponds to the position we want to add a piece to).
     * @param position Pair<Integer,Integer> that represents a position in a board.
     * @param myPieceType PieceType variable that represents the player in a cell.
     */
    private ArrayList< Pair<Integer,Integer> > canPlaceVertical(Pair<Integer, Integer> position, PieceType myPieceType)
    {
        ArrayList< Pair<Integer,Integer> > result = new ArrayList<Pair<Integer, Integer>>();

        Integer row = position.first;
        Integer column = position.second;
        PieceType opponentPiece = inversePlayer(myPieceType);

        if(row > 0) //To not go out of the boards boundaries
        {
            if(this.board[row-1][column] == opponentPiece) //to check if left side can be eaten
            {
                Integer it1 = row-1;
                Boolean found1 = false;

                while(it1 >= 0 && this.board[it1][column] != null && found1 == false) 
                //go through the line of the board to see if we can place the piece we want in "position". If so, we add the position of the piece that closes in the result array.
                {
                    if(this.board[it1][column] == myPieceType) //found a piece that's mine = CAN PLACE
                    {
                        result.add(new Pair<Integer,Integer>(it1,column));
                        found1 = true;
                    }
                    else //found another piece of the opponent = CONTINUE THE HUNT
                        it1 -= 1;
                }
            }
        }

        if(row < 7)
        {
            if(this.board[row+1][column] == opponentPiece) //to check if right side can be eaten
            {
                Integer it2 = row+1;
                Boolean found2 = false;

                while(it2 <= 7 && this.board[it2][column] != null && found2 == false) 
                //go through the line of the board to see if we can place the piece we want in "position". If so, we add the position of the piece that closes in the result array.
                {
                    if(this.board[it2][column] == myPieceType) //found a piece that's mine = CAN PLACE
                    {
                        result.add(new Pair<Integer,Integer>(it2,column));
                        found2 = true;
                    }
                    else //found another piece of the opponent = CONTINUE THE HUNT
                    {
                        it2 += 1;
                    }
                }
            }
        }

        return result;
    }

     /**
     * @brief Private method that returns an array of positions of the board in which you can conquer the pieces between them (diagonal search).
     * @pre Parameters aren't null and <em>position</em> is between values (0,0) and (7,7).
     * @post Returns an array of positions in which you can use with the method changePieces to conquer the pieces between them and the <em>position<e/m> parameter
     * (which corresponds to the position we want to add a piece to).
     * @param position Pair<Integer,Integer> that represents a position in a board.
     * @param myPieceType PieceType variable that represents the player in a cell.
     */
    private ArrayList< Pair<Integer,Integer> > canPlaceDiagonal(Pair<Integer, Integer> position, PieceType myPieceType)
    {
        ArrayList< Pair<Integer,Integer> > result = new ArrayList<Pair<Integer, Integer>>();

        Integer row = position.first;
        Integer column = position.second;
        PieceType opponentPiece = inversePlayer(myPieceType);

        //DIAGONAL UP LEFT
        if(row > 0 && column > 0) //To not go out of the boards boundaries
        {
            if(this.board[row-1][column-1] == opponentPiece) //to check if we can eat some opponent pieces in the upper left diagonal
            {
                Integer itRow = row-1;
                Integer itCol = column-1;
                Boolean found = false;

                while(itRow >= 0 && itCol >= 0 && this.board[itRow][itCol] != null && found == false)
                //go through the line of the board to see if we can place the piece we want in "position". If so, we add the position of the piece that closes in the result array.
                {
                    if(this.board[itRow][itCol] == myPieceType) //found a piece that's mine in the diagonal line = CAN PLACE
                    {
                        result.add(new Pair<Integer,Integer>(itRow,itCol));
                        found = true;
                    }
                    else //found another piece of the opponent in the diagonal line = CONTINUE THE HUNT
                    {
                        itRow -= 1;
                        itCol -= 1;
                    }
                }

            }
        }

        //DIAGONAL UP RIGHT 
        if(row > 0 && column < 7)//To not go out of the boards boundaries
        {
            if(this.board[row-1][column+1] == opponentPiece) //to check if we can eat some opponents pieces in the upper right diagonal
            {
                Integer itRow = row-1;
                Integer itCol = column+1;
                Boolean found = false;

                while(itRow >= 0 && itCol <= 7 && this.board[itRow][itCol] != null && found == false) 
                //go through the line of the board to see if we can place the piece we want in "position". If so, we add the position of the piece that closes in the result array.
                {
                    if(this.board[itRow][itCol] == myPieceType)
                    {
                        result.add(new Pair<Integer,Integer>(itRow,itCol));
                        found = true;
                    }
                    else //found another piece of the opponent in the diagonal line = CONTINUE THE HUNT
                    {
                        itRow -= 1;
                        itCol += 1;
                    }
                }

            }
        }

        //DIAGONAL DOWN LEFT
        if(row < 7 && column > 0) //To not go out of the boards boundaries
        {
            if(this.board[row+1][column-1] == opponentPiece) //to check if we can eat some opponents pieces in the bottom left diagonal
            {
                Integer itRow = row+1;
                Integer itCol = column-1;
                Boolean found = false;

                while(itRow <= 7 && itCol >= 0 && this.board[itRow][itCol] != null && found == false)
                //go through the line of the board to see if we can place the piece we want in "position". If so, we add the position of the piece that closes in the result array.
                {
                    if(this.board[itRow][itCol] == myPieceType)
                    {
                        result.add(new Pair<Integer,Integer>(itRow,itCol));
                        found = true;
                    }
                    else //found another piece of the opponent in the diagonal line = CONTINUE THE HUNT
                    {
                        itRow += 1;
                        itCol -= 1;
                    }
                }

            }
        }
        
        //DIAGONAL DOWN RIGHT
        if(row < 7 && column < 7) //To not go out of the boards boundaries
        {   
            if(this.board[row+1][column+1] == opponentPiece) //to check if we can eat some opponent pieces in the bottom right diagonal
            {
                Integer itRow = row+1;
                Integer itCol = column+1;
                Boolean found = false;

                while(itRow <= 7 && itCol <= 7 && this.board[itRow][itCol] != null && found == false)
                //go through the line of the board to see if we can place the piece we want in "position". If so, we add the position of the piece that closes in the result array.
                {
                    if(this.board[itRow][itCol] == myPieceType)
                    {
                        result.add(new Pair<Integer,Integer>(itRow,itCol));
                        found = true;
                    }
                    else //found another piece of the opponent in the diagonal line = CONTINUE THE HUNT
                    {
                        itRow += 1;
                        itCol += 1;
                    }
                }

            }
        }

        return result;

    }
    
     /**
     * @brief Private method that changes the pieces between two positions of the board.
     * @pre Parameters aren't null.
     * @post The pieces between the two positions in the board are changed to the <em>myPieceType</em> state.
     * @param addPiece Pair<Intenger,Integer> that represents a position in the board.
     * @param lastPiece Pair<Intenger,Integer> that represents a position in the board. 
     * @param myPieceType PieceType variable that represents the player in a cell.
     */
    private void changePieces(Pair<Integer, Integer> addPiece, Pair<Integer, Integer> lastPiece, PieceType myPieceType)
    {
        Pair<Integer,Integer> position = new Pair<Integer,Integer>(addPiece.first,addPiece.second);
        Integer diffRow = lastPiece.first - addPiece.first;
        Integer diffCol = lastPiece.second - addPiece.second;
        Integer dirRow = 0, dirCol = 0;
        PieceType opponent = inversePlayer(myPieceType);

        if(diffRow == 0) //HORIZONTAL
        {
            if(diffCol > 0) //RIGHT
                dirCol = 1;
            else //LEFT
                dirCol = -1;
        }

        if(diffCol == 0) //VERTICAL
        {
            if(diffRow > 0) //UP
                dirRow = 1;
            else //DOWN
                dirRow = -1;
        }

        if(diffCol != 0 && diffRow != 0) //DIAGONAL
        {
            if(diffRow > 0 && diffCol > 0) //DIAGONAL BOTTOM RIGHT
            {
                dirRow = 1;
                dirCol = 1;
            }
            if(diffRow > 0 && diffCol < 0) //DIAGONAL BOTTOM LEFT
            {
                dirRow = 1;
                dirCol = -1;
            }
            if(diffRow < 0 && diffCol > 0) //DIAGONAL TOP RIGHT
            {
                dirRow = -1;
                dirCol = 1;
            }
            if(diffRow < 0 && diffCol < 0) //DIAGONAL TOP LEFT
            {
                dirRow = -1;
                dirCol = -1;
            }
        }


        while((position.first != lastPiece.first) || (position.second != lastPiece.second)) {
            if(this.board[position.first][position.second] == opponent && opponent == PieceType.PLAYER1)
            {
                this.piecesPlayer1--;
                this.piecesPlayer2++;
            } 
            if(this.board[position.first][position.second] == opponent && opponent == PieceType.PLAYER2)
            {
                this.piecesPlayer1++;
                this.piecesPlayer2--;
            } 

            this.board[position.first][position.second] = myPieceType;
            position.first = position.first + dirRow;
            position.second = position.second + dirCol;
        }
    }


}