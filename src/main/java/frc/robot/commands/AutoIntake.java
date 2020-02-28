package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;
import io.github.pseudoresonance.pixy2api.*;
public class AutoIntake extends Command {
  
  
  public AutoIntake() {
      super("AutoIntake");
      requires(Subsystems.driveBase);
      requires(Subsystems.intake);      
  } 
  
    protected void intialize() {
}
    protected void execute() {
        double frameWidth = Subsystems.pixy.getFrameWidth();
        boolean ballIntaking = false;
        Pixy2CCC.Block block = Subsystems.pixy.getBiggestBlock();
        if (block != null) {
            System.out.println(block);
            
            if (ballIntaking == false){
                if (block.getWidth() < 120){
                
                    if (block.getX() > (frameWidth / 2) && block.getWidth() < 120){ //120 is not accurrate, just a value
                
                        Subsystems.driveBase.setMotors(0.1, 0.3); //consider adding speed to right motors
                        return;
                
                    } else if (block.getX() < (frameWidth / 2) && block.getWidth() < 120) { //120 is not accurrate, just a value
                
                        Subsystems.driveBase.setMotors(0.3, 0.1); //consider adding speed to left motors
                        return;
                
                    } else if (block.getWidth() > 20 && block.getWidth() < 80) { //Size needs to change based on how large it can be to intake
                
                        Subsystems.driveBase.setMotors(0.1, 0.1);
                        return;
                    } 
               
                } else if (block.getWidth() > 120){
               
                    ballIntaking = true;
               
                } 
            } else if (block.getWidth() >= 120 && ballIntaking == true){ //Size needs to change based on how large it can be to intake
               
                    if (RobotMap.isIntakeDown == false){
                    Subsystems.intake.intakeExtend();
                    RobotMap.isIntakeDown = true;
                    }
               
                    Subsystems.intake.setIntakeMotors(0.7);
               
                    if (Subsystems.intake.getBeamBreak()){
                        Subsystems.intake.setIntakeMotors(0);
                        Subsystems.intake.intakeRetract();
                        RobotMap.isIntakeDown = false;
                        ballIntaking = false;
                        return;
                    }
               
                } else {
                System.out.println("Too small boi");
            }
        } else {
            System.out.println("No blocks found");
        }
    }
    
    @Override public boolean isFinished() {
        return true;
    }
    protected void interrupted() {}
    protected void end() {}
}