/**
 * 
 * @author milanj91
 *
 */

import java.util.List;

public class GameStats {
  private List<String> players;
  private PlayerMatchup[][] matchups;
  
  /**
   * Default constructor
   * @param p A list of players to keep stats for
   */
  public GameStats(List<String> p) {
    players = p;
    
    int len = players.size() - 1;
    matchups = new PlayerMatchup[len][len];
    
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len; j++) {
        if (j >= i) {
          matchups[i][j] = new PlayerMatchup(players.get(i), players.get(j + 1));
        } else {
          matchups[i][j] = null;
        }
      }
    }
  }
  
  /**
   * Adds a match to the stats
   * @param m The match to add
   * @throws IllegalArgumentException if the players are unknown
   */
  public void addMatch(Match m) {
    if (!players.contains(m.player1) || !players.contains(m.player2)) {
      throw new IllegalArgumentException("Unknown player names");
    }
    
    if (PlayerMatchup.RACES.indexOf(m.race1) == -1 || PlayerMatchup.RACES.indexOf(m.race2) == -1) {
      throw new IllegalArgumentException("Unknown player races");
    }
    int[] indices = getPlayerIndices(m.player1, m.player2);
    matchups[indices[0]][indices[1]].addMatch(m);
  }
  
  /**
   * Gives the list of players that are being tracked
   * @return A list of player names
   */
  public List<String> getPlayers() {
    return players;
  }
  
  /* PRIVATE METHODS */
  
  /**
   * Gives the indices for the matchup between two players
   * @param p1 player 1
   * @param p2 player 2
   * @return the two indices into the two dimensional matchup array
   */
  private int[] getPlayerIndices(String p1, String p2) {
    if (p1.equals(p2)) {
      return null;
    } else if (p1.compareTo(p2) < 0) {
      int[] ar = {players.indexOf(p1), players.indexOf(p2) - 1};
      return ar;
    } else {
      int[] ar = {players.indexOf(p2), players.indexOf(p1) - 1};
      return ar;
    }
  }
}
