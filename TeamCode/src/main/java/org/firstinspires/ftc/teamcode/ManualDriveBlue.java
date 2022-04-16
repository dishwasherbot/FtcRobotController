
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bots.FSMBot;
import org.firstinspires.ftc.teamcode.bots.LEDBot;
import org.firstinspires.ftc.teamcode.bots.RumbleBot;

@TeleOp(name = "Manual Drive (Blue)")
public class ManualDriveBlue extends LinearOpMode {

    //ElapsedTime runtime = new ElapsedTime();    // Use to determine when end game is starting.

    private RumbleBot robot = new RumbleBot(this);
    //int count = 0;

    @Override
    public void runOpMode() {
        robot.snarmSnarmState = 1;
        robot.isAutonomous = false;
        robot.setSnarmRotation(0);
        robot.setDropHeight(0);
        robot.init(hardwareMap);
        robot.isAutoStart = false;
        robot.odometryRaise.setPosition(0.65);
        robot.goToIntakePosition(3);
        waitForStart();
        while (opModeIsActive()) {
// snarm code
            robot.controlExtension(gamepad1.left_trigger, gamepad1.right_trigger);
            robot.controlElevation(gamepad1.dpad_up, gamepad1.dpad_down);
            robot.controlRotation(gamepad1.dpad_right, gamepad1.dpad_left);

            robot.autoExtend(gamepad1.left_bumper);
            robot.autoRetract(gamepad1.right_bumper);
            robot.dropFreight(gamepad1.cross);
            //robot.toggleBox(gamepad1.x);
            robot.toggleHub(true, gamepad1.square);
// intake code
            robot.changeIntakePosition(gamepad2.cross, gamepad2.triangle);
            robot.intakeToggle(gamepad1.triangle);
// driving code
            robot.driveByHand(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_button, gamepad2.left_stick_x, gamepad2.left_stick_y, gamepad2.right_stick_x, gamepad2.left_stick_button);
            robot.holdSpinner(gamepad1.circle, true, 0.325);
// tape arm code
            robot.controlElevationTape(gamepad2.dpad_up, gamepad2.dpad_down);
            robot.controlRotationSlow(gamepad2.dpad_right, gamepad2.dpad_left);
//            robot.controlSwing(gamepad2.dpad_left, gamepad2.dpad_right);
            robot.controlCoreHex(gamepad2.right_trigger, gamepad2.left_trigger);

            if (robot.vibrate) {
                gamepad1.runRumbleEffect(robot.customRumbleEffect);
            }

            robot.prepareTape(gamepad2.square);
// tape arm end
            robot.onLoop(15, "manual drive");
        }
        robot.close();
    }
}