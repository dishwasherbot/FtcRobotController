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
        waitForStart();
        int[] pos = new int[2];
        pos = robot.detect();
        if (pos[1] == 0) {
            if (pos[0] == 0) {
                robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 16.5, 1);
                robot.goBacktoStartAngle();
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 20, 0.5);
                robot.goToAngle(90, 0.14);
                robot.driveStraightByDistance(robot.DIRECTION_LEFT, 10, 0.2);
                robot.driveStraightByDistance(robot.DIRECTION_FORWARD,5, 0.2);
            } else if (pos[0] == 1) {
                robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 18.5, 1);
                robot.goBacktoStartAngle();
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 20, 0.5);
                robot.goToAngle(90, 0.14);
                robot.driveStraightByDistance(robot.DIRECTION_LEFT, 10, 0.2);
                robot.driveStraightByDistance(robot.DIRECTION_FORWARD,7, 0.2);
            } else {
                robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 18.5, 1);
                robot.goBacktoStartAngle();
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 20, 0.5);
                robot.goToAngle(90, 0.14);
                robot.driveStraightByDistance(robot.DIRECTION_LEFT, 10, 0.2);
                robot.driveStraightByDistance(robot.DIRECTION_FORWARD,7, 0.2);
            }
            robot.toggleSpinner(0.35);
            robot.sleep(2700);
            robot.toggleSpinner(0.35);
            robot.sleep(2000);
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 10, 0.6);
            robot.goBacktoStartAngle();
            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 70, 0.7);
        } else {
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 6.5, 0.5);
            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 15, 0.2);
            robot.toggleSpinner(0.35);
            robot.sleep(2700);
            robot.toggleSpinner(0.35);
            robot.sleep(2000);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 27.5, 0.6, false);
            robot.sleep(500);
            robot.goBacktoStartAngle();
            robot.sleep(500);
            robot.openPinch();
            if (pos[0] == 0) {
                robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 10, 1);
                robot.goBacktoStartAngle();
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 17, 0.5);
                robot.driveStraightByDistance(robot.DIRECTION_RIGHT,3, 0.4);
            } else if (pos[0] == 1) {
                robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 12, 1);
                robot.goBacktoStartAngle();
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 17, 0.5);
                robot.driveStraightByDistance(robot.DIRECTION_RIGHT,1, 0.3);
            } else {
                robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 12, 1);
                robot.goBacktoStartAngle();
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 17, 0.5);
                robot.driveStraightByDistance(robot.DIRECTION_RIGHT,1, 0.3);
            }
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 30, 0.6);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 2, 0.1);
        }
    }
}
