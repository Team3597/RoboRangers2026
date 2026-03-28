// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Index extends Command {

  private double hopperSpeed;
  private double indexerSpeed;

  private IndexerSubsystem indexerSubsystem;

  /** Creates a new Indexer. */
  public Index(double indexerSpeed, double hopperSpeed, IndexerSubsystem indexerSubsystem) {
    this.indexerSpeed = indexerSpeed;
    this.hopperSpeed = hopperSpeed;
    this.indexerSubsystem = indexerSubsystem;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(indexerSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (this.indexerSpeed > 0) {
      indexerSubsystem.setIndexerSpeed(this.indexerSpeed, this.hopperSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    indexerSubsystem.stopIndexer();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}