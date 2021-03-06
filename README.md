# org.alcatraz.opencv

# FACE_DETECTION_AUTOMATION

The actual test code is written in Java 11 and makes use of JUnit5 framework.

## Requirements
* JDK/JRE 11 (or later)

* Gradle - After checking out make sure that the project is a Gradle project.

**Build and test using defaults**
```
for Intellij IDE
    run it from Gradle plugin -> verification -> test
```

**View Allure UI report**
```
for Intellij IDE
    run it from Gradle plugin -> other -> allureServe
```
  
## Code Structure
* `src.main.java.org.alcatraz.opencv.DetectFaceDemo` - Here is the tested method.
  
* `src.main.resources` - Here is the detection algorithm haarcascade_frontalface_default.xml.
  
* `src.test.java.org.alcatraz.opencv.FaceDetection` - Here are the automated tests.

* `src.test.resources` - Here are the photos that are being tested. And the generated images with rectangle around the faces.
  
## Test Parameters

Default - all testcases with tags: prositive|negative will be executed.

## Running The Tests
Running with gradle wrapper from command line
To execute the available tests from command line use the following command in the root directory of the project:
```
or for MAC OS
  ./gradlew clean test
  gradle clean test
```

The following files should be generated after executing the tests:

* `<PROJECT>\build\allure-results` -  Detailed HTML report. Lists nice statistics of the test run, allows overview by features executed, failures and tags, also allows in-depth inspection of the features tested.
