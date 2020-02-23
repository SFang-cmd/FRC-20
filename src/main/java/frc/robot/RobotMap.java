package frc.robot;

/**
 * Ports for motor controllers, caps for speed and rotation, and turn direction.
 */
public class RobotMap {

    public static double speedCap = 0.25;
    public static double rotationCap = 0.5;

    public static boolean isFastMode = false;
    public static boolean isFirstCamera = true;
    public static boolean isIntakeDown = false;
    public static boolean isShooterOn = false;
    public static boolean arePinsOut = false;
    public static boolean isHelixToggled = false;
    public static boolean isClimberBrakeToggled = false;
    public static boolean isCellyToggled = false;

    // Double solenoid ports

    public static int cellStopOut;
    public static int cellStopIn;
    public static int intakeExtensionOut;
    public static int intakeExtensionIn;
    // public static final int climberBrakeIn = 0; //!
    // public static final int climberBrakeOut = 1; //!
    // public static final int leftClimbPinIn = 2; //!
    // public static final int leftClimbPinOut = 3; //!
    // public static final int rightClimbPinIn = 4; //!
    // public static final int rightClimbPinOut = 5; //!

    // Sensor ports

    public static final int intakeBeamBreak = 9; //!

    // UI Ports

    public static final int driverXboxController = 1;
    public static final int operatorXboxController = 2;

    public static int leftFrontFollower = 43;
    public static int leftMiddleMaster = 18;
    public static int leftRearFollower = 2;
    public static int rightFrontFollower = 60;
    public static int rightMiddleMaster = 14;
    public static int rightRearFollower = 57;
    public static int wheelDiameter = 8;

    /**
     * @return The speed cap for the drive base in teleop.
     */
    public static double getSpeedCap() {
        return speedCap;
    }

    /**
     * @return The rotation speed cap for the drive base in teleop.
     */
    public static double getRotationCap() {
        return rotationCap;
    }

    /**
     * Sets the caps on speed & rotation for the drive base in teleop.
     * @param newSpeedCap The speed cap to set.
     * @param newRotationCap The rotation speed cap to set.
     */
    public static void setSpeedAndRotationCaps(final double newSpeedCap, final double newRotationCap) {
        speedCap = newSpeedCap;
        rotationCap = newRotationCap;
    }

}