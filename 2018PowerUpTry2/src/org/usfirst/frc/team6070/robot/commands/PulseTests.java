package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.OI;
import org.usfirst.frc.team6070.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;


public class PulseTests extends Command {
	
	Timer myTimer;
		
	public PulseTests() {
		myTimer.start();	
		if (myTimer.get() <= 5) {
			Robot.DriveBase.FL.set(0.6);
		} else if (myTimer.get() <= 10) {
			Robot.DriveBase.FR.set(0.6);
		} else if (myTimer.get() <= 15) {
			Robot.DriveBase.BR.set(0.6);
		} else if (myTimer.get() <= 20) {
			Robot.DriveBase.BL.set(0.6);
		} else {
			end();
		}
		
	}
	
	protected void end() {
    	Robot.DriveBase.accelPID.resetPID();
    	Robot.DriveBase.gyroPID.resetPID();
    	Robot.DriveBase.stop();	
    }
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
