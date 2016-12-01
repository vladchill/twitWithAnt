package com.qatestlab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.allure.annotations.Step;

import static com.qatestlab.utils.ReporterCustom.log;

public class LoginPage {

    private WebDriver driver;
    private static final String BASE_URL = "https://twitter.com";

    @FindBy(xpath = "//div/input[@id='signin-email']")
    private WebElement loginField;

    @FindBy(xpath = "//div/input[@id='signin-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//form//button[contains(@class,'submit')]")
    private WebElement singInButton;

    @FindBy(xpath = "//div/ul/li[@id='user-dropdown']/a")
    private WebElement accountInfoButton;

    @FindBy(xpath = "//div/ul/li[@id='user-dropdown']/div[@class='dropdown-menu']//*[@class='fullname']")
    private WebElement accountName;



    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get(BASE_URL);
        PageFactory.initElements(driver, this);
    }

    @Step("Set username {0}")
    public void setUserName(String username){
        loginField.sendKeys(username);
        log("Set username "+username);
    }

    @Step("Set password {0}")
    public void setPassword(String password){
        passwordField.sendKeys(password);
        log("Set password "+password);
    }

    @Step("Click sing in button")
    public void clicksingInButton(){
        singInButton.click();
        log("click sing in button");
    }

    public void authorization(String username, String password){
        setUserName(username);
        setPassword(password);
        clicksingInButton();
    }

    @Step("Click account info button")
    public void clickAccountInfoButton(){
        accountInfoButton.click();
        log("click account info button");
    }

    public String getAccountName(){
        return accountName.getText();
    }


}
