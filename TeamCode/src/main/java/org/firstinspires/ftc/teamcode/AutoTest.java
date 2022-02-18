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
        for (int i = 0; i < 3; i++) {
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 18, 0.5, false, 0, true);
            robot.autoGrabFreight(0.12);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 18, 0.5, false, 0, true);
            robot.servoPosIndex = 0;
            robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
            robot.sleep(500);
            robot.setArmPositionNoWait(-25, 0.3);
            robot.servoPosIndex = 2;
            robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
            robot.sleep(500);
        }
    }
}
