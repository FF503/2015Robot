package elevator;

import org.usfirst.frc.team503.robot.OI;

import drivetrain.CustomRobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevatorGoToPosition extends Command {
	
	int position;
	int positionsRunning;

    public ElevatorGoToPosition(int position) {
    	this.position = position;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(4);
    	if(position > -1 && position < ElevatorSubsystem.getInstance().getPositions().length){
    		OI.positionCommandsRan++;
    		positionsRunning = OI.positionCommandsRan;
    		ElevatorSubsystem.setSetpoint(ElevatorSubsystem.getInstance().getPositions()[position]);
    		ElevatorSubsystem.pidEnable();
    	}else{
    		end();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = ElevatorSubsystem.getError();
    	SmartDashboard.putNumber("Elevator dte:", error);
    		if(Math.abs(error) >  2){ //rate*3 is usually a lot higher than 5
    			CustomRobotDrive.getInstance().setElevatorSpeed(error > 0 ? -0.5 : 0.5); 
    			SmartDashboard.putNumber("ElevatorSpeed", error > 0 ? 0.5 : -0.5);
    		}else{
    			CustomRobotDrive.getInstance().setElevatorSpeed(ElevatorSubsystem.getPIDLastOutput() < 0 ? ElevatorSubsystem.getPIDLastOutput() : 0*ElevatorSubsystem.getPIDLastOutput());
    			SmartDashboard.putNumber("getPIDLastOutput", ElevatorSubsystem.getPIDLastOutput());
    		}
    		if(OI.positionCommandsRan > positionsRunning){
    			SmartDashboard.putBoolean("ELEVATOR END", true);
    		}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return OI.positionCommandsRan > positionsRunning;
    }
    // Called once after isFinished returns true
    protected void end() {
    	//ElevatorSubsystem.pidDisable();
    	//ElevatorSubsystem.getInstance().setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
