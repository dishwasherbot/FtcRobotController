
package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;
import com.stormbots.MiniPID;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import com.stormbots.MiniPID;


public class DuckBot extends FourWheelDriveBot{
    public DcMotor duckSpinner = null;

    long currentTime = 0;
    long lastTime = 0;
    long timeDifference = 0;

    double currentPosition = 0;
    double lastPosition = 0;
    double positionDifference = 0;

    public double currentSpinnerSpeed = 1;

    //change these values to control what speed the shooter spins around
    public double setSpinnerSpeed = 0.591;

    MiniPID shooterPID = new MiniPID(0.6, 0.2, 0.5);


    public DuckBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        duckSpinner = hwMap.get(DcMotor.class, "leftFront");
        duckSpinner.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooterPID.setOutputLimits(0.9);
    }


    public void spinCarousel(boolean button) {
        if (button) {

            // determine current speed:

            //calculate difference in time between last and current cycle
            currentTime = System.currentTimeMillis();
            timeDifference = currentTime - lastTime;
            //calculate difference in position between last and current cycle
            currentPosition = duckSpinner.getCurrentPosition();
            positionDifference = Math.abs(currentPosition - lastPosition);
            //calculate current shooter speed
            currentSpinnerSpeed = positionDifference / (double)timeDifference;

            // PID controller to adjust to set speed:
            double adjustSpeed = shooterPID.getOutput(currentSpinnerSpeed, setSpinnerSpeed);
            duckSpinner.setPower(adjustSpeed);

            //save current time and position for next cycle
            lastTime = currentTime;
            lastPosition = currentPosition;

            opMode.telemetry.addData("Shooter speed", currentSpinnerSpeed);
            opMode.telemetry.addData("Position Difference", positionDifference);
            opMode.telemetry.addData("Time Difference", (double)timeDifference);
            opMode.telemetry.addData("Current Position", currentPosition);
            opMode.telemetry.update();
        } else {
            leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            duckSpinner.setPower(0);
        }
    }

}
