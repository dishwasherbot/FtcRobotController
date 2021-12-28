
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

    private WobbleGoalBot robot = new WobbleGoalBot(this);
    //int count = 0;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
//            robot.driveByHand(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_button);
//            robot.holdSpinner(gamepad1.a, gamepad1.b, 0.4);
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
//            robot.slideControl(gamepad1.dpad_up, gamepad1.dpad_down);
// slide arm end
            robot.onLoop(15, "manual drive");
        }
        robot.close();
    }
}