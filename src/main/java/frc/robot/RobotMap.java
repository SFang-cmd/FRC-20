package frc.robot;

public class RobotMap {

    public static String botName = "Toaster Bot";
    public static double idealAngle = 0;
    public static double driveOffset = 0;
    public static double turnDirection = 0;
    public static double speedCap = 0.2;
    public static double rotationCap = 0.2;
    
    /**
     * Various Ports
     */
    // UI Ports

    public static final int driverXboxController = 1;
    public static final int operatorXboxController = 2;
    
    // Talon/Victor IDs
    public static final int leftFrontFollower = 3;
    public static final int leftMiddleMaster = 2;
    public static final int leftRearFollower = 6;
    public static final int rightFrontFollower = 5;
    public static final int rightMiddleMaster = 14;
    public static final int rightRearFollower = 4;
    public static final int intake = 999; //replace with actual value
    public static final int intakeExtensionOut = 3003;
    public static final int intakeExtensionIn = 4004;

    public static double getRotationCap() {
        return rotationCap;
    }
    
    public static double getSpeedCap() {
        return speedCap;
    }

    public static void setSpeedAndRotationCaps(final double newSpeedCap, final double newRotationCap) {
        speedCap = newSpeedCap;
        rotationCap = newRotationCap;
    }
    
    public static void setDriveOffset(final double offset) {
        driveOffset = offset;
        System.out.println("Drive offset is now " + driveOffset);
    }
    
    public static double getDriveOffset() {
        return driveOffset;
    }
    
    public static double getTurnDirection() {
        return turnDirection;
    }
    
    public static void setTurnDirection(final double direction) {
        turnDirection = direction;
    }
}