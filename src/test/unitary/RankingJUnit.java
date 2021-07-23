/** @file RankingJUnit.java
 @brief Specification of class RankingJUnit
 */
package test.unitary;

import domain.Entry;
import domain.Ranking;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/** @class RankingJUnit
 @brief Allows JUnit testing of class Ranking

 Created by Roger Mollon

 Class that represents a testing of class Ranking. It contains tester methods for all public Ranking methods
 */

public class RankingJUnit {

    @Test
    public void Ranking() {
        Ranking r = new Ranking("win_percentage");
        assertEquals("Ranking failed because", "win_percentage", r.getName());
        assertEquals("Ranking failed because", new ArrayList<Entry>(), r.getEntries());
    }

    private int randomInt(Integer min, Integer max) {
        return min+(int)(Math.random()*((max-min)+1));
    }

    @Test
    public void deserialize() {
        Ranking r = new Ranking("number_of_games");
        for(int i=0; i<5; ++i) r.addEntry(new Entry(UUID.randomUUID(), randomInt(0,i)));
        Ranking r1 = new Ranking(r.serialize());

        assertEquals("deserialize failed because", r.getName(), r1.getName());
        for(int j=0; j<5; ++j) {
            Entry entry = r.getEntries().get(j);
            Entry entry1 = r1.getEntries().get(j);
            assertEquals("deserialize failed because", entry.getPlayerID(), entry1.getPlayerID());
            assertEquals("deserialize failed because", entry.getValue(), entry1.getValue());
        }
    }

    @Test
    public void serialize() {
        Ranking r = new Ranking("number_of_pieces");
        for(int i=0; i<4; ++i) r.addEntry(new Entry(UUID.randomUUID(), randomInt(0,i)));
        JSONObject jranking = r.serialize();
        assertEquals("serialize failed because", r.getName(), jranking.getString("name"));
        JSONArray entries = jranking.getJSONArray("entries");
        for(int i=0; i<entries.length(); ++i) {
            assertEquals("serialize failed because", entries.getJSONObject(i).getString("player_id"), r.getEntries().get(i).getPlayerID().toString());
            assertEquals("serialize failed because", entries.getJSONObject(i).getInt("value"), r.getEntries().get(i).getValue());
        }
    }

    @Test
    public void getRecord() {
        Ranking r = new Ranking("time");
        UUID playerID = UUID.randomUUID();

        // Case 1
        assertEquals("Record failed because", null, r.getRecord(playerID));

        // Case 2
        for(int i=0; i<4; ++i) r.addEntry(new Entry(UUID.randomUUID(), randomInt(0,i)));
        assertEquals("Record failed because", null, r.getRecord(playerID));

        // Case 3
        r.addEntry(new Entry(playerID, 6));
        assertEquals("Record failed because", new Entry(playerID, 6).getValue(), r.getRecord(playerID).getValue());

        // Case 4
        r.addEntry(new Entry(playerID, 6));
        r.addEntry(new Entry(playerID, 7));
        r.addEntry(new Entry(playerID, 5));
        r.addEntry(new Entry(playerID, 7));
        assertEquals("Record failed because", new Entry(playerID, 7).getValue(), r.getRecord(playerID).getValue());
    }

    @Test
    public void addEntry() {
        Ranking r = new Ranking("number_of_wins");
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();

        ArrayList<UUID> player_ids = new ArrayList<UUID>();
        ArrayList<Integer> player_values = new ArrayList<Integer>();

        // Case 1
        player_ids.add(0, id);
        player_values.add(0, 5);
        r.addEntry(new Entry(id, 5));
        for(int i=0; i < r.getEntries().size(); ++i) {
            assertEquals("addEntry failed because", player_ids.get(i), r.getEntries().get(i).getPlayerID());
            assertEquals("addEntry failed because",(int) player_values.get(i), r.getEntries().get(i).getValue());
        }

        // Case 2
        player_ids.add(1, id1);
        player_values.add(1, 2);
        r.addEntry(new Entry(id1, 2));
        for(int i=0; i < r.getEntries().size(); ++i) {
            assertEquals("addEntry failed because", player_ids.get(i), r.getEntries().get(i).getPlayerID());
            assertEquals("addEntry failed because",(int) player_values.get(i), r.getEntries().get(i).getValue());
        }

        // Case 3
        player_values.add(0, 25);
        player_ids.add(0, id2);
        r.addEntry(new Entry(id2, 25));
        for(int i=0; i < r.getEntries().size(); ++i) {
            assertEquals("addEntry failed because", player_ids.get(i), r.getEntries().get(i).getPlayerID());
            assertEquals("addEntry failed because",(int) player_values.get(i), r.getEntries().get(i).getValue());
        }

        // Case 4
        player_ids.add(1, id3);
        player_values.add(1, 10);
        r.addEntry(new Entry(id3, 10));
        for(int i=0; i<r.getEntries().size(); ++i) {
            assertEquals("addEntry failed because", player_ids.get(i), r.getEntries().get(i).getPlayerID());
            assertEquals("addEntry failed because",(int) player_values.get(i), r.getEntries().get(i).getValue());
        }

        // Case 5
        player_ids.add(0, id3);
        player_values.add(0, 25);
        r.addEntry(new Entry(id3, 25));
        for(int i=0; i<r.getEntries().size(); ++i) {
            assertEquals("addEntry failed because", player_ids.get(i), r.getEntries().get(i).getPlayerID());
            assertEquals("addEntry failed because",(int) player_values.get(i), r.getEntries().get(i).getValue());
        }

        // Case 6
        player_ids.add(5, id3);
        player_values.add(5, 0);
        r.addEntry(new Entry(id3, 0));
        for(int i=0; i<r.getEntries().size(); ++i) {
            assertEquals("addEntry failed because", player_ids.get(i), r.getEntries().get(i).getPlayerID());
            assertEquals("addEntry failed because",(int) player_values.get(i), r.getEntries().get(i).getValue());
        }

        // Case 7
        player_ids.add(0, id3);
        player_values.add(0, 69);
        r.addEntry(new Entry(id3, 69));
        for(int i=0; i<r.getEntries().size(); ++i) {
            assertEquals("addEntry failed because", player_ids.get(i), r.getEntries().get(i).getPlayerID());
            assertEquals("addEntry failed because",(int) player_values.get(i), r.getEntries().get(i).getValue());
        }

    }

    @Test
    public void getName() {
        Ranking r = new Ranking("number_of_ties");
        assertEquals("getName failed because", "number_of_ties", r.getName());
    }

    @Test
    public void getEntries() {
        Ranking r = new Ranking("number_of_losses");
        ArrayList<UUID> expectedIDs = new ArrayList<UUID> ();
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        expectedIDs.add(id);
        expectedIDs.add(id3);
        expectedIDs.add(id2);
        expectedIDs.add(id1);

        r.addEntry(new Entry(id, 52));
        r.addEntry(new Entry(id1, 7));
        r.addEntry(new Entry(id2, 15));
        r.addEntry(new Entry(id3, 40));

        ArrayList<Integer> expectedValue = new ArrayList<Integer>();
        expectedValue.add(52);
        expectedValue.add(40);
        expectedValue.add(15);
        expectedValue.add(7);

        for(int i=0; i<4; ++i) {
            assertEquals("getEntries failed because", expectedIDs.get(i), r.getEntries().get(i).getPlayerID());
            assertEquals("getEntries failed because",(int) expectedValue.get(i), r.getEntries().get(i).getValue());
        }
    }
}
