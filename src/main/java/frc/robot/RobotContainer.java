// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DriveTrain;
// import frc.robot.subsystems.ExampleSubsystem;
// import frc.robot.subsystems.Intake;
// import frc.robot.subsystems.Lift;
// import frc.robot.subsystems.LimelightSub;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// import frc.robot.commands.Limelight.*;
// import frc.robot.commands.Other.ExampleCommand;
import frc.robot.commands.ShootHigh;
import frc.robot.commands.ShootLow;
import frc.robot.commands.TransportDown;
import frc.robot.commands.TransportUp;
import frc.robot.limelight.Limelight;
// import frc.robot.commands.Auto.Autonomous;
// import frc.robot.commands.Climber.ClimberDown;
// import frc.robot.commands.Climber.ClimberUp;
import frc.robot.commands.TankDrive;
// import frc.robot.commands.Intake.IntakeCommand;
// import frc.robot.commands.Lift.LiftDown;
// import frc.robot.commands.Lift.LiftUp;
// import frc.robot.subsystems.Climber;
// import frc.robot.commands.Auto.Autonomous2;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DriveTrain s_driveTrain             = new DriveTrain();
  // private final Intake s_intake                     = new Intake();
  private final Transport s_transport               = new Transport();
  // private final Lift s_lift                         = new Lift();
  private final Shooter s_shooter                   = new Shooter();
  // private final LimelightSub s_limelight            = new LimelightSub();

  private final Limelight limelight = new Limelight();

  // private final Climber s_climber                   = new Climber();
  // private double x = 0;
  

  // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  // private final Command Autonomous           = new Autonomous(s_driveTrain, s_transport, s_intake, s_shooter, s_lift, s_limelight, x);
  // private final Command Autonomous2          = new Autonomous(s_driveTrain, s_transport, s_intake, s_shooter, s_lift, s_limelight, x);
  // private final Command c_intake             = new IntakeCommand(s_intake, s_lift);
  private final Command c_transportUp        = new TransportUp(s_transport);
  private final Command c_transportDown      = new TransportDown(s_transport);
  // private final Command c_liftDown           = new LiftDown(s_lift);
  // private final Command c_liftUp             = new LiftUp(s_lift);
  private final Command c_shootHigh          = new ShootHigh(s_shooter, s_transport, limelight);
  private final Command c_shootLow           = new ShootLow(s_shooter, s_transport);
  // private final Command c_limelightTurn      = new LimelightCommand(s_driveTrain, s_limelight, x);
  // private final Command c_limelightMove      = new TurnForLimelight(s_driveTrain, x);
  // private final Command c_climberUp          = new ClimberUp(s_climber);
  // private final Command c_climberDown        = new ClimberDown(s_climber);

  // private final Command c_limelightMove = new MoveForLimelight(distanceFromLimelightToGoalInches, s_driveTrain, left, right)


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
    // LBBtnD.whileHeld(c_intake);
    // RedBtnD.whileHeld(c_limelightTurn);
    // BluBtnD.whileHeld(c_limelightMove);

    GrnBtnA.whileHeld(c_transportUp);
    BluBtnA.whileHeld(c_transportDown);
    // LBBtnA.whileHeld(c_intake);
    YelBtnA.whileHeld(c_shootHigh);
    RedBtnA.whileHeld(c_shootLow);
    // StartBtnA.whileHeld(c_liftDown);
    // SelectBtnA.whileHeld(c_liftUp);
    // UpBtnA.whileHeld(c_climberUp);
    // DwnBtnA.whileHeld(c_climberDown);
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
