// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.transport;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Transport;

public class TransportDown extends CommandBase {

  private final Transport s_transport;

  /** Creates a new TransportDown. */
  public TransportDown(Transport s_transport) {
    this.s_transport = s_transport;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_transport);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    s_transport.transportDown();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    s_transport.transportStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
