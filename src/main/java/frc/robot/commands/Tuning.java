// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Tuning extends CommandBase {

  private final DriveTrain m_driveTrain;

  private final DoubleSupplier m_left;
  private final DoubleSupplier m_right;

  private double maxMultiplier = 0.2;

  /** Creates a new TankDrive. */
  public Tuning(DoubleSupplier left, DoubleSupplier right, DriveTrain drivetrain) {
    m_driveTrain = drivetrain;
    m_left = left;
    m_right = right;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double left = m_left.getAsDouble() * maxMultiplier;
    double right = m_right.getAsDouble() * -maxMultiplier;

    SmartDashboard.putNumber("Left Power Assignment", left);
    SmartDashboard.putNumber("Right Power Assignment", right);

    SmartDashboard.putNumber("LPA 75%", left * 0.75);
    SmartDashboard.putNumber("RPA 75%", right * 0.75);

    m_driveTrain.drive(left, right);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}