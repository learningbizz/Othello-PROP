/** @file Entry.java
 @brief Specification of class Entry
 */

package domain;

import org.json.JSONObject;

import java.util.UUID;

/** @class Entry
 @brief Represents an entry in a Ranking table

 Created by Roger Mollon

 Class that represents an entry. It contains a player ID and a player value
 */

public class Entry {
    /** @brief ID of the player*/
    private UUID playerID;
    /** @brief Value of the player*/
    private int value;

    /** @brief Builder operation that has parameters playerID and playerValue and creates a new Entry with them
     \pre <em>value > 0</em>
     \post An Entry with playerID and value has been created
     @param playerID ID of the player about to be created
     @param value value of the player about to be created
     */
    public Entry (UUID playerID, int value) {
        this.playerID = playerID;
        this.value = value;
    }

    /** @brief Builder operation that creates a new Entry using the information from a parameter entry
     \pre <em>entry.getInt("value") > 0</em>
     \post An Entry with its attributes based on entry has been created
     @param entry JSONObject which contains information to create an Entry
     */
    public Entry(JSONObject entry) {
        this.playerID = UUID.fromString(entry.getString("player_id"));
        this.value = entry.getInt("value");
    }

    /** @brief Operation that translates an Entry into a JSONObject
     \pre <em>True</em>
     \post A new JSONObject with the information from the implicit Entry has been returned
     @return JSONObject with the attributes from implicit Entry
     */
    public JSONObject serialize() {
        JSONObject entry = new JSONObject();

        entry.put("player_id", this.playerID.toString());
        entry.put("value", this.value);

        return entry;
    }

    /** @brief Consulting operation that returns the id of the player
     \pre <em>True</em>
     \post The ID of the player in the Entry has been returned
     @return UUID of the player in the Entry
     */
    public UUID getPlayerID() {
        return this.playerID;
    }

    /** @brief Consulting operation that returns the value of the player
    \pre <em>True</em>
    \post The value of the player in the Entry has been returned
    @return Value of the Entry
     */
    public int getValue() {
        return this.value;
    }

    /** @brief Modifying operation that swaps the playerID in Entry for the parameter newPlayerID
     \pre <em>True</em>
     \post playerID has been changed to newPlayerID
     @param newPlayerID New ID of the player
     */
    public void setPlayerID(UUID newPlayerID) {
        this.playerID = newPlayerID;
    }

    /** @brief Modifying operation that swaps the value in Entry for the parameter newValue
     \pre <em>newValue > 0</em>
     \post value has been changed to newValue
     @param newValue New value of the player
     */
    public void setValue(int newValue) {
        this.value = newValue;
    }

}
