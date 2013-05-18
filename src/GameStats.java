/**
 * 
 * @author milanj91
 *
 */

import java.util.List;

public class GameStats {
  private List<String> players;
  private PlayerMatchup[][] matchups;
  
  public GameStats(List<String> p) {
    players = p;
    
    int len = players.size() - 1;
    matchups = new PlayerMatchup[len][len];
    
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len; j++) {
        if (i >= j) {
          matchups[i][j] = new PlayerMatchup(players.get(i), players.get(j + 1));
        } else {
          matchups[i][j] = null;
        }
      }
    }
  }
  
  public void addMatch(Match m) {
    String s;
  }
}
