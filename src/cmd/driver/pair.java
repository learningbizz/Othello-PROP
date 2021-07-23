/**
 * @file pair.java
 * @author Alex Rodriguez
 * @brief PairDriver entrypoint class specification.
 */
package cmd.driver;

import test.driver.PairDriver;

/**
 * @class pair
 * @brief Pair driver entrypoint.
 * By Alex Rodriguez.
 */
public class pair {
    /**
     * @brief Pair driver main function.
     * Creates an instance of the Pair driver and starts it.
     * @pre <em>True</em>.
     * @post The Pair driver has started.
     */
    public static void main(String[] args) {
        new PairDriver().start();
    }
}
