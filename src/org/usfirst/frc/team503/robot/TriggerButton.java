package org.usfirst.frc.team503.robot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.InternalButton;

/**
 *
 * @author skodali
 * makes buttons out of axes
 */
public class TriggerButton extends InternalButton{
    
    
    private final Joystick joystick;
    private final int axisNumber;
    
    public TriggerButton(Joystick joystick, int axisNumber){
        this.joystick = joystick;
        this.axisNumber = axisNumber;
    }
     
    //if axis is pushed over .5, button is pressed
    public boolean get(){
        return joystick.getRawAxis(axisNumber) > 0.5;
        
    }
    
}

