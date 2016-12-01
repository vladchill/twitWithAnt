package com.qatestlab.twit;


import com.qatestlab.pages.TwitRange;
import com.qatestlab.utils.Sender;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class TestTwitRange extends BaseTest{

    private TwitRange twitPage;

    @BeforeClass
    public void setUp() {
        twitPage = new TwitRange(driver);
    }

    @Test
    @Parameters("daysRange")
    public void testRangeOfTwits(int daysRange){

        twitPage.goToPage("https://twitter.com/f1");
        twitPage.doTwitsOfRange(daysRange);

        Sender mailSend = new Sender("testoviyacc123456","zaqxswcde12345678");
        mailSend.send("test of twits", twitPage.getReportMessage(), "testoviyacc123456@gmail.com", "vlad_x_sumy@mail.ru");


    }


}
