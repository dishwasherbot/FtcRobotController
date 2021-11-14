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
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 10, 0.5);
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 15, 0.4);

            if (pos[0] == 0) {
                //robot drops cube on top layer
            } else if (pos[0] == 1) {
                //robot drops cube on middle layer
            } else {
                //robot drops cube on bottom layer
            }
            robot.driveCurveByDistance(robot.DIRECTION_LEFT, 69, 0.2, 0.8);
            robot.goToAngle(-90, 0.1);
            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 95, 0.9);
            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 50, 0.4);
            //spin carousel
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 150, 1);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 35, 0.4);
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 120, 1);
        }
    }
}
