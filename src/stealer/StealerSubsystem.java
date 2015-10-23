package stealer;

import drivetrain.CustomRobotDrive;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class StealerSubsystem extends Subsystem implements PIDSource, PIDOutput{
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private StealerSubsystem() {}
	private static StealerSubsystem instance = new StealerSubsystem();
	public static StealerSubsystem getInstance() {
		return instance;
	}
	
	private static final double p = 0.01, i = 0.0000, d = 0; // why is p negative
	private static final PIDController controller = new PIDController(p, i, d,
			getInstance(), getInstance());
	private static final Encoder stealerEncoder = new Encoder(6, 7, true, EncodingType.k4X);
	private static Counter lassoCounter = new Counter(10);
	private double previousSpeed = 0;

	static{
		//stealerEncoder.setDistancePerPulse(.0245);
		stealerEncoder.setMaxPeriod(1 /* seconds */);
		stealerEncoder.reset();
		lassoCounter.reset();
		controller.setOutputRange(-0.2, 0.2);
		controller.setAbsoluteTolerance(50);
	}
	
	private DigitalInput outSwitch = new DigitalInput(11); 	
	private DigitalInput inSwitch = new DigitalInput(12);
	private Talon lasso = new Talon(2);
	
	
	public static enum StealerPosition{
		IN(0), OUT(200);
		double position;
		private StealerPosition(double position){
			this.position = position;
		}	
	}
	
	public static enum LassoPosition{
		IN(0), OUT(1);
		double position;
		private LassoPosition(double position){
			this.position = position;
		}	
	}
	
	public double getLassoDistance(){
		return lassoCounter.getDistance();
	}
	
	public void zeroLasso(){
		lassoCounter.reset();
	}
	
	public boolean getOutSwitch() {
		return outSwitch.get();	
	}
	
	public boolean getInSwitch() {
		return inSwitch.get();
	}
	
	public void setSpeed(double speed) {
		
		CustomRobotDrive.getInstance().setGrabberSpeed(speed);
		previousSpeed = speed;
	}
	
	public void grabberStop(){
		setSpeed(0);
	}
	
	public void setLassoSpeed(double speed) {
		lasso.set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		
	}
	
	public static double getDistance(){
		return stealerEncoder.getDistance();
	}
	
	public static double getError(){
		return controller.getError();
	}
	
	public static double getPIDLastOutput(){
		return controller.get();
	}

	public static boolean onTarget(){
		return controller.onTarget();
	}
	
	public static boolean isStopped(){
		return stealerEncoder.getRate() < 3;
	}
	
	public static void pidEnable(){
		controller.enable();
	}
	
	public static void pidDisable(){
		controller.disable();
	}
	
	public static void setSetpoint(double position){
		controller.setSetpoint(position);
	}
	
	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return getDistance();
	}
}

 