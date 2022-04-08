// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

public class ShootLow extends CommandBase {

  private final Shooter s_shooter;
  private final Transport s_transport;

  /** Creates a new ShooterCommand. */
  public ShootLow(Shooter s_shooter, Transport s_transport) {
    this.s_shooter = s_shooter;
    this.s_transport = s_transport;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_shooter);
    addRequirements(s_transport);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    s_shooter.setPower(0.37); //0.50
  }

  // 0.50

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    s_shooter.updateRPMOut();
    boolean atSpeed = s_shooter.maxRPM(false);
    if (atSpeed) {
      s_transport.transportDown();
    }
    if (!atSpeed) {
      s_transport.setPower(0.0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    s_shooter.off();
    s_transport.setPower(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
