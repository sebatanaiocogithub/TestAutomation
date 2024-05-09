//package com.applitools.example;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.ChromeEmulationInfo;
import com.applitools.eyes.visualgrid.model.DesktopBrowserInfo;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestAut1 {

    private final static BatchInfo BATCH = new BatchInfo("Selenium Java Basic Quickstart");

    public static void main(String [] args) {

        EyesRunner runner = null;
        Eyes eyes = null;
        WebDriver driver = null;

        try {
            // Configure Applitools SDK to run on the Ultrafast Grid
            runner = new VisualGridRunner(new RunnerOptions().testConcurrency(5));
            eyes = new Eyes(runner);
            Configuration config = eyes.getConfiguration();
            //config.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
            config.setApiKey("NkIDWkQzbUXpJYHAW6bs1qFRG16YpQrnE92ObmMvQAI110");
            //eyes.setApiKey("NkIDWkQzbUXpJYHAW6bs1qFRG16YpQrnE92ObmMvQAI110");
            config.setBatch(BATCH);
            config.addBrowsers(
                    new DesktopBrowserInfo(800, 1024, BrowserType.CHROME),
                    new DesktopBrowserInfo(1600, 1200, BrowserType.FIREFOX),
                    new DesktopBrowserInfo(1024, 768, BrowserType.SAFARI),
                    new ChromeEmulationInfo(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT),
                    new ChromeEmulationInfo(DeviceName.Nexus_10, ScreenOrientation.LANDSCAPE)
            );

            eyes.setConfiguration(config);
            ChromeOptions options = new ChromeOptions().addArguments("--headless=new");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Start Applitools Visual AI Test
            eyes.open(driver,"App360Review", "Selenium Java Basic: Quickstart", new RectangleSize(1200, 600));
            driver.get("https://www.demoblaze.com/");

            // Full Page - Visual AI Assertion
            eyes.check(Target.window().fully().withName("Login page"));

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loginButton = driver.findElement(By.id("login2"));
            loginButton.click();

            WebElement loginUsernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginusername")));
            loginUsernameField.sendKeys("Moeletsi");

            WebElement loginPasswordField = driver.findElement(By.id("loginpassword"));
            loginPasswordField.sendKeys("123456");

            // Click on Log In button
            WebElement logInButton = driver.findElement(By.cssSelector("#logInModal .btn-primary"));
            logInButton.click();

            // Full Page - Visual AI Assertion
            eyes.check(
                    Target.window().fully().withName("After Log in")
                    // Uncomment to apply Layout regions and have test pass
                /* .layout(
                    By.cssSelector(".dashboardOverview_accountBalances__3TUPB"),
                    By.cssSelector(".dashboardTable_dbTable___R5Du")
                ) */
            );

            // End Applitools Visual AI Test
            eyes.closeAsync();
        }
        catch (Exception e) {
            e.printStackTrace();
            if (eyes != null)
                eyes.abortAsync();
        } finally {
            if (driver != null)
                driver.quit();
            if (runner != null) {
                TestResultsSummary allTestResults = runner.getAllTestResults();
                System.out.println(allTestResults);
            }
            System.exit(0);
        }
    }
}
