package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoFlip extends Command {

	double timeout = 0;
	boolean flip = false;
	double speed = 0.8;
	
	 //Impromptu Auto Code To Test Flipper  	
  public AutoFlip(double speed, double timeout) {
    	this.speed = speed;
    	this.timeout = timeout;
    	
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(timeout);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.sparkFlip.AutoFlip(speed,timeout);
    	Robot.sparkFlip.AutoFlip(-speed, timeout);
    	
    	//
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
