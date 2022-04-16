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
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;

public class FSMBot extends NewDistanceSensorBot {

    ElapsedTime snarmTimer = new ElapsedTime();
    ElapsedTime timeSince1 = new ElapsedTime(500);
    ElapsedTime timeSince2 = new ElapsedTime(500);
    ElapsedTime timeSince3 = new ElapsedTime(500);
    ElapsedTime timeSince4 = new ElapsedTime(500);
    ElapsedTime timeSince5 = new ElapsedTime(500);
    ElapsedTime timeSince6 = new ElapsedTime(500);

    public boolean isAutoStart = false;
    public boolean isAutonomous = true;
    public boolean shouldAutoExtend = true;
    public boolean drivingDone = false;
    public boolean shouldIdle = false;
    public boolean breakOutOfIdle = false;
    public boolean isAllianceHub = true;

    public int snarmSnarmState = 0;

    protected double dropHeight = 0;
    protected double snarmRotation = rotationCenter;
    protected double retractionStep = 2600;
    protected double extensionStep1 = 200;
    protected double extensionStep2 = 1000;
    protected double snarmIntakingHeight = 0.1;

    public FSMBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        snarmTimer.reset();
        drivingDone = false;
        if (!isAutonomous) {
            snarmState = SnarmState.RETRACTING_STAGE_1;
        }
    }

    public void setDropHeight(int pos) {
        switch (pos) {
            case 0:
                dropHeight = 0.3;
                maxExtension = 3100;
                extensionStep1 = 200;
                extensionStep2 = 1000;
                retractionStep = 2600;
                break;
            case 1:
                dropHeight = 0.49;
                maxExtension = 3100;
                extensionStep1 = 200;
                extensionStep2 = 1000;
                retractionStep = 2600;
                break;
            case 2:
                dropHeight = 0.68;
                maxExtension = 2900;
                extensionStep1 = 200;
                extensionStep2 = 1000;
                retractionStep = 2600;
                break;
            case 3:
                dropHeight = 0.75;
                maxExtension = 800;
                extensionStep1 = 200;
                extensionStep2 = 500;
                retractionStep = 300;
                break;
        }
    }

    public void setSnarmRotation(int side) {
        switch (side) {
            case 0:
                snarmRotation = 0.65;
                break;
            case 1:
                snarmRotation = 0.32;
                break;
            case 2:
                snarmRotation = 0.69;
                break;
            case 3:
                snarmRotation = 0.28;
                break;
        }
    }

    public void toggleHub(boolean blue, boolean button) {
        if (button && blue && timeSince5.milliseconds() > 500) {
            if (isAllianceHub) {
                setSnarmRotation(3);
                setDropHeight(3);
                isAllianceHub = false;
                snarmSnarmState = 2;
                opMode.telemetry.addData("switched to shared", true);
            } else {
                setSnarmRotation(0);
                setDropHeight(0);
                isAllianceHub = true;
                snarmSnarmState = 1;
                opMode.telemetry.addData("switched to alliance", true);
            }
            opMode.telemetry.update();
            timeSince5.reset();
        } else if (button && timeSince5.milliseconds() > 500) {
            if (isAllianceHub) {
                setSnarmRotation(2);
                setDropHeight(3);
                isAllianceHub = false;
                snarmSnarmState = 2;
                opMode.telemetry.addData("switched to shared", true);
            } else {
                setSnarmRotation(1);
                setDropHeight(0);
                isAllianceHub = true;
                snarmSnarmState = 1;
                opMode.telemetry.addData("switched to alliance", true);
            }
            opMode.telemetry.update();
            timeSince5.reset();
        }
    }

    public void autoExtend(boolean button) {
        if (button && timeSince1.milliseconds() > 500) {
            snarmState = SnarmState.READY;
            isAutoStart = true;
            shouldAutoExtend = true;
            timeSince1.reset();
        } else {
            shouldAutoExtend = false;
        }
    }

    public void autoRetract(boolean button) {
        if (button && timeSince2.milliseconds() > 500) {
            setExtension(minExtension);
            snarmState = SnarmState.RETRACTING_STAGE_1;
            timeSince2.reset();
        }
    }

    public void dropFreight(boolean button) {
        if (button && timeSince3.milliseconds() > 500) {
            snarmState = SnarmState.EXTENDING_STAGE_3;
            //extender.setTargetPosition(extender.getCurrentPosition());
            isAutonomous = true;
            timeSince3.reset();
        } else {
            isAutonomous = false;
        }
    }

    public void prepareTape(boolean button) {
        if (button && timeSince5.milliseconds() > 500) {
            snarmState = SnarmState.IDLE;

            timeSince5.reset();
        }
    }

    public void toggleBox(boolean button) {
        if (button && timeSince4.milliseconds() > 500) {
            if (box.getPosition() == boxOpened) {
                box.setPosition(boxLocked);
                timeSince4.reset();
            } else if (box.getPosition() == boxLocked || box.getPosition() == boxInit){
                box.setPosition(boxOpened);
                timeSince4.reset();
            }
        }
    }

    protected void onTick() {
        switch (snarmSnarmState) {
            case 0:
                switch (snarmState) {
                    case READY:
                        if (isAutoStart) {
                            RobotLog.d("ready");

                            setElevationPosition(elevationInit);
                            setRotationPosition(rotationInit);
                            setExtension(maxExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(0);

                            stopRotation();
                            goToIntakePosition(3);

                            isAutoStart = false;

                            snarmState = SnarmState.EXTENDING_STAGE_1;
                        }
                        break;
                    case EXTENDING_STAGE_1:
                        if (extender.getCurrentPosition() > extensionStep1) {
                            RobotLog.d("200 passed");

                            setElevationPosition(dropHeight);
                            setRotationPosition(snarmRotation);
                            setExtension(maxExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(0);

                            stopRotation();
                            //goToIntakePosition(3);

                            snarmState = SnarmState.EXTENDING_STAGE_2;
                        }
                        break;
                    case EXTENDING_STAGE_2:
                        if (extender.getCurrentPosition() > extensionStep2) {
                            RobotLog.d("1000 passed");

                            setElevationPosition(dropHeight);
                            setRotationPosition(snarmRotation);
                            setExtension(maxExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(3);

                            stopRotation();
                            goToIntakePosition(5);

                            snarmState = SnarmState.EXTENDING_STAGE_3;
                        }
                        break;
                    case EXTENDING_STAGE_3:
                        if (extender.getCurrentPosition() > maxExtension - 100) {
                            RobotLog.d("max passed");

                            setElevationPosition(dropHeight);
                            setRotationPosition(snarmRotation);
                            setExtension(maxExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(3);

                            stopRotation();
                            //goToIntakePosition(3);

                            snarmTimer.reset();

                            snarmState = SnarmState.RELEASING;
                        }
                        break;
                    case RELEASING:
                        if (snarmTimer.milliseconds() >= 100) {
                            RobotLog.d("retraction started");

                            setElevationPosition(dropHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(0);

                            stopRotation();
                            goToIntakePosition(3);

                            snarmState = SnarmState.RETRACTING_STAGE_1;
                        }
                        break;
                    case RETRACTING_STAGE_1:
                        if (extender.getCurrentPosition() < 2600) {
                            RobotLog.d("2600 passed");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(0);

                            startRotation();
                            goToIntakePosition(3);

                            snarmState = SnarmState.INTAKING;
                        }
                        break;
                    case INTAKING:
                        if (drivingDone && (extender.getCurrentPosition() < minExtension + 100)) {
                            RobotLog.d("intaking started");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(0);

                            startRotation();
                            goToIntakePosition(4);

                            drivingDone = false;
                            intakeFast = false;

                            snarmTimer.reset();

                            snarmState = SnarmState.RAISING_INTAKE;
                        }
                        break;
                    case RAISING_INTAKE:
                        if ((distanceIntake < 7 || distanceIntake > 100 || snarmTimer.milliseconds() > 5000) && (intakePosIndex == 3 || intakePosIndex == 4) && extender.getCurrentPosition() < minExtension + 100) {
                            RobotLog.d("intake raised");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(0);

                            stopRotation();
                            goToIntakePosition(2);

                            drivingDone = false;
                            intakeFast = false;

                            leftFront.setPower(0);
                            rightFront.setPower(0);
                            leftRear.setPower(0);
                            rightRear.setPower(0);

                            snarmTimer.reset();

                            snarmState = SnarmState.FEEDING;
                        }
                        break;
                    case FEEDING:
                        if (snarmTimer.milliseconds() >= 500) {
                            RobotLog.d("feeding started");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(0);

                            intakeFast = true;
                            startRotation();
                            //goToIntakePosition(2);

                            snarmTimer.reset();

                            snarmState = SnarmState.READY_AGAIN;
                        }
                        break;
                    case READY_AGAIN:
                        if (distanceBox < 8 || snarmTimer.milliseconds() > 1300) {
                            RobotLog.d("ready again");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(0);

                            intakeFast = true;
                            stopRotation();
                            goToIntakePosition(3);

                            isAutoStart = false;

                            snarmState = SnarmState.AFTER_READY_AGAIN;
                        }
                        break;
                    case AFTER_READY_AGAIN:
                        if (snarmTimer.milliseconds() > 150 && keepExtending) {
                            RobotLog.d("after ready again");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(maxExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(0);

                            intakeFast = false;
                            stopRotation();
                            goToIntakePosition(3);

                            isAutoStart = false;

                            snarmState = SnarmState.EXTENDING_STAGE_1;
                        }
                        break;
                    case IDLE:
                        if (!shouldIdle) {
                            RobotLog.d("idle");

                            setElevationPosition(0.2);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(4);

                            intakeFast = false;
                            stopRotation();
                            goToIntakePosition(0);

                            isAutoStart = false;

                            snarmState = SnarmState.READY;
                        }
                        break;
                    case IDLE_WAIT:
                        if (breakOutOfIdle) {
                            RobotLog.d("idle_wait");

                            setElevationPosition(0.1);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(0);

                            intakeFast = false;
                            stopRotation();
                            goToIntakePosition(3);

                            isAutoStart = false;

                            snarmState = SnarmState.EXTENDING_STAGE_1;
                        }
                        break;
                }
                break;
            case 1:
                switch (snarmState) {
                    case READY:
                        if (isAutoStart) {
                            RobotLog.d("ready");

                            setElevationPosition(elevationInit);
                            setRotationPosition(rotationInit);
                            setExtension(maxExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(0);

                            stopRotation();
                            goToIntakePosition(3);

                            isAutoStart = false;

                            snarmState = SnarmState.EXTENDING_STAGE_1;
                        }
                        break;
                    case EXTENDING_STAGE_1:
                        if (extender.getCurrentPosition() > extensionStep1) {
                            RobotLog.d("200 passed");

                            setElevationPosition(dropHeight);
                            setRotationPosition(snarmRotation);
                            setExtension(maxExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(0);

                            stopRotation();
                            //goToIntakePosition(3);

                            snarmState = SnarmState.EXTENDING_STAGE_2;
                        }
                        break;
                    case EXTENDING_STAGE_2:
                        if (extender.getCurrentPosition() > extensionStep2) {
                            RobotLog.d("1000 passed");

                            setElevationPosition(dropHeight);
                            setRotationPosition(snarmRotation);
                            setExtension(maxExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(3);

                            stopRotation();
                            goToIntakePosition(5);

                            snarmState = SnarmState.EXTENDING_STAGE_3;
                        }
                        break;
                    case EXTENDING_STAGE_3:
                        if (isAutonomous) {
                            RobotLog.d("max passed");

                            setElevationPosition(dropHeight);
                            setRotationPosition(snarmRotation);
                            setExtension(maxExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(5);

                            stopRotation();
                            //goToIntakePosition(3);

                            snarmTimer.reset();

                            snarmState = SnarmState.RELEASING;
                        }
                        break;
                    case RELEASING:
                        if (snarmTimer.milliseconds() >= 500) {
                            RobotLog.d("retraction started");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(3);

                            stopRotation();
                            goToIntakePosition(3);

                            snarmState = SnarmState.RETRACTING_STAGE_1;
                        }
                        break;
                    case RETRACTING_STAGE_1:
                        if (extender.getCurrentPosition() < retractionStep) {
                            RobotLog.d("2600 passed");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(3);

                            stopRotation();
                            //goToIntakePosition(3);

                            snarmState = SnarmState.INTAKING;
                        }
                        break;
                    case INTAKING:
                        if ((extender.getCurrentPosition() < minExtension + 100)) {
                            RobotLog.d("intaking started");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(0);

                            startRotation();
                            goToIntakePosition(4);

                            drivingDone = false;
                            intakeFast = false;

                            snarmState = SnarmState.RAISING_INTAKE;
                        }
                        break;
                    case RAISING_INTAKE:
                        if (distanceIntake < 5 && (intakePosIndex == 3 || intakePosIndex == 4) && extender.getCurrentPosition() < minExtension + 100) {
                            RobotLog.d("intake raised");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(0);

                            stopRotation();
                            goToIntakePosition(2);

                            drivingDone = false;
                            intakeFast = false;

                            leftFront.setPower(0);
                            rightFront.setPower(0);
                            leftRear.setPower(0);
                            rightRear.setPower(0);

                            snarmTimer.reset();

                            snarmState = SnarmState.FEEDING;
                        }
                        break;
                    case FEEDING:
                        if (snarmTimer.milliseconds() >= 500) {
                            RobotLog.d("feeding started");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(0);

                            intakeFast = true;
                            startRotation();
                            //goToIntakePosition(2);

                            snarmTimer.reset();

                            snarmState = SnarmState.READY_AGAIN;
                        }
                        break;
                    case READY_AGAIN:
                        if (distanceBox < 8 || snarmTimer.milliseconds() > 1000) {
                            RobotLog.d("ready again");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(0);

                            intakeFast = true;
                            startRotation();
                            goToIntakePosition(3);

                            isAutoStart = false;

                            snarmTimer.reset();

                            snarmState = SnarmState.AFTER_READY_AGAIN;
                        }
                        break;
                    case AFTER_READY_AGAIN:
                        if (snarmTimer.milliseconds() > 150) {
                            RobotLog.d("after ready again");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(0);

                            intakeFast = false;
                            stopRotation();
                            goToIntakePosition(3);

                            isAutoStart = false;

                            snarmTimer.reset();

                            snarmState = SnarmState.READY;
                        }
                        break;
                    case IDLE:
                        if (!shouldIdle) {
                            RobotLog.d("idle");

                            setElevationPosition(0.2);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(5);

                            intakeFast = false;
                            stopRotation();
                            goToIntakePosition(3);

                            isAutoStart = false;

                            snarmState = SnarmState.READY;
                        }
                        break;
                }
            case 2:
                switch (snarmState) {
                    case READY:
                        if (isAutoStart) {
                            RobotLog.d("ready");

                            setElevationPosition(elevationInit);
                            setRotationPosition(rotationInit);
                            setExtension(maxExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(0);

                            stopRotation();
                            goToIntakePosition(3);

                            isAutoStart = false;

                            snarmState = SnarmState.EXTENDING_STAGE_1;
                        }
                        break;
                    case EXTENDING_STAGE_1:
                        if (extender.getCurrentPosition() > extensionStep1) {
                            RobotLog.d("200 passed");

                            setElevationPosition(dropHeight);
                            setRotationPosition(snarmRotation);
                            setExtension(maxExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(0);

                            stopRotation();
                            //goToIntakePosition(3);

                            snarmState = SnarmState.EXTENDING_STAGE_2;
                        }
                        break;
                    case EXTENDING_STAGE_2:
                        if (extender.getCurrentPosition() > extensionStep2) {
                            RobotLog.d("1000 passed");

                            setElevationPosition(dropHeight);
                            setRotationPosition(snarmRotation);
                            setExtension(maxExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(3);

                            stopRotation();
                            goToIntakePosition(5);

                            snarmState = SnarmState.EXTENDING_STAGE_3;
                        }
                        break;
                    case EXTENDING_STAGE_3:
                        if (isAutonomous) {
                            RobotLog.d("max passed");

                            setElevationPosition(dropHeight);
                            setRotationPosition(snarmRotation);
                            setExtension(maxExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(5);

                            stopRotation();
                            //goToIntakePosition(3);

                            snarmTimer.reset();

                            snarmState = SnarmState.RELEASING;
                        }
                        break;
                    case RELEASING:
                        if (snarmTimer.milliseconds() >= 500) {
                            RobotLog.d("retraction started");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(snarmRotation);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(3);

                            stopRotation();
                            goToIntakePosition(3);

                            snarmState = SnarmState.RETRACTING_STAGE_1;
                        }
                        break;
                    case RETRACTING_STAGE_1:
                        if (extender.getCurrentPosition() < retractionStep) {
                            RobotLog.d("2600 passed");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(3);

                            stopRotation();
                            //goToIntakePosition(3);

                            snarmState = SnarmState.INTAKING;
                        }
                        break;
                    case INTAKING:
                        if ((extender.getCurrentPosition() < minExtension + 100)) {
                            RobotLog.d("intaking started");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(0);

                            startRotation();
                            goToIntakePosition(4);

                            drivingDone = false;
                            intakeFast = false;

                            snarmState = SnarmState.RAISING_INTAKE;
                        }
                        break;
                    case RAISING_INTAKE:
                        if (distanceIntake < 5 && (intakePosIndex == 3 || intakePosIndex == 4) && extender.getCurrentPosition() < minExtension + 100) {
                            RobotLog.d("intake raised");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(0);

                            stopRotation();
                            goToIntakePosition(2);

                            drivingDone = false;
                            intakeFast = false;

                            leftFront.setPower(0);
                            rightFront.setPower(0);
                            leftRear.setPower(0);
                            rightRear.setPower(0);

                            snarmTimer.reset();

                            snarmState = SnarmState.FEEDING;
                        }
                        break;
                    case FEEDING:
                        if (snarmTimer.milliseconds() >= 500) {
                            RobotLog.d("feeding started");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(0);

                            intakeFast = true;
                            startRotation();
                            //goToIntakePosition(2);

                            snarmTimer.reset();

                            snarmState = SnarmState.READY_AGAIN;
                        }
                        break;
                    case READY_AGAIN:
                        if (distanceBox < 8 || snarmTimer.milliseconds() > 1000) {
                            RobotLog.d("ready again");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxOpened);
                            goToFlipperPosition(0);

                            intakeFast = true;
                            startRotation();
                            goToIntakePosition(3);

                            isAutoStart = false;

                            snarmTimer.reset();

                            snarmState = SnarmState.AFTER_READY_AGAIN;
                        }
                        break;
                    case AFTER_READY_AGAIN:
                        if (snarmTimer.milliseconds() > 150) {
                            RobotLog.d("after ready again");

                            setElevationPosition(snarmIntakingHeight);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(0);

                            intakeFast = false;
                            stopRotation();
                            goToIntakePosition(3);

                            isAutoStart = false;

                            snarmTimer.reset();

                            snarmState = SnarmState.READY;
                        }
                        break;
                    case IDLE:
                        if (!shouldIdle) {
                            RobotLog.d("idle");

                            setElevationPosition(0.2);
                            setRotationPosition(rotationCenter);
                            setExtension(minExtension);

                            box.setPosition(boxLocked);
                            goToFlipperPosition(5);

                            intakeFast = false;
                            stopRotation();
                            goToIntakePosition(3);

                            isAutoStart = false;

                            snarmState = SnarmState.READY;
                        }
                        break;
                }
        }
        super.onTick();
    }
}
