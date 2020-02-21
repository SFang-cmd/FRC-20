package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Subsystems;


public class Turn extends Command {

    private double degrees;
    private double speed;
    private double timeout;
    private boolean isCorrecting = false;
    private double P = 1;
    private double I = 1;
    private double D = 1;
    private double integral, derivative, previousError = 0;
    private double PID;

    public Turn(double Degrees, double Speed, double Timeout){
        super("Turn");
        requires(Subsystems.driveBase);
        degrees = Degrees;
        speed = Speed;
        timeout = Timeout;
        setTimeout(timeout);
    }

    public void initialize() {
        Subsystems.driveBase.zeroGyroAngle();
        Subsystems.driveBase.zeroEncoderPosition();
    }

    public double PID(){
        double error = degrees - Subsystems.driveBase.getGyroAngle(); // Error = Target - Actual
        this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        derivative = (error - this.previousError) / .02;
        this.previousError = error;
        this.PID = P * error + I * this.integral + D * derivative;
        return PID / degrees;
    }

    public void execute() {
        if ((degrees > 0) && !isCorrecting) {
            // Turning to the right
            Subsystems.driveBase.setMotors(-speed * PID(), speed * PID());
        } else if ((degrees < 0) && !isCorrecting) {
            // Turning to the left
            PID();
            Subsystems.driveBase.setMotors(speed * PID(), -speed * PID());
        } else if (degrees > 0) {
            // Turned to the right, but correcting to the left
            PID();
            Subsystems.driveBase.setMotors(speed / 2 * PID(), -speed / 2 * PID());
        } else {
            // Turned to the left, but correcting to the right
            PID();
            Subsystems.driveBase.setMotors(-speed / 2 * PID(), speed / 2 * PID());
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