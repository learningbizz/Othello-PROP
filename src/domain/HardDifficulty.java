/**
 * @file HardDifficulty.java
 * @author Roger Mollon
 * @brief HardDifficulty class specification.
 */
package domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import domain.Board.PieceType;
import util.Pair;

/**
 * @class HardDifficulty
 * @brief Implements the Monte Carlo Tree Search algorithm to get the next best possible position for a given player.
 * By Roger Mollon
 */
public class HardDifficulty extends Difficulty {
    /* ATTRIBUTES */

    /**
     * @brief Random number used in the UCT (Upper Confidence bounds applied to Trees) formula to break ties when choosing a path
     */
    private static Random random = new Random();
    /**
     * @brief Small number used to prevent divisions by zero
     */
    private static double epsilon = 1e-6;

    /* CONSTRUCTORS */

    /**
     * @brief Create a HardDifficulty instance.
     * @pre The given difficulty is a positive number. The given rules are not all false.
     * @post A HardDifficulty instance is created and its implicits difficulty, canEatHorizontally, canEatVertically,
     * canEatDiagonally and pieceType attributes are setted. The implicit maxDepth attribute is setted to 1000 times the entered difficulty.
     * @param difficulty Difficulty for the Monte Carlo Tree Search algorithm.
     * @param canEatHorizontally Whether the pieces of the current Game can be eaten horizontally.
     * @param canEatVertically Whether the pieces of the current Game can be eaten vertically.
     * @param canEatDiagonally Whether the pieces of the current Game can be eaten diagonally.
     * @param pieceType Player that wants to be maximized.
     */
    public HardDifficulty(Integer difficulty, Boolean canEatHorizontally, Boolean canEatVertically,
            Boolean canEatDiagonally, PieceType pieceType) {
        super(difficulty, canEatHorizontally, canEatVertically, canEatDiagonally, pieceType);
        this.maxDepth = difficulty * 1000;
    }

    /* METHODS */


    /**
     * @brief Get the next best possible position for the implicit player.
     * @pre <em>True</em>
     * @post It is returned the next best possible position for the implicit player, using the Monte Carlo Tree Search algorithm with
     * the implicit maximum depth, or null if there isn't any.
     * @param playingBoard Current playing Board.
     * @return The next best possible position for the implicit player or null if there isn't any.
     */
    @Override
    public Pair<Integer, Integer> place(PieceType[][] playingBoard) {
        Pair<Integer, Integer> bestPosition = null;

        Board initialBoard = new Board(playingBoard);

        TreeNode rootGame = new TreeNode(
            this.pieceType, this.pieceType, initialBoard, this.canEatHorizontally, this.canEatVertically, this.canEatDiagonally, null);

        for (int i = 0; i < this.maxDepth; ++i) rootGame.play();

        ArrayList<TreeNode> playedGames = rootGame.getChildren();

        double maxWinRatio = Double.NEGATIVE_INFINITY;
        for (TreeNode game: playedGames) {
            if (game.getWinRatio() > maxWinRatio) {
                maxWinRatio = game.getWinRatio();
                bestPosition = game.getSelectedPosition();
            }
        }

        return bestPosition;
    }

    private class TreeNode {
        /* ATTRIBUTES */

        /**
        * @brief Possible future moves and board states that can be obtained given the currrent board state
        */
        private ArrayList<TreeNode> children;
        /**
        * @brief Number of times a TreeNode has been traversed
        */
        private double nVisits;
        /**
        * @brief Number of wins obtained in this TreeNode
        */
        private double totValue;
        /**
        * @brief Move that produces the current board state. It can be null since you can have the first board state, which isn't produced by a move
        */
        private Pair<Integer, Integer> selectedPosition;
        /**
        * @brief PieceType used to identify whose turn to make a move is between Player1 (White) and Player2 (Black) 
        */
        private PieceType pieceType;
        /**
         * @brief PieceType used to identify whose turn it is in the initial board state
         */
        private PieceType rootType;
        /**
        * @brief Current board state in an instance of a game
        */
        private Board currentBoard;
        /**
        * @brief Whether the pieces of the current Game can be eaten horizontally.
        */
        private boolean canEatHorizontally;
        /**
        * @brief Whether the pieces of the current Game can be eaten vertically.
        */
        private boolean canEatVertically;
        /**
        * @brief Whether the pieces of the current Game can be eaten diagonally.
        */
        private boolean canEatDiagonally;

