package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.CameraBot;

@Autonomous(name="Camera Test", group="Template")

public class CameraTest extends LinearOpMode {

    protected CameraBot robot = new CameraBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        Bitmap bitmap = BitmapFactory.decodeFile("C:/Users/allen/Pictures/FTC Camera/freight frenzy/sample/tse1.jpg");

        waitForStart();
        int viablePixels = robot.countPixels(bitmap, 40, 120);

    }

}
