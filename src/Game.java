/*
 * Milan Justel
 * Game
 */

public class Game {
  String player1;
  String player2;
  String race1;
  String race2;
  String winner;
  
  public Game(String p1, String p2, String r1, String r2, boolean p1Won) {
    player1 = p1;
    player2 = p2;
    race1 = r1;
    race2 = r2;
    if (p1Won) {
      winner = p1;
    } else {
      winner = p2;
    }
  }
  
  public String getWinner() {
    return winner;
  }
  
  public String getMatchup() {
    if (race1.compareTo(race2) < 0) {
      return race1 + "v" + race2;
    } else {
      return race2 + "v" + race1;
    }
  }
  
  public String toString() {
    String p1Text = player1 + "(" + race1 + ")";
    String p2Text = player2 + "(" + race2 + ")";
    if (winner.equals(player1)) {
      p1Text += " (W)";
    } else {
      p2Text += " (W)";
    }
    return "[" + p1Text + " vs " + p2Text + "]";
  }
}
