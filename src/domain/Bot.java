/** @file Bot.java
 @brief Bot subclass specification
 */
package domain;

import org.json.JSONObject;

import java.util.UUID;

import domain.Exceptions.InvalidDifficultyException;

/** @class Bot
 @brief Represents a bot in our system

 Done by Arnau Pujantell

 Subclass that represents a bot. It contains a difficulty and a creatorID.
 */

public class Bot extends Player {
    /** @brief bot's difficulty*/
    private int difficulty;
    /** @brief bot's creator ID*/
    private UUID creatorID;


    /**CREATORS*/

    /** @brief Creator that, given a name 'name', a difficulty 'difficulty', an ID id and an ID creatorID, returns a Bot.
     @pre <em>None of the elements is null</em>
     @post It creates a new bot with name 'name', difficulty 'difficulty', id 'id', type 'BOT', isDeleted as 'false' and creatorID
     creatorID.
     */
    public Bot (String name, int difficulty, UUID id, UUID creatorID)
    {
        this.name = name;
        this.difficulty = difficulty;
        this.id = id;
        this.isDeleted = false;
        this.creatorID = creatorID;
    }

    /** @brief Creator that, given a JSONObject bot, deserializes it.
     @pre <em>bot is not null</em>
     @post bot is not a JSONObject anymore
     */
    public Bot(JSONObject bot) {
        this.name = bot.getString("name");
        this.id = UUID.fromString(bot.getString("id"));
        this.difficulty = bot.getInt("difficulty");
        this.isDeleted = bot.getBoolean("is_deleted");
        this.creatorID = UUID.fromString(bot.getString("creator_id"));
    }

    /** @brief Creator that serializes the current object to a JSON Object
     @pre <em>True</em>
     @post The current bot becomes a JSON Object
     */
    public JSONObject serialize() {
        JSONObject bot = new JSONObject();
        bot.put("name", this.name);
        bot.put("id", this.id.toString());
        bot.put("difficulty", this.difficulty);
        bot.put("type", "BOT");
        bot.put("is_deleted", this.isDeleted);
        bot.put("creator_id", this.creatorID.toString());

        return bot;
    }


    /**CONSULTANTS*/

    /** @brief Consultant that returns the implicit parameter's difficulty.
     @pre <em>True</em>
     @post The implicit parameter's difficulty is returned.
      * @return
     */
    public int getDifficulty() {
        return this.difficulty;
    }

    /** @brief Consultant that returns the implicit parameter's creatorID.
     @pre <em>True</em>
     @post The implicit parameter's creatorID is returned.
      * @return
     */
    public UUID getCreatorID() {
        return this.creatorID;
    }

    /**MODIFIERS*/

    /** @brief Modifier that, given a difficulty, changes the implicit parameter's difficulty for a new difficulty 'difficulty'.
     @pre <em>difficulty is not null</em>
     @post The implicit parameter's difficulty has changed.
     */
    public void setDifficulty(int difficulty) throws InvalidDifficultyException {
        if(difficulty < 1 || difficulty > 10) {
            throw new InvalidDifficultyException();
        }
        this.difficulty = difficulty;
    }
}
