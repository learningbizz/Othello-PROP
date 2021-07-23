/**
 * @file RankingRepository.java
 * @author Alex Rodriguez
 * @brief RankingRepository class specification.
 */
package repository;

import java.util.ArrayList;

import org.json.JSONObject;

/**
 * @class RankingRepository
 * @brief Implements various CRUD operations to work with the Ranking repository.
 * By Alex Rodriguez.
 * @see repository.Repository
 */
public class RankingRepository extends Repository {
    /* ATTRIBUTES */

    /* CONSTRUCTORS */

    /**
     * @brief Create a RankingRepository instance.
     * @pre The Ranking repository JSON files and all the default Rankings exists.
     * @post A RankingRepository instance is created.
     */
    public RankingRepository() {
        super(RepositoryType.RANKING);
    }

    /* METHODS */

    /**
     * @brief Save a Ranking into the ranking database.
     * @pre The Ranking repository JSON files and the Ranking to be saved already exists, so it's only updated.
     * @post The Ranking is saved into the ranking database.
     * @param ranking Ranking to be saved.
     */
    public void save(JSONObject ranking) {
        String name = ranking.getString("name");
        this.createOrUpdate(name, ranking);
    }

    /**
     * @brief Get the Ranking by name from the ranking database or null if it does not exist.
     * @pre The Ranking repository JSON files and the Ranking identified by name exists.
     * @post A JSONObject representing the Ranking by name from the ranking database is returned or null if it does not exist.
     * @param name Name of the Ranking to be getted.
     * @return JSONObject that represents the Ranking by name from the ranking database or null if it does not exist.
     */
    public JSONObject get(String name) {
        return super.get(name);
    }

    /**
     * @brief List all Rankings of the ranking database.
     * @pre The Ranking repository JSON files and all the default Rankings exists.
     * @post An ArrayList containing the Ranking names of the ranking database is returned.
     * @return ArrayList of the Ranking names of the ranking database.
     */
    public ArrayList<String> listRankings() {
        JSONObject all = this.list();
        return new ArrayList<String>(all.keySet());
    }
}
