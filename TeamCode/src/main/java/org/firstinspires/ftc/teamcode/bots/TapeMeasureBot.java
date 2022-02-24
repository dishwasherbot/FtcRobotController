package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TapeMeasureBot extends DistanceSensorBot{
    public DcMotor coreHex = null;
    public Servo tapeSwing = null;
    public Servo tapeElevate = null;

    public TapeMeasureBot(LinearOpMode opMode) {
        super(opMode);
    }

    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        coreHex = hwMap.get(DcMotor.class, "coreHex");
        coreHex.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        coreHex.setDirection(DcMotorSimple.Direction.FORWARD);
        tapeSwing = hwMap.get(Servo.class, "tapeSwing");
        tapeSwing.setPosition(0.9);
        tapeElevate = hwMap.get(Servo.class, "tapeElevate");
        tapeElevate.setPosition(0.5);
    }

    public void setSwing(double input){
        tapeSwing.setPosition(input);
    }

    public void controlSwing(boolean left, boolean right) {
        if (left) {
            tapeSwing.setPosition(tapeSwing.getPosition()-0.003);
        } else if (right) {
            tapeSwing.setPosition(tapeSwing.getPosition()+0.003);
        }
    }

    public void setElevation(double input) {
        tapeElevate.setPosition(input);
    }

    public void controlElevation(boolean up, boolean down) {
        if (up) {
            tapeElevate.setPosition(tapeElevate.getPosition()+0.003);
        } else if (down) {
            tapeElevate.setPosition(tapeElevate.getPosition()-0.003);
        }
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

    protected void onTick() {
        opMode.telemetry.addData("swing: ", tapeSwing.getPosition());
        opMode.telemetry.addData("elevation: ", tapeElevate.getPosition());
        opMode.telemetry.update();
        super.onTick();
    }
}
