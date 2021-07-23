/**
 * @file ranking.java
 * @author Alex Rodriguez
 * @brief JUnit Ranking tests entrypoint class specification.
 */
package cmd.unitary;

import org.junit.runner.JUnitCore;

import test.unitary.RankingJUnit;

/**
 * @class ranking
 * @brief JUnit Ranking tests entrypoint.
 * By Alex Rodriguez.
 */
public class ranking {
    /**
     * @brief JUnit Ranking tests main function.
     * Calls the JUnitCore main entrypoint and runs the Ranking unitary tests.
     * @pre <em>True</em>.
     * @post The JUnit Ranking tests have started.
     */
    public static void main(String[] args) {
        JUnitCore.main(new RankingJUnit().getClass().getName());
    }
}
