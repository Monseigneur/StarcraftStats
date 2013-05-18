/**
 * @author Milan
 * PlayerMatchup
 * 
 * Represents a two player match up from one player's perspective with all races
 */

import java.util.List;
import java.util.ArrayList;

public class PlayerMatchup1 {
  private String p1;
  private String p2;
  private List<List<Game>> raceMatches;
  
  public PlayerMatchup1(String player1, String player2) {
    p1 = player1;
    p2 = player2;
    
    raceMatches = new ArrayList<List<Game>>();
    
    for (int i = 0; i < GameStats1.NUM_RACES; i++) {
      List<Game> games = new ArrayList<Game>();
      for (int j = 0; j < GameStats1.NUM_RACES; j++) {
        games.add(new Game());
      }
      raceMatches.add(games);
    }
  }
  
  public void addGame(Match g) {
    int p1Index = GameStats1.RACES.indexOf(g.getRaceForPlayer(p1));
    int p2Index = GameStats1.RACES.indexOf(g.getRaceForPlayer(p2));
    
    raceMatches.get(p1Index).get(p2Index).addMatch(g);
  }
  
  public void print() {
    String p1Line1 = "";
    String p1Line2 = "";
    String p1Line3 = "";
    
    for (int i = 0; i < GameStats1.NUM_RACES; i++) {
      p1Line1 += "\t" + raceMatches.get(0).get(i).getString(false);
      p1Line2 += "\t" + raceMatches.get(1).get(i).getString(false);
      p1Line3 += "\t" + raceMatches.get(2).get(i).getString(false);
    }
    
    p1Line1 += "\t";
    p1Line2 += "\t";
    p1Line3 += "\t";
    
    for (int i = 0; i < GameStats1.NUM_RACES; i++) {
      p1Line1 += "\t" + raceMatches.get(0).get(i).getString(true);
      p1Line2 += "\t" + raceMatches.get(1).get(i).getString(true);
      p1Line3 += "\t" + raceMatches.get(2).get(i).getString(true);
    }

    System.out.println("\t\t\t" + p2 + "\t\t\t\tPercentage");
    String headerLine = GameStats1.RACES.charAt(0) + "\t" + GameStats1.RACES.charAt(1) + "\t" + GameStats1.RACES.charAt(2);
    System.out.println("\t\t" + headerLine + "\t\t" + headerLine);
    System.out.println("\t" + GameStats1.RACES.charAt(0) + p1Line1);
    System.out.println(p1 + "\t" + GameStats1.RACES.charAt(1) + p1Line2);
    System.out.println("\t" + GameStats1.RACES.charAt(2) + p1Line3);
    System.out.println();
  }
  
  public List<Game> getPlayerRaceMatchup() {
    List<Game> games = new ArrayList<Game>();
    
    for (int i = 0; i < GameStats1.NUM_RACES; i++) {
      Game g = new Game();
      for (int j = 0; j < GameStats1.NUM_RACES; j++) {
        g.wins += raceMatches.get(i).get(j).wins;
        g.loses += raceMatches.get(i).get(j).loses;
      }
      games.add(g);
      
    }
    return games;
  }
}
