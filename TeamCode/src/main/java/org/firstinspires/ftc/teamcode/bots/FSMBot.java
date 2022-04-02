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
import com.qualcomm.robotcore.util.RobotLog;

public class FSMBot extends NewDistanceSensorBot {

    public enum SnarmState {
        READY,
        EXTENDING_STAGE_1,
        EXTENDING_STAGE_2,
        EXTENDING_STAGE_3,
        RELEASING,
        INTAKING,
        RAISING_INTAKE,
        FEEDING
    }

    protected boolean isAutoStart = false;

    SnarmState snarmState = SnarmState.READY;

    public FSMBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
    }

    protected void onTick() {
        switch (snarmState) {
            case READY:
                goToIntakePosition(3);
                if (isAutoStart) {
                    setExtension(maxExtension);
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
                    snarmState = SnarmState.RELEASING;
                }
                break;
        }
        super.onTick();
    }
}
