// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import frc.robot.InterpolatingTreeMap.InterpolatingDouble;
import frc.robot.InterpolatingTreeMap.InterpolatingTreeMap;
import frc.robot.limelight.Limelight;
import frc.robot.limelight.LimelightData;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class ShootHigh extends CommandBase {

  private final Shooter s_shooter;
  private final Transport s_transport;
  private final Limelight limelight;
  private LimelightData data;

  private InterpolatingTreeMap<InterpolatingDouble, InterpolatingDouble> points;

  /** Creates a new ShooterCommand. */
  public ShootHigh(Shooter s_shooter, Transport s_transport, Limelight limelight) {
    this.s_shooter = s_shooter;
    this.s_transport = s_transport;
    this.limelight = limelight;

    points = new InterpolatingTreeMap<>();

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_shooter);
    addRequirements(s_transport);
  }

  private void createTreeMap() {
    points.put(new InterpolatingDouble(0.0), new InterpolatingDouble(0.0));
  }

  // Returns a double representing RPM
  private double getVelocityFromLimelight(double ty) {
    return points.getInterpolated(new InterpolatingDouble(ty)).value;
}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    createTreeMap();
    limelight.LEDMode(true);
    s_shooter.setPower(getVelocityFromLimelight(limelight.getLimeLightValues().y)); // 0.85
  }

  // 0.85
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    s_shooter.updateRPMOut();
    boolean atSpeed = s_shooter.maxRPM(true);
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
    limelight.LEDMode(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
