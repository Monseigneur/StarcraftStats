/*
 * Milan Justel
 * GameStats
 */

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class GameStats {
  public static final String RACES = "PTZ";
  public static final int NUM_RACES = RACES.length();
  
  private List<String> playerNames;
  private Map<String, Integer> playerMap;
  private List<List<PlayerMatchup>> matches;
  private int numGames;
  
  
  public GameStats(List<String> players) {
    playerNames = players;
    playerMap = new HashMap<String, Integer>();
    for (int index = 0; index < playerNames.size(); index++) {
      playerMap.put(playerNames.get(index), Integer.valueOf(index));
    }
    
    matches = new ArrayList<List<PlayerMatchup>>();
    
    int numPlayers = players.size();
    for (int i = 0; i < numPlayers; i++) {
      List<PlayerMatchup> pm = new ArrayList<PlayerMatchup>();
      for (int j = 0; j < numPlayers; j++) {
        PlayerMatchup p = null;
        if (i != j) {
          p = new PlayerMatchup(playerNames.get(i), playerNames.get(j));
        }
        pm.add(p);
      }
      matches.add(pm);
    }
    numGames = 0;
  }
  
  public void addGame(String p1, String p2, String r1, String r2, boolean p1Won) {
    Set<String> players = playerMap.keySet();
    if (!players.contains(p1) || !players.contains(p2)) {
      throw new IllegalArgumentException("Illegal players");
    }
    
    Game g = new Game(p1, p2, r1, r2, p1Won);
    matches.get(playerMap.get(p1)).get(playerMap.get(p2)).addGame(g);
    matches.get(playerMap.get(p2)).get(playerMap.get(p1)).addGame(g.flipGame());
    numGames++;
  }
  
  public List<String> getPlayers() {
    return playerNames;
  }
  
  public int totalGames() {
    return numGames;
  }
  
  public int gamesForPlayer(String player) {
    return -1;
  }
  
  public void printMatchup(String p1, String p2, boolean percentage) {
    matches.get(playerMap.get(p1)).get(playerMap.get(p2)).print(percentage);
  }
  
  public void printPlayerOverallStats(String p, boolean percentage) {
    List<RaceMatchup> rm = getStats(p);

    RaceMatchup r = new RaceMatchup();
    for (int i = 0; i < rm.size(); i++) {
      r.p1Wins += rm.get(i).p1Wins;
      r.p2Wins += rm.get(i).p2Wins;
    }
    
    String text = percentage ? "Percentage\n" : "";
    System.out.println(text + p + "\t" + r.getString(percentage));
  }
  
  public void printPlayerRaceStats(String p, boolean percentage) {
    List<RaceMatchup> rm = getStats(p);
    
    String text = percentage ? "Percentage" : "\t";
    System.out.println(text);
    System.out.println("\t" + RACES.charAt(0) + "\t" + rm.get(0).getString(percentage));
    System.out.println(p + "\t" + RACES.charAt(1) + "\t" + rm.get(1).getString(percentage));
    System.out.println("\t" + RACES.charAt(2) + "\t" + rm.get(2).getString(percentage));
  }
  
  private List<RaceMatchup> getStats(String p) {
    List<RaceMatchup> rm = new ArrayList<RaceMatchup>();
    for (int i = 0; i < NUM_RACES; i++) {
      rm.add(new RaceMatchup());
    }
    
    for (int i = 0; i < playerNames.size(); i++) {
      if (playerMap.get(p) != i) {
        List<RaceMatchup> r = matches.get(playerMap.get(p)).get(i).getPlayerRaceMatchup();
        for (int j = 0; j < r.size(); j++) {
          rm.get(j).p1Wins += r.get(j).p1Wins;
          rm.get(j).p2Wins += r.get(j).p2Wins;
        }
      }
    }
    return rm;
  }
}
