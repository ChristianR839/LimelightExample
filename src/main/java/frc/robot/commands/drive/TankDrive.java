// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TankDrive extends CommandBase {

  private final DriveTrain m_driveTrain;

  private final DoubleSupplier m_left;
  private final DoubleSupplier m_right;

  /** Creates a new TankDrive. */
  public TankDrive(DoubleSupplier left, DoubleSupplier right, DriveTrain drivetrain) {
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
    double left = m_left.getAsDouble() * 0.85; // -0.75
    double right = m_right.getAsDouble() * -0.85; // 0.75

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