package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurn extends Command {
	double timeout = 0;
	double angle = 0;
	double speed = 0;

    public AutoTurn(double angle, double timeout, double speed) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.DriveBase);
        this.timeout = timeout;
        this.angle = angle;
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.DriveBase.setDirection();
    	Robot.DriveBase.turnToAngle(angle, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.DriveBase.accelPID.resetPID();
    	Robot.DriveBase.gyroPID.resetPID();
    	Robot.DriveBase.resetGyro(); //so robot doesn't twitch
    	Robot.DriveBase.stop();
    	
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    
}
