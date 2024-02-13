package tryLoop.org;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class tryLoopTest extends baseClass {

	private WebDriver driver;

	@Test(priority = 1)
	private void historyByStore() throws InterruptedException {
		launchBrowser();
		login();
		Chargebacks();
		validatingtotal();
		Thread.sleep(10000);
		quit();
	}

	@Test(priority = 2)
	private void extractdataandcsvgenerating() throws InterruptedException {
		launchBrowser();
		login();
		transactions();
		extractdataandgeneratecsv();
		quit();
	}
}