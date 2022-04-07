import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecurePage extends Page {
    private By correctPasswordFlashSelector = By.cssSelector("#flash.success");

    public SecurePage(WebDriver driver) {
        super(driver);
    }

    public void goToPage() {
        driver.get("http://the-internet.herokuapp.com/secure");
    }

    public boolean verifyPage() {
        return driver.findElements(correctPasswordFlashSelector).size() > 0;
    }
}
