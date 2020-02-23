package frc.robot.commands;

import frc.robot.subsystems.Subsystems;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * A Command to turn the bot a set number of degrees.
 */
public class Turn extends PIDCommand {

    private double degrees;
    private double speed;
    private boolean isCorrecting = false;

    /**
     * Turns the bot a set number of degrees.
     * @param Degrees The number of degrees to turn - negative to the left, positive to the right.
     * @param Speed The speed at which to turn (0 to 1). Speeds over x are not recommended for maximal accuracy.
     * @param Timeout The timeout, in seconds.
     */
    //private double P = 1;
    //private double I = 1;
    //private double D = 1;
    //private double integral, derivative, previousError = 0;
    //private double PID;

    public Turn(double Degrees, double Speed, double Timeout){
        super("Turn", 4, 2, 2);
        requires(Subsystems.driveBase);
        degrees = Degrees;
        speed = Speed;
        setTimeout(Timeout);
    }

    @Override
    protected double returnPIDInput() {
        return Subsystems.driveBase.getGyroAngle();
    }

    @Override
    protected void usePIDOutput(double output) {
        speed = output;
    }

    public void initialize() {
        System.out.println("Starting turn!");
        Subsystems.driveBase.zeroGyroAngle();
        Subsystems.driveBase.zeroEncoderPosition();
    }

    // public double PID(){
    //     double error = degrees - Subsystems.driveBase.getGyroAngle(); // Error = Target - Actual
    //     this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    //     derivative = (error - this.previousError) / .02;
    //     this.previousError = error;
    //     this.PID = P * error + I * this.integral + D * derivative;
    //     return PID / degrees;
    // }

    public void execute() {
        if ((degrees > 0) && !isCorrecting) {
            // Turning to the right
            Subsystems.driveBase.setMotors(-speed, speed);
        } else if ((degrees < 0) && !isCorrecting) {
            // Turning to the left
            Subsystems.driveBase.setMotors(speed, -speed);
        } else if (degrees > 0) {
            // Turned to the right, but correcting to the left
            Subsystems.driveBase.setMotors(speed / 2, -speed / 2);
        } else {
            // Turned to the left, but correcting to the right
            Subsystems.driveBase.setMotors(-speed / 2, speed / 2);
        }
    }

    public boolean isFinished() {
        double angle = Subsystems.driveBase.getGyroAngle();
        if (degrees > 0) {
            // Turning to the right
            if (!isCorrecting) {
                if (angle > degrees) {
                    isCorrecting = true;
                }
                return isTimedOut();
            }
            return (angle < degrees) || isTimedOut();
        } else {
            // Turning to the left
            if (!isCorrecting) {
                if (angle < degrees) {
                    isCorrecting = true;
                }
                return isTimedOut();
            }
            return (angle > degrees) || isTimedOut();
        }
    }

    public void interrupted() {
        Subsystems.driveBase.setMotors(0,0);
    }

    public void end() {
        Subsystems.driveBase.setMotors(0,0);
    }

}