package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.teamcode.bots.CameraBot;
import org.firstinspires.ftc.teamcode.bots.FSMBot;
import org.firstinspires.ftc.teamcode.bots.NewDistanceSensorBot;
import org.firstinspires.ftc.teamcode.bots.SnarmBot;

@Autonomous(name="Auto Test", group="Tests")

public class AutoTest extends LinearOpMode {

    protected FSMBot robot = new FSMBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.setElevationPosition(0.2);
        robot.goToFlipperPosition(0);
        robot.goToIntakePosition(2);
        waitForStart();
        robot.isAutoStart = true;

        //robot.autoGrabFreight(0.2, robot.SIDE_BLUE);

        robot.sleep(10000);
    }
}

