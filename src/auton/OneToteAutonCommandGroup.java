package auton;

import org.usfirst.frc.team503.robot.SetModeCommand;

import drivetrain.DriveStraightCommand;
import drivetrain.Turn90Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import elevator.ElevatorSubsystem.ElevatorSolenoidPosition;
import elevator.SetElevatorClawCommand;

/**
 *
 */
public class OneToteAutonCommandGroup extends CommandGroup {
    
    public  OneToteAutonCommandGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new SetModeCommand(1));
    	addParallel(new SetElevatorClawCommand(ElevatorSolenoidPosition.CLOSE));
    	addSequential(new Turn90Command(false));
    	addSequential(new DriveStraightCommand(108));
    	addSequential(new Turn90Command(true));
    }
}
