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
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class NewDistanceSensorBot extends NewIntakeBot {

    protected DistanceSensor distSensorIntake = null;
    protected DistanceSensor distSensorBox = null;

    public double distanceIntake = 0;
    public double distanceBox = 0;

    public boolean isRepeating = false;

    boolean shouldGrabDrive = false;
    private boolean grabDriveForward = true;

    private long lastToggleDone6 = 0;
    private long timeSinceToggle6 = 0;

    public NewDistanceSensorBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        distSensorIntake = hwMap.get(DistanceSensor.class, "distanceSensorIntake");
        distSensorBox = hwMap.get(DistanceSensor.class, "distanceSensorBox");
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

    public void checkFreightInIntake() {
        if (distanceIntake < 5 && intakePosIndex == 3) {
            stopRotation();
            goToIntakePosition(1);
            sleep(500);
            startRotation();
            sleep(400);
            stopRotation();
            goToIntakePosition(3);
            box.setPosition(boxLocked);
            setRotationPosition(0.4);
            setElevationPosition(0.6);
            setExtension(maxExtension);
        }
    }

    public void checkFreightInBox() {
        if (distanceBox < 5) {

        }
    }

    protected void onTick() {
        getDistanceIntake();
        checkFreightInIntake();
//        opMode.telemetry.addData("distance: ", distanceIntake);
//        opMode.telemetry.update();
        super.onTick();
    }
}
