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
  public static final String VERSION = "1.0";
  public static final String[] commands = {
    "0: Total games in program",
    "1: All player stats",
    "2: Player stats",
    "3: All matchup stats",
    "4: Matchup stats",
    "5: All player race stats",
    "6: Player race stats"
  };
  
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			usage();
		}
		
		GameStats gs = populateGameStats(args[0]);
		Scanner console = new Scanner(System.in);
		
		System.out.println("Welcome to the Starcraft Stats application! Version " + VERSION);
		do {
		  displayCommands();
		  executeCommand(gs, console);
		} while (newCommand(console));
		
		System.out.println("Thank you!");
		
		console.close();
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
	
	public static boolean newCommand(Scanner console) {
	  System.out.print("New command? Y/N ");
	  String line = console.nextLine();
	  return line.startsWith("y") || line.startsWith("Y");
	}
	
	public static void executeCommand(GameStats gs, Scanner console) {
	  List<String> players = gs.getPlayers();
    String line = console.nextLine();
    if (line.isEmpty()) {
      System.out.println("Unknown command");
      return;
    }
	  int choice = Integer.parseInt(line);
	  
	  if (choice == 0) {
	    gs.printTotalGames();
	  } else if (choice == 1) {
	    System.out.println("Player game stats");
	    System.out.println("Name\tRaw\tPercentage");
	    for (String p : players) {
	      gs.printPlayerOverallStats(p);
	    }
	  } else if (choice == 2) {
	    System.out.print("Which player? ");
	    String p = console.nextLine();
	    System.out.println("Player game stats");
	    System.out.println("Name\tRaw\tPercentage");
	    gs.printPlayerOverallStats(p);
	  } else if (choice == 3) {
	    System.out.println("Player matchup stats");
	    for (String p1 : players) {
  	    for (String p2 : players) {
  	      if (!p1.equals(p2)) {
  	        gs.printMatchup(p1, p2);
  	      }
  	    }
	    }
	  } else if (choice == 4) {
	    System.out.print("Which two players? ");
	    String nameLine = console.nextLine();
	    Scanner temp = new Scanner(nameLine);
	    String p1 = temp.next();
	    String p2 = temp.next();
	    temp.close();
	    System.out.println("Player matchup stats");
	    gs.printMatchup(p1, p2);
	  } else if (choice == 5) {
	    System.out.println("Name\tRace\tRaw\tPercentage");
	    for (String p : players) {
	      gs.printPlayerRaceStats(p);
	    }
	  } else if (choice == 6) {
	    System.out.print("Which player? ");
	    String p = console.nextLine();
	    System.out.println("Name\tRace\tRaw\tPercentage");
	    gs.printPlayerRaceStats(p);
	  } else {
	    System.out.println("Command NYI");
	  }
	}
	
	public static void displayCommands() {
	  System.out.println("Possible commands:");
	  for (int i = 0; i < commands.length; i++) {
	    System.out.println("\t" + commands[i]);
	  }
	}
}