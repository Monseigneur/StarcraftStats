/**
 * @author Milan
 * Game
 * 
 * Represents a set of wins and loses
 */

public class Game {
  public int wins;
  public int loses;
  
  public Game() {
    wins = 0;
    loses = 0;
  }
  
  public void addMatch(Match g) {
    if (g.player1Won()) {
      wins++;
    } else {
      loses++;
    }
  }
  
  public int total() {
    return wins + loses;
  }
  
  public String getString(boolean percentage) {
    if (percentage) {
      int p1Percent = 0;
      int p2Percent = 0;
      if (total() != 0) {
        p1Percent = (int) Math.round(100.0 * wins / total());
        p2Percent = (int) Math.round(100.0 * loses / total());
      }
      return "[" + p1Percent + ":" + p2Percent + "]";
    } else {
      return "[" + wins + ":" + loses + "]";
    }
  }
}
