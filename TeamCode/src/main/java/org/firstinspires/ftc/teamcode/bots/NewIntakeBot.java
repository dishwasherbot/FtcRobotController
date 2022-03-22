package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class NewIntakeBot extends BotBot{
    public DcMotor intakeSpin = null;
    public Servo intakeRaise = null;

    private long lastToggleDone9 = 0;
    private long timeSinceToggle9 = 0;
    private boolean isIntakeSpinning = false;
    private boolean shouldUpdateIntake = true;
    private long timeSincePosSwitch1 = 0;
    private long lastPosSwitch1 = 0;

    public final double[] intakePositions = new double[]{0, 0.1, 0.3, 0.5};
    public int intakePosIndex = 1;

    public NewIntakeBot(LinearOpMode opMode) {
        super(opMode);
    }

    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        intakeSpin = hwMap.get(DcMotor.class, "intakeSpin");
        intakeSpin.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeSpin.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeRaise = hwMap.get(Servo.class, "intakeRaise");
        goToIntakePosition(intakePosIndex);
    }

    public void intakeToggle(boolean button) {
        timeSinceToggle9 = System.currentTimeMillis() - lastToggleDone9;
        if (button && timeSinceToggle9 > 300) {
            if (isIntakeSpinning) {
                isIntakeSpinning = false;
            } else {
                isIntakeSpinning = true;
            }
            lastToggleDone9 = System.currentTimeMillis();
        }
    }

    public void goToIntakePosition(int index) {
        intakePosIndex = index;
        intakeRaise.setPosition(intakePositions[intakePosIndex]);
    }

    public void changeIntakePosition(boolean up, boolean down) {
        timeSincePosSwitch1 = System.currentTimeMillis() - lastPosSwitch1;
        if (up && timeSincePosSwitch1 > 200) {
            if (intakePosIndex < 3) {
                intakePosIndex ++;
                goToIntakePosition(intakePosIndex);
                lastPosSwitch1 = System.currentTimeMillis();
            }
        }
        if (down && timeSincePosSwitch1 > 200) {
            if (intakePosIndex > 0) {
                intakePosIndex --;
                goToIntakePosition(intakePosIndex);
                lastPosSwitch1 = System.currentTimeMillis();
            }
        }
    }

    protected void updateIntake() {
        if (shouldUpdateIntake) {
            if (isIntakeSpinning) {
                intakeSpin.setPower(1);
            } else {
                intakeSpin.setPower(0);
            }
        }
    }

    public void startRotation() {
        intakeSpin.setPower(1);
    }

    public void stopRotation() {
        intakeSpin.setPower(0);
    }

    protected void onTick() {
        updateIntake();
        super.onTick();
    }
}
