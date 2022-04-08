// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Add your docs here. */
public class Limelight {
    
    NetworkTable table;

    NetworkTableEntry m_pipeline;

    public Limelight() {
        table = NetworkTableInstance.getDefault().getTable("rosie-limelight");
        m_pipeline = table.getEntry("pipeline");

        NetworkTableEntry ledMode = table.getEntry("ledMode");
        ledMode.setNumber(false?0:1);
    }

    public void LEDMode(boolean lightOn) {
        NetworkTableEntry ledMode = table.getEntry("ledMode");
        ledMode.setNumber(lightOn?0:1);
    }
    
    public LimelightData getLimeLightValues() {
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry tv = table.getEntry("tv");
        
        LimelightData data = new LimelightData(tx.getDouble(0), ty.getDouble(0), tv.getDouble(0) == 1);

        SmartDashboard.putNumber("LimelightX", data.x);
        SmartDashboard.putNumber("LimelightY", data.y);
        SmartDashboard.putBoolean("LimelightTarget", data.target);

        return data;
    }
}