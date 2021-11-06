package org.firstinspires.ftc.teamcode.bots;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class CameraBotTest{

    public class TestOpMode extends LinearOpMode{
        public void runOpMode(){
        }
    }

    @Test
    public void getNumberOfViablePixelsTest() throws InterruptedException {
//        Bitmap bitmap = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_8888);
        CameraBot bot = mock(CameraBot.class);
//        when(bot.getImageFromCamera()).thenReturn(bitmap);
//        when(bot.detectRings()).thenCallRealMethod();
//        when(bot.getNumberOfViablePixels(bitmap, 40, 120)).thenCallRealMethod();
//        when(bot.chooseRings()).thenCallRealMethod();
//        assertEquals(bot.detectRings(), 0);
        Bitmap bitmap = BitmapFactory.decodeFile("C:/Users/allen/Pictures/FTC Camera/freight frenzy/sample/tse1.jpg");
        //bot.countYellowPixels(bitmap, 40, 120);
    }
}