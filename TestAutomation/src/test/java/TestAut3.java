import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class TestAut3 {

    private static final Logger LOGGER = Logger.getLogger(TestAut2.class.getName());
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\Desktop\\TestAutomation\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testLogin() {
        Dimension screenSize = new Dimension(1280, 800); // Change values as needed
        driver.manage().window().setSize(screenSize);


        driver.get("https://opensource-demo.orangehrmlive.com/");

        // Take screenshot before login
        takeScreenshot(driver, "before_login");

        System.out.println(driver.getTitle());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-input")));
        // Find username field and enter username
        usernameField.sendKeys("Admin");

        // Find password field and enter password
        WebElement passwordField = driver.findElement(By.cssSelector(".oxd-input"));
        passwordField.sendKeys("admin123");

        // Click on login button
        WebElement loginButton = driver.findElement(By.cssSelector("button.orangehrm-login-button"));
        loginButton.click();

        // Take screenshot after login
        takeScreenshot(driver, "after_login");

        System.out.println("Logged in successfully");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        // Wait for 5 seconds before quitting
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Interrupted while waiting to quit WebDriver", e);
            Thread.currentThread().interrupt(); // Reset interrupted status
        }

        // Quit WebDriver
        if (driver != null) {
            driver.quit();
        }
    }

    private static void takeScreenshot(WebDriver driver, String fileName) {
        // Take screenshot and save it to a file
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path destination = Paths.get("screenshots", fileName + ".png");
        try {
            Files.createDirectories(destination.getParent());
            Files.move(screenshotFile.toPath(), destination);
            System.out.println("Screenshot captured: " + destination);
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

}

