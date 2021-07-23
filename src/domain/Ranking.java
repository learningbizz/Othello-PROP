/** @file Ranking.java
 @brief Specification of class Ranking
 */

package domain;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

/** @class Ranking
 @brief Representation of a ranking table

 Created by Roger Mollon

 Class that represents a ranking table. Contains the tableName and its Entries. The table is ordered by values
 */

public class Ranking {
    public enum RankingType {
        MULTIPLE, UNIQUE, INCREMENTAL
    };

    /** @brief Name of the table*/
    private String name;
    /** @brief Ranking table*/
    private ArrayList<Entry> entries;

    /** @brief Builder operation that gets a name for a new Ranking as a parameter and creates an empty Ranking
     \pre <em>True</em>
     \post An empty Ranking of name entriesName has been created
     @param name Name of the table to be created
     */
    public Ranking(String name) {
        this.entries = new ArrayList<Entry>();
        this.name = name;
    }

    /** @brief Builder operation that creates a new Ranking based on parameter ranking
     \pre <em>True</em>
    \post A Ranking with its attributes based on ranking has been created
     @param ranking JSONObject with the attributes of the implicit Ranking
     */
    public Ranking(JSONObject ranking) {
        this.name = ranking.getString("name");
        this.entries = new ArrayList<Entry>();
        JSONArray entries = ranking.getJSONArray("entries");
        for(int i=0; i<entries.length(); ++i) this.entries.add(i, new Entry(entries.getJSONObject(i)));
    }

    /** @brief Operation that translates a Ranking into a JSONObject
     \pre <em>True</em>
     \post A new JSONObject with information from implicit Ranking has been returned
     @return JSONObject with attributes from implicit Ranking
     */
    public JSONObject serialize() {
        JSONObject ranking = new JSONObject();
        ranking.put("name", this.name);
        JSONArray entries = new JSONArray();
        for(Entry e: this.entries) entries.put(e.serialize());
        ranking.put("entries", entries);
        return ranking;
    }

    /** @brief Consulting operation that returns the record of the player with the playerID passed as a parameter
     \pre <em>True</em>
     \post The first entry from the player has been returned if possible
     @param playerID ID of the player whose record will be returned
     @return First Entry of the requested player in case the player has at least 1 instance. If not it returns null
     */
    public Entry getRecord(UUID playerID) {
        for(Entry entry : this.entries)
            if(entry.getPlayerID().equals(playerID))
                return entry;
        return null;
    }

    /** @brief Modifying operation that removes a player's entries from the implicit ranking.
     \pre <em>True</em>
     \post All the player's entries have been removed from the implicit ranking.
     @param playerID Player ID to remove from.
     */
    public void removePlayer(UUID playerID) {
        ArrayList<Entry> newEntries = new ArrayList<Entry>();

        for(Entry entry : this.entries)
            if(!entry.getPlayerID().equals(playerID))
                newEntries.add(entry);
        
        this.entries = newEntries;
    }

    /** @brief Private method that returns where to place parameter value in the ranking table based on a binary search
     \pre <em>value > 0, start >= 0 and end <= this.entries.size()</em>
     \private The position in which to insert in the ranking table has been returned
     @param value Value of the Entry to be placed in the ranking table
     @param start Starting place of the segment of the ranking table to check
     @param end Ending place of the segment of the ranking table to check
     @return Position of the value in the ranking table
     */
    private int whereInsert(int value, int start, int end) {
        if(start == end) {
            if(start == this.entries.size()) return start;
            if(this.entries.get(start).getValue() > value) return start+1;
            else return start;
        }
        if(start > end) return start;

        int middle = (start+end)/2;
        int current = this.entries.get(middle).getValue();

        if(current > value) return whereInsert(value, middle+1, end);
        else if(current < value) return whereInsert(value, start, middle-1);
        else return middle;
    }

    /** @brief Modifying operation that adds the parameter entry to the ranking table
     \pre <em>True</em>
     \post A new Entry has been correctly added to the ranking table
     @param entry Entry added to Ranking
     */
    public void addEntry(Entry entry) {
        this.entries.add(this.whereInsert(entry.getValue(), 0, this.entries.size()), entry);
    }

    /** @brief Consulting operation that returns the implicit Ranking's name
     \pre <em>True</em>
     \post String name from the implicit Ranking has been returned
     @return String name
     */
    public String getName() {
        return this.name;
    }

    /** @brief Consulting operation that returns the implicit Ranking's ArrayList
     \pre <em>True</em>
     \post ArrayList<Entry> entries has been returned
     @return ArrayList<Entry> entries
     */
    public ArrayList<Entry> getEntries() {
        return this.entries;
    }
}
