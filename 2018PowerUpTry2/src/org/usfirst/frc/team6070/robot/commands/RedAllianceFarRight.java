package org.usfirst.frc.team6070.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedAllianceFarRight extends CommandGroup {

	
	
	public RedAllianceFarRight() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
	    if(gameData.length() > 0){
	    	if(gameData.charAt(0) == 'R'){
	    		addSequential(new AutoDrive(5, 3, false));
		    	addSequential(new AutoTurn(10.0,3,0.6)); 
		    	addSequential(new AutoDrive(135, 3, false));
		    	addSequential(new AutoTurn(-100.0,3,0.6));
		    	addSequential(new AutoDrive(12, 1, false));
		    	addSequential(new AutoFlip(0.8, 1.5));
		    	addSequential(new AutoFlip(0.8, 1.5));
		    	addSequential(new AutoTurn(90.0,3,0.6));  
		    	addSequential(new AutoDrive(50, 3, false));
	    	} 
	    	else {
	    		addSequential(new AutoDrive(335.65));
	    	}
	    }
	}
}