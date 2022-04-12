package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

public class LEDBot extends FSMBot{
    public Servo LEDControl = null;

    final double defaultPattern = 0.2525; //rainbow, glitter

    public LEDBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);

        LEDControl = hwMap.servo.get("LED");

        LEDControl.setPosition(defaultPattern);
    }

    public void switchPattern(double input) {
        LEDControl.setPosition(input);
    }

    public void updateLED() {
        if (snarmState == SnarmState.FEEDING) {
            LEDControl.setPosition(0.7145);
        }
    }

//    protected void onTick(){
//        calculateShootingDistance();
//        updateLED();
//        RobotLog.d(String.format("Distance to tower: %.2f", shootingDistance));
//        //opMode.telemetry.addData("Shooting Distance", shootingDistance);
//        //opMode.telemetry.update();
//        super.onTick();
//    }
}
