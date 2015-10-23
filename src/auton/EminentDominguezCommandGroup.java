package auton;

import org.usfirst.frc.team503.robot.SetModeCommand;

import stealer.ConstantStealerUpCommand;
import stealer.StealerGoToPositionTimeCommand;
import stealer.StealerSubsystem.StealerPosition;
import drivetrain.DriveStraightStealCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class EminentDominguezCommandGroup extends CommandGroup {
    
    public  EminentDominguezCommandGroup() {
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
    	//addSequential(new SetModeCommand(2));
    	//addSequential(new DriveStraightStealCommand(-50));
    	//addSequential(new SetModeCommand(2));
    	addSequential(new StealerGoToPositionTimeCommand(StealerPosition.OUT));
    	//addSequential`(new SetModeCommand(0));
    	addSequential(new DriveStraightStealCommand(24));
    	//addParallel(new LassoInCommand());
    	//should bring the stealer up
    	//addSequential(new ConstantStealerUpCommand());
    	//addSequential(new Turn90Command(true));
    	//addSequential(new WaitCommand(1));
    	//addSequential(new SetModeCommand(1));
    	addSequential(new WaitCommand(5));
    	//addSequential(new WaitCommand(3000));
    }
}
