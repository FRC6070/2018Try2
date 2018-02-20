package org.usfirst.frc.team6070.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FarSideAuto extends CommandGroup {
	public FarSideAuto(int direction) {
		if (direction == 0) {
			midAuto();
		} else if (direction == 1) {
			sideAuto(1.0);
		} else {
			sideAuto(-1.0);
		}
	}
	
	void midAuto() {
		addSequential(new AutoDrive(100, 3, false));
		addSequential(new AutoFlip(0.8, 1.5));	
	}
	
	void sideAuto(double parameterRight) {
		addSequential(new AutoDrive(5, 3, false));
    	addSequential(new AutoTurn(parameterRight*10.0,3,0.6)); 
    	addSequential(new AutoDrive(135, 3, false));
    	addSequential(new AutoTurn(parameterRight*-100.0,3,0.6));
    	addSequential(new AutoDrive(12, 1, false));
    	addSequential(new AutoFlip(0.8, 1.5));
    	addSequential(new AutoFlip(0.8, 1.5));
    	addSequential(new AutoTurn(parameterRight*90.0,3,0.6));  
    	addSequential(new AutoDrive(50, 3, false));
	}
}
