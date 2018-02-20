package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.OI;
import org.usfirst.frc.team6070.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BoxWindowControl extends Command {

    public BoxWindowControl() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.pneumaticIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (OI.stick.getTrigger()==false){
    		Robot.pneumaticIntake.openBoxWindow();
    	}else if (OI.stick.getTrigger()==true){
    		Robot.pneumaticIntake.closeBoxWindow();
    	}
    	
    	//if (OI.stick.getRawButton(7)){
    		//Robot.pneumaticIntake.openBoxWindow();
    	//}else if (OI.stick.getRawButton(8)){
    		//Robot.pneumaticIntake.closeBoxWindow();
    	//}
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
