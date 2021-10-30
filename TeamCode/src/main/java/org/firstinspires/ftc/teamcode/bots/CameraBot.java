package org.firstinspires.ftc.teamcode.bots;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.RobotLog;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.concurrent.BlockingQueue;

public class CameraBot extends LEDBot {

    final int cameraWidth = 1280;
    final int cameraHeight = 720;
    final int offsetX = 0;
    final int offsetY = 120;
    final int spacing = 16;
//    final int redDotWidth = ((cameraWidth - 2*offsetX)/spacing);
//    final int redDotHeight = ((cameraHeight - 2*offsetY)/spacing);

    //public int[] cameraView = new int[redDotWidth*redDotHeight];

    //protected Area[][] boxes = new Area[numberOfColumns][numberOfRows];

    public CameraBot(LinearOpMode opMode) {
        super(opMode);
    }

    private static final String VUFORIA_KEY =
            "AW3DaKr/////AAABmbYMj0zPp0oqll2pQvI8zaoN8ktPz319ETtFtBMP7b609q4wWm6yRX9OVwWnf+mXPgSC/fSdDI2uUp/69KTNAJ6Kz+sTx+9DG+mymW00Xm3LP7Xe526NP/lM1CIBsOZ2DJlQ2mqmObbDs5WR5HXyfopN12irAile/dEYkr3uIFnJ95P19NMdbiSlNQS6SNzooW0Nc8cBKWz91P020YDqC4dHSpbQvYeFgVp2VWZJC/uyvmE15nePzZ30Uq/n8pIeYWKh4+XR74RoRyabXMXFB6PZz7lgKdRMhhhBvQ5Eh21VxjE5h8ZhGw27K56XDPk63eczGTYP/FfeLvTuK4iKSNyqRLS/37kuxKn3t/dlkwv1";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {

        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Camera bot

        // setup frame queue
        vuforia.enableConvertFrameToBitmap();
        vuforia.setFrameQueueCapacity(5);

    }


    @Override
    public void init(HardwareMap ahwMap) {

        super.init(ahwMap);
        initVuforia();

    }

    @Override
    public void print(String message) {

    }

    final int LEFT = 0;
    final int MIDDLE = 1;
    final int RIGHT = 2;

    protected void printAndSave(Bitmap bmp, int average, String label){
        RobotLog.d("Image %s with %d x %d and average RGB #%02X #%02X #%02X", label, bmp.getWidth(), bmp.getHeight(),
                Color.red(average), Color.green(average), Color.blue(average));
        //try (FileOutputStream out = new FileOutputStream(String.format("/sdcard/FIRST/ftc_%s.png", label))) {
        try (FileOutputStream out = new FileOutputStream(String.format("C:/Users/allen/Pictures/FTC Camera/freight frenzy/output/ftc_%s.png", label))) {
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected Bitmap getImageFromCamera() throws InterruptedException {
        BlockingQueue<VuforiaLocalizer.CloseableFrame> queue = vuforia.getFrameQueue();
        VuforiaLocalizer.CloseableFrame frame = queue.take();
        RobotLog.d("Took Frames from Vuforia");
        Image image = frame.getImage(0);
        Bitmap bmp = vuforia.convertFrameToBitmap(frame);
        RobotLog.d(String.format("Converted Vuforia frame to BMP: %s", bmp.getConfig().toString()));
        // DEBUG : uncomment the following line to save the whole picture captured
        //printAndSave(bmp, "camera");
        RobotLog.d("Saved camera BMP");
        frame.close();
        RobotLog.d("Closed frame");
        return bmp;
    }

    public int detectDuck() {
        RobotLog.d("Detecting Started ...");
        try {
            Bitmap bmp = getImageFromCamera();

            int left = countPixels(bmp, 0, 0, offsetX, offsetY, cameraWidth/3, cameraHeight);
            int middle = countPixels(bmp, cameraWidth/3, 0, offsetX, offsetY, cameraWidth/3, cameraHeight);
            int right = countPixels(bmp, (cameraWidth/3)*2, 0, offsetX, offsetY, cameraWidth/3, cameraHeight);

            //Bitmap bmp2 = Bitmap.createBitmap(bmp, 0, 0, redDotWidth, 110);
            //printAndSave(bmp, viablePixels, "red");
            //bmp2.setPixels(cameraView, 0, redDotWidth, 0, 0, redDotWidth, redDotHeight);
            //printAndSave(bmp2, viablePixels, "small");
            RobotLog.d("Counted pixels");

            int positionOfDuck = choosePosition(left, middle, right);
            RobotLog.d("Determined which position the duck/TSE is in");

            return positionOfDuck;
        } catch (InterruptedException e) {
            print("Photo taken has been interrupted !");
            return LEFT;
        }

    }

    public int choosePosition(int left, int middle, int right){
        if (left > middle && left > right) {
            return LEFT;
        } else if (middle > left && middle > right) {
            return MIDDLE;
        } else if (right > left && right > middle) {
            return RIGHT;
        }
        return LEFT;
    }

    public int countPixels(Bitmap bmp, int startX, int startY, int offsetX, int offsetY, int width, int height){
        int viablePixelsCount = 0;

        for (int y = startY + offsetY; y < height - offsetY; y += spacing) {
            for (int x = startX + offsetX; x < width - offsetX; x += spacing) {
                int pixel = bmp.getPixel(x, y);

                //cameraView[count] = pixel;

                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);
                int redBlueDifference = Math.abs(red - blue);
                //int average = (red + green + blue) / 3;
                int redGreenDifference = Math.abs(red - green);
                //int greenBlueDifference = Math.abs(green - blue);

                if ((red < 250 && red > 155) && (green < 230 && green > 145) && (blue < 200 && blue > 50)
                        && redGreenDifference < 15 && redBlueDifference > 50) {
                    //bmp.setPixel(x, y, Color.RED);
                    viablePixelsCount++;
                }
            }
        }

        RobotLog.d(String.format("%d pixels meet criteria", viablePixelsCount));
        opMode.telemetry.addData("Viable Pixels: ", viablePixelsCount);
        //opMode.telemetry.addData("Total Pixels: ", count);
        opMode.telemetry.update();
        return viablePixelsCount;
    }

}
