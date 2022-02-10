package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.CameraBot;

@Autonomous(name="Auto 2", group="Autos")

public class Auto2 extends LinearOpMode {

    protected CameraBot robot = new CameraBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.controlServo(false, true);
        robot.isRepeating = true;
        waitForStart();
        int[] pos;
        pos = robot.detect();
        if (pos[1] == 0) {

            if (pos[0] == 0) {
                robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 17.2, 1);
                robot.sleep(500);
                robot.driveStraightByDistance(robot.DIRECTION_LEFT, 5, 0.5);
                robot.goBacktoStartAngle();
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 20, 0.5);
                robot.goToAngle(90, 0.14);
                robot.driveStraightByDistance(robot.DIRECTION_LEFT, 17, 0.2);
                robot.driveStraightByDistance(robot.DIRECTION_FORWARD,5, 0.2);
            } else if (pos[0] == 1) {
                robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 19.5, 1);
                robot.sleep(500);
                robot.driveStraightByDistance(robot.DIRECTION_LEFT, 7, 0.5);
                robot.goBacktoStartAngle();
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 20, 0.5);
                robot.goToAngle(90, 0.14);
                robot.driveStraightByDistance(robot.DIRECTION_LEFT, 17, 0.2);
                robot.driveStraightByDistance(robot.DIRECTION_FORWARD,5, 0.2);
            } else {
                robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 19.5, 1);
                robot.sleep(500);
                robot.driveStraightByDistance(robot.DIRECTION_LEFT, 7, 0.5);
                robot.goBacktoStartAngle();
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 20, 0.5);
                robot.goToAngle(90, 0.14);
                robot.driveStraightByDistance(robot.DIRECTION_LEFT, 17, 0.2);
                robot.driveStraightByDistance(robot.DIRECTION_FORWARD,5, 0.2);
            }
            robot.toggleSpinner(0.35, false);
            robot.sleep(3200);
            robot.toggleSpinner(0.35, false);
            robot.sleep(1000);
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 10, 0.6);
            robot.goBacktoStartAngle();
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 25, 0.5, false, 0);
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 10, 0.5);
            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 45, 0.6);
            //robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 70, 0.7);
        } else {
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 6.5, 0.5);
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 9, 0.2);
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 2, 0.2);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 0.2, 0.14);
            robot.toggleSpinner(0.35, true);   
            robot.sleep(3500);
            robot.toggleSpinner(0.35, true);
            robot.sleep(1000);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 32.5, 0.6, false, 0);
            robot.sleep(500);
            robot.goBacktoStartAngle();
            robot.controlExtension(true);
            robot.sleep(500);
            if (pos[0] == 0) {
                robot.setArmPositionNoWait(-935, 0.2);
                robot.goToAngle(-90, 0.18);
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 6, 0.2);
                robot.controlServo(false, true);
                robot.sleep(1000);
                robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 2, 0.5);
            } else if (pos[0] == 1) {
                robot.setArmPositionNoWait(-760, 0.18);
                robot.goToAngle(-90, 0.2);
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 6, 0.2);
                robot.controlServo(false, true);
                robot.sleep(1000);
                robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 2, 0.5);
            } else {
                robot.setArmPositionNoWait(-580, 0.18);
                robot.goToAngle(-90, 0.2);
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 11, 0.2);
                robot.controlServo(false, true);
                robot.sleep(1000);
                robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 7, 0.7);
            }
            robot.controlServo(true, false);
            robot.sleep(300);
            robot.controlServo(true, false);
            robot.setArmPositionNoWait(-25, 0.1);
            robot.goBacktoStartAngle();
            robot.controlExtension(true);
            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 43, 0.6);
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 8, 0.7);
        }
    }
}
