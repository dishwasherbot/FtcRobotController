package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class NoodleSpinnerBot extends FourWheelDriveBot{
    public CRServo intake = null;

    boolean isSpinning = false;

    long lastToggleDone = 0;
    long timeSinceToggle = 0;

    public NoodleSpinnerBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);

        intake = hwMap.get(CRServo.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void intakeToggle(boolean button) {
        timeSinceToggle = System.currentTimeMillis() - lastToggleDone;
        if (button && timeSinceToggle > 200) {
            if (isSpinning) {
                intake.setDirection(DcMotorSimple.Direction.FORWARD);
                intake.setPower(1);
                isSpinning = false;
                lastToggleDone = System.currentTimeMillis();
            } else {
                intake.setDirection(DcMotorSimple.Direction.REVERSE);
                intake.setPower(1);
                isSpinning = true;
                lastToggleDone = System.currentTimeMillis();
            }
        }
    }

}
