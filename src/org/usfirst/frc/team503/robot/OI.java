package org.usfirst.frc.team503.robot;

import roller.RollerSubsystem.Direction;
import roller.RollerSubsystem.RollerSolenoidPosition;
import roller.SetRollerCommand;
import roller.SetRollerSolenoidCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import elevator.ElevatorDownBinClawOpenCommandGroup;
import elevator.ElevatorDownCommand;
import elevator.ElevatorDownCommandGroup;
import elevator.ElevatorSubsystem.BinClawPosition;
import elevator.ElevatorSubsystem.ElevatorSolenoidPosition;
import elevator.ElevatorUpCommand;
import elevator.OpenAllClawsCommand;
import elevator.SetBinClawCommand;
import elevator.SetElevatorClawCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	public static int mode = 2;
	
	public static boolean elevatorMove = true;
	
	public static boolean stealerMove = true;
	
	public static boolean endStealer = false;
	
	public static int position = 0;
	
	public static int positionCommandsRan = 0;
	
	public static int lassoPosition = 0;
	
	public static boolean squaredInputs = false;
	
	public static double stealerScale = .7;
	
	private final static double DRIVE_SENSITIVITY = .5;
	private final static double TURN_SENSITIVITY = .1;
	
	static Joystick joystick = new Joystick(0);
	static Joystick leftJoystick = new Joystick(1);
	static Joystick rightJoystick = new Joystick(2);
	
	static JoystickButton elevatorButtonOpen = new JoystickButton(joystick, 9),
			elevatorButtonClose = new JoystickButton(joystick, 10),
			binClawButtonClose = new JoystickButton(joystick, 2),
			binClawButtonOpen = new JoystickButton(joystick, 1),
			elevatorButtonMoveUp = new JoystickButton(joystick, 8),
			elevatorButtonMoveDown = new JoystickButton(joystick, 11),
			mode0Button = new JoystickButton(joystick, 7),
			mode1Button = new JoystickButton(joystick, 5),
			mode2Button = new JoystickButton(joystick, 6),
			Y = new JoystickButton(joystick, 4),
			X = new JoystickButton(joystick, 3),
			rollerOut = new JoystickButton(leftJoystick, 3),
			rollerIn = new JoystickButton(leftJoystick, 4),
			//rollerSolenoidIn = new JoystickButton(leftJoystick, 1),
			//rollerSolenoidOut = new JoystickButton(leftJoystick, 2),
			turboButton = new JoystickButton(leftJoystick, 6),
			straightButton = new JoystickButton(leftJoystick, 5),
			scoreButton = new JoystickButton(leftJoystick, 8);
	static TriggerButton rightTrigger = new TriggerButton(joystick, 3),
			leftTrigger = new TriggerButton(joystick, 2),
			rightDriveTrigger = new TriggerButton(leftJoystick, 3),
			leftDriveTrigger = new TriggerButton(leftJoystick, 2);
	
	public static double getJoystickY(){
		if(turboButton.get()){
			return scale(-leftJoystick.getRawAxis(1), DRIVE_SENSITIVITY);
		}else{
			return scale(-leftJoystick.getRawAxis(1), DRIVE_SENSITIVITY)/2;
		}
		//return scale(-leftJoystick.getY(), DRIVE_SENSITIVITY); 
	}
	
	public static double getJoystickX(){
		if(turboButton.get()){
			return scale(leftJoystick.getRawAxis(0), DRIVE_SENSITIVITY);
		}else{
			return scale(leftJoystick.getRawAxis(0), DRIVE_SENSITIVITY)/2.5;
		}
		
		//return scale(leftJoystick.getX(), DRIVE_SENSITIVITY);
	}
	
	public static double getLeftInput(){
		return scale(-leftJoystick.getY(), DRIVE_SENSITIVITY);
	}
	
	public static double getRightInput(){
		return scale(-rightJoystick.getY(), DRIVE_SENSITIVITY);
	}
	
	public static double getElevatorInput(){
		return joystick.getRawAxis(1);	
	}
	
	public static double getStealerInput(){
		return joystick.getRawAxis(4)*stealerScale;
	}
	
	public static boolean getRollerButtonIn(){
		return rollerIn.get();
	}
	
	public static boolean getStraightButton(){
		return straightButton.get();
	}
	
	
	public static boolean getRollerButtonOut(){
		return rollerOut.get();
	}
	
	public static double getLassoInput(){
//		return joystick.getRawAxis(4);
		return 0;
	}
	
	public static double scale(double input, double sensitivity){
        return sensitivity * (input*input*input) + (1-sensitivity) * input;
    }
	
	public static void init(){
		elevatorButtonOpen.whenPressed(new SetElevatorClawCommand(ElevatorSolenoidPosition.OPEN));
		elevatorButtonClose.whenPressed(new SetElevatorClawCommand(ElevatorSolenoidPosition.CLOSE));
		binClawButtonOpen.whenPressed(new SetBinClawCommand(BinClawPosition.CLOSED));
		binClawButtonClose.whenPressed(new SetBinClawCommand(BinClawPosition.OPEN));
		rightTrigger.whenPressed(new SetElevatorClawCommand(ElevatorSolenoidPosition.OPEN));
		leftTrigger.whenPressed(new SetElevatorClawCommand(ElevatorSolenoidPosition.CLOSE));
		rightDriveTrigger.whenPressed(new SetRollerSolenoidCommand(RollerSolenoidPosition.IN));
		leftDriveTrigger.whenPressed(new SetRollerSolenoidCommand(RollerSolenoidPosition.OUT));
		elevatorButtonMoveUp.whenPressed(new ElevatorUpCommand());
		elevatorButtonMoveDown.whenPressed(new ElevatorDownCommand());
		mode0Button.whenPressed(new SetModeCommand(0));
		mode1Button.whenPressed(new SetModeCommand(1));
		mode2Button.whenPressed(new SetModeCommand(2));
		rollerIn.whenPressed(new SetRollerCommand(Direction.IN));
		rollerOut.whenPressed(new SetRollerCommand(Direction.OUT));
		scoreButton.whenPressed(new OpenAllClawsCommand());
		Y.whenPressed(new ElevatorDownCommandGroup());
		X.whenPressed(new ElevatorDownBinClawOpenCommandGroup());
		//rollerButtonIn.whenPressed(new SetRollerCommand(Direction.IN));
		//rollerButtonOut.whenPressed(new SetRollerCommand(Direction.OUT));
	}
}

