package org.usfirst.frc.team6070.robot.subsystems;

import org.usfirst.frc.team6070.robot.RobotMap;
import org.usfirst.frc.team6070.robot.commands.SparkFlip;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *@author Katie Chen
 */

//set angle of flip to 180 degrees
public class Sparky extends Subsystem {
	
	Spark sparkFlip = new Spark(RobotMap.sparkFlip);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new SparkFlip()); //See command SparkFlip
    }
    
    public void liftLoad(double speed){ //speed TBD
      	sparkFlip.set(speed);
    }

	//Impromptu AutoFlip code
    public void AutoFlip(double speed, double timeout) {
		// TODO Auto-generated method stub
		sparkFlip.set(speed);
		sparkFlip.setExpiration(timeout);
	}
   
    		
    
    }

