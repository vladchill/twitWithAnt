package com.qatestlab.twit;


import com.qatestlab.pages.LoginPage;
import static org.testng.Assert.*;

import com.qatestlab.utils.AllUtils;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestLoginPage extends BaseTest{

    private LoginPage loginPage;


    @BeforeClass
    private void initPage() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginPageAppearCorrect() {

        loginPage.authorization("vlad_x_sumy@mail.ru","zaqxswcde12345678");
        loginPage.clickAccountInfoButton();
        assertEquals("testoviyacc", loginPage.getAccountName(),"Login is different");
        AllUtils.saveScreenshotAllure(" loginPage", driver);

    }


}
