package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class NoodleSpinnerBot extends GyroBot{
    public CRServo intake = null;
    public Servo inOut = null;

    final double retracted = 0;
    final double extended = 1;

    final double[] inOutPositions = new double[]{0, 1};
    public int inOutPosIndex = 0;

    boolean isIntakeSpinning = false;

    long lastToggleDone9 = 0;
    long timeSinceToggle9 = 0;
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
        inOut.setPosition(inOutPositions[inOutPosIndex]);
    }

    public void intakeToggle(boolean button) {
        timeSinceToggle9 = System.currentTimeMillis() - lastToggleDone9;
        if (button && timeSinceToggle9 > 300) {
            if (isIntakeSpinning) {
                isIntakeSpinning = false;
                lastToggleDone9 = System.currentTimeMillis();
            } else {
                isIntakeSpinning = true;
                lastToggleDone9 = System.currentTimeMillis();
            }
        }
    }
    protected void updateIntake() {
        if (isIntakeSpinning) {
            intake.setPower(1);
        } else {
            intake.setPower(0);
        }
    }

    public void controlExtension(boolean toggle) {
        timeSinceToggle1 = System.currentTimeMillis() - lastToggleDone1;
        if (toggle && timeSinceToggle1 > 200) {
            if (inOutPosIndex == 0) {
                inOutPosIndex = 1;
                inOut.setPosition(inOutPositions[inOutPosIndex]);
            } else if (inOutPosIndex == 1) {
                inOutPosIndex = 0;
                inOut.setPosition(inOutPositions[inOutPosIndex]);
            }
            lastToggleDone1 = System.currentTimeMillis();
        }
    }
    protected void onTick() {
        updateIntake();
        super.onTick();
    }
}
