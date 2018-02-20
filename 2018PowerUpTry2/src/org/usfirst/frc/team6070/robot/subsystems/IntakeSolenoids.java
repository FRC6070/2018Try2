package org.usfirst.frc.team6070.robot.subsystems;

import org.usfirst.frc.team6070.robot.commands.BoxWindowControl;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSolenoids extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Solenoid SoleIn = new Solenoid (1);
	Solenoid SoleOut = new Solenoid (2);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new BoxWindowControl());
    }
    
    public void openBoxWindow(){
    	SoleIn.set(false);
    	SoleOut.set(true);
    }
    
    public void closeBoxWindow(){
    	SoleIn.set(true);
    	SoleOut.set(false);
    }
    
    
}

