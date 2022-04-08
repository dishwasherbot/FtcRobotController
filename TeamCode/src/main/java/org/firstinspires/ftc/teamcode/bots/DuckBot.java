
package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.RobotLog;
import com.stormbots.MiniPID;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;



public class DuckBot extends TapeMeasureBot{
    public DcMotor duckSpinner = null;

    boolean isSpinning = false;

    public double currentSpinnerSpeed = 1;

    //change these values to control what speed the spinner spins around
    public double setSpinnerSpeed = 0.4; //385

    public DuckBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        duckSpinner = hwMap.get(DcMotor.class, "duckArm");
        duckSpinner.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void toggleSpinner(double power, boolean isForward) {
        setSpinnerSpeed = power;
        if (isForward) {
            duckSpinner.setDirection(DcMotorSimple.Direction.FORWARD);
        } else {
            duckSpinner.setDirection(DcMotorSimple.Direction.REVERSE);
        }
        if (!isSpinning) {
            isSpinning = true;
        } else {
            isSpinning = false;
        }
    }

    public void holdSpinner (boolean button, boolean switchDirection, double power) {
        setSpinnerSpeed = power;
        if (button) {
            isSpinning = true;
        } else {
            isSpinning = false;
        }
        if (duckSpinner.getDirection() == DcMotorSimple.Direction.FORWARD && switchDirection) {
            duckSpinner.setDirection(DcMotorSimple.Direction.REVERSE);
        } else if (duckSpinner.getDirection() == DcMotorSimple.Direction.REVERSE && switchDirection){
            duckSpinner.setDirection(DcMotorSimple.Direction.FORWARD);
        }
    }

    public void spinCarousel(boolean button) {
        if (button && isSpinning) {
            duckSpinner.setPower(-setSpinnerSpeed);
        } else {
            leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            duckSpinner.setPower(0);
        }
    }
    protected void onTick(){
        spinCarousel(true);
        super.onTick();
    }
}
