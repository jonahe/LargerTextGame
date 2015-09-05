package app;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


import battle.Battle;
import domain.EnemyBaseClass;
import domain.Player;
import domain.PowerUp;
import domain.Weapon;
import maps.GameMap;
import maps.GameMapLevel01;
import maps.GameMapLevel02;
import maps.GameMapLevel03;
import maps.IEnterable;
import maps.IExitable;
import maps.OccupiedArea;

public class App {

	private static Scanner scanner;
	
	private static Player player;
	
	private static final int NORTH = 1; 
	private static final int SOUTH = 2; 
	private static final int WEST = 3;
	private static final int EAST = 4;
	
	private static final String QUIT_REQUEST = "q";
	
	// maps to choose from  
	private static GameMap currentMap;
	
	public static void main(String[] args) {
		
		// setup scanner 
		initializeGame();
		
		// keeps going if player died and exited the game loop, it will just start over
		while(true){

			System.out.println("Welcome to the game!");
			// setup
			setupPlayer();

			chooseMap();

			// Welcome to the game.
			System.out.printf(	"Hello %s! Good pick, %s is a nice weapon of choice. Let's begin the adventure!\n", 
								player.getName(),
								player.getWeapon());
			String info = "\nRemember: to QUIT, just write \"q\" as input and press enter.";
			System.out.println(info);

			// start game loop
			gameLoop();
			
			// try to remove any lingering input that might interfere with the setting up of the player
			scanner.nextLine();
			System.out.println(); // newline
		
		}
		
		

	}
			
	
	private static void setupPlayer() {
	
		String name = askForAndGetNextString("Please enter your name", new ArrayList<String>());
		
		int weaponId;

		// produce an option list of all the currently available weapons
		String weaponChoiceMessage = "Choose a weapon! \n";
		for(Weapon w : Weapon.values()){
			weaponChoiceMessage += w.getId() + ") " + w.toString() + ", ";
		}
		// trim off the last comma
		int length = weaponChoiceMessage.length();
		weaponChoiceMessage = weaponChoiceMessage.substring(0, (length-2));

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
		handicap = askForAndGetNextInt("Choose the difficulty of your game: 1) Easy, 2) Normal, 3 Hard", 1, 3);
		if(handicap == easy) handicap = 5;
		else if (handicap == normal) handicap = 0;
		else if (handicap == hard) handicap = -5;

		// create player using user input
		player = new Player(name, weapon, handicap);
			
	}
	
	
	private static void chooseMap(){
		
		// create map list
		List<GameMap> mapList = createMapList(player);
		
		
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
	
	
	/**
	 * 
	 * @param player Player that's going to play map
	 * @return List with maps
	 */
	private static List<GameMap> createMapList(Player player) {
		return new ArrayList<GameMap>(Arrays.asList(	new GameMapLevel01(player),
														new GameMapLevel02(player), 
														new GameMapLevel03(player)
				));
	}


	private static void gameLoop() {
		while(true){
			// move around in world and discover things.. 
			exploreWorld();
			
			// if all the enemies are gone, break the loop
			if(currentMap.getEnemyList().size() == 0){
				System.out.println("Congratulations! You completed " + currentMap.getName());
				// Ask if they want to continue or quit
				int choice = askForAndGetNextInt("What do you want to do? 1) Play again, 2) Quit", 1, 2);
				if(choice == 2){
					quit();
				} else{
					break; // break out, end up in loop in the main method
				}
			}
			// if player is dead after battle 
			if(!player.isAlive()){
				int choice = askForAndGetNextInt("What do you want to do? 1) Play again, 2) Quit", 1, 2);
				if(choice == 2){
					quit();
				} else{
					break; // break out, end up in loop in the main method
				}
			}
		}
		
		
		
	}
	
	private static void initializeGame() {
		// setup scanners
		scanner = new Scanner(System.in);
		scanner.useDelimiter(Pattern.compile("[\\r\\n]+")); // next item separated by return or newline
		
	}
	
	/**
	 * Prints a message asking for (int) user input, until a valid input is given. Also checks if user wants to quit.
	 * 
	 * @param askMessage Message asking for input.
	 * @param validRangeStart Lowest accepted int - usually 1
	 * @param validRangeEnd Highest accepted int
	 * @return The valid input. OR if input is "quit", asks for confirmation and then quits program
	 */
	
	public static int askForAndGetNextInt(String askMessage, int validRangeStart, int validRangeEnd){
		// keep asking for an int until there is one
		while(true){
			// show the message
			System.out.println();
			System.out.print(askMessage + ": ");
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
			} else {  // it can be read as a string
				
				// check if user wanted to quit
				
				String input = scanner.next(); // this also skips the input that wasn't an int
				System.out.println(input);
				if(input.equalsIgnoreCase(QUIT_REQUEST)){

					String confirmation = askForAndGetNextString(	"Are you sure you want to quit? Y / N", 
																	new ArrayList<String>(Arrays.asList("Y","N")));
					if(confirmation.equals("y")){
						quit();
					} else {
						continue;
					}
				} // else move on and repeat loop. 
				
				System.out.println("Don't be silly. I asked for a NUMBER, didn't I?");
			}
		}
	}
	
	// similar to askForAndGetNextInt() but for Strings 

