import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;

import java.io.File;

/**
 * Created by Adebowale on 11/05/2019.
 */
public class TestBase {
    public static WebDriver driver = null;
    public static WebDriverWait wait;

    public void initiate() {
        if(driver == null) {
            File classpathRoot = new File(System.getProperty("user.dir"));
            File chromeDriver = new File(classpathRoot, "src/main/resources/browserDriver/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, 20);
            driver.manage().window().maximize();
        }
        driver.get("https://www.talentrocket.de/jura-jobs/");
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
