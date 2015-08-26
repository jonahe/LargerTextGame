package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.Enemy;
import domain.Player;
import domain.Weapon;

public class App {

	public static InputStreamReader rdr;
	public static BufferedReader reader;
	
	public static Player player;
	public static List<Enemy> enemies = new ArrayList<Enemy>();
	
	public static void main(String[] args) {
		
		initializeGame();
		
		//TODO: Remove soon
		boolean test = true;
		while(test){
			sanitizeInputToInt(reader);
		}
		
		setupPlayer();
		
		// Welcome to the game.
		System.out.printf(	"Hello %s! The %s is a nice weapon of choice! Let's begin the adventure!", 
							player.getName(),
							player.getWeapon());
		
		// start game loop
		gameLoop();
		
		

	}
	
	public static void setupPlayer() {
		System.out.print("Hi and welcome to the game. Please enter your name: ");
		String name;
		int weaponId;
		
		try {
			name = reader.readLine();
			
			
			// Choose weapon
			System.out.print("Choose a weapon! \n1) Gun, 2) Melee, 3) Magic and 4) Weirdness: ");
			
			//TODO: sanitize input. Maybe it's not convertible to an int
			
			weaponId = Integer.parseInt(reader.readLine());
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
			System.out.print("Choose the difficulty of your game: 1) Easy, 2) Normal, 3 Hard: ");
			handicap = Integer.parseInt(reader.readLine());
			if(handicap == easy) handicap = 10;
			else if (handicap == normal) handicap = 0;
			else if (handicap == hard) handicap = -10;
			
			// create player using user input
			player = new Player(name, weapon, handicap);
			

			
			
			
		} catch (IOException e) {
			System.out.println("An error occured while setting up player: ");
			e.printStackTrace();
		}
	}
	
	public static void gameLoop() {
		//TODO: Figure out how to create
	}
	
	public static void initializeGame() {
		// setup scanners
		rdr = new InputStreamReader(System.in);
		reader = new BufferedReader(rdr);
		// create list of enemies
		enemies.addAll(Arrays.asList(Enemy.BIRD, Enemy.TROLL, Enemy.WIZARD));
		
	}
	
	//TODO:  write a good documentation of this method
	/*
	 * checks if the user input is a number, like it should be. If so, it returns the string with the number.
	 * else it returns "FAILED"
	 * 
	 */
	
	public static String sanitizeInputToInt(BufferedReader reader) {
		String input = "";
		boolean isNumber = false;
		try {
			input = reader.readLine();
			input = input.substring(0, 1);
			System.out.println(input);
			
			isNumber = Character.isDigit(input.charAt(0));
			System.out.println(isNumber);
			
			
			
		} catch (IOException e) {
			System.out.println("Error: The reading from BufferedReader did not complete.");
			e.printStackTrace();
		}
		
		if(isNumber) return input;
		else return "FAILED";
		
	}

}
