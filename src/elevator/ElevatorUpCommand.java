package elevator;

import org.usfirst.frc.team503.robot.OI;

import drivetrain.CustomRobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevatorUpCommand extends Command {
	

    public ElevatorUpCommand() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(OI.position < ElevatorSubsystem.getInstance().getPositions().length - 1){
    		ElevatorSubsystem.setSetpoint(ElevatorSubsystem.getInstance().getPositions()[OI.position++]);
    	}
    	ElevatorSubsystem.pidEnable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = ElevatorSubsystem.getError();
    	SmartDashboard.putNumber("El dte:", error);
    	if(Math.abs(error) > Math.min(Math.abs(ElevatorSubsystem.getRate()*3), 5)){
    		SmartDashboard.putBoolean("El", true);
    		CustomRobotDrive.getInstance().setElevatorSpeed(error > 0 ? 0.75 : -0.75); 
    	}else{
    		SmartDashboard.putBoolean("El", false);
    		CustomRobotDrive.getInstance().setElevatorSpeed(ElevatorSubsystem.getPIDLastOutput());
    	}
    	SmartDashboard.putBoolean("El END", false);
    	if((ElevatorSubsystem.onTarget() && ElevatorSubsystem.isStopped())){
    		SmartDashboard.putBoolean("El END", true);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (ElevatorSubsystem.onTarget() && ElevatorSubsystem.isStopped());
    }

    // Called once after isFinished returns true
    protected void end() {
    	ElevatorSubsystem.pidDisable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
