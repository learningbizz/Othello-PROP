/**
 * @file hardDifficulty.java
 * @author Alex Rodriguez
 * @brief HardDifficultyDriver entrypoint class specification.
 */
package cmd.driver;

import test.driver.HardDifficultyDriver;

/**
 * @class hardDifficulty
 * @brief HardDifficulty driver entrypoint.
 * By Alex Rodriguez.
 */
public class hardDifficulty {
    /**
     * @brief HardDifficulty driver main function.
     * Creates an instance of the HardDifficulty driver and starts it.
     * @pre <em>True</em>.
     * @post The HardDifficulty driver has started.
     */
    public static void main(String[] args) {
        new HardDifficultyDriver().start();
    }
}
