package Stakeholder;

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

public class AddStakeholderTest1 {

    private static final Logger LOGGER = Logger.getLogger(AddStakeholderTest1.class.getName());
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
    public void testAddStakeholderTest1() throws InterruptedException {
        try {
            Dimension screenSize = new Dimension(1280, 800);
            driver.manage().window().setSize(screenSize);

            driver.findElement(By.xpath("//span[3]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Overview'])[1]/following::span[2]")).click();
            driver.get("http://localhost:4200/review-groups");
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Review groups'])[1]/following::span[3]")).click();
            driver.findElement(By.id("mat-input-5")).click();
            driver.findElement(By.id("mat-input-5")).clear();
            driver.findElement(By.id("mat-input-5")).sendKeys("Stakeholder");
            driver.findElement(By.xpath("//div[@id='cdk-step-content-0-0']/app-add-members/form/div/mat-grid-list/div/mat-grid-tile[2]/div/div/button/span[4]")).click();
            driver.findElement(By.id("mat-input-6")).click();
            driver.findElement(By.id("mat-input-6")).clear();
            driver.findElement(By.id("mat-input-6")).sendKeys("a");
            driver.findElement(By.xpath("//mat-option[@id='mat-option-21']/span/mat-list-item/span/span/div[2]")).click();
            driver.findElement(By.xpath("//mat-dialog-container[@id='mat-mdc-dialog-0']/div/div/app-add-member-dialog/mat-dialog-actions/div/button[2]/span[4]")).click();
            driver.findElement(By.xpath("//div[@id='mat-select-value-11']/span")).click();
            driver.findElement(By.id("mat-option-20")).click();
            driver.findElement(By.xpath("//div[@id='cdk-step-content-0-0']/div/div/button[2]/span[4]")).click();
            driver.findElement(By.xpath("//div[@id='cdk-step-content-0-1']/app-stakeholders-dialog/div/div/div[2]/button/span[4]")).click();
            driver.findElement(By.id("mat-radio-5-input")).click();
            driver.findElement(By.xpath("//mat-dialog-container[@id='mat-mdc-dialog-1']/div/div/app-stakeholders-type-selector/mat-dialog-actions/div/button[2]/span[2]")).click();
            driver.findElement(By.id("mat-input-7")).clear();
            driver.findElement(By.id("mat-input-7")).sendKeys("a");
            driver.findElement(By.xpath("//mat-option[@id='mat-option-25']/span/mat-list-item/span/span/div[2]")).click();
            driver.findElement(By.id("mat-radio-8-input")).click();
            driver.findElement(By.xpath("//div[@id='mat-select-value-13']/span")).click();
            driver.findElement(By.id("mat-option-28")).click();
            driver.findElement(By.xpath("//mat-dialog-container[@id='mat-mdc-dialog-2']/div/div/app-stakeholders-selector/mat-dialog-actions/div/button[2]/span[2]")).click();
            driver.findElement(By.xpath("//div[@id='cdk-step-content-0-1']/div/div/button[2]/span[2]")).click();
            driver.findElement(By.xpath("//div[@id='cdk-step-content-0-2']/div/div/button[2]/span[2]")).click();

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