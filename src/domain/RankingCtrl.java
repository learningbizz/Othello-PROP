/**
 * @file RankingCtrl.java
 * @author Alex Rodriguez
 * @brief RankingCtrl class specification.
 */
package domain;

import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONObject;

import domain.Ranking.RankingType;
import repository.RankingRepositoryCtrl;

import util.Pair;

/**
 * @class RankingCtrl
 * @brief Ranking domain sub-controller.
 * It communicates with the main domain controller and the ranking repository controller.
 * By Alex Rodriguez.
 * @see domain.Ranking
 */
public class RankingCtrl {
    /* ATTRIBUTES */

    /**
    * @brief Ranking repository controller.

    */
    private RankingRepositoryCtrl repositoryCtrl;

    /* CONSTRUCTORS */

    /**
     * @brief Creator method that creates an instance of Ranking Controller.
     * @pre <em>True</em>
     * @post An instsance of Ranking Control is created.
     */
    public RankingCtrl() {
        this.repositoryCtrl = new RankingRepositoryCtrl();
    }

    /* METHODS */

    /**
     * @brief Returns the ranking identified by name.
     * @pre The Ranking repository JSON files and the Ranking identified by name exists.
     * @post The Ranking identified by name is returned
     * @param name Name of a ranking
     * @return Ranking identified by name
     */
    public Ranking getRanking(String name) {
        JSONObject ranking = this.repositoryCtrl.get(name);
        if (ranking == null)
            return null;

        return new Ranking(ranking);
    }

    /**
     * @brief Returns a list of all ranking names in the system. 
     * @pre The Ranking repository JSON files and the default Rankings exists.
     * @post The list of names of rankings are returned in an ArrayList of Strings.
     * @return ArrayList of Strings
     */
    public ArrayList<String> listRankings() {
        return this.repositoryCtrl.listRankings();
    }

    /**
     * @brief Returns the entries with the highest score of the current user for each ranking in the system.
     * @pre PlayerID is not null
     * @post Returns a list of entries which corresponds to the records of the playerID
     * @param playerID UUID of a Player
     * @return List of records of a Player
     */
    public ArrayList<Pair<String, Entry>> listRecords(UUID playerID) {
        ArrayList<String> rankings = this.repositoryCtrl.listRankings();
        ArrayList<Pair<String, Entry>> records = new ArrayList<Pair<String, Entry>>();

        for (String name : rankings) {
            Ranking ranking = this.getRanking(name);
            if (ranking != null) {
                Entry record = ranking.getRecord(playerID);
                if (record != null)
                    records.add(new Pair<String, Entry>(name, record));
            }
        }

        return records;
    }

    /**
     * @brief Lets the system to automatically create an entry of the associated ranking when the current user finishes a game.
     * @pre Parameters aren't null
     * @post Returns the created entry
     * @param rankingName Name of a Ranking
     * @param playerID UUID of a Player
     * @param value Value of an entry
     * @param rankingType Type of Ranking
     * @return Entry
     */
    public Entry createEntry(String rankingName, UUID playerID, Integer value, RankingType rankingType) {
        Ranking ranking = this.getRanking(rankingName);
        if (ranking == null)
            ranking = new Ranking(rankingName);

        switch (rankingType) {
            case INCREMENTAL:
                Entry oldRecord = ranking.getRecord(playerID);
                if (oldRecord != null)
                    value += oldRecord.getValue();
            case UNIQUE:
                ranking.removePlayer(playerID);
            case MULTIPLE:
            default:
                break;
        }

        Entry entry = new Entry(playerID, value);
        ranking.addEntry(entry);

        this.repositoryCtrl.save(ranking.serialize());

        return entry;
    }
}
