package elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import elevator.ElevatorSubsystem.BinClawPosition;

/**
 *
 */
public class SetBinClawCommand extends Command {
	BinClawPosition position;
	//String SBCC = "";
    public SetBinClawCommand(BinClawPosition position) {
    	this.position = position;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	//SmartDashboard.putString("SBCC", SmartDashboard.getString("SBCC")+position.name() + "SetBinClaw;");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//SBCC+=position.name() + "initialize;";
    	//SmartDashboard.putString("SBCC", SBCC);
    	//SmartDashboard.putString("Position", position.name());
    	ElevatorSubsystem.getInstance().setBinClawPosition(position);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    //	SBCC +=position.name() + "isFinished;";
    //	SmartDashboard.putString("SBCC", SBCC);
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//SBCC+=position.name() + "end;";
    	//SmartDashboard.putString("SBCC", SBCC);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//SmartDashboard.putString("SBCC", SmartDashboard.getString("SBCC")+position.name() + "interrupted;");
    }
}
