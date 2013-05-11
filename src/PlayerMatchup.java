/**
 * Milan Justel
 * Matchup
 */

import java.util.List;
import java.util.ArrayList;

public class PlayerMatchup {
  private String p1;
  private String p2;
  private List<List<RaceMatchup>> raceMatches;
  
  public PlayerMatchup(String player1, String player2) {
    p1 = player1;
    p2 = player2;
    
    raceMatches = new ArrayList<List<RaceMatchup>>();
    
    for (int i = 0; i < GameStats.NUM_RACES; i++) {
      List<RaceMatchup> games = new ArrayList<RaceMatchup>();
      for (int j = 0; j < GameStats.NUM_RACES; j++) {
        games.add(new RaceMatchup());
      }
      raceMatches.add(games);
    }
  }
  
  public void addGame(Game g) {
    int p1Index = GameStats.RACES.indexOf(g.getRaceForPlayer(p1));
    int p2Index = GameStats.RACES.indexOf(g.getRaceForPlayer(p2));
    
    raceMatches.get(p1Index).get(p2Index).addGame(g);
  }
  
  public void print(boolean percentage) {
    String p1Line1 = "";
    String p1Line2 = "";
    String p1Line3 = "";
    
    for (int i = 0; i < GameStats.NUM_RACES; i++) {
      p1Line1 += "\t" + raceMatches.get(0).get(i).getString(percentage);
      p1Line2 += "\t" + raceMatches.get(1).get(i).getString(percentage);
      p1Line3 += "\t" + raceMatches.get(2).get(i).getString(percentage);
    }
    
    String text = percentage ? "Percentage" : "\t";
    System.out.println("\t\t\t" + p2);
    System.out.println(text + "\t" + GameStats.RACES.charAt(0) + "\t" + GameStats.RACES.charAt(1) + "\t" + GameStats.RACES.charAt(2));
    System.out.println("\t" + GameStats.RACES.charAt(0) + p1Line1);
    System.out.println(p1 + "\t" + GameStats.RACES.charAt(1) + p1Line2);
    System.out.println("\t" + GameStats.RACES.charAt(2) + p1Line3);
  }
  
  public List<RaceMatchup> getPlayerRaceMatchup() {
    List<RaceMatchup> rm = new ArrayList<RaceMatchup>();
    
    for (int i = 0; i < GameStats.NUM_RACES; i++) {
      RaceMatchup r = new RaceMatchup();
      for (int j = 0; j < GameStats.NUM_RACES; j++) {
        r.p1Wins += raceMatches.get(i).get(j).p1Wins;
        r.p2Wins += raceMatches.get(i).get(j).p2Wins;
      }
      rm.add(r);
      
    }
    return rm;
  }
}
