import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.StdoutLogHandler;

public class TestAddStk {

    private static final Logger LOGGER = Logger.getLogger(TestAut2.class.getName());
    private Eyes eyes;
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\Desktop\\TestAutomation\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        eyes = new Eyes();
        eyes.setApiKey("NkIDWkQzbUXpJYHAW6bs1qFRG16YpQrnE92ObmMvQAI110");
        //eyes.setApiKey("NgoUFx7FxKHgSX7cHj5SL0S1IaGbDukGbkG2qopTwO0110");
        eyes.setBatch(new BatchInfo("NewTestAdd1"));
        eyes.setLogHandler(new StdoutLogHandler(true));

        // Open Eyes session and take base image
        eyes.open(driver, "TestAdd1", "CreateReviewGroup");
        driver.get("http://localhost:4200/");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        // Wait for 5 seconds before quitting
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cdk-focused > .mat-mdc-button-touch-target")));

        // Close Eyes session
        eyes.abortIfNotClosed();

        // Quit WebDriver
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAddStakeholder() throws InterruptedException {
        try {
            Dimension screenSize = new Dimension(1280, 800);
            driver.manage().window().setSize(screenSize);

            // Create Actions object
            Actions actions = new Actions(driver);

            // 3. Mouse over
            WebElement element1 = driver.findElement(By.cssSelector(".mdc-icon-button:nth-child(1) > .mat-mdc-button-touch-target"));
            actions.moveToElement(element1).perform();

            // 4. Find element
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cdk-focused > .mat-mdc-button-touch-target")));

            // 5. Mouse over
            WebElement element3 = driver.findElement(By.cssSelector(".mat-mdc-list-item:nth-child(3) .mat-mdc-list-item-unscoped-content"));
            actions.moveToElement(element3).perform();

            // 6. Click
            element3.click();

            // 7. Mouse out
            WebElement element4 = driver.findElement(By.cssSelector(".active .mat-mdc-list-item-unscoped-content"));
            actions.moveToElement(element4).perform();

            // Check for alert
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (alertText.equals("Sign up successful.")) {
                System.out.println("Alert text: " + alertText);
                alert.accept(); // Dismiss the alert
            }

            // Take base image after signing up
            eyes.checkWindow("After Registration");
        } catch (NoAlertPresentException e) {
            // No alert present, continue with the test
        }
    }

}
