package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.FourWheelDriveBot;
import org.firstinspires.ftc.teamcode.bots.TapeMeasureBot;

@Autonomous(name="Drive Test", group="Tests")

public class DriveTest extends LinearOpMode {

    protected FourWheelDriveBot robot = new FourWheelDriveBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.testOneMotor(robot.leftFront, 0.5, 1);
        robot.testOneMotor(robot.rightFront, 0.5, 1);
        robot.testOneMotor(robot.leftRear, 0.5, 1);
        robot.testOneMotor(robot.rightRear, 0.5, 1);

    }
}
