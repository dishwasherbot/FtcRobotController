package org.firstinspires.ftc.teamcode.bots;

import android.graphics.Bitmap;
import android.graphics.Color;

import static org.junit.Assert.*;
import org.junit.Test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.RobotLog;

public class CameraBotTest {

    final int cameraWidth = 1280;
    final int cameraHeight = 720;
    final int offsetX = 40;
    final int offsetY = 120;
    final int spacing = 4;
    final int redDotWidth = ((cameraWidth - 2*offsetX)/spacing);
    final int redDotHeight = ((cameraHeight - 2*offsetY)/spacing);

    public int[] cameraView = new int[redDotWidth*redDotHeight];

    public class TestOpMode extends LinearOpMode{
        public void runOpMode(){

        }
    }

    @Test
    public int getNumberOfViablePixels (Bitmap bmp, int offsetX, int offsetY){

        int viablePixelsCount = 0;
        int count = 0;

        for (int y = offsetY; y < bmp.getHeight() - offsetY; y += spacing) {
            for (int x = offsetX; x < bmp.getWidth() - offsetX; x += spacing) {
                int pixel = bmp.getPixel(x, y);

                //cameraView[count] = pixel;

                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);
                int average = (red + green + blue) / 3;
                int redGreenDifference = Math.abs(red - green);
                int greenBlueDifference = Math.abs(green - blue);

                //bmp.setPixel(x, y, Color.RED);

                if (red >= average && green > blue && red > green && green > red/2 && greenBlueDifference > 20 && redGreenDifference > 10
                        && ((70 < red && 220 > red && 50 < green && 150 > green) || (70 < red && 220 > red && 100 > blue))) {
                    viablePixelsCount++;
                }
                count++;
            }
        }

        RobotLog.d(String.format("%d pixels meet criteria", viablePixelsCount));
        opMode.telemetry.addData("Viable Pixels: ", viablePixelsCount);
        opMode.telemetry.addData("Total Pixels: ", count);
        opMode.telemetry.update();

        return viablePixelsCount;
    }

}
