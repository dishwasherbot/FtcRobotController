package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.FSMBot;
import org.firstinspires.ftc.teamcode.bots.FourWheelDriveBot;
import org.firstinspires.ftc.teamcode.bots.TapeMeasureBot;

@Autonomous(name="Drive Test", group="Tests")

public class DriveTest extends LinearOpMode {

    protected FSMBot robot = new FSMBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            robot.driveByVector(1, -0.4, 0, 0.4);
        }
        //robot.driveAgainstWallWithEncodersVertical(robot.DIRECTION_FORWARD, robot.SIDE_BLUE, 40000, 500, 200);
    }
}
