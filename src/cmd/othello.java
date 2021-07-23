/**
 * @file othello.java
 * @author Alex Rodriguez
 * @brief Othello entrypoint class specification.
 */

/**
 * @mainpage Othello, PROP 20-21 Q2.
 * This project is a Java implementation of the Othello game, also known as Reversi.
 * The architecture used is a three layer design composed of the view, domain and repository classes.
 * Classes of different layers are interconnected via their designated controllers.
 * For testing, Drivers for all clases have been made, and also, unitary tests with the JUnit library for the Ranking and Entry classes.
 * For persisting program data, we have opted for local JSON files using the org.JSON library.
 * 
 * Detailed Domain composition:
 * - Util Classes:
 *      - <em>Pair</em>
 * 
 * - Domain Classes:
 *      - <em>Player</em>
 *          - <em>User</em>
 *          - <em>Bot</em>
 *      - <em>Configuration</em>
 *      - <em>Game</em>
 *      - <em>Board</em>
 *      - <em>Difficulty</em>
 *          - <em>EasyDifficulty</em>
 *          - <em>MediumDifficulty</em>
 *          - <em>HardDifficulty</em>
 *      - <em>Entry</em>     
 *      - <em>Ranking</em>
 *      
 * - Domain Controllers:
 *      - <em>PlayerCtrl</em>
 *      - <em>ConfigurationCtrl</em>
 *      - <em>GameCtrl</em>
 *      - <em>BoardCtrl</em>
 *      - <em>DifficultyCtrl</em>
 *      - <em>RankingCtrl</em>
 */
package cmd;

import view.ViewCtrl;

/**
 * @class othello
 * @brief Othello application entrypoint.
 * By Alex Rodriguez.
 */
public class othello {
    /**
     * @brief Othello application main function.
     * Creates an instance of the othello application and starts it.
     * @pre <em>True</em>.
     * @post The Othello application has started.
     */
    public static void main(String[] args) {
        ViewCtrl.main(args);
    }
}
