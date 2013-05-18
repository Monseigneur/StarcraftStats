/**
 * @author Milan
 * Match
 * 
 * Represents a single Starcraft match between two players with their race and the winner
 */

public class Match {
  public String player1;
  public String player2;
  public char race1;
  public char race2;
  boolean p1Won;
  
  /**
   * Default constructor
   * @param p1 Player 1
   * @param p2 Player 2
   * @param r1 Race of player 1
   * @param r2 Race of player 2
   * @param p1Winner If player 1 won the game
   */
  public Match(String p1, String p2, char r1, char r2, String winner) {
    if(p1.equals(p2)) {
      throw new IllegalArgumentException("Illegal players for this game!");
    }
    
    if (p1.compareTo(p2) < 0) {
      player1 = p1;
      player2 = p2;
      race1 = r1;
      race2 = r2;
    } else {
      player1 = p2;
      player2 = p1;
      race1 = r2;
      race2 = r1;
    }
    p1Won = winner.equals(player1);
  }  
}
