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

        } else {
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 6.5, 0.5);
            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 15, 0.2);
            robot.toggleSpinner(0.35);
            robot.sleep(2700);
            robot.toggleSpinner(0.35);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 27.5, 0.6, false);
            robot.sleep(3000);
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
