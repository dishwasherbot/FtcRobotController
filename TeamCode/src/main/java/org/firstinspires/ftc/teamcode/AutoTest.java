package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.CameraBot;

@Autonomous(name="Auto Test", group="Tests")

public class AutoTest extends LinearOpMode {

    protected CameraBot robot = new CameraBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        for (int i = 0; i < 2; i++) {
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 10, 0.3, false, 0);
            robot.autoGrabFreight(0.12);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 10, 0.3, false, 0);
            robot.servoPosIndex = 0;
            robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
            robot.sleep(2000);
            robot.setArmPositionNoWait(-25, 0.3);
            robot.servoPosIndex = 2;
            robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
            robot.sleep(500);
        }
    }
}
