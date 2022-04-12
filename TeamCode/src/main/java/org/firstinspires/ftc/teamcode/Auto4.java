package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.CameraBot;
import org.firstinspires.ftc.teamcode.bots.FSMBot;
import org.firstinspires.ftc.teamcode.bots.SnarmBot;

@Autonomous(name="Auto 4 (FSM)", group="Autos")

public class Auto4 extends LinearOpMode {

    protected CameraBot robot = new CameraBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.isAutoStart = true;
        int[] pos;
        pos = robot.detect();
        robot.setDropHeight(pos[0]);

        robot.waitOnSnarmState(SnarmBot.SnarmState.RELEASING, 10000);
        int distanceFromStart = Math.abs(robot.horizontal.getCurrentPosition());
        int drivingDistance = distanceFromStart + 27000;
        robot.driveAgainstWallWithEncodersVertical(robot.DIRECTION_FORWARD, robot.side, drivingDistance, 500, 0);
        robot.drivingDone = true;

        robot.autoGrabFreight(0.2, robot.side);
        if (robot.side == CameraBot.autoSide.BLUE) {
            robot.driveStraightByTime(robot.DIRECTION_LEFT, 500, 0.2);
        } else {
            robot.driveStraightByTime(robot.DIRECTION_RIGHT, 500, 0.2);
        }
        robot.setDropHeight(0);

        robot.waitOnSnarmState(SnarmBot.SnarmState.RELEASING, 10000);
        distanceFromStart = Math.abs(robot.horizontal.getCurrentPosition());
        drivingDistance = distanceFromStart + 27000;
        robot.driveAgainstWallWithEncodersVertical(robot.DIRECTION_FORWARD, robot.side, drivingDistance, 500, 0);
        robot.drivingDone = true;

        robot.autoGrabFreight(0.2, robot.side);
        robot.setDropHeight(0);

        robot.waitOnSnarmState(SnarmBot.SnarmState.RELEASING, 10000);
        distanceFromStart = Math.abs(robot.horizontal.getCurrentPosition());
        drivingDistance = distanceFromStart + 27000;
        robot.driveAgainstWallWithEncodersVertical(robot.DIRECTION_FORWARD, robot.side, drivingDistance, 500, 0);
        robot.drivingDone = true;

        robot.autoGrabFreight(0.2, robot.side);
        robot.setDropHeight(0);

        robot.waitOnSnarmState(SnarmBot.SnarmState.RELEASING, 10000);
        distanceFromStart = Math.abs(robot.horizontal.getCurrentPosition());
        drivingDistance = distanceFromStart + 27000;
        robot.driveAgainstWallWithEncodersVertical(robot.DIRECTION_FORWARD, robot.side, drivingDistance, 500, 0);
        robot.drivingDone = true;
        robot.sleep(10000);
    }
}
