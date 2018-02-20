package org.usfirst.frc.team6070.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlueAllianceMidRight extends CommandGroup {
	
	public String gameData;
	
    public BlueAllianceMidRight() {
    	
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	if (gameData.equalsIgnoreCase("LLL")){
    		
    	}else if (gameData.equalsIgnoreCase("LLR")){
    		
    	}else if (gameData.equalsIgnoreCase("LRL")){
    		
    	}else if (gameData.equalsIgnoreCase("LRR")){
    		
    	}else if (gameData.equalsIgnoreCase("RRR")){
    		
    	}else if (gameData.equalsIgnoreCase("RRL")){
    		
    	}else if (gameData.equalsIgnoreCase("RLR")){
    		
    	}else if (gameData.equalsIgnoreCase("RLL")){
    		
    	}
    		
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
