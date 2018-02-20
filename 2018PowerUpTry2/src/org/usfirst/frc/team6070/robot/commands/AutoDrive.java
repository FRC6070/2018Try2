package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {
	
	double dist = 0;
	double timeout = 0;
	boolean backwards = false;
	boolean withEnc = false;
	
//Might not call anything above public initilize, then make a subsystem
    public AutoDrive(double timeout) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.DriveBase);
        this.timeout = timeout;
    }
    
    public AutoDrive(double timeout, boolean backwards){
    	requires (Robot.DriveBase);
    	this.timeout = timeout;
    	this.backwards = backwards;
    	
    }
    
    public AutoDrive(double dist, double timeout, boolean backwards){
    	requires (Robot.DriveBase);
    	this.dist= dist;
    	this.backwards = backwards;
    	this.timeout = timeout;
    	withEnc = true;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(timeout);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (withEnc){
    		drive();
    	}else{
    		Robot.DriveBase.driveStraight(backwards);
    	}
    	
    	// Robot.drive.autoDrive(3, 882, 8)
    }
    
    void drive() {
    	double accuracy = Robot.DriveBase.driveStraightDist(dist);
		if (Math.abs(accuracy - dist) < 0.1) {
			end();
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.DriveBase.drivePID.resetPID();
    	Robot.DriveBase.resetEnc(); 
    	Robot.DriveBase.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
