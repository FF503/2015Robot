package auton;

import org.usfirst.frc.team503.robot.SetModeCommand;

import roller.RollerSubsystem.Direction;
import roller.RollerSubsystem.RollerSolenoidPosition;
import roller.SetRollerAutonCommand;
import roller.SetRollerLeftCommand;
import roller.SetRollerSolenoidCommand;
import drivetrain.DriveStraightTimeCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import elevator.ElevatorGoToPosition;
import elevator.ElevatorSubsystem.ElevatorSolenoidPosition;
import elevator.SetElevatorClawCommand;

/**
 *
 */
public class ZackMooreTotesAuton extends CommandGroup {
    
    public  ZackMooreTotesAuton() {
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
    	addSequential(new SetElevatorClawCommand(ElevatorSolenoidPosition.CLOSE));
    	addSequential(new SetRollerSolenoidCommand(RollerSolenoidPosition.IN));
    	addParallel(new ElevatorGoToPosition(1));
    	addSequential(new WaitCommand(2));
    	addSequential(new SetRollerLeftCommand());
    	addSequential(new DriveStraightTimeCommand(1));
    	addSequential(new SetRollerSolenoidCommand(RollerSolenoidPosition.OUT));
    	addSequential(new SetRollerAutonCommand(Direction.IN));
    	addSequential(new DriveStraightTimeCommand(1));
    }
}
