package app;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import domain.Enemy;
import domain.Player;
import domain.Weapon;
import maps.GameMap;
import maps.GameMapLevel01;
import maps.GameMapLevel02;

public class App {

	private static Scanner scanner;
	
	private static Player player;
	private static List<Enemy> enemies = new ArrayList<Enemy>();
	
	private static final int NORTH = 1;
	private static final int SOUTH = 2;
	private static final int WEST = 3;
	private static final int EAST = 4;
	
	// maps to choose from  --  add new levels here
	private static List<GameMap> mapList = 
			new ArrayList<GameMap>(Arrays.asList(	new GameMapLevel01(), 
													new GameMapLevel02()
													));
	private static GameMap currentMap;
	
	public static void main(String[] args) {
		
		initializeGame();
		
		
		System.out.println();
		
		setupPlayer();
		chooseMap();
		
		// Welcome to the game.
		System.out.printf(	"Hello %s! Good pick, %s is a nice weapon of choice. Let's begin the adventure!", 
							player.getName(),
							player.getWeapon());
		
		// start game loop
		gameLoop();
		
		
		
		// end main. close scanner
		scanner.close();
		System.out.println("Bye bye!");
		

	}
			
	
	private static void setupPlayer() {
		System.out.print("Hi and welcome to the game. Please enter your name: ");
		String name;
		int weaponId;
		
		name = scanner.nextLine();


		// produce an option list of all the currently available weapons
		String weaponChoiceMessage = "Choose a weapon! \n";
		for(Weapon w : Weapon.values()){
			weaponChoiceMessage += w.getId() + ") " + w.toString() + ", ";
		}
		// trim off the last comma, and add colon
		int length = weaponChoiceMessage.length();
		weaponChoiceMessage = weaponChoiceMessage.substring(0, (length-2)) + ": ";

		// Choose weapon
		weaponId = askForAndGetNextInt(weaponChoiceMessage, 1, Weapon.values().length);
		
		// standard weapon, to initialize variable
		Weapon weapon = Weapon.GUN;
		// cycle through enums until the weapon id matches the chosen one.
		for(Weapon w : Weapon.values()){
			if(w.getId() == weaponId) weapon = w;
		}

		// choose difficulty  - handicap is added to the weapon damage
		int handicap = 0;
		int easy = 1;
		int normal = 2;
		int hard = 3;
		handicap = askForAndGetNextInt("Choose the difficulty of your game: 1) Easy, 2) Normal, 3 Hard: ", 1, 3);
		if(handicap == easy) handicap = 10;
		else if (handicap == normal) handicap = 0;
		else if (handicap == hard) handicap = -10;

		// create player using user input
		player = new Player(name, weapon, handicap);
			
	}
	
	
	private static void chooseMap(){
		// dynamically create the options message
		String mapOptions = "Choose a level to play:\n";
		int count = 1;
		for(GameMap map : mapList){
			mapOptions += count + ") " + map.toString() + "\n";
			count++;
		}
		int chosenMapIndex = askForAndGetNextInt(mapOptions, 1, mapList.size());
		// adjust to 0 index
		chosenMapIndex--;
		currentMap = mapList.get(chosenMapIndex);
		
		// set initial position for player in map
		player.setPosition(currentMap.getRandomNonOccupiedPoint());
		
	}
	
	
	
	private static void gameLoop() {
		
		exploreWorld();
		
		
		
	}
	
	private static void initializeGame() {
		// setup scanners
		scanner = new Scanner(System.in);

		enemies.addAll(Arrays.asList(Enemy.values()));
		
		
	}
	
	
	private static int askForAndGetNextInt(String askMessage, int validRangeStart, int validRangeEnd){
		// keep asking for an int until there is one
		while(true){
			// show the message
			System.out.println(askMessage);
			// wait for input and check if it is an int.
			if(scanner.hasNextInt()){
				// check if input if within the valid range
				int value = scanner.nextInt();
				if(value >= validRangeStart && value <= validRangeEnd){
					return value;
				} else {
					System.out.println("That number is NOT one of the options! Try again..");
					continue;
				}
			} else {
				// skip the input that wasn't an int, then repeat the check
				scanner.next();
			}
		}
	}
	
	private static int generateRandomFromOne(int inclusiveUpperLimit){
		Random randGen = new Random();
		// nextInt actually uses exclusive upper limit, but we add one to the result anyway, because our options start with 1, not 0.
		int randNum = randGen.nextInt(inclusiveUpperLimit);
		return ++randNum;
	}
	
	private static Enemy generateRandomEnemy(){
		int index = generateRandomFromOne(enemies.size());
		return enemies.get(--index); //  --index since list index is zero-based
	}
	
	private static void exploreWorld(){
		while(true){
			int direction = askForAndGetNextInt("Which direction do you want to go?\n1) North, 2) South, 3) West, 4) East: ", 1, 4);
			
			// if move is valid, move player
			if(isValidMove(direction)){
				movePlayer(direction);
			}
			
		}

		
		
	}
	
	// check if player tried to move outside of map
	
	private static boolean isValidMove(int direction){
		
		
		boolean valid = true;
		String endOfMapMessage = "What's this? A massive wall? I guess we can't go any further ";
		
		if(direction == NORTH){
			
			if(player.getPosition().y == currentMap.getMIN_Y_POSITION()){
				System.out.println(endOfMapMessage + "north.");
				valid = false;
			}
		} else if(direction == SOUTH){
			if(player.getPosition().y == currentMap.getMAX_Y_POSITION()){
				System.out.println(endOfMapMessage + "south.");
				valid = false;
			}
		} else if(direction == WEST){
			if(player.getPosition().x == currentMap.getMIN_X_POSITION()){
				System.out.println(endOfMapMessage + "west.");
				valid = false;
			}
		} else if(direction == EAST){
			if(player.getPosition().x == currentMap.getMAX_X_POSITION()){
				System.out.println(endOfMapMessage + "east.");
				valid = false;
			}
		}
		return valid;
	}
	
	
	private static void movePlayer(int direction){
		// move the player
		player.move(direction);
		
		if(direction == NORTH){
			System.out.println("You walked north..");
			System.out.println(player.getPosition().toString());
		
		} else if(direction == SOUTH){
			System.out.println("You walked south..");
			System.out.println(player.getPosition().toString());
		
		} else if(direction == WEST){
			System.out.println("You walked west..");
			System.out.println(player.getPosition().toString());
		
		} else if(direction == EAST){
			System.out.println("You walked east..");
			System.out.println(player.getPosition().toString());
		}
	}
	
	
	
	
	


}
