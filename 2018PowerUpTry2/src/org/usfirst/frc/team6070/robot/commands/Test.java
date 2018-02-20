package org.usfirst.frc.team6070.robot.commands;

import org.usfirst.frc.team6070.robot.OI;
import org.usfirst.frc.team6070.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */


public class Test extends CommandGroup {

	Timer myTimer = new Timer();
    public Test() {
 	
    	//addSequential(new AutoFlip(0.6, 0.8));
//    	myTimer.start();	
//		if (myTimer.get() <= 5) {
//			Robot.DriveBase.FL.set(0.6);
//		} else if (myTimer.get() <= 10) {
//			Robot.DriveBase.FR.set(0.6);
//		} else if (myTimer.get() <= 15) {
//			Robot.DriveBase.BR.set(0.6);
//		} else if (myTimer.get() <= 20) {
//			Robot.DriveBase.BL.set(0.6);
//		} else {
//			end();
//		}
    	
    	
    	//Autonomous L from position 3
    	addSequential(new AutoDrive(5, 3, false));
    	addSequential(new AutoTurn(10.0,3,0.6)); 
    	addSequential(new AutoDrive(135, 3, false));
    	addSequential(new AutoTurn(-100.0,3,0.6));
    	addSequential(new AutoDrive(12, 1, false));
    	addSequential(new AutoFlip(0.8, 1.5));
    	addSequential(new AutoFlip(0.8, 1.5));
    	addSequential(new AutoTurn(90.0,3,0.6));  
    	addSequential(new AutoDrive(50, 3, false));
    	
    	//Autonomous L from position 1
    	addSequential(new AutoDrive(5, 3, false));
    	addSequential(new AutoTurn(-10.0,3,0.6)); 
    	addSequential(new AutoDrive(135, 3, false));
    	addSequential(new AutoTurn(100.0,3,0.6));
    	addSequential(new AutoDrive(12, 1, false));
    	addSequential(new AutoFlip(0.8, 1.5));
    	addSequential(new AutoFlip(0.8, 1.5));
    	addSequential(new AutoTurn(-90.0,3,0.6));  
    	addSequential(new AutoDrive(50, 3, false));
    	
    	//Autonomous L from position 2 without cube
    	addSequential(new AutoDrive(30, 3, false));
    	addSequential(new AutoTurn(45.0,3,0.6)); 
    	addSequential(new AutoDrive(150, 3, false));
    	addSequential(new AutoTurn(-45.0,3,0.6));
    	addSequential(new AutoDrive(100, 1, false));
    	
    	//Autonomous L from position 2 with cube
    	addSequential(new AutoDrive(5, 3, false));
    	addSequential(new AutoTurn(15.0,3,0.6)); 
    	addSequential(new AutoDrive(80, 3, false));
    	addSequential(new AutoTurn(-15.0,3,0.6));
    	addSequential(new AutoDrive(12, 1, false));
    	addSequential(new AutoFlip(0.8, 1.5));
    	addSequential(new AutoFlip(0.8, 1.5));
    	addSequential(new AutoDrive(20, 3, true));
    	addSequential(new AutoTurn(45.0,3,0.6));  
    	addSequential(new AutoDrive(25, 3, false));
    	addSequential(new AutoTurn(-45.0,3,0.6));  
    	addSequential(new AutoDrive(25, 3, false));
    	
    	
    	
    	//addSequential(new AutoTurn(90,2,0.5));
    	//addSequential(new AutoDrive(335.65, 3, false));
    	//addSequential(new AutoTurn(90.0,5,0.5));     // Turns 90deg cw at 50% speed (i.e. left)
//		//addSequential(new AutoDrive(43.37, 3, false));
		//addSequential(new AutoFlip(0.8, 1.5));            // Technically invalid now, but boolean will be added later; true lifts, false drops.
//		//addSequential(new AutoDrive(43.37,3,true)); // Backwards 44.25 in, 10s timeout
//		addParallel(new AutoTurn(-90.0,2,0.5));
//		addSequential(new AutoDrive(200,2,true));
//    	
    	//addSequential(new AutoDrive(154.25,10,false));
    	
    	//addSequential(new AutoTurn(90.0,2.0,0.5));
    	
    	/**
    	addSequential(new AutoDrive(154.25,10,false));
		addSequential(new AutoTurn(90.0,2.0,0.5));
		addSequential(new AutoDrive(27.99,10,false));
    	*/
 
		
		
		
		// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
