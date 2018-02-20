package org.usfirst.frc.team6070.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static int frontLeft = 2;
	public static int backLeft = 3;
	public static int rightFront = 1;// victor motor map; naming...??
	public static int backRight = 4; //Robot says use Port 0, but currently plugged into port 4
	
	//public static int climber1 = 4; // climber motor map
	public static int climber2 = 5;
	
	public static double catapultSpeedModifier = 0.8;
	
	public static int sparkFlip = 5;
	
	public static int leftRoller = 7;
	public static int rightRoller = 6;
	
	/*
	public static int boxIntakeLeft = 6;
	public static int boxIntakeRight = 7;
	
	public static int gearmanip = 6; //linear actuator map
	public static int gearisthere = 7;// ??????
	*/
	
	public static double forwardval = 3; // values for turning, update ASAP 
	public static double turnangle = 54.9;
	
	public static final int driveWheelRadius = 3;
	public static final int drivePulsePerRotation = 2048; // amount of pulses per wheel/motor rotation
	public static final double driveGearRatio = 1/1; //ratio between number of wheels to encoder;
	public static final double driveEncoderPulsePerRotation = drivePulsePerRotation*driveGearRatio; // pulse per rotation * gear ratio
	public static final double driveEncoderDistPerTick = (Math.PI*2*driveWheelRadius)/driveEncoderPulsePerRotation; // circumference over number of ticks gives dist traveled per tick
	
	
	public static final double forkliftSpeed = 0.3;
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
