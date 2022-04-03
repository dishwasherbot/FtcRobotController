package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.FSMBot;
import org.firstinspires.ftc.teamcode.bots.SnarmBot;

@Autonomous(name="Auto 4 (FSM)", group="Autos")

public class Auto4 extends LinearOpMode {

    protected FSMBot robot = new FSMBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.isAutoStart = true;
//        int[] pos;
//        pos = robot.detect();
        if (true) {
            robot.waitOnSnarmState(SnarmBot.SnarmState.RELEASING, 10000);
            robot.driveAgainstWallWithEncodersVertical(robot.DIRECTION_FORWARD, robot.SIDE_BLUE, 27000, 1000, 0);
            robot.drivingDone = true;

            robot.autoGrabFreight(0.2, robot.SIDE_BLUE);
            robot.waitOnSnarmState(SnarmBot.SnarmState.RELEASING, 10000);
            robot.driveAgainstWallWithEncodersVertical(robot.DIRECTION_FORWARD, robot.SIDE_BLUE, 27000, 1000, 0);
            robot.drivingDone = true;

            robot.sleep(10000);
        } else {

        }
    }
}
