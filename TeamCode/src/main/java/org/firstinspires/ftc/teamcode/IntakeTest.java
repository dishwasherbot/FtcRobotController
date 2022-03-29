package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.DistanceSensorBot;
import org.firstinspires.ftc.teamcode.bots.FourWheelDriveBot;
import org.firstinspires.ftc.teamcode.bots.NewDistanceSensorBot;
import org.firstinspires.ftc.teamcode.bots.NewIntakeBot;
import org.firstinspires.ftc.teamcode.bots.NoodleSpinnerBot;

@Autonomous(name="Intake Test", group="Tests")

public class IntakeTest extends LinearOpMode {

    protected NewDistanceSensorBot robot = new NewDistanceSensorBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.goToIntakePosition(4);
        waitForStart();
        robot.intakeFast = false;
        robot.goToFlipperPosition(0);
        robot.setElevationPosition(0.2);//0.23
        robot.box.setPosition(robot.boxOpened);
        robot.sleep(3000);
        robot.startRotation();
        robot.sleep(30000);
    }

}
