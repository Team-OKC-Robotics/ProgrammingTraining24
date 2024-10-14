package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

public class NoteSensorSubsystem extends SubsystemBase {
    // Motor variables
    private final CANSparkMax intake_motor = new CANSparkMax(Constants.kIntakeMotorPort, CANSparkMax.MotorType.kBrushless);

    // Sensor variables
    private final DigitalInput photoelectricSwitch = new DigitalInput(Constants.kPhotoelectricSwitchPort);

    // Controller variables
    private final XboxController driverController = new XboxController(0);
    private final JoystickButton driverLeftBumperButton = new JoystickButton(driverController, 5);

    // Shuffleboard variables
    private final ShuffleboardTab tab = Shuffleboard.getTab("Driver");
    private final GenericEntry hasNoteEntry = tab.add("Has Note", "No Note!").getEntry();
    private final GenericEntry intakeMotorSpeedEntry = tab.add("Intake Motor Speed", 0.0).getEntry();

    /*
     * Constructor for the NoteSensorSubsystem. This is where we do one-time setup.
     */
    public NoteSensorSubsystem() {
        // Setup intake motor
        intake_motor.restoreFactoryDefaults();
        intake_motor.setInverted(false);
        intake_motor.setIdleMode(CANSparkMax.IdleMode.kCoast);
    }

    /*
     * This method is called periodically (about every 20ms)
     */
    @Override
    public void periodic() {
        // TODO: Test if the robot has a note. If it does, set hasNoteEntry to "Has Note!"
        // Otherwise, set hasNoteEntry to "No Note!"

        // TODO: Test if the driver is holding the left bumper button. If they are,
        // and there is no note in the robot, start the intake motor. If there is a note,
        // stop the intake motor.

        // TODO: Set intakeMotorSpeedEntry to the speed of the intake motor
    }

    /*
     * Returns true if the photoelectric switch is triggered, meaning there is a note in the robot
     */
    public boolean hasNote() {
        return !photoelectricSwitch.get();
    }

    /*
     * Stops the intake motor (used to stop the motor on teleop init)
     */
    public void stopMotor() {
        intake_motor.stopMotor();
    }
}
