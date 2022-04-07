import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.fluent.BatchClose;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.List;

public abstract class testBase {
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected ThreadLocal<Eyes> eyes = new ThreadLocal<>();
    protected BatchInfo batch;
    private ThreadLocal<ClassicRunner> runner;

    public abstract String getAppName();

    @BeforeClass
    public void setupClass() {
        batch = new BatchInfo(System.getenv("APPLITOOLS_BATCH_NAME"));
        batch.setNotifyOnCompletion(true);
        batch.setSequenceName("TEST_SEQ_1");


    }

    @BeforeMethod
    public void setup(Method method) {
        runner.set(new ClassicRunner());
        runner.get().setDontCloseBatches(true);

        driver.set(new ChromeDriver());

        eyes.set(new Eyes(runner.get()));

        eyes.get().setConfiguration(eyes.get().getConfiguration()
                .setBatch(batch)
                .setApiKey(System.getenv("APPLITOOLS_API_KEY"))
                .setForceFullPageScreenshot(true)
                .setStitchMode(StitchMode.CSS)
                .setHideScrollbars(true)
                .setHideCaret(true));

        eyes.get().open(driver.get(), getAppName(), method.getName(), new RectangleSize(1244, 750));
    }

    @AfterMethod
    public void teardown() {
        eyes.get().closeAsync();
        driver.get().quit();
        runner.get().getAllTestResults();
    }

    @AfterClass
    public void classTeardown() {
        List<String> batchIds = List.of(batch.getId());
        System.out.println(batchIds);
        BatchClose batchClose = new BatchClose();
        batchClose.setBatchId(batchIds).close();
    }
}
