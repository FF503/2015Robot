package drivetrain;

import org.usfirst.frc.team503.robot.OI;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightStealCommand extends Command {
	double distance;
	private final double STR_K = 0.01;
	Timer timer = new Timer();

    public DriveStraightStealCommand(double inches) {
    	this.distance = inches;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	//OI.mode = 1;
    	Drivetrain.zero();
    	Drivetrain.setSetpoint(distance);
    	Drivetrain.pidEnable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = Drivetrain.getError();
    	if(timer.get() > 0.3){
    		//OI.mode = 0;
    	}
    	if(Math.abs(error) > 10){
    		SmartDashboard.putBoolean("DriveStraight", true);
    		Drivetrain.getInstance().arcadeDrive(error > 0 ? 1 : -1, STR_K, false); 
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
