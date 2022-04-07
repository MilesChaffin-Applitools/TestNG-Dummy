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

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.List;

public class testLogin extends testBase {

    @Test
    public void test_login_failure() {
        LoginPage loginPage = new LoginPage(driver.get());
        loginPage.goToPage();

        loginPage.takeFullPageScreenShot(eyes.get(), "Homepage default");
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("wrongPassword");
        loginPage.clickLogin();
        loginPage.takeFullPageScreenShot(eyes.get(), "Login Failure");
    }

    @Test
    public void test_login_success() {
        LoginPage loginPage = new LoginPage(driver.get());
        loginPage.goToPage();

        loginPage.takeFullPageScreenShot(eyes.get(), "Homepage default");
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        SecurePage securePage = loginPage.clickLogin();
        securePage.takeFullPageScreenShot(eyes.get(), "Logged-in Page");
    }

    @Override
    public String getAppName() {
        return "Heroku Dummy";
    }
}
