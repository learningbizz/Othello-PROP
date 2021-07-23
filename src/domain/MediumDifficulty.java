/**
 * @file MediumDifficulty.java
 * @author Alex Rodriguez
 * @brief MediumDifficulty class specification.
 */
package domain;

import java.util.ArrayList;

import domain.Board.PieceType;
import util.Pair;

/**
 * @class MediumDifficulty
 * @brief Implements the Minimax algorithm with alpha-beta pruning to get the next best possible position for a given player.
 * By Alex Rodriguez
 */
public class MediumDifficulty extends Difficulty {
    /* ATTRIBUTES */

    /* CONSTRUCTORS */

    /**
     * @brief Create a MediumDifficulty instance.
     * @pre The given difficulty is a positive number. The given rules are not all false.
     * @post A MediumDifficulty instance is created and its implicits difficulty, canEatHorizontally, canEatVertically,
     * canEatDiagonally and pieceType attributes are setted. The implicit maxDepth attribute is setted to the double of the entered difficulty.
     * @param difficulty Difficulty for the Minimax algorithm with alpha-beta pruning.
     * @param canEatHorizontally Whether the pieces of the current Game can be eaten horizontally.
     * @param canEatVertically Whether the pieces of the current Game can be eaten vertically.
     * @param canEatDiagonally Whether the pieces of the current Game can be eaten diagonally.
     * @param pieceType Player that wants to be maximized.
     */
    public MediumDifficulty(Integer difficulty, Boolean canEatHorizontally, Boolean canEatVertically,
            Boolean canEatDiagonally, PieceType pieceType) {
        super(difficulty, canEatHorizontally, canEatVertically, canEatDiagonally, pieceType);
    }

    /* METHODS */

    /**
     * @brief Get the heuristic evaluation for the given Board state.
     * @pre <em>True</em>
     * @post It is returned the heuristic evaluation for the given Board state.
     * The evaluation is the subtraction of the maximized player's control of the board minus the control of the board for the opponent.
     * A player's control of the board is obtained with the number of pieces in his control and adding or subtracting to that based on important positions in the board.
     * Those important positions are corners, positions adjacent to corners, borders of the board which aren't adjacent to corners and positions adjacent to the centre square of the board.
     * @param currentBoard Current playing Board to get the heuristic evaluation from.
     * @return The heuristic evaluation for the given Board state.
     */
    private int evaluation(Board currentBoard) {
        int player1 = currentBoard.getPiecesPlayer1();
        int player2 = currentBoard.getPiecesPlayer2();

        PieceType[][] board = currentBoard.getBoard();

        // Check corners of the Board
        if (board[0][0] == PieceType.PLAYER1) player1 += 50;
        else if (board[0][0] == PieceType.PLAYER2) player2 += 50;

        if (board[0][7] == PieceType.PLAYER1) player1 += 50;
        else if (board[0][7] == PieceType.PLAYER2) player2 += 50;

        if (board[7][0] == PieceType.PLAYER1) player1 += 50;
        else if (board[7][0] == PieceType.PLAYER2) player2 += 50;

        if (board[7][7] == PieceType.PLAYER1) player1 += 50;
        else if (board[7][7] == PieceType.PLAYER2) player2 += 50;

        // Check borders not next to corner
        for (int k = 2; k < 6; ++k) {
            if (board[k][0] == PieceType.PLAYER1) player1 += 17;
            else if (board[k][0] == PieceType.PLAYER2) player2 += 17;

            if (board[k][7] == PieceType.PLAYER1) player1 += 17;
            else if (board[k][7] == PieceType.PLAYER2) player2 += 17;

            if (board[0][k] == PieceType.PLAYER1) player1 += 17;
            else if (board[0][k] == PieceType.PLAYER2) player2 += 17;

            if (board[7][k] == PieceType.PLAYER1) player1 += 17;
            else if (board[7][k] == PieceType.PLAYER2) player2 += 17;
        }

        // Check next to center of the Board
        for (int i = 2; i < 6; ++i) {
            if (board[i][2] == PieceType.PLAYER1) player1 += 10;
            else if (board[i][2] == PieceType.PLAYER2) player2 += 10;

            if (board[i][5] == PieceType.PLAYER1) player1 += 10;
            else if (board[i][5] == PieceType.PLAYER2) player2 += 10;

            if (board[2][i] == PieceType.PLAYER1) player1 += 10;
            else if (board[2][i] == PieceType.PLAYER2) player2 += 10;

            if (board[5][i] == PieceType.PLAYER1) player1 += 10;
            else if (board[5][i] == PieceType.PLAYER2) player2 += 10;
        }

        // Check next to corners
        for (int j = 0; j < 2; ++j) {
            if (board[1][j] == PieceType.PLAYER1) player1 -= 25;
            else if (board[1][j] == PieceType.PLAYER2) player2 -= 25;

            if (board[1][7 - j] == PieceType.PLAYER1) player1 -= 25;
            else if (board[1][7 - j] == PieceType.PLAYER2) player2 -= 25;

            if (board[6][j] == PieceType.PLAYER1) player1 -= 25;
            else if (board[6][j] == PieceType.PLAYER2) player2 -= 25;

            if (board[6][7 - j] == PieceType.PLAYER1) player1 -= 25;
            else if (board[6][7 - j] == PieceType.PLAYER2) player2 -= 25;
        }

        if (board[0][1] == PieceType.PLAYER1) player1 -= 25;
        else if (board[0][1] == PieceType.PLAYER2) player2 -= 25;

        if (board[7][1] == PieceType.PLAYER1) player1 -= 25;
        else if (board[7][1] == PieceType.PLAYER2) player2 -= 25;

        if (board[0][6] == PieceType.PLAYER1) player1 -= 25;
        else if (board[0][6] == PieceType.PLAYER2) player2 -= 25;

        if (board[7][6] == PieceType.PLAYER1) player1 -= 25;
        else if (board[7][6] == PieceType.PLAYER2) player2 -= 25;

        if (this.pieceType == PieceType.PLAYER1) return player1 - player2;
        else return player2 - player1;
    }

