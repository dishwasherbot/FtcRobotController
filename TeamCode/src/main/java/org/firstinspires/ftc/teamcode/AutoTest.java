package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.teamcode.bots.CameraBot;
import org.firstinspires.ftc.teamcode.bots.NewDistanceSensorBot;
import org.firstinspires.ftc.teamcode.bots.SnarmBot;

@Autonomous(name="Auto Test", group="Tests")

public class AutoTest extends LinearOpMode {

    protected NewDistanceSensorBot robot = new NewDistanceSensorBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.intakeFast = false;
        robot.goToIntakePosition(3);
        robot.goToFlipperPosition(0);
        robot.setElevationPosition(0.23);
        robot.box.setPosition(robot.boxOpened);
        robot.sleep(1000);
        robot.startRotation();
        robot.waitOnExtension(200);
        robot.setRotationPosition(0.65);
        robot.waitOnExtension(1000);
        robot.goToFlipperPosition(3);
        robot.waitOnExtension(robot.maxExtension);
        //RobotLog.d("after wait");

        robot.sleep(200);
        robot.box.setPosition(robot.boxOpened);
        robot.sleep(300);
        robot.goToFlipperPosition(0);
        robot.setExtension(0);
        robot.setRotationPosition(robot.rotationCenter);
        robot.waitOnExtension(2600);
        robot.box.setPosition(robot.boxOpened);
        robot.setElevationPosition(0.23);
        robot.waitOnExtension(0);
        robot.sleep(10000);
        //RobotLog.d("end of program");
    }
}
