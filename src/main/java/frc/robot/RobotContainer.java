// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.NoteSensorSubsystem;

public class RobotContainer {

  NoteSensorSubsystem noteSensorSubsystem = new NoteSensorSubsystem();

  public RobotContainer() {}

  public void resetRobot() {
    noteSensorSubsystem.stopMotor();
    noteSensorSubsystem.startTimer();
  }
}
