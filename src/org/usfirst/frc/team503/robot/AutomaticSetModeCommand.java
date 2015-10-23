package org.usfirst.frc.team503.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutomaticSetModeCommand extends Command {

    public AutomaticSetModeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.getElevatorInput() > 0.5 && OI.mode != 1){
    		(new SetModeCommand(1)).start();
    		OI.elevatorMove = false;
    	}else if(OI.getStealerInput()*OI.stealerScale > 0.5 && OI.mode != 2){
    		(new SetModeCommand(2)).start();
    		OI.stealerMove = false;
    	}
    	if(OI.getElevatorInput() < 0.05 && !OI.elevatorMove){
    		OI.elevatorMove = true;
    	}
    	if(OI.getStealerInput() < 0.05 && !OI.stealerMove){
    		OI.stealerMove = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !DriverStation.getInstance().isOperatorControl();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