        /* CONSTRUCTORS */

        /**
        * @brief Create a TreeNode instance.
        * @pre The given rules are not all false.
        * @post A TreeNode instance is created and its implicits rootType, pieceType, currentBoard, canEatHorizontally, canEatVertically, canEatDiagonally and selectedPosition attributes are setted.
        * @param rootType PieceType of the root TreeNode of a tree.
        * @param pieceType PieceType used to know whose turn it is to make a move.
        * @param currentBoard Current state of the board.
        * @param canEatHorizontally Whether the pieces of the current Game can be eaten horizontally.
        * @param canEatVertically Whether the pieces of the current Game can be eaten vertically.
        * @param canEatDiagonally Whether the pieces of the current Game can be eaten diagonally.
        * @param selectedPosition Position in the board that resulted in the current board state (it can be null).
        */
        public TreeNode(PieceType rootType, PieceType pieceType, Board currentBoard, boolean canEatHorizontally,
            boolean canEatVertically, boolean canEatDiagonally, Pair<Integer, Integer> selectedPosition) {
            this.rootType = rootType;
            this.pieceType = pieceType;
            this.currentBoard = currentBoard;
            this.canEatHorizontally = canEatHorizontally;
            this.canEatVertically = canEatVertically;
            this.canEatDiagonally = canEatDiagonally;
            this.children = new ArrayList<TreeNode> ();
            this.nVisits = 0;
            this.totValue = 0;
            this.selectedPosition = selectedPosition;
        }

        /* METHODS */

        /**
         * @brief Returns whether the implicit TreeNode has possible future moves or not.
         * @pre <em>True</em> 
         * @post A boolean which has value true if the implicit TreeNode has future moves or false otherwise is returned.
         * @return Boolean which tells whether the children attribute of the implicit TreeNode is empty or not.
         */
        public boolean isLeaf() {
            return this.children.isEmpty();
        }

        /**
         * @brief Returns the selectedPosition attribute of the implicit TreeNode.
         * Since the initial board state doesn't have a selectedPosition, this method can return null.
         * @pre <em>True</em> 
         * @post The implicit TreeNode's selectedPosition, which can be either a position of the board or null, is returned.
         * @return Pair of Integers which represents a position inside of a board.
         */
        public Pair<Integer, Integer> getSelectedPosition() {
            return this.selectedPosition;
        }

        /**
         * @brief Returns the win ratio of a TreeNode, which is the result of the division of attribute totValue, 
         which represents the number of wins in the implicit TreeNode and the attribute nVisits, 
         which represents the number of times the implicit TreeNode has been visited. Since nVisits is initialized with value 0 
         we use the attribute epsilon to prevent division by 0.
         * @pre <em>True</em> 
         * @post The implicit TreeNode's win ratio is returned.
         * @return Double equal to the division between totValue and nVisits of the implicit TreeNode.
         */
        public double getWinRatio() {
            return (this.totValue/(this.nVisits + HardDifficulty.epsilon));
        }

        /**
         * @brief Returns the implicit TreeNode's private attribute children, which represents future board states obtained from the current state.
         * @pre <em>True</em> 
         * @post The implicit TreeNode's children attribute is returned.
         * @return ArrayList which acts as a representation of the possible future states of a board.
         */
        public ArrayList<TreeNode> getChildren() {
            return this.children;
        }

        /**
         * @brief Simulation of a game used as the basis of the Monte Carlo Tree Search algorithm.
         * Given the root TreeNode of the stats tree, which represents the initial board state, it traverses
         the tree using the UCT formula to select, in every TreeNode, which of its future states is best.
         Once it reaches an unexplored TreeNode, it generates its children TreeNodes and picks the best out of them 
         using the UCT formula once more. After this, based on the number of pieces of each player, it returns whether there has been a win or not,
         and every single TreeNode that was traversed to get to that state is updated based on the outcome.
         * @pre <em>True</em> 
         * @post The simulation of the game is done and the tree is updated based on the outcome of that simulation.
         */
        public void play() {
            List<TreeNode> visited = new LinkedList<TreeNode>();
            TreeNode current = this;
            visited.add(this);

            while (!current.isLeaf()) {
                current = current.select();
                visited.add(current);
            }

            current.expand();

            if(current.isLeaf()) {
                double value = current.rollOut();
                for(TreeNode node : visited) node.updateStats(value);
            }

            else {
                TreeNode bestChild = current.select();
                visited.add(bestChild);
                double value = bestChild.rollOut();
                for (TreeNode node : visited) node.updateStats(value);
            }
        }

