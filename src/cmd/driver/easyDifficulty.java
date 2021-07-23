/**
 * @file easyDifficulty.java
 * @author Alex Rodriguez
 * @brief EasyDifficultyDriver entrypoint class specification.
 */
package cmd.driver;

import test.driver.EasyDifficultyDriver;

/**
 * @class easyDifficulty
 * @brief EasyDifficulty driver entrypoint.
 * By Alex Rodriguez.
 */
public class easyDifficulty {
    /**
     * @brief EasyDifficulty driver main function.
     * Creates an instance of the EasyDifficulty driver and starts it.
     * @pre <em>True</em>.
     * @post The EasyDifficulty driver has started.
     */
    public static void main(String[] args) {
        new EasyDifficultyDriver().start();
    }
}
