package org.usfirst.frc.team503.robot;

import roller.RollerSubsystem.Direction;
import elevator.ElevatorSubsystem.ElevatorPosition;
import elevator.ElevatorSubsystem.ElevatorSolenoidPosition;

/**
 *
 */
public class OneButtonPreset extends CommandGroup {
    
    public  OneButtonPreset() {
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
    	addSequential(SetElevatorClawCommand(ElevatorSolenoidPosition.OPEN));
    	addSequential(ElevatorGoToPosition(ElevatorPosition.BOTTOM));
    	addSequential(SetRollerCommand(Direction.IN));
    	addSequential(WaitCommand(2));
    	addSequential(SetRollerCommand(Direction.OFF));
        addSequential(SetElevatorClawCommand(ElevatorSolenoidPosition.CLOSE));
    	addSequential(ElevatorGoToPosition(ElevatorPosition.SECOND));    	
    }
}