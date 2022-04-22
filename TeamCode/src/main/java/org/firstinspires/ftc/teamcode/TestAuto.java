package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.FSMBot;
import org.firstinspires.ftc.teamcode.bots.FourWheelDriveBot;

@Autonomous(name="Test Robot", group="Tests")

public class TestAuto extends LinearOpMode {

    protected FSMBot robot = new FSMBot(this); //replace FourWheelDriveBot with whichever Bot is required

    @Override
    public void runOpMode() {
        robot.isAutonomous = false;
        robot.init(hardwareMap);
        waitForStart();

        robot.goToFlipperPosition(5);
        robot.sleep(1000);

        robot.setElevationPosition(0.66);
        robot.sleep(1000);

        robot.setExtension(700);
        robot.sleep(1000);

        robot.setRotationPosition(robot.blueSharedRotation);
        robot.sleep(2000);

        
    }
}