        /**
         * @brief Method that gets the best move to play out of the implicit TreeNode's children attribute.
         * This is done using the UCT formula to compare each TreeNode and get the best one of them.
         * UCT takes into consideration the percentage of wins of the TreeNode and if it has been explored very few times.
         * In the case of a tie between two different candidates, the random attribute is used to break the tie.
         * Since a TreeNode could have no possible future states, this method can return null.
         * @pre <em>True</em> 
         * @post The best Node of the next board states of the implicit TreeNode or null is returned.
         * @return TreeNode with the best TreeNode value based on the UCT formula out of all 
         * the TreeNodes in attribute children of the implicit TreeNode.
         */
        private TreeNode select() {
            TreeNode selected = null;
            double bestValue = Double.NEGATIVE_INFINITY;

            for (TreeNode child : this.children) {
                double uctValue = child.totValue / (child.nVisits + HardDifficulty.epsilon) +
                            Math.sqrt(Math.log(this.nVisits+1) / (child.nVisits + HardDifficulty.epsilon)) +
                            HardDifficulty.random.nextDouble() * HardDifficulty.epsilon;

                if (uctValue > bestValue) {
                    bestValue = uctValue;
                    selected = child;
                }
            }

            return selected;
        }

        /**
         * @brief Generates the next board states of a game given the implicit TreeNode's currentBoard
         * and saves them in the implicit TreeNode's children attribute. Since a board state of a TreeNode 
         * could have no possible future moves, then it could occur that children is left unchanged after the method. 
         * @pre <em>True</em> 
         * @post If a board state has next states that can be obtained, TreeNodes that represent them will be generated
         * and saved in the implicit TreeNode's children attribute. If that isn't the case, then children remains the same
         * as before calling this function.
         */
        private void expand() {
            ArrayList<Pair<Integer,Integer>> validPositions = this.currentBoard.validPositions(
                this.pieceType, this.canEatHorizontally, this.canEatVertically, this.canEatDiagonally);

            for (int i = 0; i < validPositions.size(); ++i) {
                Board board = new Board(this.currentBoard.getBoard());
                board.placePiece(validPositions.get(i), this.pieceType, this.canEatHorizontally,
                    this.canEatVertically, this.canEatDiagonally);

                this.children.add(i, new TreeNode(this.rootType, HardDifficulty.inversePieceType(this.pieceType), board, this.canEatHorizontally,
                    this.canEatVertically, this.canEatDiagonally, validPositions.get(i)));
            }
        }

        /**
         * @brief Returns whether the implicit TreeNode's currentBoard would result in a win or a loss for the indicated player.
         * The indicated player is known using the rootType attribute. 
         * @pre <em>True</em> 
         * @post If rootType is equal to PLAYER1 and the number of pieces of PLAYER1 is greater than the number of pieces of PLAYER2
         * this method returns 1, if not it returns 0. If instead rootType is equal to PLAYER2 and the number of pieces of PLAYER2
         * is greater than the number of pieces of PLAYER1 it returns 1, and if not it returns 0.
         * @return Double which is equal to 1 if the rootType's number of pieces is greater than the opposing PieceType's number of pieces, and 0 otherwise.
         */
        private double rollOut() {
            int piecesPlayer1 = this.currentBoard.getPiecesPlayer1();
            int piecesPlayer2 = this.currentBoard.getPiecesPlayer2();
            if(this.rootType == PieceType.PLAYER1) {
                if(piecesPlayer1 > piecesPlayer2) return 1;
                else return 0;
            }
            else {
                if(piecesPlayer2 > piecesPlayer1) return 1;
                else return 0;
            }
        }

        /**
         * @brief Updates information on the stats tree when a simulation is finished. 
         * @pre <em>True</em> 
         * @post For every single one of the TreeNodes traversed to get to the ending of a simulation, its number of visits is increased by 1 
         * and its number of wins changes based on the parameter value.
         * @param value Double which equals either 1 or 0 and represents whether the final board state of a simulation ended in a victory or
         * in a loss.
         */
        private void updateStats(double value) {
            ++this.nVisits;
            this.totValue += value;
        }
    }
}
