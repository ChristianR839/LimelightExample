// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.drive.TankDrive;
import frc.robot.commands.sequential.AimAndShootHigh;
import frc.robot.commands.shooter.ShootLow;
import frc.robot.commands.transport.TransportDown;
import frc.robot.commands.transport.TransportUp;
import frc.robot.limelight.Limelight;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain s_driveTrain             = new DriveTrain();
  private final Transport s_transport               = new Transport();
  private final Shooter s_shooter                   = new Shooter();

  private final Limelight limelight = new Limelight();

  private final Command c_transportUp        = new TransportUp(s_transport);
  private final Command c_transportDown      = new TransportDown(s_transport);
  private final Command c_shootLow           = new ShootLow(s_shooter, s_transport);

  private final SequentialCommandGroup c_aimAndShootHigh = new AimAndShootHigh(s_driveTrain, limelight, s_shooter, s_transport);

  public XboxController driveController = new XboxController(0);
  public XboxController accController   = new XboxController(1);

  public JoystickButton YelBtnD    = new JoystickButton(driveController, 4);
  public JoystickButton RedBtnD    = new JoystickButton(driveController, 2);
  public JoystickButton BluBtnD    = new JoystickButton(driveController, 3);
  public JoystickButton GrnBtnD    = new JoystickButton(driveController, 1);
  public JoystickButton LBBtnD     = new JoystickButton(driveController, 5);
  public JoystickButton RBBtnD     = new JoystickButton(driveController, 6);

  public JoystickButton YelBtnA    = new JoystickButton(accController, 4);
  public JoystickButton RedBtnA    = new JoystickButton(accController, 2);
  public JoystickButton BluBtnA    = new JoystickButton(accController, 3);
  public JoystickButton GrnBtnA    = new JoystickButton(accController, 1);
  public JoystickButton LBBtnA     = new JoystickButton(accController, 5);
  public JoystickButton RBBtnA     = new JoystickButton(accController, 6);
  public JoystickButton StartBtnA  = new JoystickButton(accController, 8);
  public JoystickButton SelectBtnA = new JoystickButton(accController, 7);
  public JoystickButton UpBtnA     = new JoystickButton(accController, 15);
  public JoystickButton DwnBtnA    = new JoystickButton(accController, 19);
  //D = Driver Controller / A = Autonomous Controller


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    s_driveTrain.setDefaultCommand(new TankDrive( () -> -driveController.getLeftY(), () -> -driveController.getRightY(), s_driveTrain));
    limelight.LEDMode(false);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    GrnBtnA.whileHeld(c_transportUp);
    BluBtnA.whileHeld(c_transportDown);
    YelBtnA.whileHeld(c_aimAndShootHigh);
    RedBtnA.whileHeld(c_shootLow);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
