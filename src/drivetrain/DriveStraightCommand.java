package drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightCommand extends Command {
	double distance;
	private final double STR_K = 0.01;
	Timer timer = new Timer();

    public DriveStraightCommand(double inches) {
    	this.distance = inches;
    	//setTimeout(1 + inches/12);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	//setTimeout(1 + distance/12);
    	Drivetrain.zero();
    	Drivetrain.setSetpoint(distance);
    	Drivetrain.pidEnable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = Drivetrain.getError();
    	
    	if(Math.abs(error) > 20){
    		SmartDashboard.putBoolean("DriveStraight", true);
    		Drivetrain.getInstance().arcadeDrive(error > 0 ? 0.5 : -0.5, STR_K, false); 
    	}else{
    		SmartDashboard.putBoolean("DriveStraight", false);
    		Drivetrain.getInstance().arcadeDrive(Drivetrain.getPIDLastOutput(), STR_K, false);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Drivetrain.onTarget() && Drivetrain.isStopped());
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drivetrain.pidDisable();
    	Drivetrain.getInstance().arcadeDrive(0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
