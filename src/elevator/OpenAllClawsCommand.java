package elevator;

import roller.RollerSubsystem.RollerSolenoidPosition;
import roller.SetRollerSolenoidCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import elevator.ElevatorSubsystem.BinClawPosition;
import elevator.ElevatorSubsystem.ElevatorSolenoidPosition;

public class OpenAllClawsCommand extends CommandGroup{
	public OpenAllClawsCommand(){
		addParallel(new SetBinClawCommand(BinClawPosition.CLOSED));
		addParallel(new SetElevatorClawCommand(ElevatorSolenoidPosition.CLOSE));
		addParallel(new SetRollerSolenoidCommand(RollerSolenoidPosition.OUT));
	}
}
