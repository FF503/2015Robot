package auton;

import org.usfirst.frc.team503.robot.SetModeCommand;

import drivetrain.DriveStraightCommand;
import drivetrain.Turn90Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import elevator.ElevatorGoToPosition;
import elevator.SetElevatorClawCommand;
import elevator.ElevatorSubsystem.ElevatorSolenoidPosition;

public class OneContainerAuton extends CommandGroup{
	public OneContainerAuton(){
		addSequential(new SetModeCommand(1));
    	addParallel(new ElevatorGoToPosition(2));
    	addSequential(new WaitCommand(3));
		addSequential(new SetElevatorClawCommand(ElevatorSolenoidPosition.CLOSE));
		addSequential(new Turn90Command(true));
		addSequential(new DriveStraightCommand(108));
		addSequential(new Turn90Command(false));
	}
}
