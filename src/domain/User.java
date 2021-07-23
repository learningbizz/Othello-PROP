/** @file User.java
 @brief User subclass specification
 */

package domain;

import domain.Exceptions.InvalidPasswordException;

import org.json.JSONObject;

import java.util.UUID;

/** @class User
 @brief Represents a human user in our system.

 Done by Arnau Pujantell

 Subclass that represents a human. It contains a password.
 */

public class User extends Player {
    /** @brief User's password*/
    private String password;


    /**CREATORS*/

    /** @brief Creator that, given a username 'name', a password 'password' and an id 'id'.
    @pre <em>None of the elements is null</em>
    @post It creates a new User with name 'name', password 'password', id 'id', type 'USER' and isDeleted as 'false'.
     */
    public User(String name, String password, UUID id)
    {
        this.name = name;
        this.password = password;
        this.id = id;
        this.isDeleted = false;
    }

    /** @brief Creator that, given a JSONObject user, deserializes it.
    @pre <em>user is not null</em>
    @post user is not a JSONObject anymore
     */
    public User(JSONObject user) {
        this.name = user.getString("name");
        this.id = UUID.fromString(user.getString("id"));
        this.password = user.getString("password");
        this.isDeleted = user.getBoolean("is_deleted");
    }

    /** @brief Creator that serializes the current object to a JSON Object
    @pre <em>True</em>
    @post The current user becomes a JSON Object
     */
    public JSONObject serialize() {
        JSONObject user = new JSONObject();
        user.put("name", this.name);
        user.put("id", this.id.toString());
        user.put("password", this.password);
        user.put("type", "USER");
        user.put("is_deleted", this.isDeleted);

        return user;
    }


    /**CONSULTANTS*/

    /** @brief Consultant that returns the implicit parameter's password.
    @pre <em>True</em>
    @post Implicit parameter's password is returned.
     */
    public String getPassword() {
        return this.password;
    }


    /**MODIFIERS*/

    /** @brief Modifier that, given a password, changes the implicit parameter's password for a new password 'password'.
    @pre <em>password is not null</em>
    @post Implicit parameter's password has been changed.
     */
    public void setPassword(String password) throws InvalidPasswordException {
        if(password.isBlank()) {
            throw new InvalidPasswordException();
        }
        this.password = password;
    }
}

