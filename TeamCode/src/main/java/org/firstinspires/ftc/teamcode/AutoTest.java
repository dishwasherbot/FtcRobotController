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
        robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 15, 0.5);
        robot.sleep(2000);
        robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 15, 0.5);
//        robot.sleep(2000);
//        robot.goToAngle(-90, 0.1);
//        robot.sleep(2000);
//        robot.goToAngle(0, 0.1);
//        robot.sleep(2000);
//        robot.driveStraightByDistance(robot.DIRECTION_LQUARTER, 5, 0.4);
//        robot.driveStraightByDistance(robot.DIRECTION_RQUARTER, 5, 0.4);
//        robot.sleep(2000);
//        robot.toggleSpinner(0.4, true);
//        robot.sleep(2000);
//        robot.toggleSpinner(0.4, true);
//        robot.sleep(3000);
    }
}
