package drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Turn90Command extends Command {

	int isLeft;
	final double DISTANCE = 19;
	Timer timer = new Timer();
    public Turn90Command(boolean isLeft) {
		this.isLeft = (isLeft) ? -1 : 1;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		//setTimeout(3.5);
    	timer.start();
    	Drivetrain.zero();
    	Drivetrain.setSetpoint(isLeft*DISTANCE);
    	Drivetrain.pidEnable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = Drivetrain.getError();
    	
    	if(Math.abs(error) > 8){
        	Drivetrain.getInstance().arcadeDrive(0, isLeft*0.45, false);
    	}else{
    		Drivetrain.getInstance().arcadeDrive(0, Drivetrain.getPIDLastOutput(), false);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Drivetrain.onTarget() && Drivetrain.isStopped()) || timer.get() > 6;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drivetrain.getInstance().arcadeDrive(0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
