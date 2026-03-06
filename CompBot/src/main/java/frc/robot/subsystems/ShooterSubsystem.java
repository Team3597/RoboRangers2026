// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */

  private static SparkMax shooterMotorRight = new SparkMax(9, MotorType.kBrushless);
  private static SparkMax shooterMotorLeft = new SparkMax(10, MotorType.kBrushless);

  private static SparkMaxConfig shooterConfig = new SparkMaxConfig();
  private static SparkMaxConfig slaveConfig = new SparkMaxConfig();

  public ShooterSubsystem() {
    shooterConfig.idleMode(IdleMode.kBrake);
    shooterConfig.inverted(false);
    shooterConfig.smartCurrentLimit(40);
    shooterMotorRight.configure(shooterConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // Following right motor but inverted
    slaveConfig.idleMode(IdleMode.kBrake);
    slaveConfig.inverted(true);
    slaveConfig.smartCurrentLimit(40);
    slaveConfig.follow(shooterMotorRight);
    shooterMotorLeft.configure(shooterConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public void setShooterSpeed(double speed) {
    shooterMotorRight.set(speed);
  }

  public void stopShooter() {
    shooterMotorRight.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}