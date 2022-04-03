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
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;

public class FSMBot extends NewDistanceSensorBot {

    ElapsedTime snarmTimer = new ElapsedTime();

    public boolean isAutoStart = false;
    public boolean drivingDone = false;

    public FSMBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        drivingDone = false;
    }

    protected void onTick() {
        switch (snarmState) {
            case READY:
                if (isAutoStart || snarmTimer.milliseconds() >= 500) {
                    RobotLog.d("ready");
                    stopRotation();
                    goToIntakePosition(3);
                    setExtension(maxExtension);
                    isAutoStart = false;
                    snarmState = SnarmState.EXTENDING_STAGE_1;
                }
                break;
            case EXTENDING_STAGE_1:
                if (extender.getCurrentPosition() > 200) {
                    RobotLog.d("200 passed");
                    setElevationPosition(0.5);
                    setRotationPosition(0.65);
                    snarmState = SnarmState.EXTENDING_STAGE_2;
                }
                break;
            case EXTENDING_STAGE_2:
                if (extender.getCurrentPosition() > 1000) {
                    RobotLog.d("1000 passed");
                    goToFlipperPosition(3);
                    snarmState = SnarmState.EXTENDING_STAGE_3;
                }
                break;
            case EXTENDING_STAGE_3:
                if (extender.getCurrentPosition() > maxExtension-100) {
                    RobotLog.d("max passed");
                    box.setPosition(boxOpened);
                    snarmTimer.reset();
                    snarmState = SnarmState.RELEASING;
                }
                break;
            case RELEASING:
                if (snarmTimer.milliseconds() >= 100) {
                    RobotLog.d("retraction started");
                    setExtension(minExtension);
                    setRotationPosition(rotationCenter);
                    snarmState = SnarmState.RETRACTING_STAGE_1;
                }
                break;
            case RETRACTING_STAGE_1:
                if (extender.getCurrentPosition() < 2600) {
                    RobotLog.d("2600 passed");
                    box.setPosition(boxOpened);
                    setElevationPosition(0.2);//0.23
                    snarmState = SnarmState.INTAKING;
                }
                break;
            case INTAKING:
                if (drivingDone && (extender.getCurrentPosition() < minExtension+100)) {
                    RobotLog.d("intaking started");
                    drivingDone = false;
                    intakeFast = false;
                    goToIntakePosition(4);
                    startRotation();
                    snarmState = SnarmState.RAISING_INTAKE;
                }
                break;
            case RAISING_INTAKE:
                if (distanceIntake < 5 && (intakePosIndex == 3 || intakePosIndex == 4) && extender.getCurrentPosition() < minExtension+100) {
                    RobotLog.d("intake raised");
                    leftFront.setPower(0);
                    rightFront.setPower(0);
                    leftRear.setPower(0);
                    rightRear.setPower(0);
                    stopRotation();
                    goToIntakePosition(2);
                    snarmTimer.reset();
                    snarmState = SnarmState.FEEDING;
                }
                break;
            case FEEDING:
                if (snarmTimer.milliseconds() >= 500) {
                    RobotLog.d("feeding started");
                    intakeFast = true;
                    startRotation();
                    snarmTimer.reset();
                    snarmState = SnarmState.READY;
                }
                break;
        }
        super.onTick();
    }
}
