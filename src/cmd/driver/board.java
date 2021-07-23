/**
 * @file board.java
 * @author Alex Rodriguez
 * @brief BoardDriver entrypoint class specification.
 */
package cmd.driver;

import test.driver.BoardDriver;

/**
 * @class board
 * @brief Board driver entrypoint.
 * By Alex Rodriguez.
 */
public class board {
    /**
     * @brief Board driver main function.
     * Creates an instance of the Board driver and starts it.
     * @pre <em>True</em>.
     * @post The Board driver has started.
     */
    public static void main(String[] args) {
        new BoardDriver().start();
    }
}
