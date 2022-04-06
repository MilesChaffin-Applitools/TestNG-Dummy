import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import org.openqa.selenium.WebDriver;

public abstract class Page {

    protected WebDriver driver;

    public abstract String getPageAddress();


    public Page(WebDriver driver) {
        this.driver = driver;
        driver.get(getPageAddress());
    }

    public void takeFullPageScreenShot(Eyes eyes, String checkName) {
        eyes.check(Target.window().fully().withName(checkName));
    }

    public void takeFullPageScreenShot(Eyes eyes) {
        eyes.check(Target.window().fully());
    }
}
