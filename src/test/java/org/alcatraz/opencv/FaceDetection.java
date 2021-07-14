package org.alcatraz.opencv;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static org.alcatraz.opencv.DetectFaceDemo.*;
import static org.junit.jupiter.params.ParameterizedTest.DEFAULT_DISPLAY_NAME;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;

public class FaceDetection {

    /**
     * CsvSource provide the data for the test.
     * The first param is the name of the picture.
     * The second param is the expected faces that should present on that picture.
     * This data is passed to the test method via this params:
     * @param pictureName
     * and
     * @param expectedNumberFaces
     */
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + DEFAULT_DISPLAY_NAME)
    @Tag("positive")
    @DisplayName("Verify the correct size of the faces")
    @Description("Verify the correct size of the faces")
    @CsvSource({
            "alcatraz, 0",
            "4_abba, 4",
            "11_bulgarian_team, 11",
            "18_faces ,18"})
    public void verifyFaceSize(String pictureName, int expectedNumberFaces) {
        int actualNumberFaces = detectingFaces(sourcePath + pictureName);
        verifyFaces(actualNumberFaces, expectedNumberFaces, pictureName);
    }

    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + DEFAULT_DISPLAY_NAME)
    @Tag("negative")
    @DisplayName("Negative input for the faceDetection method")
    @Description("Test with empty inputs in order to get readable error messages")
    @ValueSource(strings = {"  ", "\t", "\n"})
    public void verifyFaceSizeNegative(String picturePath) {
        new DetectFaceDemo().faceDetection(picturePath);
    }

    /**
     * The method we are testing.
     * @param pictureName
     * @return the size of the detected faces.
     */
    @Step
    public int detectingFaces(String pictureName) {
        return new DetectFaceDemo().faceDetection(pictureName);
    }

    /**
     * This is the assertion step.
     * The test will be marked as failed in the allure report if the conditions are not met.
     * Because of the attaching functionality of the allure report the image should be transform into byte of arrays.
     *
     * @param actualNumberFaces
     * @param expectedNumberFaces
     * @param pictureName
     */
    @Step
    public void verifyFaces(int actualNumberFaces, int expectedNumberFaces, String pictureName) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        File image = new File(sourcePath + pictureName + faceDetectOutput + ".png");
        try {
            BufferedImage bImage = ImageIO.read(image);
            ImageIO.write(bImage, "png", bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        attachScreenshot(bos.toByteArray(), pictureName + ".png");
        Assertions.assertEquals(expectedNumberFaces, actualNumberFaces, "The number of the faces are incorrect");
    }


    /**
     * Attach images into the allure report.
     * If there is any differences between the number of the detected faces and the expected number of the faces
     * image with rectangle around the faces will be attached for verification.
     *
     * @param imageResult
     * @param name        of the step
     * @return attached image into the allure report
     */
    @Attachment(value = "Page screenshot {name}", type = "image/png")
    public byte[] attachScreenshot(byte[] imageResult, String name) {
        return imageResult;
    }


}
