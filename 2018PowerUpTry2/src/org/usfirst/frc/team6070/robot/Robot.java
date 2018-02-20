
package org.usfirst.frc.team6070.robot;

import org.usfirst.frc.team6070.robot.commands.AllAuto;
import org.usfirst.frc.team6070.robot.commands.AutoDrive;
import org.usfirst.frc.team6070.robot.commands.AutoTurn;
import org.usfirst.frc.team6070.robot.commands.BlueAllianceFarLeft;
import org.usfirst.frc.team6070.robot.commands.BlueAllianceFarRight;
import org.usfirst.frc.team6070.robot.commands.BlueAllianceMidRight;
import org.usfirst.frc.team6070.robot.commands.Donuts;
import org.usfirst.frc.team6070.robot.commands.EZ5;
import org.usfirst.frc.team6070.robot.commands.FarSideAuto;
import org.usfirst.frc.team6070.robot.commands.NoAuto;
import org.usfirst.frc.team6070.robot.commands.RedAllianceFarLeft;
import org.usfirst.frc.team6070.robot.commands.RedAllianceFarRight;
import org.usfirst.frc.team6070.robot.commands.RedAllianceMidRight;
import org.usfirst.frc.team6070.robot.commands.Test;
import org.usfirst.frc.team6070.robot.subsystems.Chassis;
import org.usfirst.frc.team6070.robot.subsystems.IntakeSolenoids;
import org.usfirst.frc.team6070.robot.subsystems.Roller;
import org.usfirst.frc.team6070.robot.subsystems.Sparky;
import org.usfirst.frc.team6070.robot.OI;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
	
	public static PowerDistributionPanel pdp;
	public static Chassis DriveBase;
	public static Roller Rollers;
	public static Sparky sparkFlip;
	public static IntakeSolenoids pneumaticIntake;
	public static OI oi;
	Preferences pref;
	
	UsbCamera cam1;
	UsbCamera cam2;
	VideoSink server;
	

	Command autonomousCommand;
	public SendableChooser<Command> chooser;
	
	boolean frontCamisEnabled = false;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		pdp = new PowerDistributionPanel(0);
		/*
		if (OI.xbox.getRawButton(1)) {
			pdp.clearStickyFaults();
		} */
	/*	
	 * ah, the PDP. The source of all my comms problems, the thing that gave me infinite 
	 * headaches. 
	 * 
	 * Theoretically, you do not need a PDP to actually run a robot. The CAN,
	 * or Control Area Network, regulates most voltage outputs with ease. 
	 * However, it is safe to use a PDP because the power regulation,
	 * working in accordance to the CAN, is incredibly efficient and 
	 * powerful. 
	 * 
	 * However, before actually implementing the PDP, you must first undergo a series 
	 * of steps that are painfully arduous. They must be done for the CAN to 
	 * not timeout, which can result in lag in the robot as well as total comms loss. 
	 * 
	 * First, download CTRE PHOENIX FRAMEWORK INSTALLER at 
	 * https://www.ctr-electronics.com/hro.html#product_tabs_technical_resources. Install
	 * the program, run the software, click on the RIO tab, and install the lifeboat
	 * program onto the RIO. Then go to 
	 * https://www.ctr-electronics.com/pdp.html#product_tabs_technical_resources and
	 * download the PDP imaging file. Then, connect to the Robot via USB, and search
	 * roboRIO-6070-FRC.local in INTERNET EXPLORER ONLY with Microsoft Silverlight 
	 * installed, which will bring you to the Webdashboard of the RIO and CAN. There,
	 * click on the PDP tab, which will show you the PDP operation specs and its current
	 * state. At the bottom right corner, click Update Firmware, and select the
	 * de-compressed PDP imaging file. Update the firmware, restart the Robot, restart
	 * the DS, SmartDashBoard, eclipse, etc and run the robot. It should have no further
	 * problems. 
	 * 
	 * Oh, and make sure that inside the brackets of pdp= new PowerDistributionPanel(0);
	 * you know that the constructor number is correct. This can also be found in
	 * the webDashBoard. 
	 *
	 * 
	*/
		DriveBase = new Chassis ();
		Rollers = new Roller();
		sparkFlip = new Sparky();
		pneumaticIntake = new IntakeSolenoids();
		oi = new OI();
		pref = Preferences.getInstance();
		chooser = new SendableChooser<Command>();
		
		
		chooser.addDefault("No Auto", new NoAuto());
		chooser.addObject("Test", new Test());
		// chooser.addObject("My Auto", new MyAutoCommand());
		//chooser.addObject("EZ5", new EZ5());
