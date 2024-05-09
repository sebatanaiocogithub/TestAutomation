import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class TestAut2 {
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
        eyes.setBatch(new BatchInfo("NewTest"));
        eyes.setLogHandler(new StdoutLogHandler(true));

        // Open Eyes session and take base image
        eyes.open(driver, "TestApp2", "Login%Signup");
        driver.get("https://www.demoblaze.com/");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        // Wait for 5 seconds before quitting
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Interrupted while waiting to quit WebDriver", e);
            Thread.currentThread().interrupt(); // Reset interrupted status
        }

        // Close Eyes session
        eyes.abortIfNotClosed();

        // Quit WebDriver
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testRegistration() throws InterruptedException {
        try {
            Dimension screenSize = new Dimension(1280, 800);
            driver.manage().window().setSize(screenSize);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement signinButton = driver.findElement(By.id("signin2"));
            signinButton.click();

            WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-username")));
            usernameField.sendKeys("Tshepo23");
            WebElement passwordField = driver.findElement(By.id("sign-password"));
            passwordField.sendKeys("123456");

            WebElement signUpButton = driver.findElement(By.cssSelector("#signInModal .btn-primary"));
            signUpButton.click();

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

    @Test
    public void testLogin() throws InterruptedException {
        try {
            Dimension screenSize = new Dimension(1280, 800);
            driver.manage().window().setSize(screenSize);

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

            // Take base image after logging in
            eyes.checkWindow("After Login");
        } catch (UnhandledAlertException alertException) {
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert text: " + alert.getText());
            alert.accept();
        }
    }
}


