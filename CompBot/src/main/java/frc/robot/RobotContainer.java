// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.Index;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import swervelib.SwerveInputStream;

import java.io.File;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  private static final ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();
  private static final IndexerSubsystem m_IndexerSubsystem = new IndexerSubsystem();
  private final SwerveSubsystem drivebase  = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));

  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

// Replace with CommandPS4Controller or CommandJoystick if needed
  final CommandXboxController driverXbox = new CommandXboxController(0);

  final CommandXboxController m_gunnerXbox = new CommandXboxController(1);

  SwerveInputStream driveRobotOriented = SwerveInputStream.of(drivebase.getSwerveDrive(),
                                                              () -> driverXbox.getRawAxis(1) * -1 * Constants.OperatorConstants.maxSpeed,
                                                              () -> driverXbox.getRawAxis(0) * -1 * Constants.OperatorConstants.maxSpeed)
                                                              
                                                          .withControllerRotationAxis(driverXbox::getRightX)
                                                          .deadband(OperatorConstants.DEADBAND)
                                                          .scaleTranslation(0.8)
                                                          .robotRelative(false)
                                                          .scaleRotation(-0.7);
                                                          
SwerveInputStream driveAngularVelocity = SwerveInputStream.of(drivebase.getSwerveDrive(),
                                                              () -> driverXbox.getRawAxis(1) * -1 * Constants.OperatorConstants.maxSpeed,
                                                              () -> driverXbox.getRawAxis(0) * -1 * Constants.OperatorConstants.maxSpeed)
                                                          .withControllerRotationAxis(driverXbox::getRightX)
                                                          .deadband(OperatorConstants.DEADBAND)
                                                          .scaleTranslation(0.8)
                                                          .scaleRotation(-0.7)
                                                          .allianceRelativeControl(false);


  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */

  private void configureBindings()
  {
    Command driveFieldOrientedAngularVelocity = drivebase.driveFieldOriented(driveAngularVelocity);

    drivebase.setDefaultCommand(driveFieldOrientedAngularVelocity);

    driverXbox.back().or(driverXbox.start()).onTrue(Commands.runOnce(() -> drivebase.resetOdometry(new Pose2d(drivebase.getPose().getTranslation(), new Rotation2d()))));

    // m_gunnerXbox.button(1).whileTrue(new Shoot(1, m_ShooterSubsystem)); // While button A depressed, schedule shoot command

    // m_gunnerXbox.button(3).whileTrue(new Index(1, m_IndexerSubsystem)); // While button X depressed, schedule shoot command

    // m_gunnerXbox.button(4).whileTrue(new Shoot(0.75, m_ShooterSubsystem)); // 75% speed button Y

    autoChooser.addOption("nothing", Commands.none());
    autoChooser.addOption("backup auto", Autos.backupAuto(drivebase));
    autoChooser.addOption("sweep", Autos.sweepAuto(drivebase));
    SmartDashboard.putData(autoChooser);
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return autoChooser.getSelected();
  }
}