//		chooser.addObject("Blue Left", new BlueAllianceFarLeft());
//		//chooser.addObject("Blue Right", new BlueAllianceFarRight());
//		chooser.addObject("Blue Middle", new BlueAllianceMidRight());
//		chooser.addObject("Red Left", new RedAllianceFarLeft());
//		chooser.addObject("Red Right", new RedAllianceFarRight());
//		chooser.addObject("Red Middle", new RedAllianceMidRight());
		SmartDashboard.putData("Auto mode", chooser);
		
		chooser.addObject("Blue Left", new AllAuto(-1));
		chooser.addObject("Blue Right", new AllAuto(1));
		chooser.addObject("Blue Middle", new AllAuto(0));
		chooser.addObject("Red Left", new AllAuto(-1));
		chooser.addObject("Red Right", new AllAuto(1));
		chooser.addObject("Red Middle", new AllAuto(0));
		
		Robot.DriveBase.resetAccel();
		Robot.DriveBase.resetGyro();
		Robot.DriveBase.resetEnc();
		
		cam1 = CameraServer.getInstance().startAutomaticCapture();
//		cam2 = CameraServer.getInstance().startAutomaticCapture();
		server = CameraServer.getInstance().getServer();
		
		SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putData("DriveStraight for 5:", new AutoDrive(10,2,false));
		SmartDashboard.putData("Test Turn", new AutoTurn(90, 2, 0.6));
		  
		//SmartDashboard.putData("Auto Balls", new DeliverBall(1, 0.25));
		SmartDashboard.putData("Chassis:", DriveBase);
			
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Robot.DriveBase.resetGyro();
	}

	@Override
	public void disabledPeriodic() {
		runCameraSystem();
		updateSmartDashboard();
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
//		pdp.clearStickyFaults();
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
//		pdp.clearStickyFaults();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}
	
	public void updateSmartDashboard(){
		SmartDashboard.putNumber("Accel", DriveBase.getAccel());
		SmartDashboard.putNumber("Gyro val", DriveBase.getGyroYaw());
		SmartDashboard.putNumber("Dist: ", DriveBase.getDist());
		SmartDashboard.putData("Chassis:", DriveBase);
		SmartDashboard.putNumber("Encoders", DriveBase.getAvgDist());
		SmartDashboard.putNumber("Left Side", DriveBase.getLeftEnc());
		SmartDashboard.putNumber("Right Side", DriveBase.getRightEnc());
		SmartDashboard.putNumber("Right Side Ticks", DriveBase.getRightEncTick());
		SmartDashboard.putNumber("Left Side Ticks", DriveBase.getLeftEncTick());
		
		//SmartDashboard.putNumber("Current", pdp.getCurrent(ROBOT_TASK_PRIORITY));
		//SmartDashboard.putNumber("Current", pdp.getTotalCurrent());
		//SmartDashboard.putNumber("Watts", pdp.getTotalPower());
		//SmartDashobard.putNumber("",pdp.getSubsystem());
//		SmartDashboard.putNumber(", value)
	}

	
	public void runCameraSystem(){
		
		frontCamisEnabled =  OI.stick.getRawButton(1);
		
	//	if (OI.stick.getRawButton(2)&&!frontCamisEnabled){
		//	server.setSource(cam2);
		//}else
			if (OI.stick.getRawButton(2) && frontCamisEnabled){
			server.setSource(cam1);
		}
	}
	
	
}


