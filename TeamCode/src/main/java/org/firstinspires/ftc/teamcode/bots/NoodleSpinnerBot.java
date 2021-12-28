package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class NoodleSpinnerBot extends FourWheelDriveBot{
    public CRServo intake = null;
    public Servo inOut = null;

    final double retracted = 0;
    final double extended = 1;

    int inOutPosIndex = 0;

    boolean isSpinning = false;

    long lastToggleDone = 0;
    long timeSinceToggle = 0;
    long lastToggleDone1 = 0;
    long timeSinceToggle1 = 0;

    public NoodleSpinnerBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);

        intake = hwMap.get(CRServo.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        inOut = hwMap.get(Servo.class, "inOut");
        inOut.setPosition(retracted);
    }

    public void intakeToggle(boolean button) {
        timeSinceToggle = System.currentTimeMillis() - lastToggleDone;
        if (button && timeSinceToggle > 200) {
            if (isSpinning) {
                intake.setPower(0);
                isSpinning = false;
                lastToggleDone = System.currentTimeMillis();
            } else {
                intake.setPower(1);
                isSpinning = true;
                lastToggleDone = System.currentTimeMillis();
            }
        }
    }

    public void controlExtension(boolean toggle) {
        timeSinceToggle1 = System.currentTimeMillis() - lastToggleDone1;
        if (toggle && timeSinceToggle1 > 200) {
            if (inOutPosIndex == 0) {
                inOut.setPosition(extended);
                inOutPosIndex = 1;
            } else if (inOutPosIndex == 1) {
                inOut.setPosition(retracted);
                inOutPosIndex = 0;
            }
            lastToggleDone1 = System.currentTimeMillis();
        }
    }

}
