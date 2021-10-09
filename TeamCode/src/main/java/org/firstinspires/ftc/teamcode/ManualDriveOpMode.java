
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bots.EndgameBot;

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

    private EndgameBot robot = new EndgameBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.readPosition();
        robot.startAngle = robot.savedStartAngle;
        robot.isAuto = false;
        waitForStart();
        while (opModeIsActive()) {
            if (!robot.isCoordinateDriving) {
                robot.driveByHand(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_button);
            }
            if (!robot.isDown) {
                robot.launchRing(gamepad1.right_bumper);
            }
            robot.toggleShooter(gamepad1.left_bumper);
            robot.toggleWobble(gamepad1.x);
            robot.toggleFeeder(gamepad1.a);
            robot.toggleFeeder(gamepad2.a);
            robot.controlWobbleArm(gamepad1.y, gamepad1.b);
            robot.intakeControl(gamepad1.dpad_down);
            robot.directionToForward(gamepad1.dpad_left);
            robot.directionToReverse(gamepad1.dpad_right);
//            if (gamepad1.dpad_down) {
//                robot.goToAngle(2, 0.2);
//            }
            robot.endgame(gamepad2.y);
            robot.resetOdometry(gamepad2.dpad_up);
            if (gamepad1.dpad_up) {
                robot.isCoordinateDriving = true;
                robot.goToShoot(count);
            } else {
                robot.isCoordinateDriving = false;
                count = 0;
            }
            count++;
            if (gamepad2.x) {
                robot.resetAngle();
            }
//            if (robot.isForward) {
//                telemetry.addData("Intake Direction: Forward", true);
//            } else {
//                telemetry.addData("Intake Direction: Backward", true);
//            }

            robot.onLoop(15, "manual drive");
        }
        robot.close();
    }
}