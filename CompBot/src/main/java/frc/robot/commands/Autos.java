// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }

  public static Command backupAuto(SwerveSubsystem drive) {
    return Commands.sequence(Commands.runOnce(() -> drive.zeroGyroWithAlliance()), drive.driveForward().withTimeout(2));
  }

  // Take a look at the drive methods, they may or may not need to be inverted
  // Currently the auto works on only the blue allaince (????)
  public static Command sweepAuto(SwerveSubsystem drive) {
    return Commands.sequence(
      Commands.runOnce(() -> drive.zeroGyroWithAlliance()),
      Commands.waitSeconds(1.0),
      drive.driveBackward().withTimeout(2.85),         // Inverted command
      Commands.runOnce(() -> drive.setChassisSpeeds(new ChassisSpeeds())),
      Commands.waitSeconds(11.0),
      drive.driveRight().withTimeout(5),               // Inverted command
      Commands.runOnce(() -> drive.setChassisSpeeds(new ChassisSpeeds())),
      drive.driveDiagonalFowardRight().withTimeout(4), // Inverted command
      Commands.runOnce(() -> drive.setChassisSpeeds(new ChassisSpeeds())));
  }
}
