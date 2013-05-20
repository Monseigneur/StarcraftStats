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
    verifyPlayer(m.player1);
    verifyPlayer(m.player2);
    verifyGameRace(m.race1);
    verifyGameRace(m.race2);
    
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
  
  /* GETTER METHODS */
  public Integer[] getSpecificMatchup(String p1, String p2, char r1, char r2) {
    verifyPlayer(p1);
    verifyPlayer(p2);
    verifyGameRace(r1);
    verifyGameRace(r2);
    int[] indices = getPlayerIndices(p1, p2);
    return matchups[indices[0]][indices[1]].getRecordForMatchup(r1, r2);
  }
  
  public int[] getOverallRecord(String p) {
    return null;
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
  
  private void verifyPlayer(String p) {
    if (!players.contains(p)) {
      throw new IllegalArgumentException("Illegal player names");
    }
  }
  
  private void verifyGameRace(char r) {
    if (PlayerMatchup.RACES.indexOf(Character.toUpperCase(r)) == -1) {
      throw new IllegalArgumentException("Illegal player race");
    }
  }
}
