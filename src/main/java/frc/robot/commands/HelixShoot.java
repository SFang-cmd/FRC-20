package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.RobotMap;

/**
 * Turns the helix when the flywheel is running & up to speed. If this is executed in auto, it must have a timeout.
 */
public class HelixShoot extends Command {

    private Boolean warmedUp = false;
    private int counter = 0;

    private final double helixSpeed = 0.75;

    public HelixShoot() {
        super("HelixShoot");
        requires(Subsystems.helix);
    }

    @Override
    public void initialize() {
        warmedUp = false;
    }

    @Override
    public void execute() {
        System.out.println(Subsystems.flyboi.getPower());
        if(Subsystems.flyboi.getPower() >= Subsystems.flyboi.wheelSpeed-0.005) {
            if (!RobotMap.isCellStopUp) {
                Subsystems.helix.cellStopIn();
                RobotMap.isCellStopUp = true;
            } else if (counter < 5) {
                counter++;
            } else {
                Subsystems.helix.setHelixMotors(helixSpeed);
                warmedUp = true;
            }
        } else {
            Subsystems.helix.stopHelixMotors();
            if (warmedUp && Subsystems.flyboi.getPower() < Subsystems.flyboi.wheelSpeed-0.020) {
                warmedUp = false;
                Subsystems.helix.cellCount--;
                System.out.println("BALL SHOT, " + Subsystems.helix.cellCount + " BALLS REMAINING");
            }
        }
    }

    @Override
    public boolean isFinished() {
        if (DriverStation.getInstance().isAutonomous()) {
            return false;
        } else {
            return UserInterface.operatorController.getRightTrigger() < 0.4;
        }
    }

    @Override
    public void interrupted() {}

    @Override
    public void end() {}

}