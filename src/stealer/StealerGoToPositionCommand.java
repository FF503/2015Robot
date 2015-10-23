package stealer;

import org.usfirst.frc.team503.robot.OI;

import stealer.StealerSubsystem.StealerPosition;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class StealerGoToPositionCommand extends Command {
	StealerPosition position;
	Timer timer = new Timer();
    public StealerGoToPositionCommand(StealerPosition position) {
    	this.position = position;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//setTimeout(6);
    	timer.start();
		StealerSubsystem.setSetpoint(position.position);
		StealerSubsystem.pidEnable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = StealerSubsystem.getError();
    	SmartDashboard.putNumber("Grabber dte:", error);
    		if(Math.abs(error) >  50){ //rate*3 is usually a lot higher than 5
    			StealerSubsystem.getInstance().setSpeed((error > 0 ? -0.4 : 0));
    		}else{
    			StealerSubsystem.getInstance().setSpeed(0);
    		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > 5 || OI.endStealer;
    }

    // Called once after isFinished returns true
    protected void end() {
    	StealerSubsystem.getInstance().grabberStop();
    	StealerSubsystem.pidDisable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
