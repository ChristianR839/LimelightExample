// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.limelight;

/** Add your docs here. */
public class LimelightData {

    public double x = 0;
    public double y = 0;
    public boolean target = false;

    public LimelightData(double m_x, double m_y, boolean m_target) {
        x = m_x;
        y = m_y;
        target = m_target;
    }

    public double getNegMotorPower() {
        double Xneg = (-Math.abs(x));
        if (Xneg < -1) {
            Xneg = -1;
        }
        return Xneg;
    }

    public double getPosMotorPower() {
        double Xpos = (Math.abs(x));
        if (Xpos > 1) {
            Xpos = 1;
        }
        return Xpos;
    }
}
