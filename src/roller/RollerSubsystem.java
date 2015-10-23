package roller;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import elevator.ElevatorSubsystem;
import elevator.ElevatorSubsystem.ElevatorSolenoidPosition;

/**
 *:^)
 */
public class RollerSubsystem extends Subsystem {
	
	private RollerSubsystem(){}
	private static RollerSubsystem instance = new RollerSubsystem();
	public static RollerSubsystem getInstance(){
		return instance;
	}
	Solenoid rollerSolenoid = new Solenoid(6);
	Talon leftTalon = new Talon(0);
	Talon rightTalon = new Talon(1);
	
	public static enum Direction{
		IN(-1), OUT(1), OFF(0);
		private int value;
		
		private Direction(int value){
			this.value = value;
		}
	}
	
	public static enum RollerSolenoidPosition{
        OUT(false), IN(true);
        private boolean position;

        private RollerSolenoidPosition(boolean position){
                this.position = position;
        }
	}   
	public void setSolenoidPosition(RollerSolenoidPosition position){
		rollerSolenoid.set(position.position);
		if(position == RollerSolenoidPosition.OUT){
			ElevatorSubsystem.intakeClosed = false;
		} else {
			ElevatorSubsystem.intakeClosed = true;
		}
		ElevatorSubsystem.updateLights();
	}
	
	public void setRollerTalon(Direction direction){
		leftTalon.set(direction.value);		
		rightTalon.set(-direction.value);	
		SmartDashboard.putNumber("Speed", direction.value);
	}
	
	public void setRollerLeft(){
		leftTalon.set(-1);		
		rightTalon.set(-1);
	}
    
	public void setSingleTalon(boolean right, Direction direction){
		if(right){
			rightTalon.set(direction.value);
		}else{
			leftTalon.set(-direction.value);
		}
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

