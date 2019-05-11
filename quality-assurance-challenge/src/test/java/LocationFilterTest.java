import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by Adebowale on 11/05/2019.
 */
public class LocationFilterTest extends TestBase {

    @BeforeTest
    public void setUp() {
        initiate();
    }

    @Test
    public void updateButtonTextToLocation_when_locationIsSelected() {
        driver.findElement(By.xpath("//span[text()='Standort']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Ergebnisse anzeigen']")));
        driver.findElement(By.xpath("//input[@placeholder='Suchbegriff']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Suchbegriff']")).sendKeys("Berlin");
        driver.findElement(By.xpath("//div[@class='mat-checkbox-inner-container']")).click();
        driver.findElement(By.xpath("//button[text()='Ergebnisse anzeigen']")).click();

        //Verify current text of location button
        String locationButtonText = driver.findElement(By.xpath("//span[@class='active-link ng-star-inserted']")).getText();
        AssertJUnit.assertTrue(locationButtonText.contains("Berlin"));
    }

    @Test (dependsOnMethods = { "updateButtonTextToLocation_when_locationIsSelected" })
    public void updateButtonTextToNumberOfLocations_when_multipleLocationsAreSelected() {
        driver.findElement(By.xpath("//span[@class='active-link ng-star-inserted']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Ergebnisse anzeigen']")));
        driver.findElement(By.xpath("//input[@placeholder='Suchbegriff']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Suchbegriff']")).sendKeys("Hamburg");
        driver.findElement(By.xpath("//div[@class='mat-checkbox-inner-container']")).click();
        driver.findElement(By.xpath("//button[text()='Ergebnisse anzeigen']")).click();

        //Verify current text of location button
        String locationButtonText = driver.findElement(By.xpath("//span[@class='active-link ng-star-inserted']")).getText();
        Assert.assertEquals(locationButtonText, "2 Standorte");
    }
}
