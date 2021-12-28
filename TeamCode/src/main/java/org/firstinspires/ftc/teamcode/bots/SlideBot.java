package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

public class SlideBot extends FourWheelDriveBot{
    public DcMotor slide = null;

    public int slidePosition = 0;

    public SlideBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);

        slide = hwMap.get(DcMotor.class, "slide");
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void slideUp(boolean button) {
        if (button && slidePosition<23){
            slide.setPower(1);
            slidePosition++;
        } else {
            slide.setPower(0);
        }
    }
    public void slideDown(boolean button) {
        if (button && slidePosition>0){
            slide.setPower(-1);
            slidePosition--;
        } else {
            slide.setPower(0);
        }
    }
    public void slideControl(boolean down, boolean up) {
        if (up){
            slide.setPower(0.8);
        } else if (down){
            slide.setPower(-0.8);
        } else {
            slide.setPower(0);
        }
    }
}
