package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.CameraBot;
import org.firstinspires.ftc.teamcode.bots.SnarmBot;

@Autonomous(name="Auto Test", group="Tests")

public class AutoTest extends LinearOpMode {

    protected SnarmBot robot = new SnarmBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.setElevationPosition(0.8);
        robot.sleep(1000);
        robot.setRotationPosition(0.4);
        robot.sleep(1000);
        robot.setExtension(3000);
        robot.sleep(7000);
        robot.setExtension(0);
        robot.sleep(7000);
    }
}
