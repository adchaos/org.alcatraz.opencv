package org.alcatraz.opencv;

import nu.pattern.OpenCV;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;

public class DetectFaceDemo {

    public static String faceDetectOutput = "_facedetect_output";
    public static String sourcePath = "src/test/resources/";

    public int faceDetection(String pictureName) {

        // Loading the OpenCV core library
        OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Instantiating the CascadeClassifier
        String xmlFile = "src/main/resources/haarcascade_frontalface_default.xml";
        CascadeClassifier classifier = new CascadeClassifier(xmlFile);

        // Read the image
        Mat src = Imgcodecs.imread(pictureName + ".png");

        // Detecting the face in the snap
        MatOfRect faceDetections = new MatOfRect();
        classifier.detectMultiScale(src, faceDetections);
        System.out.println(String.format("Detected %s faces",
                faceDetections.toArray().length));

        // Drawing boxes
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(
                    src,
                    new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 0, 255),
                    3
            );
        }

        // Writing the image
        Imgcodecs.imwrite(pictureName + faceDetectOutput + ".png", src);

        System.out.println("Image Processed");
        return faceDetections.toArray().length;
    }
}