package elevator;

import org.usfirst.frc.team503.robot.OI;

import drivetrain.CustomRobotDrive;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorSubsystem extends Subsystem implements PIDSource,
		PIDOutput {

	private ElevatorSubsystem(){}
	private static ElevatorSubsystem instance = new ElevatorSubsystem();

	public static ElevatorSubsystem getInstance() {
		return instance;
	}

	private static final double p = 0.1, i = 0.0001, d = 0; // why is p negative
	private static final PIDController controller = new PIDController(p, i, d,
			getInstance(), getInstance());
	private static final Encoder elevatorEncoder = new Encoder(5, 4, false, EncodingType.k4X);
	private final DoubleSolenoid elevatorSolenoid = new DoubleSolenoid(4, 5); 
	private final Solenoid binClawSolenoid = new Solenoid(7);

	static{
		elevatorEncoder.setDistancePerPulse(.0245);
		elevatorEncoder.setMaxPeriod(1 /* seconds */);
		elevatorEncoder.reset();
		controller.setOutputRange(-1, 1);
		controller.setAbsoluteTolerance(0.1);
	}

	public static enum BinClawPosition{
		OPEN(true), CLOSED(false);
		private final boolean position;
		private BinClawPosition(boolean position){
			this.position = position;
		}
	}
	
	public static enum ElevatorSolenoidPosition {
		OPEN(DoubleSolenoid.Value.kForward), CLOSE(DoubleSolenoid.Value.kReverse);
		private DoubleSolenoid.Value position;

		private ElevatorSolenoidPosition(DoubleSolenoid.Value position) {
			this.position = position;
		}
	}

	public static enum ElevatorPosition {
		TOP(62), SECOND(46.5), THIRD(31), FOURTH(20.5), BOTTOM(0);
		private double position;

		private ElevatorPosition(double position) {
			this.position = position;
		}
	}

	// lowest distance at pos 0
	private static ElevatorPosition[] positions = new ElevatorPosition[] {
			ElevatorPosition.BOTTOM, ElevatorPosition.FOURTH,
			ElevatorPosition.THIRD, ElevatorPosition.SECOND,
			ElevatorPosition.TOP };

	public static void determinePosition() {
		if (getDistance() > (positions[positions.length - 1].position + positions[positions.length-2].position)/2) {
			OI.position = positions.length - 1;
		}else if (getDistance() < ((positions[0].position + positions[1].position)/2)) {
			OI.position = 0;
		}else {
			for(int i = 1; i < positions.length - 1; i++) {
				if ((positions[i + 1].position + positions[i].position) / 2 > getDistance()
						&& (positions[i].position + positions[i - 1].position) / 2 < getDistance()) {
					OI.position = i;
				}
			}
		}
	}

	public ElevatorPosition[] getPositions() {
		return positions;
	}

	public void setSolenoidPosition(ElevatorSolenoidPosition position) {
		elevatorSolenoid.set(position.position);
		if(position == ElevatorSolenoidPosition.CLOSE){
			gripperClosed = false;
		} else {
			gripperClosed = true;
		}
		updateLights();
	}

	public void setBinClawPosition(BinClawPosition position) {
		binClawSolenoid.set(position.position);
	}

	public void setSpeed(double speed) {
		CustomRobotDrive.getInstance().setElevatorSpeed(speed);
	}

	public static double getDistance() {
		return elevatorEncoder.getDistance();
	}

	@Override
	public void pidWrite(double speed) {
		SmartDashboard.putNumber("PIDWRITE", speed);
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return getDistance();
	}

	public static void setSetpoint(ElevatorPosition position) {
		controller.setSetpoint(position.position);
	}

	public static boolean onTarget() {
		return controller.onTarget();
	}

	public static boolean isStopped() {
		return elevatorEncoder.getRate() < 3;
	}
	
	public static double getError(){
		return controller.getError();
	}

	public static void pidEnable() {
		controller.enable();
	}

	public static void pidDisable() {
		controller.disable();
	}
	
	public static double getRate(){
		return elevatorEncoder.getRate();
	}
	
	private static boolean gripperClosed;
	public static boolean intakeClosed;
	private static Relay lightrelay = new Relay(0, Relay.Direction.kBoth);
	public static void updateLights(){
		if(!gripperClosed && intakeClosed){
			lightrelay.set(Relay.Value.kOn);
		} else if ( !gripperClosed){
			lightrelay.set(Relay.Value.kReverse);
		} else if ( intakeClosed){
			lightrelay.set(Relay.Value.kForward);
		} else {
			lightrelay.set(Relay.Value.kOff);
		}
	}
	
	public static double getPIDLastOutput(){
		return controller.get();
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}