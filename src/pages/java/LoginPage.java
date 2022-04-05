import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

    private WebDriver driver;

    private By usernameSelector = By.cssSelector("#username");
    private By passwordSelector = By.cssSelector("#password");
    private By loginSelector = By.cssSelector("#login > button");
    public  By invalidPasswordFlashSelector = By.cssSelector("#flash.error");
    public  By correctPasswordFlashSelector = By.cssSelector("#flash.success");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://the-internet.herokuapp.com/login");
    }

    public void takeFullPageScreenShot(Eyes eyes, String checkName) {
        eyes.check(Target.window().fully().withName(checkName));
    }

    public void takeFullPageScreenShot(Eyes eyes) {
        eyes.check(Target.window().fully());
    }

    public void enterUsername(String username) {
        driver.findElement(usernameSelector).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordSelector).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginSelector).click();
    }
}
