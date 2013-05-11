/**
 * @author Milan
 * RaceMatchup
 */

public class RaceMatchup {
  public int p1Wins;
  public int p2Wins;
  
  public RaceMatchup() {
    p1Wins = 0;
    p2Wins = 0;
  }
  
  public void addGame(Game g) {
    if (g.player1Won()) {
      p1Wins++;
    } else {
      p2Wins++;
    }
  }
  
  public int total() {
    return p1Wins + p2Wins;
  }
  
  public String getString(boolean percentage) {
    if (percentage) {
      int p1Percent = 0;
      int p2Percent = 0;
      if (total() != 0) {
        p1Percent = (int) Math.round(100.0 * p1Wins / total());
        p2Percent = (int) Math.round(100.0 * p2Wins / total());
      }
      return "[" + p1Percent + ":" + p2Percent + "]";
    } else {
      return "[" + p1Wins + ":" + p2Wins + "]";
    }
  }
}
