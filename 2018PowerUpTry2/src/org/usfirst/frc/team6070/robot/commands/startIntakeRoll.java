package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.OI;
import org.usfirst.frc.team6070.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class startIntakeRoll extends Command {

    public startIntakeRoll() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.Rollers);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (OI.stick.getRawButton(5)){
    		 Robot.Rollers.roll(-1);
    	}else if (OI.stick.getRawButton(3)){
    		Robot.Rollers.roll(0.8);
    	}else{
    		Robot.Rollers.roll(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
