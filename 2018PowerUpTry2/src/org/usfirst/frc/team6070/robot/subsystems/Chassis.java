package org.usfirst.frc.team6070.robot.subsystems;

import org.usfirst.frc.team6070.robot.RobotMap;
import org.usfirst.frc.team6070.robot.commands.startDriving;
import org.usfirst.frc.team6070.robot.theory6PID.PIDController;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *@author Stephen Guo
 *@author Kshitij Vaidya
 *@author Katie Chen
 */
public class Chassis extends Subsystem {
	
	public Victor BR = new Victor (RobotMap.backLeft);
	public Victor BL = new Victor (RobotMap.backRight); // Creation of Victor SpeedControllers
	public Victor FR = new Victor (RobotMap.frontLeft); // Check RobotMap for more details
	public Victor FL = new Victor (RobotMap.rightFront);

	SpeedControllerGroup motorsLeft = new SpeedControllerGroup (BL, FL);
	SpeedControllerGroup motorsRight = new SpeedControllerGroup (BR, FR);

	public DifferentialDrive drive = new DifferentialDrive (motorsLeft, motorsRight);
	/*
	After the deprecated RobotDrive, we use differential drive instead. Using 2 speed controller
	groups, we can use differential drive. Motors are already inverted. 
	*/
	ADXRS450_Gyro gyro = new ADXRS450_Gyro (); // Initialize gyro
	
	Accelerometer accel = new BuiltInAccelerometer (Accelerometer.Range.k4G);
	
	//Accel
	
	double acc = 0;
	double vel = 0;
	public double dist = 0;
	double anglefix;
	
	double kpgyro = 0.1;
	double kigyro = 0.0;
	double kdgyro = 0.02;
	
	double kpaccel = 0.7;
	double kiaccel = 0.0; // all error values for integration and derivation of values; epsilon values
	double kdaccel = 0.0;
	
	double kpdrive = 0.11;
	double kidrive = 0.0;
	double kddrive = 0.05;
	
	double kpTutorial = 0.3;
	
	
	
	//Mark: Encoders
	
	/*Wheel Diameter: 6 inches
	 * Circumference Formula: 2*pi*r
	 * Wheel Circumference, theory: 18.84995
	 * Wheel Circumference: 19 inches
	 */
	
	Encoder leftEnc = new Encoder (3, 4, false, Encoder.EncodingType.k4X); // 3, 4 refers to channels; These are located on the RIO's DIO ports 
	Encoder rightEnc = new Encoder (1, 2, true, Encoder.EncodingType.k4X); // and refers either to power or input. 
	
	public PIDController gyroPID = new PIDController (kpgyro, kigyro, kdgyro);
	public PIDController accelPID = new PIDController (kpaccel, kiaccel, kdaccel);
	public PIDController drivePID = new PIDController (kpdrive, kidrive, kddrive);
	

	
	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
    	// Invert motors in case we are backwards
    	// will comment out if Mechanical does this right
    	
        // Set the default command for a subsystem here.
    	//motorsLeft.setInverted(true);
    	//motorsRight.setInverted(true);
        
