
/*

 * Milan Justel
 * GameStats
 */

/*
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class GameStats1 {
  public static final String RACES = "PTZ";
  public static final int NUM_RACES = RACES.length();
  
  private List<String> playerNames;
  private Map<String, Integer> playerMap;
  private List<List<PlayerMatchup1>> matches;
  private int numGames;
  
  
  public GameStats1(List<String> players) {
    playerNames = players;
    playerMap = new HashMap<String, Integer>();
    for (int index = 0; index < playerNames.size(); index++) {
      playerMap.put(playerNames.get(index), Integer.valueOf(index));
    }
    
    matches = new ArrayList<List<PlayerMatchup1>>();
    
    int numPlayers = players.size();
    for (int i = 0; i < numPlayers; i++) {
      List<PlayerMatchup1> pm = new ArrayList<PlayerMatchup1>();
      for (int j = 0; j < numPlayers; j++) {
        PlayerMatchup1 p = null;
        if (i != j) {
          p = new PlayerMatchup1(playerNames.get(i), playerNames.get(j));
        }
        pm.add(p);
      }
      matches.add(pm);
    }
    numGames = 0;
  }
  
  public void addGame(String p1, String p2, char r1, char r2, boolean p1Won) {
    Set<String> players = playerMap.keySet();
    if (!players.contains(p1) || !players.contains(p2)) {
      throw new IllegalArgumentException("Illegal players");
    }
    
    Match g = new Match(p1, p2, r1, r2, "");
    matches.get(playerMap.get(p1)).get(playerMap.get(p2)).addGame(g);
    matches.get(playerMap.get(p2)).get(playerMap.get(p1)).addGame(g);
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
  
  public void printTotalGames() {
    System.out.println("Total games in program: " + totalGames());
  }
  
  public void printMatchup(String p1, String p2) {
    if (p1.equals(p2) || !playerMap.containsKey(p1) || !playerMap.containsKey(p2)) {
      System.out.println("Illegal players");
    } else {
      matches.get(playerMap.get(p1)).get(playerMap.get(p2)).print();
    }
  }
  
  public void printPlayerOverallStats(String p) {
    List<Game> rm = getStats(p);

    Game r = new Game();
    for (int i = 0; i < rm.size(); i++) {
      r.wins += rm.get(i).wins;
      r.loses += rm.get(i).loses;
    }
    
    System.out.println(p + "\t" + r.getString(false) + "\t" + r.getString(true));
  }
  
  public void printPlayerRaceStats(String p) {
    List<Game> rm = getStats(p);
    
    System.out.println("\t" + RACES.charAt(0) + "\t" + rm.get(0).getString(false) + "\t" + rm.get(0).getString(true));
    System.out.println(p + "\t" + RACES.charAt(1) + "\t" + rm.get(1).getString(false) + "\t" + rm.get(1).getString(true));
    System.out.println("\t" + RACES.charAt(2) + "\t" + rm.get(2).getString(false) + "\t" + rm.get(2).getString(true));
    System.out.println();
  }
  
  private List<Game> getStats(String p) {
    List<Game> rm = new ArrayList<Game>();
    for (int i = 0; i < NUM_RACES; i++) {
      rm.add(new Game());
    }
    
    for (int i = 0; i < playerNames.size(); i++) {
      if (playerMap.get(p) != i) {
        List<Game> r = matches.get(playerMap.get(p)).get(i).getPlayerRaceMatchup();
        for (int j = 0; j < r.size(); j++) {
          rm.get(j).wins += r.get(j).wins;
          rm.get(j).loses += r.get(j).loses;
        }
      }
    }
    return rm;
  }
}
*/