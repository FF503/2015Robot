
package org.usfirst.frc.team503.robot;

//import com.ni.vision.NIVision;
//import com.ni.vision.NIVision.DrawMode;
//import com.ni.vision.NIVision.Image;
//import com.ni.vision.NIVision.ShapeMode;

import roller.RollerSubsystem;
import stealer.StealerSubsystem;
import stealer.StealerTeleopCommand;
import auton.DoNothingAuton;
import auton.EminentDominguezCommandGroup;
import auton.KennyDAuton;
import auton.Mode;
import auton.OneContainerAuton;
import auton.OneToteAutonCommandGroup;
import auton.StealerOutCommandGroup;
import drivetrain.CustomRobotDrive;
import drivetrain.DriveStraightCommand;
import drivetrain.Drivetrain;
import drivetrain.TeleopDriveCommand;
import drivetrain.Turn90Command;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import elevator.DeterminePositionCommand;
import elevator.ElevatorGoToPosition;
import elevator.ElevatorTeleopCommand;
import elevator.ElevatorSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
//	int session;
//    Image frame;
    public void robotInit() {
    	
//    frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
//
//    // the camera name (ex "cam0") can be found through the roborio web interface
//    session = NIVision.IMAQdxOpenCamera("cam0",
//            NIVision.IMAQdxCameraControlMode.CameraControlModeController);
//    NIVision.IMAQdxConfigureGrab(session);
    	OI.init();
    	Mode.AUTON.addChoice(new OneToteAutonCommandGroup(), false);
    	Mode.AUTON.addChoice(new ElevatorGoToPosition(2), false);
    	Mode.AUTON.addChoice(new Turn90Command(false), false, "Turn Right");
    	Mode.AUTON.addChoice(new DoNothingAuton(), false, "Nothing");
    	Mode.AUTON.addChoice(new DriveStraightCommand(72), false, "Drive 9 feet forward");
    	Mode.AUTON.addChoice(new EminentDominguezCommandGroup(), false, "Fast Stealer auton");
    	Mode.AUTON.addChoice(new KennyDAuton(), true, "Slow Stealer auton");
    	Mode.AUTON.addChoice(new OneContainerAuton(), false);
    	Mode.AUTON.addChoice(new StealerOutCommandGroup(), false, "Stealer out only");
    	Mode.TELEOP.addCommand((new SetModeCommand(1)));
    	Mode.TELEOP.addCommand((new DeterminePositionCommand()));
    	Mode.TELEOP.addCommand((new TeleopDriveCommand()));
    	Mode.TELEOP.addCommand((new ElevatorTeleopCommand()));
    	Mode.TELEOP.addCommand(new StealerTeleopCommand());
    	Mode.TELEOP.addCommand(new AutomaticSetModeCommand());
    	SmartDashboard.putData("Drivetrain", Drivetrain.getInstance());
    	SmartDashboard.putData("Elevator", ElevatorSubsystem.getInstance());
    	SmartDashboard.putData("Roller", RollerSubsystem.getInstance());
    	SmartDashboard.putData("Stealer", StealerSubsystem.getInstance());
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	//public static string SuhasLove(int degree, int time, string type){
	//		string result;
	//		if (degree = cum) {
	//				result = "mmmm is enjoy";
	//			}
	//		else{
	//				result = "eh you need to get better";
	//			}
	//		return result;
	
	//		}
    public void autonomousInit() {
    	//(new DeterminePositionCommand()).start();
    	//(new OneToteAutonCommandGroup()).start();
    	//(new StealerGoToPositionCommand(StealerPosition.OUT)).start();
    	//(new SetElevatorClawCommand(ElevatorSolenoidPosition.CLOSE)).start();
    	//(new ZackMooreTotesAuton()).start();
    	//(new Turn90Command(false)).start();
    	//(new DriveStraightCommand(108)).start();
    	//(new TestCommandGroup()).start();
    	Mode.changeMode(Mode.AUTON);
    	//(new OneContainerAuton()).start();
    	//(new StealerGoToPositionTimeCommand(StealerPosition.OUT)).start();
    	//(new EminentDominguezCommandGroup()).start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
//    	SmartDashboard.putNumber("ELEVATORSOSOFD Error", ElevatorSubsystem.getError());
//    	SmartDashboard.putNumber("Drivetrain Encoder", Drivetrain.getDistance());
//    	SmartDashboard.putNumber("Elevator Enc", ElevatorSubsystem.getDistance());    
//    	SmartDashboard.putNumber("ELEVATORSOFSOFF Output", ElevatorSubsystem.getPIDLastOutput());
//    	SmartDashboard.putNumber("Stealer Enc", StealerSubsystem.getDistance());
    }

    public void teleopInit() {
//    	NIVision.IMAQdxStartAcquisition(session);
//
//        /**
//         * grab an image, draw the circle, and provide it for the camera server
//         * which will in turn send it to the dashboard.
//         */
//        NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
//
//        while (isOperatorControl() && isEnabled()) {
//
//            NIVision.IMAQdxGrab(session, frame, 1);
//            NIVision.imaqDrawShapeOnImage(frame, frame, rect,
//                    DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
//            
//            CameraServer.getInstance().setImage(frame);
//
//            /** robot code here! **/
//            Timer.delay(0.005);		// wait for a motor update time
//        }
//        NIVision.IMAQdxStopAcquisition(session);
    	
    	Mode.changeMode(Mode.TELEOP);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	SmartDashboard.putNumber("Drivetrain Encoder", Drivetrain.getDistance());
    	SmartDashboard.putNumber("Elevator Enc", ElevatorSubsystem.getDistance());
    	SmartDashboard.putNumber("Stealer Enc", StealerSubsystem.getDistance());
    	SmartDashboard.putNumber("Encoder Rate", Drivetrain.getRate());
    	SmartDashboard.putNumber("Y", OI.getJoystickY());
    	SmartDashboard.putNumber("X", OI.getJoystickX());
    	//SmartDashboard.putBoolean("DIGITAL VALUE", testSensor.get());
    	//SmartDashboard.putNumber("ANALOG VALUE", analogSensor.getVoltage());
        Scheduler.getInstance().run();
        CustomRobotDrive.getInstance().setGrabberSpeed(OI.getStealerInput());
        //StealerSubsystem.getInstance().setLassoSpeed(OI.getLassoInput());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
