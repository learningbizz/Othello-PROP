/**
 * @file entry.java
 * @author Alex Rodriguez
 * @brief JUnit Entry tests entrypoint class specification.
 */
package cmd.unitary;

import org.junit.runner.JUnitCore;

import test.unitary.EntryJUnit;

/**
 * @class entry
 * @brief JUnit Entry tests entrypoint.
 * By Alex Rodriguez.
 */
public class entry {
    /**
     * @brief JUnit Entry tests main function.
     * Calls the JUnitCore main entrypoint and runs the Entry unitary tests.
     * @pre <em>True</em>.
     * @post The JUnit Entry tests have started.
     */
    public static void main(String[] args) {
        JUnitCore.main(new EntryJUnit().getClass().getName());
    }
}
