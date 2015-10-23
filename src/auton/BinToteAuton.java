package auton;

import org.usfirst.frc.team503.robot.SetModeCommand;

import drivetrain.DriveStraightCommand;
import drivetrain.Turn90Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import elevator.ElevatorGoToPosition;
import elevator.ElevatorSubsystem.ElevatorSolenoidPosition;
import elevator.SetElevatorClawCommand;

/**
 *
 */
public class BinToteAuton extends CommandGroup {
    
    public  BinToteAuton() {
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
    	addParallel(new ElevatorGoToPosition(2));
    	addSequential(new WaitCommand(1.8));
    	addSequential(new SetElevatorClawCommand(ElevatorSolenoidPosition.CLOSE));
    	addSequential(new DriveStraightCommand(26));
    	addSequential(new SetElevatorClawCommand(ElevatorSolenoidPosition.OPEN));
    	addParallel(new ElevatorGoToPosition(0));
    	addSequential(new WaitCommand(3));
    	addSequential(new SetElevatorClawCommand(ElevatorSolenoidPosition.CLOSE));
    	addParallel(new ElevatorGoToPosition(1));
    	addSequential(new WaitCommand(1));
    	addSequential(new Turn90Command(true));
    	addSequential(new DriveStraightCommand(100));
    	
    }
}
