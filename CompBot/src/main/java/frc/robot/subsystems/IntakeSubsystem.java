package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

  
  private static SparkMax intakeMotor =  new SparkMax(36, MotorType.kBrushless);

  
  private static SparkMaxConfig intakeConfig = new SparkMaxConfig();

  /** Creates a new HopperSubsystem. */
  public IntakeSubsystem() {
    intakeConfig.idleMode(IdleMode.kCoast);
    intakeConfig.inverted(true);
    intakeConfig.smartCurrentLimit(40);
    intakeMotor.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
   }

   public void setIntakeSpeed(double speed) {
    intakeMotor.set(speed);
   }

   public void stopIntake() {
    intakeMotor.stopMotor();
   }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
