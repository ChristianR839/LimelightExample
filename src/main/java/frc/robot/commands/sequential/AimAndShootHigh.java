// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.sequential;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.AimHorizontal;
import frc.robot.commands.shooter.ShootHigh;
import frc.robot.limelight.Limelight;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AimAndShootHigh extends SequentialCommandGroup {

  private final DriveTrain driveTrain;
  private final Limelight limelight;
  private final Shooter shooter;
  private final Transport transport;

  /** Creates a new AimAndShoot. */
  public AimAndShootHigh(DriveTrain mDriveTrain, Limelight mLimelight, Shooter mShooter, Transport mTransport) {
    this.driveTrain = mDriveTrain;
    this.limelight = mLimelight;
    this.shooter = mShooter;
    this.transport = mTransport;
    
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new AimHorizontal(driveTrain, limelight),
      new ShootHigh(shooter, transport, limelight)
    );
  }
}
