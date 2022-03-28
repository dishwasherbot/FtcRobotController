/*
Copyright (c) 2018 FIRST

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of FIRST nor the names of its contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class NewDistanceSensorBot extends TapeMeasureBot {

    protected DistanceSensor distSensorIntake = null;
    protected DistanceSensor distSensorBox = null;

    public double distanceIntake = 0;
    public double distanceBox = 0;

    public boolean isRepeating = false;

    boolean shouldGrabDrive = false;
    private boolean canDrive = false;

    private long lastToggleDone6 = 0;
    private long timeSinceToggle6 = 0;

    public NewDistanceSensorBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        distSensorIntake = hwMap.get(DistanceSensor.class, "distanceSensorIntake");
        //distSensorBox = hwMap.get(DistanceSensor.class, "distanceSensorBox");
    }

    public void getDistanceIntake() {
//        opMode.telemetry.addData("range", String.format("%.01f cm", distSensor.getDistance(DistanceUnit.CM)));

        // you can also cast this to a Rev2mDistanceSensor if you want to use added
        // methods associated with the Rev2mDistanceSensor class.
//        Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor) distSensor;
        // Rev2mDistanceSensor specific methods.
//        opMode.telemetry.addData("ID", String.format("%x", sensorTimeOfFlight.getModelID()));
//        opMode.telemetry.addData("did time out", Boolean.toString(sensorTimeOfFlight.didTimeoutOccur()));
//        opMode.telemetry.update();
        distanceIntake = distSensorIntake.getDistance(DistanceUnit.CM);
    }

    public void getDistanceBox() {
        distanceBox = distSensorBox.getDistance(DistanceUnit.CM);
    }

    protected void checkFreightInIntake() {
        if (distanceIntake < 5 && intakePosIndex == 3) {
            RobotLog.d("freight detected");
            stopRotation();
            goToIntakePosition(2);
            leftFront.setPower(0);
            rightFront.setPower(0);
            leftRear.setPower(0);
            rightRear.setPower(0);
            sleep(1000);
            intakeFast = true;
            startRotation();
            sleep(1000);
            stopRotation();
            sleep(500);
            goToIntakePosition(3);
            box.setPosition(boxLocked);
            RobotLog.d("can drive true");
            canDrive = true;
            setElevationPosition(0.5);
            setExtension(maxExtension);
            RobotLog.d("extension started");
            intakeFast = false;
            //sleep(500);
        }
    }

    protected void checkExtension200() {
        int index = 0;
        if (extender.getCurrentPosition() > 200 && extensionCheckpoints[index]) {
            RobotLog.d("200 passed");
            setElevationPosition(0.5);
            setRotationPosition(0.57);
            extensionCheckpoints[index] = false;
        }
    }

    protected void checkExtension1000() {
        int index = 1;
        if (extender.getCurrentPosition() > 1000 && extensionCheckpoints[index]) {
            RobotLog.d("1000 passed");
            goToFlipperPosition(3);
            extensionCheckpoints[index] = false;
        }
    }

    protected void checkExtensionMax() {
        int index = 2;
        if (extender.getCurrentPosition() > maxExtension-100 && extensionCheckpoints[index]) {
            RobotLog.d("max passed");
            box.setPosition(boxOpened);
            extensionCheckpoints[index] = false;
            RobotLog.d("max finished");
        }
    }

    protected void checkExtension2600() {
        int index = 3;
        if (extender.getCurrentPosition() < 2600 && extensionCheckpoints[index]) {
            RobotLog.d("2600 passed");
            box.setPosition(boxOpened);
            setElevationPosition(0.23);
            extensionCheckpoints[index] = false;
        }
    }

    public void autoGrabFreight(double power) {
        int startingPos = horizontal.getCurrentPosition();

        leftFront.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        leftRear.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        leftFront.setPower(power);
        rightFront.setPower(power);
        leftRear.setPower(power);
        rightRear.setPower(power);
        startRotation();
//        while (sensorDistance > 14 && opMode.opModeIsActive()) {
//            onLoop(50, "autoGrab");
//        }
//        leftFront.setPower(0);
//        rightFront.setPower(0);
//        leftRear.setPower(0);
//        rightRear.setPower(0);
//        inOutPosIndex = 0;
//        inOut.setPosition(inOutPositions[inOutPosIndex]);
        //int distanceTravelled = Math.abs(leftFront.getCurrentPosition() - LFStartingPos);
        long autoGrabStart = System.currentTimeMillis();
        long timeSinceAutoGrab = 0;
        while (!canDrive && timeSinceAutoGrab < 5000 && opMode.opModeIsActive() ){
            onLoop(50, "autoGrab 2");
            timeSinceAutoGrab = Math.abs(autoGrabStart - System.currentTimeMillis());
            opMode.telemetry.addData("time: ", timeSinceAutoGrab);
            opMode.telemetry.update();
            RobotLog.d("can drive waiting %d", timeSinceAutoGrab);
        }
        int distanceFromStart = Math.abs(horizontal.getCurrentPosition());
        driveByGyroWithEncodersVertical(DIRECTION_BACKWARD, distanceFromStart-7000, false, 500, 0);
        RobotLog.d("drive finished");
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);
    }

//    public void checkFreightInBox() {
//        if (distanceBox < 5) {
//
//        }
//    }

    protected void onTick() {
        getDistanceIntake();
        checkFreightInIntake();
        checkExtension200();
        checkExtension1000();
        checkExtensionMax();
        checkExtension2600();
//        opMode.telemetry.addData("distance: ", distanceIntake);
//        opMode.telemetry.update();
        super.onTick();
    }
}
