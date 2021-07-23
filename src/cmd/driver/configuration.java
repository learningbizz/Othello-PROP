/**
 * @file configuration.java
 * @author Alex Rodriguez
 * @brief ConfigurationDriver entrypoint class specification.
 */
package cmd.driver;

import test.driver.ConfigurationDriver;

/**
 * @class configuration
 * @brief Configuration driver entrypoint.
 * By Alex Rodriguez.
 */
public class configuration {
    /**
     * @brief Configuration driver main function.
     * Creates an instance of the Configuration driver and starts it.
     * @pre <em>True</em>.
     * @post The Configuration driver has started.
     */
    public static void main(String[] args) {
        new ConfigurationDriver().start();
    }
}
