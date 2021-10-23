package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.CameraBot;

@Autonomous(name="TemplateAuto", group="Template")

public class CameraTest extends LinearOpMode {

    protected CameraBot robot = new CameraBot(this); //replace FourWheelDriveBot with whichever Bot is required

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        Bitmap bitmap = BitmapFactory.decodeFile("C:/Users/allen/Pictures/FTC Camera/freight frenzy/tse1.jpg");

        waitForStart();
        int viablePixels = robot.getNumberOfViablePixels(bitmap, 40, 120);

    }

}
