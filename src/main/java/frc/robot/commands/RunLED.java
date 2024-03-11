// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

public class RunLED extends Command {
  /** Creates a new RunLEDs. */

  private AddressableLED led;
  private AddressableLEDBuffer ledBuffer;
  private int effectIndex;
  private int effectLength;
  public RunLED(int port, int length) 
  {

    led = new AddressableLED(port);
    ledBuffer = new AddressableLEDBuffer(length);
    led.setLength(length);

    led.setData(ledBuffer);


    effectIndex = 0;
    effectLength = Constants.LED_Constants.kEffectLength;

    addRequirements();;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    for(int i = 0; i<ledBuffer.getLength(); i++){
      if( (i>=effectIndex && i<=(effectIndex+effectLength-1))  || i<=(effectIndex+effectLength-1)%ledBuffer.getLength()){
       ledBuffer.setHSV(i,115, 30, 100 );
      }
     ledBuffer.setHSV(i, 115, 100, 75);
    }

    effectIndex = (effectIndex+1)%ledBuffer.getLength();
  }

  //for when we do different colors, make methods for each color and put into if-statement chain in execute()
  public void green(){

  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
