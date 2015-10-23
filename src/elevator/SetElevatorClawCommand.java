package elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import elevator.ElevatorSubsystem.ElevatorSolenoidPosition;

/**
 *
 */
public class SetElevatorClawCommand extends Command {
	ElevatorSolenoidPosition position;

    public SetElevatorClawCommand(ElevatorSolenoidPosition position) {
    	this.position = position;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ElevatorSubsystem.getInstance().setSolenoidPosition(position);
    	SmartDashboard.putString("ClawPosition", position.name());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
