/**
 * @file Pair.java
 * @author Alex Rodriguez
 * @brief Pair class specification.
 */
package util;

import java.util.Objects;

/**
 * @class Pair
 * @brief Implements a data structure containing two generic types.
 * By Alex Rodriguez.
 */
public class Pair<F, S> {
    /* ATTRIBUTES */

    /**
    * @brief First value of the Pair.
    */
    public F first;
    /**
    * @brief Second value of the Pair.
    */
    public S second;

    /* CONSTRUCTORS */

    /**
     * @brief Create a Pair instance.
     * @pre <em>True</em>
     * @post A Pair instance is created and its implicits first and second attributes are setted.
     * @param first First value of the Pair.
     * @param second Second value of the Pair.
     */
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    /* METHODS */

    /**
     * @brief Compare equality of the implicit Pair and another.
     * @pre <em>True</em>
     * @post It is returned True if the implicit Pair is equal to the given Pair or False if not.
     * @param object Pair to be compared.
     * @return Whether the implicit Pair and the given Pair are equal.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pair)) {
            return false;
        }
        Pair<?, ?> other = (Pair<?, ?>) object;
        return Objects.equals(other.first, this.first) && Objects.equals(other.second, this.second);
    }

    /**
     * @brief Get the String representation of the implicit Pair.
     * @pre <em>True</em>
     * @post An String representing the implicit Pair is returned.
     * @return String representation of the implicit Pair.
     */
    @Override
    public String toString() {
        return String.format("Pair<%s, %s>", this.first, this.second);
    }

    /**
     * @brief Get the First value of the implicit Pair.
     * @pre <em>True</em>
     * @post The First value of the implicit Pair is returned.
     * @return First value of the implicit Pair.
     */
    public F getFirst() {
        return this.first;
    }

    /**
     * @brief Get the Second value of the implicit Pair.
     * @pre <em>True</em>
     * @post The Second value of the implicit Pair is returned.
     * @return Second value of the implicit Pair.
     */
    public S getSecond() {
        return this.second;
    }
}
