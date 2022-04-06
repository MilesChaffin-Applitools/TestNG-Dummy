import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage extends Page {

    private By usernameSelector = By.cssSelector("#username");
    private By passwordSelector = By.cssSelector("#password");
    private By loginSelector = By.cssSelector("#login > button");
    public  By invalidPasswordFlashSelector = By.cssSelector("#flash.error");
    public  By correctPasswordFlashSelector = By.cssSelector("#flash.success");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getPageAddress() {
        return "http://the-internet.herokuapp.com/login";
    }

    public void enterUsername(String username) {
        driver.findElement(usernameSelector).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordSelector).sendKeys(password);
    }

    public SecurePage clickLogin() {
        driver.findElement(loginSelector).click();
        if (driver.findElements(correctPasswordFlashSelector).size() > 0) {
            return new SecurePage(this.driver);
        } else {
            return null;
        }
    }
}
