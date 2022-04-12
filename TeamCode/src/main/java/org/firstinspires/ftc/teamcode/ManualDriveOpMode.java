
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bots.FSMBot;
import org.firstinspires.ftc.teamcode.bots.LEDBot;

@TeleOp(name = "Manual Drive")
public class ManualDriveOpMode extends LinearOpMode {

    private LEDBot robot = new LEDBot(this);
    //int count = 0;

    @Override
    public void runOpMode() {
        robot.isAutonomous = false;
        robot.init(hardwareMap);
        robot.isAutoStart = false;
        robot.odometryRaise.setPosition(0.65);
        robot.goToIntakePosition(3);
        waitForStart();
        while (opModeIsActive()) {
// snarm code
            robot.controlExtension(gamepad1.left_trigger, gamepad1.right_trigger);
            robot.controlElevation(gamepad2.cross, gamepad2.triangle);
            robot.controlRotation(gamepad1.dpad_right, gamepad1.dpad_left);

            robot.autoExtend(gamepad1.left_bumper);
            robot.autoRetract(gamepad1.right_bumper);
            robot.dropFreight(gamepad1.a);
            robot.toggleBox(gamepad1.x);
// intake code
            robot.changeIntakePosition(gamepad1.dpad_down, gamepad1.dpad_up);
            robot.intakeToggle(gamepad1.y);
// driving code
            robot.driveByHand(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_button, gamepad2.left_stick_x, gamepad2.left_stick_y, gamepad2.right_stick_x, gamepad2.left_stick_button);
            robot.holdSpinner(gamepad1.b, gamepad1.y, 0.4);
// tape arm code
            robot.controlElevationTape(gamepad2.dpad_up, gamepad2.dpad_down);
            robot.controlRotation(gamepad2.dpad_right, gamepad2.dpad_left);
//            robot.controlSwing(gamepad2.dpad_left, gamepad2.dpad_right);
            robot.controlCoreHex(gamepad2.right_trigger, gamepad2.left_trigger);
// tape arm end
            robot.onLoop(15, "manual drive");
        }
        robot.close();
    }
}