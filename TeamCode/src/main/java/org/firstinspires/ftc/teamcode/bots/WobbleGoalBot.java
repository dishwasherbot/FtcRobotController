package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class WobbleGoalBot extends ShooterBot {
    public Servo wobblePinch = null;
    public DcMotor wobbleArm = null;

    //two positions of the wobble servo
    final double wobblePinched = 0.8;
    final double wobbleOpened = 0.44;

    final int[] armPositions = new int[]{50, 300, 700};
    int armPosIndex = 0;

    public boolean isOpen = true;
    long lastToggleDone = 0;
    long timeSinceToggle = 0;
    long lastPosSwitch = 0;
    long timeSincePosSwitch = 0;

    public WobbleGoalBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        wobblePinch = hwMap.get(Servo.class, "wobblePinch");
        wobblePinch.setPosition(wobbleOpened);
        wobbleArm = hwMap.get(DcMotor.class, "wobbleArm");
        wobbleArm.setPower(0);
        wobbleArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wobbleArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    //call openPinch() to open the arm
    public void openPinch() {
        wobblePinch.setPosition(wobblePinched);
    }

    //call closeArm() to close the arm
    public void closePinch() {
        wobblePinch.setPosition(wobbleOpened);
    }

    public void toggleWobble(boolean button) {
        timeSinceToggle = System.currentTimeMillis() - lastToggleDone;
        if (button && timeSinceToggle > 300) {
            if (isOpen) {
                wobblePinch.setPosition(wobblePinched);
                isOpen = false;
                lastToggleDone = System.currentTimeMillis();
            } else if (!isOpen) {
                wobblePinch.setPosition(wobbleOpened);
                isOpen = true;
                lastToggleDone = System.currentTimeMillis();
            }
        }
//        opMode.telemetry.addData("lastToggle", timeSinceToggle);
//        opMode.telemetry.update();
    }

    public void raiseArm() {
        wobbleArm.setPower(0.3);
        wobbleArm.setTargetPosition(500);
        sleep(1000, "wobble raise");
        wobbleArm.setPower(0);
    }
    public void lowerArm() {
        wobbleArm.setPower(0.1);
        wobbleArm.setTargetPosition(200);
        sleep(500, "wobble lower");
        wobbleArm.setPower(0);
    }

    public void controlWobbleArm(boolean buttonY, boolean buttonB) {
        timeSincePosSwitch = System.currentTimeMillis() - lastPosSwitch;
        if (buttonY && timeSincePosSwitch > 200) {
            if (armPosIndex < 2) {
                armPosIndex ++;
                wobbleArm.setTargetPosition(armPositions[armPosIndex]);
                wobbleArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                wobbleArm.setPower(0.4);
                lastPosSwitch = System.currentTimeMillis();
            }
        }
        if (buttonB && timeSincePosSwitch > 200) {
            if (armPosIndex > 0) {
                armPosIndex --;
                wobbleArm.setTargetPosition(armPositions[armPosIndex]);
                wobbleArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                wobbleArm.setPower(0.4);
                lastPosSwitch = System.currentTimeMillis();
            }
        }
//        if (timeSincePosSwitch > 100 && lastPosSwitch != 0) {
//            wobbleArm.setPower(0.4);
//        }
        //opMode.telemetry.addData("armPosIndex", armPosIndex);
        //opMode.telemetry.update();
    }

    public void setArmPosition(int position) {
        wobbleArm.setPower(0.2);
        wobbleArm.setTargetPosition(position);
        wobbleArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (this.opMode.opModeIsActive() && wobbleArm.isBusy()) {
            sleep(50, "set wobble arm position");
        }
        wobbleArm.setPower(0.5);
    }

    public void setArmPositionNoWait(int position) {
        wobbleArm.setPower(0.4);
        wobbleArm.setTargetPosition(position);
        wobbleArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
