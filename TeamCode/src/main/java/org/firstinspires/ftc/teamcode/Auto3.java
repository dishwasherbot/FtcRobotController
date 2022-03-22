package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.CameraBot;

@Autonomous(name="Auto 3", group="Autos")

public class Auto3 extends LinearOpMode {

    protected CameraBot robot = new CameraBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.isRepeating = true;
        waitForStart();
        int[] pos;
        pos = robot.detect();
        if (pos[1] == 0) {
            //snarm rotates
            //snarm extends
            //snarm drops
            //snarm retracts
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_FORWARD, 10000, 0.6, false, false, 200);
            //auto intake
            robot.driveByGyroWithEncodersVertical(robot.DIRECTION_BACKWARD, 10000, 0.6, false, false, 200);
        } else {

        }
    }
}
