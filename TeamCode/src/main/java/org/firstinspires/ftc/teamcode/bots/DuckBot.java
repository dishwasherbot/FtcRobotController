
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



public class DuckBot extends GyroBot{
    public DcMotor duckSpinner = null;

    long currentTime = 0;
    long lastTime = 0;
    long timeDifference = 0;

    double currentPosition = 0;
    double lastPosition = 0;
    double positionDifference = 0;

    boolean isSpinning = false;

    public double currentSpinnerSpeed = 1;

    //change these values to control what speed the spinner spins around
    public double setSpinnerSpeed = 0.4; //385

    OutputStreamWriter spinnerWriter;

    MiniPID duckPID = new MiniPID(0.6, 0.2, 0.5);


    public DuckBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        duckSpinner = hwMap.get(DcMotor.class, "duckArm");
        duckSpinner.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        duckPID.setOutputLimits(0.2, 0.9);
        try {
            spinnerWriter = new FileWriter("/sdcard/FIRST/spinnerlog_" + java.text.DateFormat.getDateTimeInstance().format(new Date()) + ".csv", true);
        } catch (IOException e) {
            throw new RuntimeException("spinner log file writer open failed: " + e.toString());
        }
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
            // determine current speed:

            //calculate difference in time between last and current cycle
            currentTime = System.currentTimeMillis();
            timeDifference = currentTime - lastTime;
            //calculate difference in position between last and current cycle
            currentPosition = duckSpinner.getCurrentPosition();
            positionDifference = Math.abs(currentPosition - lastPosition);
            //calculate current spinner speed
            currentSpinnerSpeed = positionDifference / (double)timeDifference;

            // PID controller to adjust to set speed:
            double adjustSpeed = duckPID.getOutput(currentSpinnerSpeed, setSpinnerSpeed);
            duckSpinner.setPower(-adjustSpeed);

            //save current time and position for next cycle
            lastTime = currentTime;
            lastPosition = currentPosition;

            opMode.telemetry.addData("Spinner speed", currentSpinnerSpeed);
            opMode.telemetry.addData("Position Difference", positionDifference);
            opMode.telemetry.addData("Time Difference", (double)timeDifference);
            opMode.telemetry.addData("Current Position", currentPosition);
            opMode.telemetry.update();
            RobotLog.d(String.format("Spinner speed: %f, Adjust speed: %f, time difference: %f", currentSpinnerSpeed, adjustSpeed, (double)timeDifference));
            try {
                RobotLog.d("spinnerWriter.write");
                spinnerWriter.write(String.format("%d, %f, %f\n", currentTime, currentSpinnerSpeed, adjustSpeed));
                //spinnerWriter.write(String.format("%d, %f\n", currentTime, currentSpinnerSpeed));
            } catch (IOException e) {
                throw new RuntimeException("spinner log file writer write failed: " + e.toString());
            }
        } else {
            leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            duckSpinner.setPower(0);
        }
    }
    protected void onTick(){
        spinCarousel(true);
        super.onTick();
    }
    public void close(){
        try {
            RobotLog.d("spinner log Writer.close");
            spinnerWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("spinner log file writer close failed: " + e.toString());
        }
        super.close();
    }


}
