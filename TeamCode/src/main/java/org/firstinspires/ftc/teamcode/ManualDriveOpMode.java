
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bots.DistanceSensorBot;
import org.firstinspires.ftc.teamcode.bots.DuckBot;
import org.firstinspires.ftc.teamcode.bots.EndgameBot;
import org.firstinspires.ftc.teamcode.bots.FSMBot;
import org.firstinspires.ftc.teamcode.bots.FourWheelDriveBot;
import org.firstinspires.ftc.teamcode.bots.NewDistanceSensorBot;
import org.firstinspires.ftc.teamcode.bots.NewIntakeBot;
import org.firstinspires.ftc.teamcode.bots.NoodleSpinnerBot;
import org.firstinspires.ftc.teamcode.bots.ScoopArmBot;
import org.firstinspires.ftc.teamcode.bots.SlideBot;
import org.firstinspires.ftc.teamcode.bots.SnarmBot;
import org.firstinspires.ftc.teamcode.bots.TapeMeasureBot;
import org.firstinspires.ftc.teamcode.bots.TouchBot;
import org.firstinspires.ftc.teamcode.bots.WobbleGoalBot;

@TeleOp(name = "Manual Drive")
public class ManualDriveOpMode extends LinearOpMode {

    private FSMBot robot = new FSMBot(this);
    //int count = 0;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.isAutoStart = false;
        waitForStart();
        while (opModeIsActive()) {
// snarm code
            robot.controlExtension(gamepad1.left_trigger, gamepad1.right_trigger);
            robot.controlElevation(gamepad1.dpad_down, gamepad1.dpad_up);
            robot.controlRotation(gamepad1.dpad_left, gamepad1.dpad_right);

            robot.autoExtend(gamepad1.left_bumper);
            robot.autoRetract(gamepad1.right_bumper);
// intake code
            robot.changeIntakePosition(gamepad2.dpad_up, gamepad2.dpad_down);
            robot.intakeToggle(gamepad1.a);
// driving code
            robot.driveByHand(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_button);
            robot.holdSpinner(gamepad2.b, gamepad2.y, 0.4);
// tape arm code
            robot.controlElevation(gamepad2.dpad_up, gamepad2.dpad_down);
            robot.controlSwing(gamepad2.dpad_left, gamepad2.dpad_right);
            robot.controlCoreHex(gamepad2.left_trigger, gamepad2.right_trigger);
// tape arm end
            robot.onLoop(15, "manual drive");
        }
        robot.close();
    }
}