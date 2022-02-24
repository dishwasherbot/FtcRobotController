package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.CameraBot;
import org.firstinspires.ftc.teamcode.bots.FourWheelDriveBot;

@Autonomous(name="Auto 1", group="Autos")

public class Auto1 extends LinearOpMode {

    protected CameraBot robot = new CameraBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.isRepeating = true;
        waitForStart();
        int[] pos;
        robot.setSwing(0.59);
        pos = robot.detect();
        if (false) {
            robot.goToInOutPosition(1);
            robot.setArmPositionNoWait(-500, 0.3);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 3, 0.9, false, 0, true);
            robot.goBacktoStartAngle();
            if (pos[0] == 0) {
                robot.setArmPositionNoWait(-935, 0.18);
                robot.sleep(500);
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 4, 0.4);
                robot.servoPosIndex = 0;
                robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
                robot.sleep(300);
                robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 2, 0.9);
            } else if (pos[0] == 1) {
                robot.setArmPositionNoWait(-760, 0.18);
                robot.sleep(100);
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 6, 0.4);
                robot.sleep(200);
                robot.servoPosIndex = 0;
                robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
                robot.sleep(300);
                robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 2, 0.9);
            } else {
                robot.setArmPositionNoWait(-580, 0.18);
                robot.sleep(400);
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 9, 0.8);
                robot.servoPosIndex = 0;
                robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
                robot.sleep(300);
                robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 7, 0.9);
            }
            robot.servoPosIndex = 2;
            robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
            robot.setArmPositionNoWait(-25, 0.1);
            robot.goToAnglePID(50);
            //robot.goToAngle(50, 0.1);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 11, 0.9, true, 50, true);
            robot.sleep(200, "after gyro wait");
            robot.goToAnglePID(90);
            robot.goToAnglePID(90);
            //robot.goToAngle(90, 0.1);
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 3, 0.4);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 1, 0.4);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 16, 0.8, true, 89, true);
            robot.sleep(200, "after gyro wait");
            robot.autoGrabFreight(0.12);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 17, 0.7, true, 89, true);
            robot.goToInOutPosition(0);
            robot.goToAnglePID(20);
            //robot.goToAngle(30, 0.1);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 13.5, 0.7, true, 20, true);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 2, 0.3, true, 20, true);
            robot.sleep(200, "after gyro wait");
            robot.sleep(300);
            robot.servoPosIndex = 0;
            robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
            robot.sleep(300);
            robot.servoPosIndex = 2;
            robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
            robot.goToInOutPosition(1);
            robot.setArmPositionNoWait(-25, 0.1);

            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 16, 0.8, true, 30, true);
            robot.sleep(200, "after gyro wait");
            robot.goToAnglePID(90);
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 5, 0.4);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 0.5, 0.1);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 20, 0.8, true, 89, true);
            robot.sleep(200, "after gyro wait");
            robot.autoGrabFreight(0.12);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 18, 0.7, true, 89, true);
            robot.goToInOutPosition(0);
            robot.goToAnglePID(20);
            //robot.goToAngle(30, 0.1);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 14.5, 0.7, true, 20, true);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 2, 0.3, true, 20, true);
            robot.sleep(200, "after gyro wait");
            robot.sleep(300);
            robot.servoPosIndex = 0;
            robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
            robot.sleep(300);
            robot.servoPosIndex = 2;
            robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
            robot.goToInOutPosition(1);
            robot.setArmPositionNoWait(-25, 0.1);
        } else {
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 3, 0.9, false, 0, true);
            robot.goToInOutPosition(1);
            robot.setArmPositionNoWait(-500, 0.3);
            robot.servoPosIndex = 1;
            robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
            robot.goBacktoStartAngle();
            robot.sleep(500);
            if (pos[0] == 0) {
                robot.setArmPositionNoWait(-935, 0.18);
                robot.sleep(500);
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 4, 0.4);
                robot.sleep(800);
                robot.servoPosIndex = 0;
                robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
                robot.sleep(300);
                robot.servoPosIndex = 2;
                robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
                robot.setArmPositionNoWait(-25, 0.1);
                //robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 2, 0.9);
            } else if (pos[0] == 1) {
                robot.setArmPositionNoWait(-760, 0.18);
                robot.sleep(100);
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 8, 0.4);
                robot.sleep(800);
                robot.servoPosIndex = 0;
                robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
                robot.sleep(300);
                robot.servoPosIndex = 2;
                robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
                robot.setArmPositionNoWait(-25, 0.1);
                robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 4, 0.9);
            } else {
                robot.setArmPositionNoWait(-580, 0.18);
                robot.sleep(400);
                robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 9, 0.8);
                robot.servoPosIndex = 0;
                robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
                robot.sleep(300);
                robot.servoPosIndex = 2;
                robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
                robot.setArmPositionNoWait(-25, 0.1);
                robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 5, 0.9);
            }
            robot.goToAnglePID(50);
            //robot.goToAngle(50, 0.1);
            robot.goToInOutPosition(0);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 10, 0.9, true, 50, false);
            //robot.sleep(200, "after gyro wait");
            robot.goToInOutPosition(1);
            robot.goToAnglePID(90);
            robot.goToAnglePID(90);
            //robot.goToAngle(90, 0.1);
            robot.driveStraightByTime(robot.DIRECTION_RIGHT, 500, 0.4);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 1, 0.4);

            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 16, 0.8, true, 89, true);
            robot.sleep(200);
            robot.goToAnglePID(90);
            robot.autoGrabFreight(0.12);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 17, 0.7, true, 89, true);
            robot.goToInOutPosition(0);
            robot.goToAnglePID(25);

            //robot.goToAngle(30, 0.1);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 3.5, 0.9, true, 25, false);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 5, 0.2, true, 25, true);
            robot.sleep(200, "after gyro wait");
            robot.goToAnglePID(25);
            //robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 1, 0.3, true, 25, true);
            robot.sleep(300);
            robot.servoPosIndex = 0;
            robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
            robot.sleep(300);
            robot.servoPosIndex = 2;
            robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
            robot.goToInOutPosition(1);
            robot.setArmPositionNoWait(-25, 0.1);

            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 3, 0.8, true, 25, false);
            robot.goToInOutPosition(0);
            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 7, 0.4, true, 25, true);
            robot.sleep(200, "after gyro wait");
            robot.goToInOutPosition(1);
            robot.goToAnglePID(90);
            robot.driveStraightByTime(robot.DIRECTION_RIGHT, 500, 0.4);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 1, 0.4);

            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 19, 0.8, true, 89, true);
            robot.sleep(200);
            robot.goToAnglePID(90);
            robot.autoGrabFreight(0.12);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 17, 0.7, true, 89, true);
            robot.goToInOutPosition(0);
            robot.goToAnglePID(25);

            //robot.goToAngle(30, 0.1);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 3.5, 0.9, true, 25, false);
            robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 5, 0.2, true, 25, false);
            robot.sleep(200, "after gyro wait");
            robot.goToAnglePID(25);
            //robot.driveStraightByGyro(robot.DIRECTION_BACKWARD, 1, 0.3, true, 25, true);
            robot.sleep(300);
            robot.servoPosIndex = 0;
            robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
            robot.sleep(300);
            robot.servoPosIndex = 2;
            robot.wobblePinch.setPosition(robot.servoPositions[robot.servoPosIndex]);
            robot.goToInOutPosition(1);
            robot.setArmPositionNoWait(-25, 0.1);
            robot.goToInOutPosition(0);

            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 10, 0.8, true, 25, false);
            robot.goToInOutPosition(1);
            //robot.sleep(200, "after gyro wait");
            robot.goToAnglePID(90);
            robot.driveStraightByTime(robot.DIRECTION_RIGHT, 500, 0.4);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 1, 0.4);

            robot.driveStraightByGyro(robot.DIRECTION_FORWARD, 20, 0.8, true, 89, true);
            robot.sleep(200);
        }
    }
}
