package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

public class SnarmBot extends BotBot {
    public Servo box = null;
    public Servo flipper = null;
    public Servo rotation = null;
    public Servo elevation = null;
    public DcMotor extender = null;

    final public int maxExtension = 2850;
    final public int minExtension = 0;

    final public double boxInit = 0.42;
    final public double boxLocked = 0.45;
    final public double boxOpened = 0.65;

    final public double rotationCenter = 0.475;

    public final double[] flipperPositions = new double[]{0, 0.05, 0.6, 0.65, 0.7};
    public int flipperPosIndex = 0;

    public SnarmBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        box = hwMap.get(Servo.class, "box");
        box.setPosition(boxInit);
        flipper = hwMap.get(Servo.class, "flipper");
        goToFlipperPosition(4);
        rotation = hwMap.get(Servo.class, "rotation");
        rotation.setPosition(rotationCenter);
        elevation = hwMap.get(Servo.class, "elevation");
        elevation.setPosition(0.4);
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
        extender.setPower(1);
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
        while (Math.abs(targetExtension - extender.getCurrentPosition()) > 100 && opMode.opModeIsActive()) {
            //RobotLog.d(String.format("target: %d  current: %d", targetExtension, extender.getCurrentPosition()));
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
