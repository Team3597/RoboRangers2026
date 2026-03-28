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

public class IndexerSubsystem extends SubsystemBase {

  private static SparkMax feedMotor =  new SparkMax(33, MotorType.kBrushless);
  private static SparkMax stablizerMotor = new SparkMax(34, MotorType.kBrushless);
  private static SparkMax hopperMotor =  new SparkMax(35, MotorType.kBrushless);

  private static SparkMaxConfig feedConfig = new SparkMaxConfig();
  private static SparkMaxConfig stablizerConfig = new SparkMaxConfig();
  private static SparkMaxConfig hopperConfig = new SparkMaxConfig();

  /** Creates a new IndexerSubsystem. */
  public IndexerSubsystem() {
    feedConfig.idleMode(IdleMode.kCoast);
    feedConfig.inverted(false);
    feedConfig.smartCurrentLimit(40);
    feedMotor.configure(feedConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    stablizerConfig.idleMode(IdleMode.kCoast);
    stablizerConfig.inverted(true);
    stablizerConfig.smartCurrentLimit(40);
    stablizerMotor.configure(stablizerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    hopperConfig.idleMode(IdleMode.kCoast);
    hopperConfig.inverted(false);
    hopperConfig.smartCurrentLimit(40);
    hopperMotor.configure(hopperConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

   public void setIndexerSpeed(double indexerSpeed, double hopperSpeed) {
    feedMotor.set(indexerSpeed);
    stablizerMotor.set(indexerSpeed); // Inverted in config
    hopperMotor.set(hopperSpeed);
  }


  public void stopIndexer() {
    feedMotor.stopMotor();
    stablizerMotor.stopMotor();
    hopperMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
