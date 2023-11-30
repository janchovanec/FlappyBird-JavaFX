package java1.cho0252;

/**
 * Interface for listening to game events.
 */
public interface GameListener {
    public void onGameEnd();

    public void onScoreUpdate(int score);
}
