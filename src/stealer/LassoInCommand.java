package stealer;

import org.usfirst.frc.team503.robot.OI;

import stealer.StealerSubsystem.LassoPosition;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LassoInCommand extends Command {
	final double TIME = 1.5;
	Timer timer = new Timer();

    public LassoInCommand() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//setTimeout(TIME);
    	timer.start();
    	//StealerSubsystem.getInstance().zeroLasso();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		StealerSubsystem.getInstance().setLassoSpeed(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > TIME;
    }

    // Called once after isFinished returns true
    protected void end() {
    	StealerSubsystem.getInstance().setLassoSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
