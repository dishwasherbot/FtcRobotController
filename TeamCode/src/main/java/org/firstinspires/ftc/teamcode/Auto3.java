package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.CameraBot;
import org.firstinspires.ftc.teamcode.bots.NewDistanceSensorBot;

@Autonomous(name="Auto 3", group="Autos")

public class Auto3 extends LinearOpMode {

    protected NewDistanceSensorBot robot = new NewDistanceSensorBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.isRepeating = true;
        waitForStart();
//        int[] pos;
//        pos = robot.detect();
        if (true) {
            robot.goToIntakePosition(3);
            robot.setExtension(robot.maxExtension);
            robot.waitOnExtension(robot.maxExtension);
            robot.sleep(100);

            robot.goToFlipperPosition(0);
            robot.extensionCheckpoints[3] = true;
            robot.setExtension(robot.minExtension);
            robot.setRotationPosition(robot.rotationCenter);

            robot.intakeFast = false;

            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 35000, false, 500, 0);
            robot.goToAngle(0, 0.16);
            robot.sleep(300);
            robot.extensionCheckpoints = new boolean[]{true, true, true, false};
            robot.autoGrabFreight(0.2);

            robot.goToAngle(0, 0.16);
            robot.waitOnExtension(robot.maxExtension);
            robot.sleep(500);
            robot.goToFlipperPosition(0);
            robot.extensionCheckpoints[3] = true;
            robot.setExtension(robot.minExtension);
            robot.setRotationPosition(robot.rotationCenter);

            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 35000, false, 500, 0);
            robot.goToAngle(0, 0.16);


            robot.sleep(10000);
            //robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 10000, 0.6, false, false, 200);
        } else {

        }
    }
}
