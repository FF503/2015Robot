
package drivetrain;

import org.usfirst.frc.team503.robot.OI;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem implements PIDSource,
PIDOutput { 
	
	private Drivetrain(){
	}
	private static Drivetrain instance = new Drivetrain();
	public static Drivetrain getInstance(){
		return instance;
	}
	private static final double p = .045, i = 0, d = 0;
	private static final PIDController controller = new PIDController(p, i, d,
			getInstance(), getInstance());
    
	private static final CustomRobotDrive drive = CustomRobotDrive.getInstance();
	private final DoubleSolenoid elevatorSolenoid = new DoubleSolenoid(3, 2);//4, 5
	private final DoubleSolenoid grabberSolenoid = new DoubleSolenoid(0, 1);
	private static final Encoder drivetrainEncoder = new Encoder(0, 1, false, EncodingType.k4X);
	public static final int encoderDir = 1;
	
	static{
		drivetrainEncoder.setDistancePerPulse(.073631077);
		drivetrainEncoder.setMaxPeriod(1 /* seconds */);
		drivetrainEncoder.reset();
		controller.setOutputRange(-0.1, 0.1);
		controller.setAbsoluteTolerance(8);
	}
	
	
	public static enum Direction{
		Drivetrain(Value.kForward), Gamespec(Value.kReverse);
		private Value value;
		
		private Direction(Value value){
			this.value = value;
		}
	}
	
	public void arcadeDrive(double speedValue, double rotateValue, boolean squaredInputs){
		if(!OI.getStraightButton()){
			drive.arcadeDrive(speedValue, -1*rotateValue, squaredInputs);
		}else{
			drive.arcadeDrive(speedValue, 0, squaredInputs);
		}
	}
	
	public void tankDrive(double leftSpeed, double rightSpeed){
		drive.tankDrive(leftSpeed, rightSpeed);
	}
	
	public void setElevatorSolenoid(Direction position){
		elevatorSolenoid.set(position.value);
	}
	
	public void setGrabberSolenoid(Direction position){
		grabberSolenoid.set(position.value);
	}
	
	public static void setSetpoint(double distance){
		controller.setSetpoint(distance);
	}
	public static boolean onTarget() {
		return controller.onTarget();
	}
	
	public static double getRate(){
		return drivetrainEncoder.getRate();
	}

	public static boolean isStopped() {
		return Math.abs(getRate()) < 0.1;
	}
	
	public static void pidReset(){
		controller.reset();
	}
	
	public static void pidEnable() {
		controller.enable();
	}

	public static void pidDisable() {
		controller.disable();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	public void pidWrite(double speed) {
		SmartDashboard.putNumber("PIDWRITE", speed);
		//arcadeDrive(speed, 0, OI.squaredInputs);			
	}
	
	public static double getDistance(){
		return drivetrainEncoder.getDistance();
		
	}
	
	public static double getError(){
		return controller.getError();
	}
	
	public static double getPIDLastOutput(){
		return controller.get();
	}
	
	public static void zero(){
		drivetrainEncoder.reset();
	}
	
	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return getDistance();
	}
}

