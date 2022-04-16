package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.GREEN;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.RED;
import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern.YELLOW;

public class LEDBot extends FSMBot{
    //public Servo LEDControl = null;
    public RevBlinkinLedDriver LED = null;

    final double defaultPattern = 0.2525; //rainbow, glitter

    public LEDBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);

        LED = hwMap.get(RevBlinkinLedDriver.class, "LED");
        LED.setPattern(RAINBOW_RAINBOW_PALETTE);

        //LEDControl.setPosition(defaultPattern);
    }

//    public void switchPattern(double input) {
//        LEDControl.setPosition(input);
//    }

    public void updateLED() {
        if (snarmState == SnarmState.FEEDING) {
            //LEDControl.setPosition(0.7145);
            LED.setPattern(YELLOW);
        } else if (snarmState == SnarmState.READY) {
            //LEDControl.setPosition(0.1);
            LED.setPattern(GREEN);
        } else {
            LED.setPattern(RED);
        }
    }

    protected void onTick(){
        updateLED();
        //opMode.telemetry.addData("Shooting Distance", shootingDistance);
        //opMode.telemetry.update();
        super.onTick();
    }
}
