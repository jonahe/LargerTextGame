package maps;

import java.awt.Point;

import domain.EnemyBaseClass;
import domain.Player;
import domain.PowerUp;

public class MapPrinter {

	private GameMap currentMap;
	private Player player;
	private String playerSign; // how a point occupied by player should look like
	private String emptySign = "|_"; //  how an empty point should look like
	private String endRow = "|\n"; // to add after last x value in each row
	private String powerUpSign = "|*";
	private String enemySign  = "|!";
	private boolean showPowerUp; // option value - should powerup be visible or not?
	private boolean showEnemy; // option value - should enemy be visible or not?
	
	/**
	 * Constructor for MapPrinter. Prints the provided map with the provided options
	 * @param currentMap
	 * @param showPowerUp
	 * @param showEnemy
	 */
	public MapPrinter(GameMap currentMap, boolean showPowerUp, boolean showEnemy){
		this.currentMap = currentMap;
		this.player = currentMap.getPlayer();
		this.showPowerUp = showPowerUp;
		this.showEnemy = showEnemy;
		
		String firstLetterOfName = ""; 
		firstLetterOfName += player.getName().toUpperCase().charAt(0);
		this.playerSign = "|" + firstLetterOfName;
	}
	
	public String printMap(){

		String map = "";
		
		int maxY = currentMap.getMAX_Y_POSITION();
		int maxX = currentMap.getMAX_X_POSITION();
		
		StringBuilder sb = new StringBuilder();
		// make "ceiling" _ _ _ 
		for(int i = 0; i <= maxX; i++){
			// not the last one
			if(i != (maxX)){
				sb.append(" _");
			} else {
				sb.append(" _ ");
			}
		}
		sb.append("\n");
		String ceiling = sb.toString();
		
		// clear for next job, start with ceiling.
		sb = new StringBuilder(ceiling);
		
		// make map
		// For example: in a 7*7 map from 0.0 to 6.6, if the player has position 0.0, that would be the upper left corner. And since
		// we start from y = 0 and x = 0, that point will also be be added to the map String first.
		// Remember that the position shown in the text game has the Y axis flipped, so 
	
		// loop through y positions
		for(int y = 0; y <= maxY; y++){
			// loop through x positions
			for(int x = 0; x <= maxX; x++){
				// append the appropriate sign for this position
				sb.append(getMapPositionSign(x, y));
				if(x == maxX){
					sb.append(endRow);
				}
			}
		}
		
		map = sb.toString();
		return map;
	}
	
	/**
	 * Returns a String that represents what this map position (x,y) holds
	 * @param xToCheck
	 * @param yToCheck
	 * @return The appropriate String. Eg. "O" if the position has player with name Ozzy on it. 
	 */
	public String getMapPositionSign(int xToCheck, int yToCheck){
		// player position is prioritized. then enemy, then power up. (assuming they should show at all)
		Point positionToCheck = new Point(xToCheck, yToCheck);
		if(isPlayerPos(xToCheck, yToCheck)){
			return playerSign;
		}
		
		// "shortcut", checking if position contains neither enemy nor powerups
		if(!currentMap.getTakenPositions().contains(positionToCheck)){
			return emptySign;
		}
		
		// if we should show enemies
		if(showEnemy){
			if(isEnemyPos(xToCheck, yToCheck)){
				return enemySign;
			}
		}
		
		// if we should show power ups
		if(showPowerUp){
			if(isPowerUpPos(xToCheck, yToCheck)){
				return powerUpSign;
			}
		}
		
		// compiler wants return statement here too. should not be needed
		return emptySign;
		
	}
	
	
	
	
	
	
	private boolean isPlayerPos(int xToCheck, int yToCheck){
		Point point = new Point(xToCheck, yToCheck);
		return point.equals(player.getPosition());
	}
	
	/**
	 * Is this position the position of a PowerUp?
	 * @param xToCheck
	 * @param yToCheck
	 * @return boolean 
	 */
	private boolean isPowerUpPos(int xToCheck, int yToCheck){
		Point point = new Point(xToCheck, yToCheck);
		boolean isPowerUpPos = false;
		for(PowerUp powerUp : currentMap.getPowerUps()){
			// is this powerUp in the position we check? if so -> return true
			if(powerUp.getPosition().equals(point)){
				isPowerUpPos = true;
				break;
			}
		}
		return isPowerUpPos;
	}
	
	/**
	 * Is this position the position of an EnemyBaseClass object?
	 * @param xToCheck
	 * @param yToCheck
	 * @return
	 */
	private boolean isEnemyPos(int xToCheck, int yToCheck){
		Point point = new Point(xToCheck, yToCheck);
		boolean isEnemyPos = false;
		for(EnemyBaseClass enemy : currentMap.getEnemyList()){
			// is this powerUp in the position we check? if so -> return true
			if(enemy.getPosition().equals(point)){
				isEnemyPos = true;
				break;
			}
		}
		return isEnemyPos;
	}
}
