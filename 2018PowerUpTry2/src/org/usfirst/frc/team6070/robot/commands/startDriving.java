package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.OI;
import org.usfirst.frc.team6070.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class startDriving extends Command {
	
	double driveTypeControl; // 0 is tank, 1 is pseudo-arcade, 2 is nafehDrive 
	double nafehDrive = 0; // nafehDrive parameter???
	int reverse = 1;
	double slow = 0.65;
	//double controlSpeedvar = 1;
	
    public startDriving() {
    	// Use requires() here to declare subsystem dependencies
        requires(Robot.DriveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveTypeControl = SmartDashboard.getNumber("driveTypeControl", 0);
    	SmartDashboard.putNumber("Drive Type Control", driveTypeControl);
    	
    	if (OI.xbox.getTriggerAxis(Hand.kLeft) >= 0.8){
    		reverse = -1;
    	}else{
    		reverse = 1;
    	}
    	
    	if (OI.xbox.getTriggerAxis(Hand.kRight) >= 0.8){
    		slow = 0.95; //slowed cause battery keeps browning out
    	}else{
    		slow = 0.65;
    	}
    	
    	if (OI.driveYleft() > 0.1){
    		nafehDrive = 1;
    	}else if (OI.driveYleft() > -0.1){
    		nafehDrive = -1;
    	}else{
    		nafehDrive = 0;
    	}
    	
    	if (driveTypeControl == 0){
    		if (reverse == 1){
    			double oiDriveYRightDouble = OI.driveYright()*slow;
    			double oiDriveYLeftDouble = OI.driveYleft()*slow;
    			
    			Robot.DriveBase.tankDrive(oiDriveYRightDouble, oiDriveYLeftDouble, true);
    			
    		}else{
    			Robot.DriveBase.tankDrive(OI.driveYleft()*slow, OI.driveYright()*slow, true);
    		}
    	}
    	
    	 /*
    	
    	all Reverse does is switch around the joysticks/hands?; what used to control 
    	the left engine using left hand now controls the right engine using the left hand, and
    	what used to control the right engine using the right hand now controls the left engine
    	using the right hand. 
    	
    	
    	*/
    	
    	else if (driveTypeControl == 1){
    		Robot.DriveBase.arcadeDrive(OI.driveYright()*slow*reverse, OI.driveX()*slow); 
    	} // pseudo arcadeDrive
    	
    	else if (driveTypeControl == 2){
    		Robot.DriveBase.tankDrive(nafehDrive*slow*OI.xbox.getTriggerAxis(Hand.kLeft),slow*OI.xbox.getTriggerAxis(Hand.kRight), false);
    	}
    }
    
    //Using the nafehDrive variable to change directions, he can use the triggers to control speed. 
    //This is admirable; I'll
	//get Rukshi to weigh in. 

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }
    /*
    void runArrayValsThread() { 
    	new Thread(() -> {
    		while(!Thread.interrupted()) {
                
            }
    	}).start();
    }
     */
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}



