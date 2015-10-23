package roller;

import org.usfirst.frc.team503.robot.OI;

import roller.RollerSubsystem.Direction;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetRollerAutonCommand extends Command {
	Direction direction;

    public SetRollerAutonCommand(Direction direction) {
    	this.direction = direction;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RollerSubsystem.getInstance().setRollerTalon(direction);
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
