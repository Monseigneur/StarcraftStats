/*
 * Milan Justel
 * GameStats
 */

import java.util.Set;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class GameStats {
  private Set<String> _players;
  private int _numGames;
  private Map<String, Map<String, List<Game>>> _playerGameMap;
  
  
  public GameStats(Set<String> players) {
    _players = players;
    _playerGameMap = new HashMap<String, Map<String, List<Game>>>();
  }
  
  public void addGame(String p1, String p2, String r1, String r2, boolean p1Won) {
    if (!_players.contains(p1) || !_players.contains(p2)) {
      throw new IllegalArgumentException("Illegal players");
    }
    
    Game g = new Game(p1, p2, r1, r2, p1Won);
    String playerMatchup = getKeyForPlayers(p1, p2);
    String raceMatchup = g.getMatchup();
    if (!_playerGameMap.containsKey(playerMatchup)) {
      Map<String, List<Game>> matchupMap = new HashMap<String, List<Game>>();
      _playerGameMap.put(playerMatchup, matchupMap);
    }
    
    if (!_playerGameMap.get(playerMatchup).containsKey(raceMatchup)) {
      List<Game> gameList = new LinkedList<Game>();
      _playerGameMap.get(playerMatchup).put(raceMatchup, gameList);
    }
    _playerGameMap.get(playerMatchup).get(raceMatchup).add(g);
    
    _numGames++;
  }
  
  public Set<String> getPlayers() {
    return _players;
  }
  
  public int totalGames() {
    return _numGames;
  }
  
  public int gamesForPlayer(String player) {
    return 0;
  }
  
  private String getKeyForPlayers(String p1, String p2) {
    if (p1.equals(p2)) {
      return null;
    } else {
      if (p1.compareTo(p2) < 0) {
        return p1 + "v" + p2;
      } else {
        return p2 + "v" + p1;
      }
    }
  }
}
