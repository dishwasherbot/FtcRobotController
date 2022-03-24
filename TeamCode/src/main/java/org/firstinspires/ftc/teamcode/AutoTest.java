package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
        robot.setElevationPosition(0.3);
        robot.sleep(1000);
        robot.startRotation();
        robot.waitOnExtension(robot.maxExtension);
        robot.goToFlipperPosition(2);
        robot.sleep(300);
        robot.box.setPosition(robot.boxOpened);
    }
}
