package org.usfirst.frc.team503.robot;

import drivetrain.Drivetrain;
import drivetrain.Drivetrain.Direction;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetModeCommand extends Command {
	
	int mode;
	
    public SetModeCommand(int mode) {
        this.mode = mode;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	OI.mode=mode;
    	if(mode == 0){
    		Drivetrain.getInstance().setElevatorSolenoid(Direction.Drivetrain);
    		Drivetrain.getInstance().setGrabberSolenoid(Direction.Drivetrain);
    	}else if(mode == 1){
    		Drivetrain.getInstance().setElevatorSolenoid(Direction.Gamespec);
    		Drivetrain.getInstance().setGrabberSolenoid(Direction.Drivetrain);
    	}else if(mode == 2){
    		Drivetrain.getInstance().setElevatorSolenoid(Direction.Drivetrain);
    		Drivetrain.getInstance().setGrabberSolenoid(Direction.Gamespec);
    	}else if(mode == 3){
    		Drivetrain.getInstance().setElevatorSolenoid(Direction.Gamespec);
    		Drivetrain.getInstance().setGrabberSolenoid(Direction.Gamespec);
    	}else {
    		Drivetrain.getInstance().setElevatorSolenoid(Direction.Drivetrain);
    		Drivetrain.getInstance().setGrabberSolenoid(Direction.Drivetrain);
    	}
    
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
