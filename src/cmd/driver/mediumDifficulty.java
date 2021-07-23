/**
 * @file mediumDifficulty.java
 * @author Alex Rodriguez
 * @brief MediumDifficultyDriver entrypoint class specification.
 */
package cmd.driver;

import test.driver.MediumDifficultyDriver;

/**
 * @class mediumDifficulty
 * @brief MediumDifficulty driver entrypoint.
 * By Alex Rodriguez.
 */
public class mediumDifficulty {
    /**
     * @brief MediumDifficulty driver main function.
     * Creates an instance of the MediumDifficulty driver and starts it.
     * @pre <em>True</em>.
     * @post The MediumDifficulty driver has started.
     */
    public static void main(String[] args) {
        new MediumDifficultyDriver().start();
    }
}
