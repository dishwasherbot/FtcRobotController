package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class NoodleSpinnerBot extends FourWheelDriveBot{
    public DcMotor intake = null;

    boolean isSpinning = false;

    long lastToggleDone = 0;
    long timeSinceToggle = 0;

    public NoodleSpinnerBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);

        intake = hwMap.get(DcMotor.class, "leftFront");
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void intakeToggle(boolean button) {
        timeSinceToggle = System.currentTimeMillis() - lastToggleDone;
        if (button && timeSinceToggle > 200) {
            if (isSpinning) {
                intake.setPower(0);
                isSpinning = false;
                lastToggleDone = System.currentTimeMillis();
            } else {
                intake.setPower(-0.5);
                isSpinning = true;
                lastToggleDone = System.currentTimeMillis();
            }
        }
    }

}
