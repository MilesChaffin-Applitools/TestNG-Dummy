import org.openqa.selenium.WebDriver;

public class SecurePage extends Page {

    public SecurePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageAddress() {
        return "http://the-internet.herokuapp.com/secure";
    }
}
