// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import swervelib.SwerveDrive;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }

  public static Command backupAuto(SwerveSubsystem drive) {
    return Commands.sequence(Commands.runOnce(() -> drive.zeroGyroWithAlliance()), drive.driveForward().withTimeout(2));
  }

  public static Command sweepAuto(SwerveSubsystem drive) {
    return Commands.sequence(
      Commands.runOnce(() -> drive.zeroGyroWithAlliance()),
      Commands.waitSeconds(1.0),
      drive.driveBackward().withTimeout(2.85),
      Commands.runOnce(() -> drive.setChassisSpeeds(new ChassisSpeeds())),
      Commands.waitSeconds(11.0),
      drive.driveRight().withTimeout(5),
      Commands.runOnce(() -> drive.setChassisSpeeds(new ChassisSpeeds())),
      drive.driveDiagonalFowardRight().withTimeout(4),
      Commands.runOnce(() -> drive.setChassisSpeeds(new ChassisSpeeds())));
  }
}
