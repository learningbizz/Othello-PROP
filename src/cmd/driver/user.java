/**
 * @file user.java
 * @author Alex Rodriguez
 * @brief UserDriver entrypoint class specification.
 */
package cmd.driver;

import test.driver.UserDriver;

/**
 * @class user
 * @brief User driver entrypoint.
 * By Alex Rodriguez.
 */
public class user {
    /**
     * @brief User driver main function.
     * Creates an instance of the User driver and starts it.
     * @pre <em>True</em>.
     * @post The User driver has started.
     */
    public static void main(String[] args) {
        new UserDriver().start();
    }
}
