/**
 * @file Difficulty.java
 * @author Arnau Pujantell
 * @brief Difficulty class specification.
 */
package domain;

import domain.Board.PieceType;
import util.Pair;

/**
 * @class Difficulty
 * @brief Implements the abstract class and methods of all the difficulty implementations.
 * By Arnau Pujantell
 */
public abstract class Difficulty {
    /* ATTRIBUTES */

    /**
    * @brief Max depth for the heuristics of the chosen algorithm. It is calculated from the implicit difficulty.
    */
    protected Integer maxDepth;
    /**
    * @brief Difficulty for the chosen algorithm. It is mainly used to calculate the implicit max depth.
    */
    protected Integer difficulty;
    /**
    * @brief Whether the pieces of the current Game can be eaten horizontally.
    */
    protected Boolean canEatHorizontally;
    /**
    * @brief Whether the pieces of the current Game can be eaten vertically.
    */
    protected Boolean canEatVertically;
    /**
    * @brief Whether the pieces of the current Game can be eaten diagonally.
    */
    protected Boolean canEatDiagonally;
    /**
    * @brief Player that wants to be maximized.
    */
    protected PieceType pieceType;

    /* CONSTRUCTORS */

    /**
     * @brief Create a Difficulty instance.
     * @pre The given difficulty is a positive number. The given rules are not all false.
     * @post An Difficulty instance is created and its implicits difficulty, canEatHorizontally, canEatVertically,
     * canEatDiagonally and pieceType attributes are setted. The implicit maxDepth attribute is setted to the double of the entered difficulty.
     * @param difficulty Difficulty for the chosen algorithm.
     * @param canEatHorizontally Whether the pieces of the current Game can be eaten horizontally.
     * @param canEatVertically Whether the pieces of the current Game can be eaten vertically.
     * @param canEatDiagonally Whether the pieces of the current Game can be eaten diagonally.
     * @param pieceType Player that wants to be maximized.
     */
    public Difficulty(Integer difficulty, Boolean canEatHorizontally, Boolean canEatVertically,
            Boolean canEatDiagonally, PieceType pieceType) {
        this.difficulty = difficulty;
        this.canEatHorizontally = canEatHorizontally;
        this.canEatVertically = canEatVertically;
        this.canEatDiagonally = canEatDiagonally;
        this.pieceType = pieceType;
        this.maxDepth = difficulty * 2;
    }

    /* METHODS */

    /**
     * @brief Get the difficulty of the implicit chosen Difficulty.
     * @pre <em>True</em>
     * @post The difficulty attribute of the implicit chosen Difficulty is returned.
     * @return difficulty of the implicit chosen Difficulty.
     */
    public int getDifficulty() {
        return this.difficulty;
    }

    /**
     * @brief Get the canEatHorizontally of the implicit chosen Difficulty.
     * @pre <em>True</em>
     * @post The canEatHorizontally attribute of the implicit chosen Difficulty is returned.
     * @return canEatHorizontally of the implicit chosen Difficulty.
     */
    public boolean getCanEatHorizontally() {
        return this.canEatHorizontally;
    }

    /**
     * @brief Get the canEatVertically of the implicit chosen Difficulty.
     * @pre <em>True</em>
     * @post The canEatVertically attribute of the implicit chosen Difficulty is returned.
     * @return canEatVertically of the implicit chosen Difficulty.
     */
    public boolean getCanEatVertically() {
        return this.canEatVertically;
    }

    /**
     * @brief Get the canEatDiagonally of the implicit chosen Difficulty.
     * @pre <em>True</em>
     * @post The canEatDiagonally attribute of the implicit chosen Difficulty is returned.
     * @return canEatDiagonally of the implicit chosen Difficulty.
     */
    public boolean getCanEatDiagonally() {
        return this.canEatDiagonally;
    }

    /**
     * @brief Get the pieceType of the implicit chosen Difficulty.
     * @pre <em>True</em>
     * @post The pieceType attribute of the implicit chosen Difficulty is returned.
     * @return pieceType of the implicit chosen Difficulty.
     */
    public PieceType getPieceType() {
        return this.pieceType;
    }

    /**
     * @brief Get the maxDepth of the implicit chosen Difficulty.
     * @pre <em>True</em>
     * @post The maxDepth attribute of the implicit chosen Difficulty is returned.
     * @return maxDepth of the implicit chosen Difficulty.
     */
    public int getMaxDepth() {
        return this.maxDepth;
    }

    /**
     * @brief Set the maxDepth of the implicit chosen Difficulty.
     * @pre The given maxDepth is a positive number.
     * @post The maxDepth attribute of the implicit chosen Difficulty is setted.
     * @param maxDepth Max depth for the heuristics of the chosen algorithm.
     */
    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    /**
     * @brief Get the inverse of the given player.
     * @pre <em>True</em>
     * @post It is returned the inverse, that is the opponent, of the given player.
     * @param pieceType Player to be inversed.
     * @return The opponent player.
     */
    protected static PieceType inversePieceType(PieceType pieceType) {
        return (pieceType == PieceType.PLAYER2 ? PieceType.PLAYER1 : PieceType.PLAYER2);
    }

    /**
     * @brief Get the next best possible position for the implicit player.
     * @pre <em>True</em>
     * @post It is returned the next best possible position for the implicit player, using the chosen algorithm with
     * the implicit maximum depth, or null if there isn't any.
     * @param playingBoard Current playing Board.
     * @return The next best possible position for the implicit player or null if there isn't any.
     */
    public abstract Pair<Integer, Integer> place(PieceType[][] playingBoard);
}
