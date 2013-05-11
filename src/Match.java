/**
 * @author Milan
 * Match
 * 
 * Represents a single Starcraft match between two players with their race and the winner
 */

public class Match {
  public String player1;
  public String player2;
  public String race1;
  public String race2;
  boolean p1Won;
  
  /**
   * Default constructor
   * @param p1 Player 1
   * @param p2 Player 2
   * @param r1 Race of player 1
   * @param r2 Race of player 2
   * @param p1Winner If player 1 won the game
   */
  public Match(String p1, String p2, String r1, String r2, boolean p1Winner) {
    player1 = p1;
    player2 = p2;
    race1 = r1;
    race2 = r2;
    p1Won = p1Winner;
  }
  
  /**
   * Returns the race for the given player
   * @param p The player
   * @return The race for the given player
   * @throws IllegalArgumentException if the player is not in this match
   */
  public String getRaceForPlayer(String p) {
    if (!p.equals(player1) && !p.equals(player2)) {
      throw new IllegalArgumentException();
    }
    
    if (p.equals(player1)) {
      return race1;
    } else {
      return race2;
    }
  }
  
  /**
   * Returns if player 1 won the game
   * @return if player 1 won
   */
  public boolean player1Won() {
    return p1Won;
  }
  
  /**
   * Returns the name of the winning player
   * @return the name of the winning player
   */
  public String getWinner() {
    if (p1Won) {
      return player1;
    } else {
      return player1;
    }
  }
  
  /**
   * Flips the match
   * @return a Match representing the game from player 2's perspective
   */
  public Match flipGame() {
    return new Match(player2, player1, race2, race1, !p1Won);
  }
}
