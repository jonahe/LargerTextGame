package domain;

import java.awt.Point;


import app.App;

public abstract class PowerUp extends MovableObject {
	
	private String optionsMessage;
	private int optionsLowerBound;
	private int optionsHigherBound;

	public PowerUp(	String optionsMessage, 
					int optionsLowerBound, 
					int optionsHigherBound
					) { 
		super(new Point(0,0), 1); // has no speed, but if move() is going to work, we need to have at least 1
		this.optionsMessage = optionsMessage;
		this.optionsLowerBound = optionsLowerBound;
		this.optionsHigherBound = optionsHigherBound;
		
		
	}
	
	public void startPowerUp(Player receiver){
		int choice = showOptionsMessageAndGetChoice();
		runChoiceLogic(choice, receiver);
		
	}
	
	private int showOptionsMessageAndGetChoice(){
		int choice = App.askForAndGetNextInt(optionsMessage, optionsLowerBound, optionsHigherBound);
		return choice;
	}
	
	public abstract void runChoiceLogic(int choice, Player receiver);
	
	

}
