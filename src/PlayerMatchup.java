/**
 * 
 * @author milanj91
 *
 */

import java.util.Map;
import java.util.HashMap;

public class PlayerMatchup {
  public static final String RACES = "PTZ";
  
  private String player1;
  private String player2;
  private int p1Wins;
  private int p2Wins;
  
  private Map<String, Integer[]> raceMatchupMap;
  
  public PlayerMatchup(String p1, String p2) {
    player1 = p1;
    player2 = p2;
    p1Wins = 0;
    p2Wins = 0;
    
    raceMatchupMap = new HashMap<String, Integer[]>();
    for (int r1 = 0; r1 < RACES.length(); r1++) {
      for (int r2 = 0; r2 < RACES.length(); r2++) {
        String key = RACES.charAt(r1) + "v" + RACES.charAt(r2);
        Integer[] raceMatchupGames = {0, 0};
        raceMatchupMap.put(key, raceMatchupGames);        
      }
    }
  }
  
  public void addMatch(Match m) {
    if (!player1.equals(m.player1) || !player2.equals(m.player2)) {
      throw new IllegalArgumentException("Illegal player for this matchup");
    }
    String key = m.race1 + "v" + m.race2;
    int winnerIndex;
    if (m.p1Won) {
      winnerIndex = 0;
      p1Wins++;
    } else {
      winnerIndex = 1;
      p2Wins++;
    }
    raceMatchupMap.get(key)[winnerIndex]++;
  }
  
  /* Getter Methods */
  // none yet lol
}
