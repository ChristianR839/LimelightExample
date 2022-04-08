// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Transport extends SubsystemBase {

  private CANSparkMax sparkTransport = new CANSparkMax(4, MotorType.kBrushed);

  /** Creates a new Transport. */
  public Transport() {}

  public void setPower(double power) {
    sparkTransport.set(power);
  }

  //To bring the balls to the shooter.
  public void transportUp() {
    sparkTransport.set(0.75);
  }

  //To eject balls from the transport.
  public void transportDown() {
    sparkTransport.set(-0.75);
  }

  //When no buttons are being pressed.
  public void transportStop () {
    sparkTransport.set(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
