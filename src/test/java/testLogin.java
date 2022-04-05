import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.fluent.BatchClose;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.selenium.fluent.Target;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.net.InetAddress;
import java.util.List;

public class testLogin {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private ThreadLocal<Eyes> eyes = new ThreadLocal<>();
    private static BatchInfo batch;
    private static String batchId;
    private static final String appName = "Heroku Dummy";


    @BeforeClass
    public void setupClass() {
        batch = new BatchInfo("Test Batch");
        try {
            batchId = InetAddress.getLocalHost().getHostName() + ":" + System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        batch.setId(batchId);
        batch.setNotifyOnCompletion(true);
    }

    @BeforeMethod
    public void setup() {
        driver.set(new ChromeDriver());
        com.applitools.eyes.config.Configuration suiteConfig = new Configuration()
                .setBatch(batch)
                .setApiKey(System.getenv("APPLITOOLS_API_KEY"))
                .setForceFullPageScreenshot(true)
                .setStitchMode(StitchMode.SCROLL)
                .setHideScrollbars(true)
                .setHideCaret(true);

        ClassicRunner runner = new ClassicRunner();
        runner.setDontCloseBatches(true);
        eyes.set(new Eyes(runner));
        eyes.get().setConfiguration(suiteConfig);
    }


    @Test
    public void test_login_failure() {
        LoginPage loginPage = new LoginPage(driver.get());
        eyes.get().open(driver.get(), appName, "Login Test Failure");

        loginPage.takeFullPageScreenShot(eyes.get(), "Homepage default");
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("wrongPassword");
        loginPage.clickLogin();
        loginPage.takeFullPageScreenShot(eyes.get(), "Login Failure");
    }

    @Test
    public void test_login_success() {
        LoginPage loginPage = new LoginPage(driver.get());
        eyes.get().open(driver.get(), appName, "Login Test Success");

        loginPage.takeFullPageScreenShot(eyes.get(), "Homepage default");
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLogin();
        loginPage.takeFullPageScreenShot(eyes.get(), "Logged-in Page");
    }

    @AfterMethod
    public void teardown() {
        eyes.get().closeAsync();
        driver.get().quit();
    }

    @AfterClass
    public void classTeardown() {
        List<String> batchIds = List.of(batchId);
        System.out.println(batchIds);
        BatchClose batchClose = new BatchClose();
        batchClose.setBatchId(batchIds).close();
    }


}
