/**
 * @file DifficultyCtrl.java
 * @author Alex Rodriguez
 * @brief DifficultyCtrl class specification.
 */
package domain;

import domain.Board.PieceType;
import util.Pair;

/**
 * @class DifficultyCtrl
 * @brief Difficulty domain sub-controller. Is in charge of EasyDifficulty, MediumDifficulty and HardDifficulty.
 * It communicates with the main domain controller. It forwards the current bot’s placePiece request to the correct algorithm depending on the current game’s difficulty:
 *   1 to 3: EasyDifficulty (Minimax).
 *   4 to 6: MediumDifficulty (Minimax alpha beta pruning).
 *   7 to 10: HardDifficulty (Montecarlo).
 * 
 * By Alex Rodriguez.
 * @see domain.Difficulty
 */
public class DifficultyCtrl {
    /* ATTRIBUTES */

    /* CONSTRUCTORS */

    /**
     * @brief Creator method that creates an instance of Difficulty Controller.
     * @pre <em>True</em>
     * @post An instance of DifficultyCtrl is instanced.
     */
    public DifficultyCtrl() {
    }

    /* METHODS */

    /**
     * @brief Returns the next best possible position, or null if none, to place a piece on the current game for the current bot.
     *        It forwards the placePiece request to the correct algorithm depending on the current bot’s difficulty.
     * @pre All parameters aren't null.
     * @post The best position for the bot is returned.
     * @param bot An instance of the Bot Class
     * @param configuration An instance of the Configuration Class
     * @param board An instance of the Board Class
     * @param myPieceType PieceType variable that represents a Player in a Board
     * @return The best position for the bot is returned.
     */
    public Pair<Integer, Integer> getBestPositionByBot(Bot bot, Configuration configuration, Board board,
            PieceType myPieceType) {
        return this.getBestPosition(bot.getDifficulty(), configuration, board, myPieceType);
    }

    /**
     * @brief Returns the next best possible position, or null if none, to place a piece on the current game for the given player.
     * It forwards the placePiece request to the correct algorithm depending on the difficulty. This method can be used to implement the assisted mode.
     * @pre All parameters aren't null.
     * @post The best position is returned.
     * @param difficulty Integer that represents the level of the assisted mode.
     * @param configuration An instance of the Configuration Class
     * @param board An instance of the Board Class
     * @param myPieceType PieceType variable that represents a Player in a Board
     * @return The best position is returned.
     */
    public Pair<Integer, Integer> getBestPosition(Integer difficulty, Configuration configuration, Board board,
            PieceType myPieceType) {
        Pair<Integer, Integer> bestPosition = null;
        boolean cH = configuration.getCanEatHorizontally();
        boolean cV = configuration.getCanEatVertically();
        boolean cD = configuration.getCanEatDiagonally();
        PieceType[][] b = board.getBoard();

        switch (difficulty) {
            case 1:
                bestPosition = new HardDifficulty(7, cH, cV, cD, myPieceType).place(b);
                break;
            case 2:
                bestPosition = new HardDifficulty(8, cH, cV, cD, myPieceType).place(b);
                break;
            case 3:
                bestPosition = new HardDifficulty(9, cH, cV, cD, myPieceType).place(b);
                break;
            case 4:
                bestPosition = new HardDifficulty(10, cH, cV, cD, myPieceType).place(b);
                break;
            case 5:
                EasyDifficulty ed5 = new EasyDifficulty(1, cH, cV, cD, myPieceType); ed5.setMaxDepth(1);
                bestPosition = ed5.place(b);
                break;
            case 6:
                bestPosition = new EasyDifficulty(1, cH, cV, cD, myPieceType).place(b);
                break;
            case 7:
                MediumDifficulty md7 = new MediumDifficulty(1, cH, cV, cD, myPieceType); md7.setMaxDepth(3);
                bestPosition = md7.place(b);
                break;
            case 8:
                bestPosition = new MediumDifficulty(2, cH, cV, cD, myPieceType).place(b);
                break;
            case 9:
                MediumDifficulty md9 = new MediumDifficulty(2, cH, cV, cD, myPieceType); md9.setMaxDepth(5);
                bestPosition = md9.place(b);
                break;
            case 10:
                bestPosition = new MediumDifficulty(3, cH, cV, cD, myPieceType).place(b);
                break;
        }

        return bestPosition;
    }
}