    /**
     * @brief Recursive implementation of the Minimax algorithm with alpha-beta pruning.
     * @pre <em>True</em>
     * @post It is returned the heuristic evaluation for the current possible position on the tree of possibilities.
     * If there aren't any possible valid positions left or the maximum depth is reached it stops.
     * The implicit player is maximized and the opponent is minimized.
     * @param currentBoard current Board in the tree of possibilities.
     * @param currentPieceType current turn in the tree of possibilities.
     * @param depth current depth in the tree of possibilities.
     * @param alpha current alpha in the tree of possibilities.
     * @param beta current beta in the tree of possibilities.
     * @return The heuristic evaluation for the current possible position on the tree of possibilities.
     */
    private int minimax(Board currentBoard, PieceType currentPieceType, int depth, int alpha, int beta) {
        ArrayList<Pair<Integer, Integer>> validPositions = currentBoard.validPositions(currentPieceType,
                this.canEatHorizontally, this.canEatVertically, this.canEatDiagonally);

        if (validPositions.isEmpty() || depth == 0)
            return this.evaluation(currentBoard);

        // Maximizer
        if (currentPieceType == this.pieceType) {
            int max = Integer.MIN_VALUE, currentMax = 0;

            for (Pair<Integer, Integer> position : validPositions) {
                // Make a duplicate in order not to work with the same Board pointer!
                Board board = new Board(currentBoard.getBoard());
                board.placePiece(position, currentPieceType, this.canEatHorizontally, this.canEatVertically,
                        this.canEatDiagonally);

                currentMax = this.minimax(board, MediumDifficulty.inversePieceType(currentPieceType), depth - 1, alpha, beta);
                max = Integer.max(max, currentMax);
                alpha = Integer.max(alpha, currentMax);
                // Prune
                if (beta <= alpha)
                    break;
            }

            return max;
        }

        // Minimizer
        else {
            Integer min = Integer.MAX_VALUE, currentMin = 0;

            for (Pair<Integer, Integer> position : validPositions) {
                // Make a duplicate in order not to work with the same Board pointer!
                Board board = new Board(currentBoard.getBoard());
                board.placePiece(position, currentPieceType, this.canEatHorizontally, this.canEatVertically,
                        this.canEatDiagonally);

                currentMin = this.minimax(board, MediumDifficulty.inversePieceType(currentPieceType), depth - 1, alpha, beta);
                min = Integer.min(min, currentMin);
                beta = Integer.min(beta, currentMin);
                // Prune
                if (beta <= alpha)
                    break;
            }

            return min;
        }
    }

    /**
     * @brief Get the next best possible position for the implicit player.
     * @pre <em>True</em>
     * @post It is returned the next best possible position for the implicit player, using the Minimax algorithm with alpha-beta
     * pruning with the implicit maximum depth, or null if there isn't any.
     * @param playingBoard Current playing Board.
     * @return The next best possible position for the implicit player or null if there isn't any.
     */
    @Override
    public Pair<Integer, Integer> place(PieceType[][] playingBoard) {
        Pair<Integer, Integer> bestPosition = null;

        Board initialBoard = new Board(playingBoard);

        ArrayList<Pair<Integer, Integer>> validPositions = initialBoard.validPositions(this.pieceType,
                this.canEatHorizontally, this.canEatVertically, this.canEatDiagonally);

        int max = Integer.MIN_VALUE, currentMax = 0;

        for (Pair<Integer, Integer> position : validPositions) {
            // Make a duplicate in order not to work with the same Board pointer!
            Board board = new Board(initialBoard.getBoard());
            board.placePiece(position, this.pieceType, this.canEatHorizontally, this.canEatVertically,
                    this.canEatDiagonally);

            currentMax = this.minimax(board, MediumDifficulty.inversePieceType(this.pieceType), this.maxDepth - 1,
                    Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (currentMax > max) {
                max = currentMax;
                bestPosition = position;
            }
        }

        return bestPosition;
    }
}
