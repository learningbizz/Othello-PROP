/**
 * @file bot.java
 * @author Alex Rodriguez
 * @brief BotDriver entrypoint class specification.
 */
package cmd.driver;

import test.driver.BotDriver;

/**
 * @class bot
 * @brief Bot driver entrypoint.
 * By Alex Rodriguez.
 */
public class bot {
    /**
     * @brief Bot driver main function.
     * Creates an instance of the Bot driver and starts it.
     * @pre <em>True</em>.
     * @post The Bot driver has started.
     */
    public static void main(String[] args) {
        new BotDriver().start();
    }
}
