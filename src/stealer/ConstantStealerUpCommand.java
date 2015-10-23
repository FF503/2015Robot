package stealer;

import org.usfirst.frc.team503.robot.OI;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ConstantStealerUpCommand extends Command {

    public ConstantStealerUpCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	OI.endStealer = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	StealerSubsystem.getInstance().setSpeed(0.2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !DriverStation.getInstance().isAutonomous() || StealerSubsystem.getDistance() < 20;
    }

    // Called once after isFinished returns true
    protected void end() {
    	StealerSubsystem.getInstance().setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
