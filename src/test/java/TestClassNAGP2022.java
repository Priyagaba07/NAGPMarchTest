


import org.testng.annotations.AfterMethod;

import org.testng.annotations.Test;

import java.time.Duration;
import com.coverage.CoverageClass;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestClassNAGP2022 {

	public WebDriver driver;
	CoverageClass  cov = new CoverageClass();

	@BeforeMethod()
	public void setUp() {

		try {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);

			driver.navigate().to("https://www.amazon.in");
		} catch (Exception e) {

		}
	}

	@Test(priority = 0)
	public void checkHomePageIcon() {
		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-label='Amazon']")));
	Boolean status =cov.coverageTest();
			Assert.assertTrue(driver.findElement(By.xpath("//a[@aria-label='Amazon']")).isDisplayed());
			Assert.assertTrue(status);

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	@Test(priority = 1)
	public void checkTitle() {

		Assert.assertTrue(driver.getTitle().toString().contains("Amazon.in"));

	}

	@AfterMethod()
	public void tearDown() {

		driver.quit();

	}

}
