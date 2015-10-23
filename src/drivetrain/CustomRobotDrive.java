package drivetrain;

import org.usfirst.frc.team503.robot.OI;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CustomRobotDrive {
	private CANTalon frontLeftMotor;
	private CANTalon midLeftMotor;
	private CANTalon backLeftMotor;
	private CANTalon frontRightMotor;
	private CANTalon midRightMotor;
	private CANTalon backRightMotor;

	private CustomRobotDrive(int frontLeftMotor, int midLeftMotor,
			int rearLeftMotor, int frontRightMotor, int midRightMotor,
			int rearRightMotor) {
		this.frontLeftMotor = new CANTalon(frontLeftMotor);
		this.midLeftMotor = new CANTalon(midLeftMotor);
		this.backLeftMotor = new CANTalon(rearLeftMotor);
		this.frontRightMotor = new CANTalon(frontRightMotor);
		this.midRightMotor = new CANTalon(midRightMotor);
		this.backRightMotor = new CANTalon(rearRightMotor);
		this.frontLeftMotor.enableBrakeMode(false);
		this.midLeftMotor.enableBrakeMode(false);
		this.backLeftMotor.enableBrakeMode(false);
		this.frontRightMotor.enableBrakeMode(false);
		this.midRightMotor.enableBrakeMode(false);
		this.backRightMotor.enableBrakeMode(false);
	}
	private static CustomRobotDrive instance = new CustomRobotDrive(4,5,1,3,2,0);//new CustomRobotDrive(4,0,2,5,1,3)
	
	public static CustomRobotDrive getInstance(){
		return instance;
	}

	public void arcadeDrive(double moveValue, double rotateValue,
			boolean squaredInputs) {

		double leftMotorSpeed;
		double rightMotorSpeed;

		moveValue = limit(moveValue);
		rotateValue = limit(rotateValue);

		if (squaredInputs) {
			// square the inputs (while preserving the sign) to increase fine
			// control while permitting full power
			if (moveValue >= 0.0) {
				moveValue = (moveValue * moveValue);
			} else {
				moveValue = -(moveValue * moveValue);
			}
			
			if (rotateValue >= 0.0) {
				rotateValue = (rotateValue * rotateValue);
			} else {
				rotateValue = -(rotateValue * rotateValue);
			}
		}

		if (moveValue > 0.0) {
			if (rotateValue > 0.0) {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = Math.max(moveValue, rotateValue);
			} else {
				leftMotorSpeed = Math.max(moveValue, -rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			}
		} else {
			if (rotateValue > 0.0) {
				leftMotorSpeed = -Math.max(-moveValue, rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			} else {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
			}
		}
		
		SmartDashboard.putNumber("frontLeftMotor", frontLeftMotor.get());
		SmartDashboard.putNumber("midLeftMotor", midLeftMotor.get());
		SmartDashboard.putNumber("backLeftMotor", backLeftMotor.get());
		SmartDashboard.putNumber("frontRightMotor", frontRightMotor.get());
		SmartDashboard.putNumber("midRightMotor", midRightMotor.get());
		SmartDashboard.putNumber("backRightMotor", backRightMotor.get());

		if (OI.mode == 0) {
			setModeZeroMotorOutputs(leftMotorSpeed, rightMotorSpeed);
		} else if (OI.mode == 1) {
			setModeOneMotorOutputs(leftMotorSpeed, rightMotorSpeed);
		} else if (OI.mode == 2) {
			setModeTwoMotorOutputs(leftMotorSpeed, rightMotorSpeed);
		}
	}
	
	public void tankDrive(double leftSpeed, double rightSpeed){
		if (OI.mode == 0) {
			setModeZeroMotorOutputs(leftSpeed, rightSpeed);
		} else if (OI.mode == 1) {
			setModeOneMotorOutputs(leftSpeed, rightSpeed);
		} else if (OI.mode == 2) {
			setModeTwoMotorOutputs(leftSpeed, rightSpeed);
		}
	}
	
	public void setElevatorSpeed(double speed){
		if(OI.mode == 1 || OI.mode == 3){
			speed = limit(speed);
			frontLeftMotor.set(-speed);
			frontRightMotor.set(speed);
		}		
	}
	
	public void setGrabberSpeed(double speed){
		if(OI.mode == 2 || OI.mode == 3){
			speed = limit(speed);
			midLeftMotor.set(-speed);
			backLeftMotor.set(-speed);
			midRightMotor.set(speed);
			backRightMotor.set(speed);
		}
	}

	private void setModeZeroMotorOutputs(double leftSpeed, double rightSpeed) {
		frontLeftMotor.set(-leftSpeed);
		midLeftMotor.set(-leftSpeed);
		backLeftMotor.set(-leftSpeed);
		frontRightMotor.set(rightSpeed);
		midRightMotor.set(rightSpeed);
		backRightMotor.set(rightSpeed);
	}

	private void setModeOneMotorOutputs(double leftSpeed, double rightSpeed) {
		midLeftMotor.set(-leftSpeed);
		backLeftMotor.set(-leftSpeed);
		midRightMotor.set(rightSpeed);
		backRightMotor.set(rightSpeed);
	}

	private void setModeTwoMotorOutputs(double leftSpeed, double rightSpeed) {
		frontLeftMotor.set(-leftSpeed);
		frontRightMotor.set(rightSpeed);
	}

	private static double limit(double num) {
		if (num > 1.0) {
			return 1.0;
		}
		
		if (num < -1.0) {
			return -1.0;
		}
		
		if(num < 0.1 && num > -0.1){
			return 0;
		}
		
		return num;
	}

}
