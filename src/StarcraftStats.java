/*
 * Milan Justel
 * Starcraft Stats
 */

import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class StarcraftStats {
  public static final String VERSION = "1.1";
  public static final String COMMENT_MARK = "#";
  public static final String[] commands = {
    "0: Total games in program",
    "1: All player stats",
    "2: Player stats",
    "3: All matchup stats",
    "4: Matchup stats",
    "5: All player race stats",
    "6: Player race stats",
    "exit"
  };
  
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			usage();
		}
		
    System.out.println("Welcome to the Starcraft Stats application! Version " + VERSION);
		
		GameStats gs = populateGameStats(args[0]);
		Scanner console = new Scanner(System.in);
		
		while (executeCommand(gs, console));
		
		System.out.println("Thank you!");
		
		console.close();
	}
	
	/* PRIVATE METHODS */
	
	/**
	 * Prints the program usage
	 */
	private static void usage() {
    System.out.println("USAGE: StarcraftStats FILENAME");
  }
	
	/**
	 * Processes the game data from a file
	 * @param fileName The name of the file
	 * @return A GameStats object with all of the data from the file
	 * @throws IOException If an error occurs when opening and reading the file
	 */
	private static GameStats populateGameStats(String fileName) throws IOException {
	  Scanner file = new Scanner(new File(fileName));
	  
	  GameStats gs = null;
	  boolean processPlayerLine = false;
	  int numGames = 0;
	  
	  while (file.hasNextLine()) {
	    String line = file.nextLine();
	    if (!line.startsWith(COMMENT_MARK)) {
	      if (!processPlayerLine) {
	        // The first line containing player names
	        List<String> players = new LinkedList<String>();
	        Scanner nameScanner = new Scanner(line);
	        while (nameScanner.hasNext()) {
	          players.add(nameScanner.next());
	        }
	        nameScanner.close();
	        Collections.sort(players);
	        
	        gs = new GameStats(players);
	        processPlayerLine = true;
	      } else {
	        // Lines containing game data
	        Scanner gameScanner = new Scanner(line);
	        
	        String p1 = gameScanner.next();
	        String p2 = gameScanner.next();
	        char r1 = gameScanner.next().toUpperCase().charAt(0);
	        char r2 = gameScanner.next().toUpperCase().charAt(0);
	        String winner = gameScanner.next();
	        gameScanner.close();
	        
	        numGames++;
	        Match m = new Match(p1, p2, r1, r2, winner);
	        gs.addMatch(m);
	      }
	    }
	  }
	  file.close();
    
	  System.out.println("Number of games processed: " + numGames);
	  
    return gs;
	}

	private static boolean executeCommand(GameStats gs, Scanner console) {
	  System.out.print("Which command? (h)elp (e)xit ");
	  String choice = console.nextLine().toLowerCase();
	  
	  if (choice.startsWith("h")) {
	    displayCommands();
	  } else if (choice.startsWith("s")) {
	    getSpecificMatchup(gs, console);
	  } else if (choice.startsWith("e")) {
	    return false;
	  } else {
	    System.out.println("Unknown command");
	  }
	  
	  return true;
	}
	
	private static void displayCommands() {
	  System.out.println("Possible commands:");
	  for (int i = 0; i < commands.length; i++) {
	    System.out.println("\t" + commands[i]);
	  }
	}
	
	private static void getSpecificMatchup(GameStats gs, Scanner console) {
	  System.out.print("Which matchup? (P1 P2 R1 R2): ");
	  String p1 = console.next();
	  String p2 = console.next();
	  char r1 = console.next().charAt(0);
	  char r2 = console.next().charAt(1);
	  Integer[] ret = gs.getSpecificMatchup(p1, p2, r1, r2);
	  System.out.println(p1 + " vs " + p2);
	}
}