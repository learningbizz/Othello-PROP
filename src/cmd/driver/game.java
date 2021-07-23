/**
 * @file game.java
 * @author Alex Rodriguez
 * @brief GameDriver entrypoint class specification.
 */
package cmd.driver;

import test.driver.GameDriver;

/**
 * @class game
 * @brief Game driver entrypoint.
 * By Alex Rodriguez.
 */
public class game {
    /**
     * @brief Game driver main function.
     * Creates an instance of the Game driver and starts it.
     * @pre <em>True</em>.
     * @post The Game driver has started.
     */
    public static void main(String[] args) {
        new GameDriver().start();
    }
}
