package org.usfirst.frc.team6070.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedAllianceFarLeft extends CommandGroup {
	public RedAllianceFarLeft() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
	    if(gameData.length() > 0){
	    	if(gameData.charAt(0) == 'L'){
			//Put left auto code here
	    	} 
	    	else {
			//Put right auto code here
	    	}
}
	/*
	public String gameData;

	 public RedAllianceFarLeft() {
	    	
	    	gameData = DriverStation.getInstance().getGameSpecificMessage();
	    	
//			Add the following code for diagonal path:
//			    addSequential(new AutoTurn(10.2849,2.0,0.5))
//			    addSequential(new AutoDrive(156.769,10,false))
			 
	    	if (gameData.equalsIgnoreCase("RRR")){
				addSequential(new AutoDrive(154.25,10,false));
				addSequential(new AutoTurn(90.0,2.0,0.5));
				addSequential(new AutoDrive(27.99,10,false));
	    	}
			else if (gameData.equalsIgnoreCase("RRL")){
				addSequential(new AutoDrive(154.25,10,false));
				addSequential(new AutoTurn(90.0,2.0,0.5));
				addSequential(new AutoDrive(27.99,10,false));
	    	}
			else if (gameData.equalsIgnoreCase("RLR")){
				addSequential(new AutoDrive(154.25,10,false));
				addSequential(new AutoTurn(90.0,2.0,0.5));
				addSequential(new AutoDrive(27.99,10,false));
	    	}
			else if (gameData.equalsIgnoreCase("RLL")){
				addSequential(new AutoDrive(154.25,10,false));
				addSequential(new AutoTurn(90.0,2.0,0.5));
				addSequential(new AutoDrive(27.99,10,false));
	    	}
	    	*/
	 }
}