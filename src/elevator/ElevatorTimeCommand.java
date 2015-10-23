package elevator;

import org.usfirst.frc.team503.robot.OI;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorTimeCommand extends Command {
	double seconds;
	double speed;
	Timer timer = new Timer();

    public ElevatorTimeCommand(double seconds, double speed) {
    	this.speed = -speed;
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
    	ElevatorSubsystem.getInstance().setSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished(){
        return timer.get()>seconds;
    }

    // Called once after isFinished returns true
    protected void end() {
    	ElevatorSubsystem.getInstance().setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
