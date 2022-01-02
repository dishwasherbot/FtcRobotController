
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bots.DuckBot;
import org.firstinspires.ftc.teamcode.bots.EndgameBot;
import org.firstinspires.ftc.teamcode.bots.FourWheelDriveBot;
import org.firstinspires.ftc.teamcode.bots.NoodleSpinnerBot;
import org.firstinspires.ftc.teamcode.bots.ScoopArmBot;
import org.firstinspires.ftc.teamcode.bots.SlideBot;
import org.firstinspires.ftc.teamcode.bots.TouchBot;
import org.firstinspires.ftc.teamcode.bots.WobbleGoalBot;

@TeleOp(name = "Manual Drive")
public class ManualDriveOpMode extends LinearOpMode {

    private TouchBot robot = new TouchBot(this);
    //int count = 0;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            robot.driveByHand(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_button);
            robot.holdSpinner(gamepad1.dpad_left, gamepad1.dpad_right, 0.4);
// wobble arm code
            robot.controlServo(gamepad1.dpad_up, gamepad1.dpad_down);
            robot.controlWobbleArm(gamepad1.y, gamepad1.b);
// wobble arm end
// intake code
            robot.intakeToggle(gamepad1.a);
            robot.controlExtension(gamepad1.x);
// intake end
// scoop arm code
//            robot.checkTouch();
//            robot.intakeToggle(gamepad1.a);
//            robot.controlScoop(gamepad1.dpad_up, gamepad1.dpad_down);
// scoop arm end
// slide arm code
//            robot.slideControl(gamepad1.left_trigger, gamepad1.right_trigger);
//            robot.slideToggle(gamepad1.a);
//            robot.toggleDropper(gamepad1.b);
// slide arm end
            robot.checkTouch();
            robot.onLoop(15, "manual drive");
        }
        robot.close();
    }
}