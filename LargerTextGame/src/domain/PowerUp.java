package domain;

import java.awt.Point;


import app.App;

public abstract class PowerUp extends MovableObject {
	
	private String optionsMessage;
	private int optionsLowerBound;
	private int optionsHigherBound;
	private Player receiver;

	public PowerUp(	String optionsMessage, 
					int optionsLowerBound, 
					int optionsHigherBound, 
					Player receiver) { 
		super(new Point(0,0), 1); // has no speed, but if move() is going to work, we need to have at least 1
		this.optionsMessage = optionsMessage;
		this.optionsLowerBound = optionsLowerBound;
		this.optionsHigherBound = optionsHigherBound;
		this.receiver = receiver;
		
		
	}
	
	public void startPowerUp(){
		int choice = showOptionsMessageAndGetChoice();
		runChoiceLogic(choice, receiver);
		
	}
	
	private int showOptionsMessageAndGetChoice(){
		int choice = App.askForAndGetNextInt(optionsMessage, optionsLowerBound, optionsHigherBound);
		return choice;
	}
	
	public abstract void runChoiceLogic(int choice, Player player);
	
	

}
