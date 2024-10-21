package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
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

    // Timer (for match timing)
    private final Timer matchTimer = new Timer();

    // Shuffleboard variables
    private final ShuffleboardTab tab = Shuffleboard.getTab("Driver");
    private final GenericEntry hasNoteEntry = tab.add("Has Note", false).getEntry();
    private final GenericEntry noteIntakeTimes = tab.add("Note Intake Times", new double[0]).getEntry();
    private final GenericEntry averageTimeEntry = tab.add("Average Time Between Notes", 0.0).getEntry();

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
        // Display on Shuffleboard the note sensor status
        hasNoteEntry.setBoolean(hasNote());

        // If the left bumper is held and we don't have a note, turn on the intake.
        // Otherwise, turn it off (either because the driver is no longer holding the
        // button or because we already have a note).
        if (driverLeftBumperButton.getAsBoolean() && !hasNote()) {
            intake_motor.set(0.5);
        } else{
            intake_motor.set(0.0);
        }

        // TODO: Record the current match time into an array for each time we have collected a note this match.
        //  - First, detect if we collected a note this iteration
        //  - Next, if we did collect a note, record the current match time into an array or ArrayList
        //     - The current match time is available using matchTimer.get()
        //  - Finally, Store the list of match times into noteIntakeTimes.



        // TODO: Using the note collection time history, compute the average time between intaking notes.
        //   Store the resulting average in averageTimeEntry.
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

    public void startTimer() {
        matchTimer.restart();
    }
}
