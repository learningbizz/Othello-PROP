/** @file Player.java
 @brief Player class specification
 */

package domain;

import domain.Exceptions.InvalidNameException;
import java.util.UUID;

/** @class Player
 @brief Represents a player in our system

 Done by Arnau Pujantell

 Class that represents a player. It contains an id, a name, a type and an isDeleted.
 */

public abstract class Player {
    /** @brief Player's ID*/
    protected UUID id;
    /** @brief Player's name*/
    protected String name;
    /** @brief Player's state*/
    protected boolean isDeleted;

    /**CONSULTANTS*/

    /** @brief Consultant that returns the implicit parameter's name.
    @pre <em>True</em>
    @post The implicit parameter's name is returned
     */
    public String getName() {
        return this.name;
    }

    /** @brief Consultant that returns the implicit parameter's ID.
    @pre <em>True</em>
    @post The implicit parameter's ID is returned.
     */
    public UUID getID() {
        return this.id;
    }

    /** @brief Consultant that returns the implicit parameter's isDeleted value.
    @pre <em>True</em>
    @post The implicit parameter's isDeleted value is returned.
     * @return
     */
    public boolean getIsDeleted() {
        return this.isDeleted;
    }


    /**MODIFIERS*/

    /** @brief Modifier that, given a name, changes the implicit parameter's name for a new name 'name'.
    @pre <em>Name is not null</em>
    @post Implicit parameter's name has been changed.
     */
    public void setName(String name) throws InvalidNameException {
        if(name.isBlank()) {
            throw new InvalidNameException();
        }
        this.name = name;
    }

    /** @brief Modifier that, given an isDeleted value, changes the implicit parameter's state for a new state 'isDeleted'.
     @pre <em>isDeleted is not null</em>
     @post Implicit parameter's state has been changed.
     */
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
