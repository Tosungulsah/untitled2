package com.Example.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BrowserUtils {
    public static void sleep(int second){
        second *=1000;
        try {
            Thread.sleep(second);
        }catch (InterruptedException e ) {

        }
    }

    /*
    This method accepts 3 arguments.
    Arg1: webdriver
    Arg2: expectedInUrl : for verify if the url contains given String.
        - If condition matches, will break loop.
    Arg3: expectedInTitle to be compared against actualTitle
     */
    public static void switchWindowAndVerify( String expectedInUrl, String expectedInTitle){

        Set<String> allWindowsHandles = Driver.getDriver().getWindowHandles();

        for (String each : allWindowsHandles) {

            Driver.getDriver().switchTo().window(each);

            System.out.println("Current URL: " + Driver.getDriver().getCurrentUrl());

            if (Driver.getDriver().getCurrentUrl().contains(expectedInUrl)){
                break;
            }
        }

        //5. Assert:Title contains “expectedInTitle”
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedInTitle));
    }

    /*
    This method accepts a String "expectedTitle" and Asserts if it is true
     */
    public static void verifyTitle(WebDriver driver , String expectedTitle){

        Assert.assertEquals(Driver.getDriver().getTitle(), expectedTitle);

    }

    /*
    Creating a utility method for ExplicitWait, so we don't have to repeat the lines

    public static void waitForInvisibilityOf(WebElement webElement){
        //Driver.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }*/

    public static void verifyTitleContains(String expectedInTitle){
        Driver.getDriver().getCurrentUrl().contains(expectedInTitle);
    }

    public static List<String> dropDown(WebElement dropDownElement){
        System.out.println(dropDownElement);
        Select select = new Select(dropDownElement);
        List<WebElement> actualOptionsAsWebElements= select.getOptions();

        List<String> actualOptionsAsString=  new ArrayList<>();

        for (WebElement each : actualOptionsAsWebElements) {
            actualOptionsAsString.add(each.getText());
        }
        return actualOptionsAsString;
    }

    public static void clickRadioButton(List<WebElement> radioButtons, String attributeValue){

        for (WebElement each : radioButtons) {
            if(each.getAttribute("value").equalsIgnoreCase(attributeValue)) {
                each.click();
            }
        }
    }
}
