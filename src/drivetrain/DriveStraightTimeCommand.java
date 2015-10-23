package drivetrain;

import org.usfirst.frc.team503.robot.OI;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightTimeCommand extends Command {
	double seconds;
	Timer timer = new Timer();

    public DriveStraightTimeCommand(double seconds) {
    	this.seconds = seconds;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Drivetrain.getInstance().arcadeDrive(0.5, -0.099, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished(){
        return timer.get()>seconds;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drivetrain.getInstance().arcadeDrive(0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
