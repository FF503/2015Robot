package roller;

import roller.RollerSubsystem.RollerSolenoidPosition;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetRollerSolenoidCommand extends Command {
	RollerSolenoidPosition position;

    public SetRollerSolenoidCommand(RollerSolenoidPosition position) {
    	this.position = position;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RollerSubsystem.getInstance().setSolenoidPosition(position);
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
