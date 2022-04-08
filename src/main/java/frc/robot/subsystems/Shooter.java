// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  private CANSparkMax SparkShoot = new CANSparkMax(3, MotorType.kBrushless);

  private RelativeEncoder shootEncoder = SparkShoot.getEncoder();

  /** Creates a new Shooter. */
  public Shooter() {}

  public void on() {
    SparkShoot.set(0.50); //low: 50, high: 85
  }

  public void off() {
    SparkShoot.set(0);
  }

  public void setPower(double power) {
    SparkShoot.set(power);
  }

  public void updateRPMOut() {
    SmartDashboard.putNumber("Shooter RPM", shootEncoder.getVelocity());
  }

  // True = High / False = Low
  public boolean maxRPM(boolean goal) {
    double RPM = shootEncoder.getVelocity();
    // TODO: For shooting high, a single target velocity is not possible. Either (1) remove this or (2) create a scale that adjusts with the velocity.
    if (goal && RPM >= 4650.0) {
      return true;
    }

    if (!goal && RPM >= 1700.0) { //2650
      return true;
    }
    
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per[] scheduler run
  }
}
