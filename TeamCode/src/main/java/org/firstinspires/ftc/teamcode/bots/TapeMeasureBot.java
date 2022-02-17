package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TapeMeasureBot extends DistanceSensorBot{
    public DcMotor coreHex = null;
    public CRServo tapeSwing = null;
    public Servo tapeElevate = null;

    public TapeMeasureBot(LinearOpMode opMode) {
        super(opMode);
    }

    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        coreHex = hwMap.get(DcMotor.class, "coreHex");
        coreHex.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        coreHex.setDirection(DcMotorSimple.Direction.FORWARD);
        tapeSwing = hwMap.get(CRServo.class, "tapeSwing");
        tapeSwing.setDirection(DcMotorSimple.Direction.FORWARD);
        tapeElevate = hwMap.get(Servo.class, "tapeElevate");
        tapeElevate.setPosition(0.5);
    }

    public void controlSwing(boolean leftPower, boolean rightPower) {
        if (leftPower) {
            //tapeSwing.setDirection(DcMotorSimple.Direction.FORWARD);
            tapeSwing.setPower(1);
        } else if (rightPower) {
            //tapeSwing.setDirection(DcMotorSimple.Direction.REVERSE);
            tapeSwing.setPower(-1);
        } else {
            tapeSwing.setPower(0);
        }
    }

    public void setElevation(double input) {
        tapeElevate.setPosition(input);
    }

    public void controlCoreHex(float extend, float retract) {
        if (extend > 0){
            coreHex.setPower(-extend);
        } else if (retract > 0){
            coreHex.setPower(retract);
        } else {
            coreHex.setPower(0);
        }
    }
}
