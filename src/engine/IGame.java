package engine;

/**
 * This interface defines the functionality for a Game, specifically adding editing functionality to
 * an IGamePlayable
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IGame extends IGamePlayable {

    /**
     * @return the ILevelManager for this Game
     */
    ILevelManager getLevelManager ();

    /**
     * @return the condition manager for this level
     */
    IConditionManager getConditionManager ();

    /**
     * @return the authoring data for this game
     */
    AuthorshipData getAuthorshipData ();

}
