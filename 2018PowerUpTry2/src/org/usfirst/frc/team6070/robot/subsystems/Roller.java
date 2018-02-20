package org.usfirst.frc.team6070.robot.subsystems;

import org.usfirst.frc.team6070.robot.RobotMap;
import org.usfirst.frc.team6070.robot.commands.startDriving;
import org.usfirst.frc.team6070.robot.commands.startIntakeRoll;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
 *
 */
public class Roller extends Subsystem {

	Spark leftRoller = new Spark (RobotMap.leftRoller);
	Spark rightRoller = new Spark (RobotMap.rightRoller);

	//Accelerometer accel = new BuiltInAccelerometer (Accelerometer.Range.k4G);
	
    public void initDefaultCommand() {
    	
    	leftRoller.setInverted(true);
    	setDefaultCommand(new startIntakeRoll());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	//create new driving command for initiating new Roller subsystem
    	//setDefaultCommand(new startDriving());
    	
    	// Set the default command for a subsystem here.
        //setDefaultCommand(new MyDefaultCommand());
    	
    	//
    }
    
    public void roll (double speed){
    	leftRoller.set(speed);
    	rightRoller.set(speed);
    }
    
}

