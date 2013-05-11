/*
 * Milan Justel
 * Starcraft Stats
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class StarcraftStats {
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			usage();
		}
		
		GameStats gs = populateGameStats(args[0]);
		
		System.out.println(gs.totalGames());
		gs.printMatchup("Huy", "Don", true);
		gs.printMatchup("Huy", "Don", false);
		gs.printPlayerOverallStats("Huy", true);
		gs.printPlayerRaceStats("Huy", false);
		gs.printPlayerRaceStats("Huy", true);
		gs.printPlayerOverallStats("Huy", false);
		gs.printPlayerOverallStats("Don", false);
		gs.printPlayerOverallStats("Milan", false);
	}
	
	public static GameStats populateGameStats(String fileName) throws IOException {
	  Scanner file = new Scanner(new File(fileName));
	  
	  if (!file.hasNextLine()) {
	    file.close();
	    throw new IllegalArgumentException("Incorrect file header: must be player names");
	  }
	  
	  List<String> players = new ArrayList<String>();
	  String names = file.nextLine();
	  Scanner nameScanner = new Scanner(names);
	  while (nameScanner.hasNext()) {
	    players.add(nameScanner.next());
	  }
	  nameScanner.close();
	  
	  GameStats gs = new GameStats(players);
	  
    while (file.hasNextLine()) {
      String gameLine = file.nextLine();
      Scanner line = new Scanner(gameLine);
      
      String p1 = line.next();
      String p2 = line.next();
      String r1 = line.next();
      String r2 = line.next();
      String winner = line.next();
      
      gs.addGame(p1, p2, r1, r2, winner.equals(p1));
      line.close();
    }
    file.close();
    
    return gs;
	}
	
	public static void usage() {
		System.out.println("USAGE: StarcraftStats FILENAME");
	}
}