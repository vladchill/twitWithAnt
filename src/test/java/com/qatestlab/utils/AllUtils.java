package com.qatestlab.utils;

import com.qatestlab.twit.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.qatestlab.utils.ReporterCustom.*;

public class AllUtils {

    public static void switchWindow(WebDriver driver){
        String currentWindow = driver.getWindowHandle();
        log("Current Window URL is : " + driver.getCurrentUrl());
        System.out.println("Current Window URL is : " + driver.getCurrentUrl());

        for (String handle: driver.getWindowHandles() ) {
            if (!handle.equals(currentWindow)){
                driver.switchTo().window(handle);
                break;
            }
        }
        System.out.println("Active Window URL is : " + driver.getCurrentUrl());
        log("Active Window URL is : " + driver.getCurrentUrl());

    }

    public static void makeScreenshot(String message) {
        makeScreenshot(message, "");
    }

    public static void makeScreenshot(String message, String screenshotName) {
        log(message);
        String imageName = screenshotName + System.currentTimeMillis() + ".png";
        String path = ".\\target\\screenshots\\";//System.getProperty("image.dir") + File.separatorChar + "html" + File.separatorChar;
        try {
            File screenshotAs = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.FILE);//future change bfore driver
            FileUtils.copyFile(screenshotAs, new File(path + imageName));
            log("<a href='" + imageName + "'><img src='" + imageName + "' width='600'/></a>");
        } catch (Exception e) {
            Reporter.log("Failed to save screenshot <br></br> " + e);
        }
    }

    public static boolean isInRange(long timeOfElement, int range){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("Current Date : " + localDateTime);
        Instant instRange = localDateTime.minusDays(range).atZone(ZoneId.systemDefault()).toInstant();
        System.out.println("Until Date : " + instRange);
        Instant instElement = Instant.ofEpochMilli(timeOfElement);
        System.out.println("Element Date : " + instElement);
        return instElement.isAfter(instRange);
    }

    @Attachment(value = "Page screenshot{0}", type = "image/png")
    public static byte[] saveScreenshotAllure(String name, WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
