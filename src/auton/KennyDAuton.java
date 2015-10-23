package auton;

import stealer.StealerSubsystem;
import stealer.StealerSubsystem.StealerPosition;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import drivetrain.DriveStraightStealCommand;

/**
 *
 */
public class KennyDAuton extends Command {
	Timer timer;
    public KennyDAuton() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		timer = new Timer();
    		timer.start();
    		StealerSubsystem.getInstance().setSpeed(-.3);
    }
   

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		//SmartDashboard.putNumber("timer", timer.get());
    }

    // Make this return true when this Command no longer needs to run execute() mj
    protected boolean isFinished() {
        return timer.get() > 1.47;
    }

    // Called once after isFinished returns true
    protected void end() {
    	StealerSubsystem.getInstance().setSpeed(-.1);
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
