/*
 * Milan Justel
 * Game
 */

public class Game {
  public String player1;
  public String player2;
  public String race1;
  public String race2;
  boolean p1Won;
  
  public Game(String p1, String p2, String r1, String r2, boolean p1Winner) {
    player1 = p1;
    player2 = p2;
    race1 = r1;
    race2 = r2;
    p1Won = p1Winner;
  }
  
  public String getRaceForPlayer(String p) {
    if (!p.equals(player1) && !p.equals(player2)) {
      throw new IllegalArgumentException();
    }
    
    if (p.equals(player1)) {
      return race1;
    } else {
      return race2;
    }
  }
  
  public boolean player1Won() {
    return p1Won;
  }
  
  public String getWinner() {
    if (p1Won) {
      return player1;
    } else {
      return player1;
    }
  }
  
  public Game flipGame() {
    return new Game(player2, player1, race2, race1, !p1Won);
  }
}
