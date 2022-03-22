package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.FourWheelDriveBot;
import org.firstinspires.ftc.teamcode.bots.NewIntakeBot;
import org.firstinspires.ftc.teamcode.bots.NoodleSpinnerBot;

@Autonomous(name="Intake Test", group="Tests")

public class IntakeTest extends LinearOpMode {

    protected NewIntakeBot robot = new NewIntakeBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.startRotation();
        robot.sleep(10000);
        robot.stopRotation();
    }

}
