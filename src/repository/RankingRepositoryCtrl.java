/**
 * @file RankingRepositoryCtrl.java
 * @author Alex Rodriguez
 * @brief RankingRepositoryCtrl class specification.
 */
package repository;

import java.util.ArrayList;

import org.json.JSONObject;

/**
 * @class RankingRepositoryCtrl
 * @brief Implements various CRUD operations to work with the Ranking repository.
 * By Alex Rodriguez.
 * @see repository.RankingRepository
 */
public class RankingRepositoryCtrl {
    /* ATTRIBUTES */

    /**
    * @brief RankingRepository instance.
    */
    private RankingRepository repository;

    /* CONSTRUCTORS */

    /**
     * @brief Create a RankingRepositoryCtrl instance.
     * @pre The Ranking repository JSON files and all the default Rankings exists.
     * @post A RankingRepositoryCtrl instance is created.
     */
    public RankingRepositoryCtrl() {
        this.repository = new RankingRepository();
    }

    /* METHODS */

    /**
     * @brief Save a Ranking into the ranking database.
     * @pre The Ranking repository JSON files and the Ranking to be saved already exists, so it's only updated.
     * @post The Ranking is saved into the ranking database.
     * @param ranking Ranking to be saved.
     */
    public void save(JSONObject ranking) {
        this.repository.save(ranking);
    }

    /**
     * @brief Get the Ranking by name from the ranking database or null if it does not exist.
     * @pre The Ranking repository JSON files and the Ranking identified by name exists.
     * @post A JSONObject representing the Ranking by name from the ranking database is returned or null if it does not exist.
     * @param name Name of the Ranking to be getted.
     * @return JSONObject that represents the Ranking by name from the ranking database or null if it does not exist.
     */
    public JSONObject get(String name) {
        return this.repository.get(name);
    }

    /**
     * @brief List all Rankings of the ranking database.
     * @pre The Ranking repository JSON files and all the default Rankings exists.
     * @post An ArrayList containing the Ranking names of the ranking database is returned.
     * @return ArrayList of the Ranking names of the ranking database.
     */
    public ArrayList<String> listRankings() {
        return this.repository.listRankings();
    }
}