	/**
	 * Ask user for a String, repeat until the user input fits one of the valid choices, then return answer
	 * @param askMessage Message to show user. eg "Do you want to quit? y/n
	 * @param validOptions List of allowed answers (case insensitive), or empty list if any answer is ok.
	 * @return The valid answer in lowercase, unless validOptions is empty: then the string is returned unchanged
	 */
	public static String askForAndGetNextString(String askMessage, List<String> validOptions){
		
		String answer;
		// ask
		System.out.print(askMessage + ": ");
		// no options, like if any answer is OK, eg. when asked to provide a player name.
		if(validOptions.isEmpty()){
			return scanner.nextLine();
		}
		
		// make all options lowercase
		// NOTE: Strings are immutable, so we can't change them, we must replace them. But we can't do that while the list is looping..
		ArrayList<String> lowerCaseOptions = new ArrayList<String>();
		for(String option : validOptions){
			lowerCaseOptions.add(option.toLowerCase());
		}
		
		// run until input is ok
		while(true){
			// get and clean the input
			answer = scanner.next().trim().toLowerCase();
			// check if input is valid
			if(lowerCaseOptions.contains(answer)){
				break;
			} // else ask again and repeat loop
			// yell on user
			System.out.println("Try again..");
			// ask
			System.out.print(askMessage + ": ");
		}
		
		return answer;
	}
	
	
		
	// this is really more of the "game loop" 
	private static void exploreWorld(){

		int direction = askForAndGetNextInt("Which direction do you want to go?\n1) North, 2) South, 3) West, 4) East", 1, 4);

		// if move is valid, move player
		if(isValidMove(direction)){
			movePlayer(direction);
			triggerEvents(player.getPosition());

		}


	}
	
	/**
	 * Check if player tried to move outside of map
	 * 
	 * @param direction Direction that player wants to go in
	 * @return boolean. true if move was ok. else false
	 */
	
	public static boolean isValidMove(int direction){
		
		
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
			System.out.println("You walked north." + " - " + getPlayerPositionDescription());
		
		} else if(direction == SOUTH){
			System.out.println("You walked south." + " - " + getPlayerPositionDescription());
		
		} else if(direction == WEST){
			System.out.println("You walked west." + " - " + getPlayerPositionDescription());
		
		} else if(direction == EAST){
			System.out.println("You walked east." + " - " + getPlayerPositionDescription());
		}
	}
	
	/**
	 * Checks for and triggers events that should happen on this position.
	 * 
	 * @param playerPosition Current position of player
	 */
	
	private static void triggerEvents(Point playerPosition){
	
		// enter events
		if(currentMap.mapPositionOccupied(playerPosition)){
			for(OccupiedArea oArea : currentMap.getOccupiedAreaList()){
				if(oArea instanceof IEnterable){
					((IEnterable) oArea).onEnter();
					oArea.setVisitedLastRound(true);
				}
			}
		}
		
		// exit events
		
		for(OccupiedArea oArea : currentMap.getOccupiedAreaList()){
			if(oArea instanceof IExitable){
				// was visited last round && is NOT currently occupied
				if(oArea.visitedLastRound() && !oArea.pointIsInsideArea(playerPosition)){
					((IExitable) oArea).onExit();
					oArea.setVisitedLastRound(false); // it was visited LAST round, but not anymore
					oArea.setExitedLastRound(true); // if true, near events should not trigger
				}
			}
		}
		
		// near events
		for(OccupiedArea oArea : currentMap.getOccupiedAreaList()){
			// if player is near and NOT already inside  and  NOT exited last round
			if(oArea.playerIsNear(player) && !oArea.visitedLastRound() && !oArea.exitedLastRound()){
				oArea.showOnNearMessage();
			} else {
				// we skipped the message if the player exited last round, but now we have to change it back
				// this is ok because it's not used further down
				oArea.setExitedLastRound(false); 
			}
		}
		
		
		// powerup event
		// check list of powerUps, if any of them is on the position the player is on.
		PowerUp foundPowerUp = null;
		for(PowerUp powerUp : currentMap.getPowerUps()){
			if(powerUp.getPosition().equals(player.getPosition())){
				foundPowerUp = powerUp;
			}
		}
		// had to do it outside of the loop, because ArrayList doesn't allow you to remove item from list while looping through it
		if(foundPowerUp != null){
			foundPowerUp.startPowerUp(player);
			// after, remove from map
			currentMap.getPowerUps().remove(foundPowerUp);
			System.out.printf("Only %d powerUp(s) left..\n", currentMap.getPowerUps().size());
		}
		
		
		// enemy events
		ArrayList<EnemyBaseClass> enemiesToFight = new ArrayList<EnemyBaseClass>();
		for(EnemyBaseClass enemy : currentMap.getEnemyList()){
			// if player is in the same position as an enemy, add enemy to list so that they can be sent to battle.
			if(playerPosition.equals(enemy.getPosition())){
				enemiesToFight.add(enemy);
			}
		}
		// if NOT empty
		if(!(enemiesToFight.isEmpty())){
			Battle battle = new Battle(player, enemiesToFight);
			// start battle
			battle.startBattle();
			// after battle 
			// remove killed enemies
			List<EnemyBaseClass> killedEnemies = battle.getKilledEnemies();
			for(EnemyBaseClass enemy : killedEnemies){
				currentMap.getEnemyList().remove(enemy);
			}
			// and give report on progress - if one or more enemies were killed
			if(killedEnemies.size() >= 1){
				System.out.println("Good job! Only " + currentMap.getEnemyList().size() + " enemies left." );
			}
			
			// it's possible the player got killed.
			// if killed - 
			if(!player.isAlive()){
				return; // exit the method
			}
			
		}
		
	}
	
	private static String getPlayerPositionDescription(){
		return String.format(	"You are now at position (%d,%d)", 
								player.getPosition().x,
								player.getPosition().y
								);
	}
	
	
	
	private static void quit(){
		// close scanner
		scanner.close();
		// quit
		System.out.println("Bye bye!");
		System.exit(0);
	}
	


}
