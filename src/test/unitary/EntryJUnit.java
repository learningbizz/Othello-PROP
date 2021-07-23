/** @file EntryJUnit.java
 @brief Specification of class EntryJUnit
 */
package test.unitary;

import domain.Entry;
import org.json.JSONObject;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/** @class EntryJUnit
 @brief Allows JUnit testing of class Entry

 Created by Roger Mollon

 Class that represents a testing of class Entry. It contains tester methods for all public Entry methods
 */

public class EntryJUnit {

    @Test
    public void Entry() {
        UUID playerID = UUID.randomUUID();
        Entry e = new Entry(playerID, 25);
        assertEquals("Entry failed because", playerID, e.getPlayerID());
        assertEquals("Entry failed because", 25, e.getValue());
    }

    @Test
    public void deserialize() {
        Entry e = new Entry(UUID.randomUUID(), 22);
        JSONObject jobj = e.serialize();
        Entry e1 = new Entry(jobj);
        assertEquals("deserialize failed because", e.getPlayerID(), e1.getPlayerID());
        assertEquals("deserialize failed because", e.getValue(), e1.getValue());
    }

    @Test
    public void serialize() {
        Entry e = new Entry(UUID.randomUUID(), 100);
        JSONObject jobj = e.serialize();
        assertEquals("serialize failed because", e.getPlayerID().toString(), jobj.getString("player_id"));
        assertEquals("serialize failed because", 100, jobj.getInt("value"));
    }

    @Test
    public void getPlayerID() {
        UUID playerID = UUID.randomUUID();
        Entry e = new Entry(playerID, 50);
        assertEquals("getPlayerId failed because", playerID, e.getPlayerID());
    }

    @Test
    public void getValue() {
        Entry e = new Entry(UUID.randomUUID(), 75);
        assertEquals("getValue failed because", 75, e.getValue());
    }

    @Test
    public void setPlayerID() {
        UUID playerID = UUID.randomUUID();
        Entry e = new Entry(UUID.randomUUID(), 150);
        e.setPlayerID(playerID);
        assertEquals("setPlayerID failed because", playerID, e.getPlayerID());
    }

    @Test
    public void setValue() {
        Entry e = new Entry(UUID.randomUUID(), 180);
        e.setValue(150);
        assertEquals("setValue failed because", 150, e.getValue());
    }
}
