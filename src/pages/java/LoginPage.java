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

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void goToPage() {
        driver.get("http://the-internet.herokuapp.com/login");
    }

    public void enterUsername(String username) {
        driver.findElement(usernameSelector).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordSelector).sendKeys(password);
    }

    public SecurePage clickLogin() {
        driver.findElement(loginSelector).click();
        SecurePage securePage = new SecurePage(this.driver);
        if (securePage.verifyPage()) {
            return securePage;
        } else {
            return null;
        }
    }
}
