package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class SnarmBot extends BotBot {
    public Servo box = null;
    public Servo flipper = null;
    public Servo rotation = null;
    public Servo elevation = null;
    public DcMotor extender = null;

    final public int maxExtension = 1500;
    final public int minExtension = 0;

    final public double boxLocked = 0.6;
    final public double boxOpened = 0.5;

    public final double[] flipperPositions = new double[]{0.5, 0.6, 0.7, 1};
    public int flipperPosIndex = 0;

    public SnarmBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        box = hwMap.get(Servo.class, "box");
        box.setPosition(boxOpened);
        flipper = hwMap.get(Servo.class, "flipper");
        goToFlipperPosition(0);
        rotation = hwMap.get(Servo.class, "rotation");
        rotation.setPosition(0.5);
        elevation = hwMap.get(Servo.class, "elevation");
        elevation.setPosition(0.7);
        extender = hwMap.get(DcMotor.class, "extender");
        extender.setPower(0);
        extender.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extender.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void goToFlipperPosition(int index) {
        flipperPosIndex = index;
        flipper.setPosition(flipperPositions[flipperPosIndex]);
    }

    public void setExtension(int position) {
        extender.setPower(0.2);
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

    public void waitOnExtension(int targetExtension) {
        while (Math.abs(targetExtension - extender.getCurrentPosition()) < 100 && opMode.opModeIsActive()) {
            sleep(50, "waitOnExtension");
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
