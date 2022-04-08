// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.limelight.Limelight;
import frc.robot.limelight.LimelightData;
import frc.robot.subsystems.DriveTrain;

public class AimHorizontal extends CommandBase {

  private final TurnInPlaceController controller;
  private final DriveTrain drivetrain;
  private final Limelight limelight;
  private LimelightData data;

  /** Creates a new AimHorizontal. */
  public AimHorizontal(DriveTrain drivetrain, Limelight limelight) {
    this.drivetrain = drivetrain;
    this.limelight = limelight;
    this.controller = new TurnInPlaceController(0.014, 0, 0.2, 0.35, 0.85); // NEEDS TUNING

    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limelight.LEDMode(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    data = limelight.getLimeLightValues();
    double mPower = controller.update(0, data.x, Timer.getFPGATimestamp());
    drivetrain.drive(mPower, -mPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.drive(0, 0);
    limelight.LEDMode(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double current = data.x;
    double high = current + controller.getAllowableError();
    double low = current - controller.getAllowableError();
    return (current >= low && current <= high);
  }
}
