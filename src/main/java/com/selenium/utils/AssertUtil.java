package com.selenium.utils;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class AssertUtil {

    public static void verifyEquals(String actual, String expected, ExtentTest test, String msg) {

        if (!actual.equals(expected)) {
           test.fail(MarkupHelper.createLabel(
    "Title validation failed → Expected: " + expected + " | Actual: " + actual,
    ExtentColor.RED
));

            Assert.fail(msg);
        } else {
            test.pass(MarkupHelper.createLabel(
    "Title validation passed → Expected: " + expected + " | Actual: " + actual,
    ExtentColor.GREEN
));
        }
    }
}
