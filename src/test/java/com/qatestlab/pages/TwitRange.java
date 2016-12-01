package com.qatestlab.pages;


import com.qatestlab.utils.AllUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.qatestlab.utils.ReporterCustom.log;

public class TwitRange {

    private WebDriver driver;
    private int numberOfTwits;
    private int rangeOfDaysTwits;


    @FindBy(xpath = "//div[@class='stream']/descendant::div[@class='content']//small[@class='time']/a/span[contains(@class,'_timestamp')]")
    private List<WebElement> massiveOfContents;

    @FindBy(xpath = "//div[@id='retweet-tweet-dialog-dialog']//div[@class='tweet-button']")
    private WebElement retwitButton;

    public TwitRange(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToPage(String pageLink){
        driver.get("https://twitter.com/f1");
        log("open page "+pageLink);
    }

    public ArrayList<WebElement> findTwitsOfRange(int range){

        boolean temp = true;
        while (temp) {
            ArrayList<WebElement> array = new ArrayList<WebElement>();
            List<WebElement> elements;
            elements = driver.findElements(By.xpath("//div[@class='stream']/descendant::div[@class='content']//small[@class='time']/a/span[contains(@class,'_timestamp')]"));
            for (int i = 1; true; i++) {

                long timeOfElement = Long.parseLong(elements.get(i - 1).getAttribute("data-time-ms"));
                System.out.println("count " + i + " " + timeOfElement);
                if (!AllUtils.isInRange(timeOfElement, range)) {
                    temp = false;
                    return array;
//                    break;
                }
                else{
                    array.add(driver.findElement(By.xpath("//div[@class='stream']/descendant::div[@class='content']["+
                            i +"]//button[contains(@class,'js-actionRetweet')][1]")));
                }
                if (i == elements.size()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elements.get(elements.size() - 1));
//
                    final int finalI = i;
                    (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver input) {
                            return massiveOfContents.size() > finalI;
                        }
                    });

                    break;
                }
            }
        }return null;
    }

    public void doTwitsOfRange(int range){
        ArrayList<WebElement> array  = findTwitsOfRange(range);
        for(WebElement elementRetwit : array){
            System.out.println(elementRetwit);

            (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver input) {
                    return elementRetwit.isDisplayed();
                }
            });

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", elementRetwit);
            elementRetwit.click();
//            log( elementRetwit.findElement(By.xpath("//preceding::div[@class='js-tweet-text-container']")).getText());//log with content text

            WebElement dynamicElement = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='retweet-tweet-dialog-dialog']//div[@class='tweet-button']")));
            retwitButton.click();
        }
        numberOfTwits = array.size();
        rangeOfDaysTwits = range;
    }

    public int getNumberOftwits(){
        return numberOfTwits;
    }

    public int getNumberOfDaysRangeTwits(){
        return rangeOfDaysTwits;
    }

    public String getReportMessage(){
        return getNumberOftwits()+" twits for "+getNumberOfDaysRangeTwits()+" days are twitted";
    }

}
