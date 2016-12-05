package com.qatestlab.twit;



import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver driver;

    public static final String USERNAME = "testoviyacc123456";
    public static final String ACCESS_KEY = "5e00c4bb-e629-4a7e-82e5-dad234b8a273";
    public static final String URLsu = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

    public static WebDriver getDriver() {
        return driver;
    }


    @BeforeClass
    public void setUp() throws MalformedURLException {

        if (System.getProperty("webbrowser").equals("chrome")) {
            String driverPath = System.getProperty("driver.exe.chrome");
            System.out.println(driverPath);
            if (driverPath == null) {
                throw new SkipException("Path for chromedrive is not specified");
            }
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver();
        }else if (System.getProperty("webbrowser").equals("ie")){
            String driverPath = System.getProperty("driver.exe.ie");
            System.out.println(driverPath);

            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setBrowserName("internet explorer");
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setCapability("nativeEvents", false);
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setCapability("ie.ensureCleanSession", true);

            if (driverPath == null) {
                throw new SkipException("Path for chromedrive is not specified");
            }
            System.setProperty("webdriver.ie.driver", driverPath);
            driver = new InternetExplorerDriver(capabilities);
        } else if(System.getProperty("webbrowser").equals("remote")){
            DesiredCapabilities caps = DesiredCapabilities.safari();
            caps.setCapability("platform", "OS X 10.11");
            caps.setCapability("version", "10.0");

            driver = new RemoteWebDriver(new URL(URLsu), caps);
        }
        else {
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        driver.manage().window().maximize();

    }

//    @AfterClass
//    public void closeBrowser() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
