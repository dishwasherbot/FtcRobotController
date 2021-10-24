
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bots.EndgameBot;
import org.firstinspires.ftc.teamcode.bots.NoodleSpinnerBot;

/**
 * Mecanum teleop (with an optional arcade mode)
 * * Left stick controls x/y translation.
 * * Right stick controls rotation about the z axis
 * * When arcade mode is enabled (press "a"), translation direction
 * becomes relative to the field as opposed to the robot. You can
 * reset the forward heading by pressing "x".
 */
@TeleOp(name = "Manual Drive")
public class ManualDriveOpMode extends LinearOpMode {

    int count = 0;

    private NoodleSpinnerBot robot = new NoodleSpinnerBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
//        robot.readPosition();
//        robot.startAngle = robot.savedStartAngle;
//        robot.isAuto = false;
        waitForStart();
        while (opModeIsActive()) {
            robot.intakeToggle(gamepad1.a);

            robot.onLoop(15, "manual drive");
        }
        robot.close();
    }
}