        gyro.reset();
        anglefix = 0;
        leftEnc.setDistancePerPulse(RobotMap.driveEncoderDistPerTick);
        rightEnc.setDistancePerPulse(RobotMap.driveEncoderDistPerTick);
        setDefaultCommand(new startDriving());
        
        
        
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed, boolean a){
    	/*
    	if (leftSpeed < 0 && rightSpeed < 0){
    		double correctionSpeed = rightSpeed;
    		drive.tankDrive(leftSpeed, correctionSpeed);
    	}else if (leftSpeed > 0 && rightSpeed > 0){
    		double correctionSpeed = 0.9*leftSpeed;
    		drive.tankDrive(correctionSpeed, rightSpeed, a);
    	}else{
    		drive.tankDrive(leftSpeed, rightSpeed);
    	}
    	*/
    	
    	drive.tankDrive(leftSpeed*0.973, rightSpeed*0.973, a);
    	
    	
    	
    	
    	/*
    	if (leftEnc.getDistance() < rightEnc.getDistance()){
    		double strength = drivePID.calcPID(returnErrorDist(), getAvgDist(), 0.5);
    		drive.tankDrive(strength*leftSpeed, rightSpeed);
    	}
    	*/
    	// tank drive; boolean value decreases joystick sensitivity at lower values
    	
    	
    }
    
    public void spin(){
    	drive.arcadeDrive(0,1);
    }
    
    public void stop(){
    	drive.tankDrive(0, 0);
    }
    
    public void arcadeDrive(double y, double x){
    	drive.arcadeDrive(y,x);
    }
    
    public double getLeftEncTick(){
    	return leftEnc.get();
    }
    
    public double getRightEncTick(){
    	return rightEnc.get();
    }
   
    
    public double getLeftEnc(){
    	return leftEnc.getDistance(); // all commands we need to call continuously to run PID loop
    }
    
    public double getRightEnc(){
    	return rightEnc.getDistance();
    }
    
    public double returnErrorDist(){
    	return leftEnc.getDistance();
    }
    
    public double getAvgDist(){
    	//return getRightEnc();
    	return ((getRightEnc() + getLeftEnc())/2);
    }
    
    public void setDirection(){
    	anglefix = gyro.getAngle();
    }
    
    public void resetEnc(){
    	leftEnc.reset();
    	rightEnc.reset();
    }
    
    public double getGyroYaw(){
    	return gyro.getAngle();
    }
    
    public void resetGyro(){
    	gyro.reset();
    }
    
    public void resetAccel(){
    	acc=0;
    	vel=0;
    	dist=0;
    	anglefix = 0;
    }
    
    public double getAccel(){
    	return accel.getX();
    }
    
    public double getDist(){
    	return dist;
    }
    
    public double driveStraightDist(double dist) {
    	double strength = drivePID.calcPID(dist, getAvgDist(), 0.5);
    	//set to *1.05 to adjust for weight imbalance
    	drive.tankDrive(-0.65*strength, -0.65*strength*1.05); //Original Value is (-0.5*strenght, ...); this might change for test purposes
    	
    	return getAvgDist();
    } 					//Set (-0.5*..) to (-0.65*...)
    
    

	/* I have no idea how this works. Since I didn't even bother trying to understand the 1241
	 * PID loop, I think this may be beyond me. I'll try to piece together how this thing works. 
	 * getAvgDist() is a command that is probably looped in the drivePID command. I think
	 * that part specifies where you are at, and the dist is the distance you want to travel
	 * to. So, assuming that the encoders constantly change, the getAvgDist command
	 * is constantly looped throughout the course of the command. Once the two values are 
	 * within the epsilon value of 0.5, the drive stops. The drivePID also somehow returns
	 * a double, which is an (ideal? constant? Variable?) speed for the speed of the
	 * Robot to drive at. Multiplying this (ideal?) speed by a base speed will return to us the
	 * ideal drive speed we want the robot to be at. Honestly, for autonomous mode,
	 * the speed must be low, and thus the strength multiplier.  
	 * 
	 * 
	 */
    
    public void driveStraight(boolean backwards){
    	if (backwards){
    		drive.tankDrive(0.7, 0.7); //Increased speed from 60% to 70%
    	}else{
    		drive.arcadeDrive(-0.7, -0.7); //Increaed speed from 60% to 70%
    	}
    }
    

	/* remember, all the motors are inverted. 
	 this means backwards = forwards, forwards = backwards. This, to go forward we actually 
	have a negative value. This does not affect the orientation of teleop, as the user's
	brain will compensate for front and back, but only applies in auto. 
	Example of this can be seen in driveStraightDist command, where both motors in the
	tankDrive are set to negative. 
	*/
    
    public void turnToAngle (double angle, double speed){ // given an angle you want to turn to,
    	double ang = gyroPID.calcPID(angle%360, getGyroYaw()%360, 1); // get the angle you are at
    	drive.tankDrive(speed*ang, -speed*ang); // returns a double, which can be used as a speed value
    	// 1241's PID works as follows: you input the angle you want, the angle you are at,
    	//and the epsilon value; it should return a double, which is speed of rotation; this is just a superflous double
    	//the PID will autocorrect given the two angles as it loops, until it reaches timeout or the episilon value. 
    	
    }
    
    public void getGyroPIDValues (){
    	kpgyro = SmartDashboard.getNumber("kpval", 0.1);
    	kigyro = SmartDashboard.getNumber("kival", 0.0);
    	kdgyro = SmartDashboard.getNumber("kdval", 0.02);
    	gyroPID.changePIDGains(kpgyro, kigyro, kdgyro);
    }
    
    public void getAccelPID(){
    	kpaccel = SmartDashboard.getNumber("kpacc", 0.07);
    	kiaccel = SmartDashboard.getNumber("kiacc", 0.0);
    	kdaccel = SmartDashboard.getNumber("kdacc", 0.0);
    	accelPID.changePIDGains(kpaccel, kiaccel, kdaccel);
    }
    
    public void getDrivePID(){
    	kpdrive = SmartDashboard.getNumber("kpdrive", 0.11);
    	kidrive = SmartDashboard.getNumber("kidrive", 0.0);
    	kddrive = SmartDashboard.getNumber("kddrive", 0.05);
    }
    
   
    
    
    
}

