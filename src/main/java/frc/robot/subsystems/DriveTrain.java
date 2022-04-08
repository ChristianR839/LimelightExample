// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  private Constants c = new Constants();

  private WPI_TalonFX talonLeft1 = new WPI_TalonFX(c.TALON_LEFT_1_ID);
  private WPI_TalonFX talonLeft2 = new WPI_TalonFX(c.TALON_LEFT_2_ID);
  private WPI_TalonFX talonRight1 = new WPI_TalonFX(c.TALON_RGHT_1_ID);
  private WPI_TalonFX talonRight2 = new WPI_TalonFX(c.TALON_RGHT_2_ID);

  private final MotorControllerGroup leftController = new MotorControllerGroup(talonLeft1, talonLeft2);
  private final MotorControllerGroup rightController = new MotorControllerGroup(talonRight1, talonRight2);
  
  private final DifferentialDrive fourWheels = new DifferentialDrive(leftController, rightController);

  private double ramprate = 1.0;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    talonLeft1.configClosedloopRamp(ramprate);
    talonLeft2.configClosedloopRamp(ramprate);
    talonRight1.configClosedloopRamp(ramprate);
    talonRight2.configClosedloopRamp(ramprate);
  }

public void drive(final double left, final double right) {
  fourWheels.tankDrive(left, right);
}

public void DriveforTime(final double left, final double right, final double seconds) {
  double endTime = Timer.getFPGATimestamp() + seconds;
  while (Timer.getFPGATimestamp() <= endTime) {
    fourWheels.tankDrive(left, right);
  }
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}