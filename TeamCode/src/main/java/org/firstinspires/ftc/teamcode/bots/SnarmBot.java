package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class SnarmBot extends BotBot {
    public Servo flipper = null;
    public Servo rotation = null;
    public Servo elevation = null;
    public DcMotor extender = null;

    final private int maxExtension = 1500;
    final private int minExtension = 0;

    public SnarmBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
//        flipper = hwMap.get(Servo.class, "flipper");
//        flipper.setPosition(0.1);
        rotation = hwMap.get(Servo.class, "rotation");
        rotation.setPosition(0.5);
        elevation = hwMap.get(Servo.class, "elevation");
        elevation.setPosition(0.1);
        extender = hwMap.get(DcMotor.class, "extender");
        extender.setPower(0);
        extender.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extender.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setExtension(int position) {
        extender.setPower(0.5);
        extender.setTargetPosition(position);
        extender.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void controlExtension(float down, float up) {
        if (up > 0 && extender.getCurrentPosition() <= maxExtension){
            extender.setTargetPosition((int) (extender.getCurrentPosition()+up*150));
            extender.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            extender.setPower(0.5);
        } else if (down > 0 && extender.getCurrentPosition() >= minExtension){
            extender.setTargetPosition((int) (extender.getCurrentPosition()-down*150));
            extender.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            extender.setPower(0.5);
        }
        if (extender.getCurrentPosition() < minExtension) {
            setExtension(minExtension);
        }
        if (extender.getCurrentPosition() > maxExtension) {
            setExtension(maxExtension);
        }
    }

    public void setRotationPosition(double position){
        rotation.setPosition(position);
    }

    public void controlRotation(boolean left, boolean right) {
        if (left) {
            rotation.setPosition(rotation.getPosition()-0.005);
        } else if (right) {
            rotation.setPosition(rotation.getPosition()+0.005);
        }
    }

    public void setElevationPosition(double position) {
        elevation.setPosition(position);
    }

    public void controlElevation(boolean down, boolean up) {
        if (down) {
            elevation.setPosition(elevation.getPosition()-0.03);
        } else if (up) {
            elevation.setPosition(elevation.getPosition()+0.03);
        }
    }

    protected void onTick() {
        opMode.telemetry.addData("rotation: ", rotation.getPosition());
        opMode.telemetry.addData("elevation: ", elevation.getPosition());
        opMode.telemetry.addData("extension: ", extender.getCurrentPosition());
        opMode.telemetry.update();
        super.onTick();
    }
}
