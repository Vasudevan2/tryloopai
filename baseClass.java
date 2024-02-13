package tryLoop.org;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class baseClass {
	private WebDriver driver;

	public void launchBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://app.tryloop.ai/chargebacks/stores/view");
	}

	@SuppressWarnings("deprecation")
	public void login() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[contains(text(),'sign-in with password instead')]")).click();
		driver.findElement(By.id(":r2:")).sendKeys("qa-engineer-assignment@test.com");
		driver.findElement(By.id(":r3:")).sendKeys("QApassword123$");
		driver.findElement(By.xpath("//*[@data-testid='login-button']")).click();
		driver.findElement(By.xpath("//* [contains(text(),\"Skip for now\")]")).click();
	}

	public void Chargebacks() {
		driver.findElement(By.xpath("//*[text()=\"3P Chargebacks\"]")).click();
		driver.findElement(By.xpath("//*[text()=\"History by Store\"]")).click();
	}

	public void validatingtotal() throws InterruptedException {
		Thread.sleep(10000);
		List<WebElement> tablRow = driver.findElements(By.xpath("//table//tbody//tr"));
		int rowCount = tablRow.size();

		List<WebElement> columnCount = driver.findElements(By.xpath("//table//tbody//tr[1]//td[contains(.,'$')]"));
		int size = columnCount.size();
		int j = 1;
		double eachMonthSum = 0;
		double grandTotal = 0;
		while (j < size) {
			for (int i = 1; i <= rowCount; i++) {
				WebElement dataIncolumn = driver
						.findElement(By.xpath("(//table//tbody//tr[" + i + "]//td[contains(.,'$')])[" + j + "]"));
				String dataTxt = dataIncolumn.getText();
				dataTxt = dataTxt.replaceAll("[,$]", "");
				eachMonthSum = Double.sum(eachMonthSum, Double.parseDouble(dataTxt));
				eachMonthSum = Math.round(eachMonthSum * 100.0) / 100.0;

				// System.out.println("*******************month data****************");
				if (i == 10) {
					String grandTitalTxt = driver
							.findElement(By.xpath("(//h6[.='Grand Total'])[2]/following::td[" + j + "]")).getText();
					grandTitalTxt = grandTitalTxt.replaceAll("[,$]", "");
					grandTotal = Double.parseDouble(grandTitalTxt);
					j++;
					System.out.println(eachMonthSum + " : " + grandTotal);
					System.out.println("*******************month data****************");
					eachMonthSum = 0;
				}
			}
		}

	}

	public void quit() {
		driver.quit();
	}

	public void transactions() {
		driver.findElement(By.xpath("//*[text()=\"3P Chargebacks\"]")).click();
		driver.findElement(By.xpath("//*[text()=\"Transactions\"]")).click();
		driver.findElement(By.xpath("//*[text()=\"Locations\"]")).click();
		driver.findElement(By.xpath("//*[contains(@data-testid, 'selectAllCheckbox')]")).click();
		driver.findElement(By.xpath("//*[contains(@aria-label, 'Artisan Alchemy')]")).click();
		driver.findElement(By.xpath("//*[contains(@aria-label, 'Blissful Buffet')]")).click();
		driver.findElement(By.xpath("//*[contains(@data-testid, 'applyBtn')]")).click();
		driver.findElement(By.xpath("//*[text()=\"Marketplaces\"]")).click();
		driver.findElement(By.xpath("//*[text()=\"Unselect All (3)\"]")).click();
		driver.findElement(By.xpath("//*[text()=\"Grubhub\"]")).click();
		driver.findElement(By.xpath("//*[contains(@data-testid, 'applyBtn')]")).click();

	}

	public void extractdataandgeneratecsv() throws InterruptedException {
		WebElement table = driver.findElement(By.xpath("//table"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		// Specify the path for the CSV file
		String filePath = "table_data.csv";

		// Generate CSV file from table data
		generateCSV(rows, filePath);

		// Close the WebDriver
		driver.quit();
	}

	public static void generateCSV(List<WebElement> rows, String filePath) {
		try (FileWriter writer = new FileWriter(filePath)) {
			for (WebElement row : rows) {
				List<WebElement> cells = row.findElements(By.tagName("td"));
				if (!cells.isEmpty()) { // Check if cells list is not empty
					StringBuilder csvLine = new StringBuilder();
					for (WebElement cell : cells) {
						csvLine.append(cell.getText()).append(",");
					}
					// Remove the trailing comma and add a new line character
					csvLine.deleteCharAt(csvLine.length() - 1);
					csvLine.append("\n");
					writer.write(csvLine.toString());
				}
			}
			System.out.println("CSV file generated successfully at: " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}}